#!/usr/bin/env node
/*
 * Mermaid Diagram Validator
 *
 * Walks every Markdown file in the repository, extracts every ```mermaid block,
 * and attempts to render it with @mermaid-js/mermaid-cli. Reports any broken
 * diagrams with file path, line number, and the renderer error message.
 *
 * Usage:
 *   npm install --no-save @mermaid-js/mermaid-cli
 *   node scripts/validate-mermaid.mjs [repoRoot]
 *
 * Exit codes:
 *   0  All diagrams render successfully
 *   1  One or more diagrams failed to render
 *   2  Tooling / environment error (mmdc not found, etc.)
 *
 * SPDX-FileCopyrightText: 2008-2026 Hack23 AB
 * SPDX-License-Identifier: Apache-2.0
 */
import { execFile } from 'node:child_process';
import { promisify } from 'node:util';
import { readFile, writeFile, mkdir, rm, readdir } from 'node:fs/promises';
import { existsSync } from 'node:fs';
import path from 'node:path';
import { fileURLToPath } from 'node:url';

const execFileP = promisify(execFile);
const __dirname = path.dirname(fileURLToPath(import.meta.url));

const REPO = path.resolve(process.argv[2] || path.join(__dirname, '..'));
const WORK = process.env.MERMAID_WORK || path.join(REPO, '.mermaid-validate');
const IGNORE = new Set(['node_modules', 'target', 'dist', 'build', '.git', '.mermaid-validate']);

// Resolve mmdc binary - prefer local node_modules, then ancestor node_modules, then PATH
function resolveMmdc() {
  const candidates = [
    path.join(REPO, 'node_modules', '.bin', 'mmdc'),
    path.join(__dirname, '..', 'node_modules', '.bin', 'mmdc'),
    '/tmp/mmd-test/node_modules/.bin/mmdc',
  ];
  for (const c of candidates) if (existsSync(c)) return c;
  return 'mmdc';
}
const MMDC = process.env.MMDC || resolveMmdc();

// Puppeteer needs --no-sandbox in CI / container environments
const PUPPETEER_CFG = path.join(WORK, 'puppeteer-config.json');

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

async function main() {
  await rm(WORK, { recursive: true, force: true });
  await mkdir(WORK, { recursive: true });
  await writeFile(PUPPETEER_CFG, JSON.stringify({
    args: ['--no-sandbox', '--disable-setuid-sandbox', '--disable-dev-shm-usage'],
  }));

  const files = [];
  for await (const f of walk(REPO)) files.push(f);
  files.sort();

  const results = [];
  let total = 0;
  const t0 = Date.now();

  for (const file of files) {
    const src = await readFile(file, 'utf8');
    let m;
    let idx = 0;
    fence.lastIndex = 0;
    while ((m = fence.exec(src)) !== null) {
      idx++;
      total++;
      const code = m[1];
      const line = src.slice(0, m.index).split('\n').length;
      const tmp = path.join(WORK, `d_${total}.mmd`);
      const out = path.join(WORK, `d_${total}.svg`);
      await writeFile(tmp, code);
      try {
        await execFileP(MMDC, ['-i', tmp, '-o', out, '-p', PUPPETEER_CFG, '-q'], {
          timeout: 90_000,
          maxBuffer: 8 * 1024 * 1024,
        });
        results.push({ file: path.relative(REPO, file), idx, line, ok: true });
      } catch (e) {
        const errMsg = (e.stderr || e.stdout || e.message || '').toString();
        results.push({
          file: path.relative(REPO, file),
          idx,
          line,
          ok: false,
          err: errMsg.slice(0, 4000),
          code,
        });
      }
    }
  }

  const broken = results.filter(r => !r.ok);
  const elapsed = ((Date.now() - t0) / 1000).toFixed(1);
  console.log(`\n=== Mermaid Validation Report ===`);
  console.log(`Diagrams tested : ${total}`);
  console.log(`Passed          : ${total - broken.length}`);
  console.log(`Failed          : ${broken.length}`);
  console.log(`Elapsed         : ${elapsed}s`);
  for (const b of broken) {
    console.log(`\n[FAIL] ${b.file}:${b.line} (diagram #${b.idx})`);
    const errLines = b.err.split('\n').filter(l => l.trim()).slice(0, 8);
    for (const l of errLines) console.log(`       ${l}`);
  }
  await writeFile(path.join(WORK, 'report.json'), JSON.stringify(results, null, 2));
  console.log(`\nDetails: ${path.relative(REPO, path.join(WORK, 'report.json'))}`);
  process.exit(broken.length === 0 ? 0 : 1);
}

main().catch(err => {
  console.error('Validator crashed:', err);
  process.exit(2);
});
