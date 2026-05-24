#!/usr/bin/env node
/*
 * Mermaid Quote Fixer
 *
 * Wraps node labels in ```mermaid blocks with double quotes when they contain
 * characters that are safer or required to be quoted:
 *   - Emoji (Unicode pictographs / symbols)
 *   - `&` (ampersand)
 *   - `(` `)` parentheses
 *   - `:` colon
 *   - `;` semicolon
 *   - `,` comma
 *   - `<` `>` (outside `<br/>` which is allowed in mermaid)
 *   - `fa:fa-…` Font Awesome icon syntax
 *
 * Rules
 * -----
 *  - Only rewrites labels inside flowchart/graph/state/class style brackets:
 *      A[label]    A(label)    A{label}    A((label))
 *      A[[label]]  A[/label/]  A[\label\]  A[/label\]
 *      A([label])  A>label]
 *  - Leaves edge labels (`-->|text|`) alone — mermaid handles those fine and
 *    rewriting them is far more error-prone.
 *  - Leaves already-quoted labels alone.
 *  - Inside a label, any pre-existing `"` is escaped to `#quot;` (mermaid's
 *    documented HTML-entity-style escape).
 *  - Skips lines that are obviously not node definitions (comments, etc.).
 *
 * Usage:
 *   node scripts/fix-mermaid-quotes.mjs [--dry-run] [repoRoot]
 *
 * SPDX-FileCopyrightText: 2008-2026 Hack23 AB
 * SPDX-License-Identifier: Apache-2.0
 */
import { readFile, writeFile, readdir } from 'node:fs/promises';
import path from 'node:path';
import { fileURLToPath } from 'node:url';

const __dirname = path.dirname(fileURLToPath(import.meta.url));
const args = process.argv.slice(2);
const DRY = args.includes('--dry-run');
const REPO = path.resolve(args.find(a => !a.startsWith('--')) || path.join(__dirname, '..'));
const IGNORE = new Set(['node_modules', 'target', 'dist', 'build', '.git', '.mermaid-validate']);

const EMOJI = /[\u{1F300}-\u{1FAFF}\u{2600}-\u{27BF}\u{1F000}-\u{1F0FF}\u{2300}-\u{23FF}\u{2B00}-\u{2BFF}]/u;
const RISKY = /[&(),;]/;
const FA_ICON = /\bfa:fa-[a-zA-Z0-9-]+/;

// Returns true if `inner` needs quoting (and isn't already).
function needsQuoting(inner) {
  if (inner.length === 0) return false;
  if (inner.startsWith('"') && inner.endsWith('"')) return false;
  if (EMOJI.test(inner) || RISKY.test(inner) || FA_ICON.test(inner)) return true;
  return false;
}

// Escape any unescaped `"` in label content so wrapping in `"…"` is safe.
function escapeInner(s) {
  // Mermaid documents `#quot;` as the HTML-entity-style escape for quotes
  // inside quoted labels. Use it consistently.
  return s.replace(/"/g, '#quot;');
}

/*
 * Token-based rewrite of one mermaid diagram body.
 *
 * We process the text bracket-by-bracket, matching balanced bracket pairs in
 * the order they open. Order of bracket-pair preference matters: the longer
 * forms `[[ ]]`, `[/ /]`, `[\ \]`, `[/ \]`, `(( ))`, `([ ])` must be tried
 * before the singletons `[ ]`, `( )`, `{ }`, `>` so we don't split them.
 */
const PAIRS = [
  { open: '(((', close: ')))' },
  { open: '[[', close: ']]' },
  { open: '((', close: '))' },
  { open: '([', close: '])' },
  { open: '[(', close: ')]' },
  { open: '[/', close: '/]' },
  { open: '[\\', close: '\\]' },
  { open: '[/', close: '\\]' },
  { open: '[\\', close: '/]' },
  { open: '{{', close: '}}' },
  { open: '>',  close: ']'  },
  { open: '[',  close: ']'  },
  { open: '(',  close: ')'  },
  { open: '{',  close: '}'  },
];

function findNextOpener(text, from) {
  let best = -1;
  let bestPair = null;
  for (const pair of PAIRS) {
    const i = text.indexOf(pair.open, from);
    if (i < 0) continue;
    if (best < 0 || i < best || (i === best && pair.open.length > bestPair.open.length)) {
      best = i;
      bestPair = pair;
    }
  }
  return best < 0 ? null : { idx: best, pair: bestPair };
}

function isIdentChar(c) {
  return /[A-Za-z0-9_]/.test(c);
}

function rewriteBlock(code) {
  let out = '';
  let i = 0;
  let changes = 0;
  while (i < code.length) {
    const found = findNextOpener(code, i);
    if (!found) { out += code.slice(i); break; }

    // Require the opener to be preceded by an identifier char, otherwise it's
    // not a node-label opening (it's punctuation in normal text — e.g. the
    // `>` in `-->`, or the `(` in a free-text phrase). If not a node open,
    // advance past JUST the opener so we don't consume bracket pairs that
    // legitimately belong to following nodes (that was a real bug).
    const prevChar = found.idx > 0 ? code[found.idx - 1] : '';
    const isNodeOpen = isIdentChar(prevChar);
    if (!isNodeOpen) {
      out += code.slice(i, found.idx + 1); // include first char of opener
      i = found.idx + 1;
      continue;
    }

    out += code.slice(i, found.idx);
    const { pair } = found;
    const closeIdx = code.indexOf(pair.close, found.idx + pair.open.length);
    if (closeIdx < 0) {
      // Unbalanced — leave as is and move on past the opener only
      out += code.slice(found.idx, found.idx + pair.open.length);
      i = found.idx + pair.open.length;
      continue;
    }
    const inner = code.slice(found.idx + pair.open.length, closeIdx);

    // Don't touch labels that contain a newline (multi-line labels are
    // exotic). Don't touch labels containing other brackets — too risky to
    // re-quote. `<br/>` is mermaid-blessed and stripped before this check.
    const hasNewline = inner.includes('\n');
    const hasNestedBracket =
      /[\[\](){}]/.test(inner.replace(/<br\s*\/?>/g, ''));

    if (!hasNewline && !hasNestedBracket && needsQuoting(inner)) {
      out += pair.open + '"' + escapeInner(inner) + '"' + pair.close;
      changes++;
    } else {
      out += pair.open + inner + pair.close;
    }
    i = closeIdx + pair.close.length;
  }
  return { code: out, changes };
}

async function* walk(dir) {
  let ents;
  try { ents = await readdir(dir, { withFileTypes: true }); } catch { return; }
  for (const ent of ents) {
    if (IGNORE.has(ent.name)) continue;
    const p = path.join(dir, ent.name);
    if (ent.isDirectory()) yield* walk(p);
    else if (ent.isFile() && ent.name.endsWith('.md')) yield p;
  }
}

const fence = /(```mermaid\s*\n)([\s\S]*?)(```)/g;

async function main() {
  const files = [];
  for await (const f of walk(REPO)) files.push(f);
  files.sort();

  let totalChanges = 0;
  let filesChanged = 0;

  for (const file of files) {
    const src = await readFile(file, 'utf8');
    let fileChanges = 0;
    const out = src.replace(fence, (_full, open, body, close) => {
      const { code, changes } = rewriteBlock(body);
      fileChanges += changes;
      return open + code + close;
    });
    if (fileChanges > 0) {
      totalChanges += fileChanges;
      filesChanged++;
      const rel = path.relative(REPO, file);
      console.log(`${DRY ? '[dry] ' : ''}${rel}  (+${fileChanges} label${fileChanges === 1 ? '' : 's'} quoted)`);
      if (!DRY) await writeFile(file, out);
    }
  }
  console.log(`\nTotal labels quoted : ${totalChanges}`);
  console.log(`Files modified      : ${filesChanged}`);
  if (DRY) console.log(`(dry run — no files written)`);
}

main().catch(err => {
  console.error('Fixer crashed:', err);
  process.exit(2);
});
