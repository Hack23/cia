#!/usr/bin/env node
/*
 * Mermaid Deep-Check Inventory
 *
 * Surveys every ```mermaid block in every *.md file in the repository and
 * reports:
 *   - Diagram type distribution
 *   - Theme/init usage
 *   - Color palette usage (fill/stroke/color in style/classDef)
 *   - Unquoted node labels that contain emoji, fa: icons, `&`, `(` … `)`
 *     or other risky characters
 *
 * Intended to be run alongside scripts/validate-mermaid.mjs to catch issues
 * the renderer accepts but that are still inconsistent or fragile.
 *
 * Usage:
 *   node scripts/deep-check-mermaid.mjs [repoRoot]
 *
 * SPDX-FileCopyrightText: 2008-2026 Hack23 AB
 * SPDX-License-Identifier: Apache-2.0
 */
import { readFile, readdir, writeFile, mkdir } from 'node:fs/promises';
import path from 'node:path';
import { fileURLToPath } from 'node:url';

const __dirname = path.dirname(fileURLToPath(import.meta.url));
const REPO = path.resolve(process.argv[2] || path.join(__dirname, '..'));
const OUT = path.join(REPO, '.mermaid-validate');
const IGNORE = new Set(['node_modules', 'target', 'dist', 'build', '.git', '.mermaid-validate']);

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

const fence = /```mermaid\s*\n([\s\S]*?)```/g;
// Unicode emoji ranges (broad)
const EMOJI = /[\u{1F300}-\u{1FAFF}\u{2600}-\u{27BF}\u{1F000}-\u{1F0FF}\u{2300}-\u{23FF}\u{2B00}-\u{2BFF}]/u;

async function main() {
  await mkdir(OUT, { recursive: true });
  const files = [];
  for await (const f of walk(REPO)) files.push(f);
  files.sort();

  const issues = {
    unquotedFaIcons: [],
    unquotedEmoji: [],
    parenInLabel: [],
    ampersandInLabel: [],
  };
  const colorUsage = new Map();
  const themeUsage = new Map();
  const diagramTypes = new Map();
  let total = 0;

  for (const file of files) {
    const src = await readFile(file, 'utf8');
    let m;
    fence.lastIndex = 0;
    while ((m = fence.exec(src)) !== null) {
      total++;
      const code = m[1];
      const startLine = src.slice(0, m.index).split('\n').length;
      const rel = path.relative(REPO, file);

      const firstNonEmpty =
        code.split('\n').find(l => l.trim() && !l.trim().startsWith('%%')) || '';
      const type = firstNonEmpty.trim().split(/\s+/)[0];
      diagramTypes.set(type, (diagramTypes.get(type) || 0) + 1);

      const themeM = code.match(
        /%%\{init:\s*\{[^}]*['"]theme['"]\s*:\s*['"]([^'"]+)['"]/,
      );
      const themeKey = themeM ? themeM[1] : '(none)';
      themeUsage.set(themeKey, (themeUsage.get(themeKey) || 0) + 1);

      const colorRe =
        /(?:fill|stroke|color)\s*:\s*(#[0-9A-Fa-f]{3,8}|[a-zA-Z]+)/g;
      let cm;
      while ((cm = colorRe.exec(code)) !== null) {
        const c = cm[1].toLowerCase();
        colorUsage.set(c, (colorUsage.get(c) || 0) + 1);
      }

      const lines = code.split('\n');
      for (let i = 0; i < lines.length; i++) {
        const line = lines[i];
        const lineNo = startLine + i;

        const faRe =
          /[\[(](?!")([^\[\]()]*?fa:fa-[a-zA-Z0-9-]+[^\[\]()]*?)[\])]/g;
        let lm;
        while ((lm = faRe.exec(line)) !== null) {
          issues.unquotedFaIcons.push({ file: rel, line: lineNo, snippet: line.trim() });
        }

        const nodeLabelRe =
          /[A-Za-z_][A-Za-z0-9_]*\s*[\[(]([^"\[\]()][^\[\]()]*?)[\])]/g;
        while ((lm = nodeLabelRe.exec(line)) !== null) {
          if (EMOJI.test(lm[1])) {
            issues.unquotedEmoji.push({ file: rel, line: lineNo, snippet: line.trim() });
          }
        }

        const parenLabelRe = /\[([^"\]][^\]]*\([^\]]*\)[^\]]*)\]/g;
        while ((lm = parenLabelRe.exec(line)) !== null) {
          if (!lm[1].startsWith('"')) {
            issues.parenInLabel.push({ file: rel, line: lineNo, snippet: line.trim() });
          }
        }

        const ampRe = /[\[(]([^"\[\]()][^\[\]()]*&[^\[\]()]*?)[\])]/g;
        while ((lm = ampRe.exec(line)) !== null) {
          if (!lm[1].startsWith('"') && !lm[1].includes('&amp;')) {
            issues.ampersandInLabel.push({ file: rel, line: lineNo, snippet: line.trim() });
          }
        }
      }
    }
  }

  console.log(`\n=== Mermaid Deep Check ===`);
  console.log(`Total diagrams: ${total}\n`);

  console.log(`Diagram types:`);
  for (const [k, v] of [...diagramTypes.entries()].sort((a, b) => b[1] - a[1])) {
    console.log(`  ${k.padEnd(20)} ${v}`);
  }

  console.log(`\nTheme usage:`);
  for (const [k, v] of [...themeUsage.entries()].sort((a, b) => b[1] - a[1])) {
    console.log(`  ${k.padEnd(20)} ${v}`);
  }

  console.log(`\nUnique colors: ${colorUsage.size} (top 20)`);
  for (const [k, v] of [...colorUsage.entries()].sort((a, b) => b[1] - a[1]).slice(0, 20)) {
    console.log(`  ${k.padEnd(20)} ${v}`);
  }

  console.log(`\nIssues:`);
  for (const k of Object.keys(issues)) {
    console.log(`  ${k.padEnd(20)} ${issues[k].length}`);
  }

  await writeFile(
    path.join(OUT, 'deep-issues.json'),
    JSON.stringify(
      {
        issues,
        colorUsage: [...colorUsage],
        themeUsage: [...themeUsage],
        diagramTypes: [...diagramTypes],
      },
      null,
      2,
    ),
  );
  console.log(`\nDetail: ${path.relative(REPO, path.join(OUT, 'deep-issues.json'))}`);
}

main().catch(err => {
  console.error('Deep-check crashed:', err);
  process.exit(2);
});
