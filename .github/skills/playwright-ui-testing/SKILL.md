---
name: playwright-ui-testing
description: Playwright browser automation, visual regression testing, accessibility testing, and E2E workflow validation for CIA platform
license: Apache-2.0
---

# Playwright UI Testing

## Purpose

This skill provides guidance for implementing comprehensive UI testing using Playwright, including browser automation, visual regression testing, accessibility validation, and end-to-end workflow testing for the CIA platform.

## When to Use

### ✅ Use this skill when:
- Writing end-to-end UI tests
- Automating browser interactions
- Performing visual regression testing
- Validating accessibility (WCAG)
- Testing across multiple browsers
- Capturing screenshots for documentation
- Testing responsive layouts
- Validating user workflows

### ❌ Don't use this skill for:
- Unit testing (use unit-testing-patterns)
- API testing (use integration-testing)
- Performance testing (use code-quality-checks)
- Security testing (use secure-code-review)

## Patterns & Examples

### Basic Playwright Test Structure

```javascript
// tests/politician-search.spec.js
const { test, expect } = require('@playwright/test');

test.describe('Politician Search', () => {
  test.beforeEach(async ({ page }) => {
    // Navigate to search page
    await page.goto('https://localhost:28443/cia/');
    await page.waitForLoadState('networkidle');
  });

  test('should search politicians by name', async ({ page }) => {
    // Enter search term
    await page.fill('[aria-label="Search politicians"]', 'Andersson');
    await page.click('button:has-text("Search")');
    
    // Wait for results
    await page.waitForSelector('.politician-card');
    
    // Verify results contain search term
    const results = await page.locator('.politician-card').all();
    expect(results.length).toBeGreaterThan(0);
    
    for (const result of results) {
      const name = await result.locator('.politician-name').textContent();
      expect(name.toLowerCase()).toContain('andersson');
    }
  });

  test('should filter by political party', async ({ page }) => {
    // Select party filter
    await page.selectOption('[aria-label="Filter by party"]', 'S');
    
    // Wait for filtered results
    await page.waitForSelector('.politician-card');
    
    // Verify all results are from selected party
    const partyBadges = await page.locator('.party-badge').allTextContents();
    partyBadges.forEach(badge => {
      expect(badge).toBe('S');
    });
  });

  test('should display politician details', async ({ page }) => {
    // Click on first politician
    await page.click('.politician-card:first-child');
    
    // Verify detail view loaded
    await expect(page.locator('h1.politician-name')).toBeVisible();
    await expect(page.locator('.risk-score')).toBeVisible();
    await expect(page.locator('.voting-history')).toBeVisible();
    
    // Verify risk score is numeric
    const riskScore = await page.locator('.risk-score').textContent();
    expect(parseFloat(riskScore)).toBeGreaterThanOrEqual(0);
    expect(parseFloat(riskScore)).toBeLessThanOrEqual(100);
  });
});
```

### Accessibility Testing with Playwright

```javascript
// tests/accessibility.spec.js
const { test, expect } = require('@playwright/test');
const { injectAxe, checkA11y, getViolations } = require('axe-playwright');

test.describe('Accessibility Compliance', () => {
  test('homepage should have no accessibility violations', async ({ page }) => {
    await page.goto('https://localhost:28443/cia/');
    await injectAxe(page);
    
    // Check for WCAG 2.1 Level AA violations
    await checkA11y(page, null, {
      detailedReport: true,
      detailedReportOptions: {
        html: true
      },
      axeOptions: {
        runOnly: {
          type: 'tag',
          values: ['wcag2a', 'wcag2aa', 'wcag21a', 'wcag21aa']
        }
      }
    });
  });

  test('politician detail page keyboard navigation', async ({ page }) => {
    await page.goto('https://localhost:28443/cia/politician/123');
    
    // Tab through interactive elements
    await page.keyboard.press('Tab');
    let focused = await page.evaluate(() => document.activeElement.tagName);
    expect(['A', 'BUTTON', 'INPUT']).toContain(focused);
    
    // Verify skip link
    await page.keyboard.press('Tab');
    const skipLink = await page.locator('a.skip-link');
    await expect(skipLink).toBeFocused();
    
    // Test escape key closes modals
    await page.click('[aria-label="Show risk details"]');
    await expect(page.locator('[role="dialog"]')).toBeVisible();
    await page.keyboard.press('Escape');
    await expect(page.locator('[role="dialog"]')).not.toBeVisible();
  });

  test('screen reader landmarks', async ({ page }) => {
    await page.goto('https://localhost:28443/cia/');
    
    // Verify ARIA landmarks
    await expect(page.locator('[role="banner"]')).toBeVisible();
    await expect(page.locator('[role="navigation"]')).toBeVisible();
    await expect(page.locator('[role="main"]')).toBeVisible();
    await expect(page.locator('[role="contentinfo"]')).toBeVisible();
  });
});
```

### Visual Regression Testing

```javascript
// tests/visual-regression.spec.js
const { test, expect } = require('@playwright/test');

test.describe('Visual Regression', () => {
  test('homepage matches baseline', async ({ page }) => {
    await page.goto('https://localhost:28443/cia/');
    await page.waitForLoadState('networkidle');
    
    // Take screenshot and compare to baseline
    await expect(page).toHaveScreenshot('homepage.png', {
      fullPage: true,
      maxDiffPixels: 100
    });
  });

  test('risk dashboard layout', async ({ page }) => {
    await page.goto('https://localhost:28443/cia/dashboard/risk');
    
    // Wait for charts to render
    await page.waitForSelector('canvas.chart-canvas');
    await page.waitForTimeout(1000); // Allow chart animations
    
    await expect(page).toHaveScreenshot('risk-dashboard.png', {
      maxDiffPixelRatio: 0.05
    });
  });

  test('responsive design - mobile', async ({ page }) => {
    // Set mobile viewport
    await page.setViewportSize({ width: 375, height: 667 });
    await page.goto('https://localhost:28443/cia/politician/list');
    
    await expect(page).toHaveScreenshot('politician-list-mobile.png');
  });

  test('responsive design - tablet', async ({ page }) => {
    await page.setViewportSize({ width: 768, height: 1024 });
    await page.goto('https://localhost:28443/cia/politician/list');
    
    await expect(page).toHaveScreenshot('politician-list-tablet.png');
  });
});
```

### Cross-Browser Testing Configuration

```javascript
// playwright.config.js
const { defineConfig, devices } = require('@playwright/test');

module.exports = defineConfig({
  testDir: './tests',
  fullyParallel: true,
  forbidOnly: !!process.env.CI,
  retries: process.env.CI ? 2 : 0,
  workers: process.env.CI ? 1 : undefined,
  reporter: [
    ['html'],
    ['junit', { outputFile: 'test-results/junit.xml' }]
  ],
  use: {
    baseURL: 'https://localhost:28443',
    trace: 'on-first-retry',
    screenshot: 'only-on-failure',
    video: 'retain-on-failure',
    ignoreHTTPSErrors: true // For self-signed certs in dev
  },

  projects: [
    {
      name: 'chromium',
      use: { ...devices['Desktop Chrome'] }
    },
    {
      name: 'firefox',
      use: { ...devices['Desktop Firefox'] }
    },
    {
      name: 'webkit',
      use: { ...devices['Desktop Safari'] }
    },
    {
      name: 'Mobile Chrome',
      use: { ...devices['Pixel 5'] }
    },
    {
      name: 'Mobile Safari',
      use: { ...devices['iPhone 12'] }
    }
  ],

  webServer: {
    command: 'cd citizen-intelligence-agency && ant start',
    port: 28443,
    timeout: 120 * 1000,
    reuseExistingServer: !process.env.CI
  }
});
```

### GitHub Actions Integration

```yaml
# .github/workflows/playwright-tests.yml
name: Playwright Tests
on: [push, pull_request]

jobs:
  test:
    timeout-minutes: 60
    runs-on: ubuntu-latest
    steps:
    - uses: actions/checkout@v4
    - uses: actions/setup-node@v4
      with:
        node-version: '24'
    - name: Install dependencies
      run: npm ci
    - name: Install Playwright Browsers
      run: npx playwright install --with-deps
    - name: Run Playwright tests
      run: npx playwright test
    - uses: actions/upload-artifact@v4
      if: always()
      with:
        name: playwright-report
        path: playwright-report/
        retention-days: 30
```

## ISMS Compliance Mapping

### ISO 27001:2022 Annex A Controls

**A.8.29 - Testing in development and acceptance**
- Automated UI testing validates functionality
- Security testing integrated in test suite
- Test results documented and reviewed

**A.8.31 - Separation of development, test and production**
- Tests run against test environment
- Production credentials never used in tests

### CIS Controls v8

**Control 16: Application Software Security**
- 16.10: Apply automated testing tools
- 16.11: Use standard security configurations

## Hack23 ISMS Policy References

- [Secure Development Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Secure_Development_Policy.md) - Testing requirements

## References

### Playwright Documentation
- [Playwright Test](https://playwright.dev/docs/intro)
- [Accessibility Testing](https://playwright.dev/docs/accessibility-testing)
- [Visual Comparisons](https://playwright.dev/docs/test-snapshots)

### CIA Documentation
- [.github/workflows/copilot-setup-steps.yml](/.github/workflows/copilot-setup-steps.yml) - Playwright setup
- [e2e-testing skill](/.github/skills/e2e-testing/) - General E2E patterns

## Remember

- **Test real user workflows**: Not just individual features
- **Cross-browser testing**: Test on Chrome, Firefox, Safari
- **Accessibility is mandatory**: Integrate axe-core checks
- **Visual regression**: Catch unintended UI changes
- **Parallel execution**: Speed up test runs
- **CI/CD integration**: Run tests on every commit
- **Test data cleanup**: Maintain test isolation
