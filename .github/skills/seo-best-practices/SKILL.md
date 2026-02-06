---
name: seo-best-practices
description: On-page SEO, technical SEO, keyword research, and content optimization for political transparency platforms
license: Apache-2.0
---

# SEO Best Practices

## Purpose

This skill provides guidance for search engine optimization (SEO) of the CIA platform, ensuring political transparency content ranks well in search results while maintaining accessibility, performance, and GDPR compliance.

## When to Use

### ✅ Use this skill when:
- Optimizing content for search engines
- Improving site structure and navigation
- Implementing schema markup
- Analyzing keyword opportunities
- Improving page load performance
- Creating content strategy
- Building backlink strategies
- Conducting technical SEO audits

### ❌ Don't use this skill for:
- Social media marketing (use social-media-management)
- Content creation (use content-marketing-strategies)
- UI design (use vaadin-component-design)
- Security (use secure-code-review)

## Patterns & Examples

### On-Page SEO Checklist

```html
<!DOCTYPE html>
<html lang="sv">
<head>
  <!-- Title tag (50-60 characters) -->
  <title>Politiker Risk analys - Citizen Intelligence Agency</title>
  
  <!-- Meta description (150-160 characters) -->
  <meta name="description" content="Analysera svenska politikers riskpoäng, röstningsbeteende och finansiella kopplingar. Oberoende politisk transparens och OSINT-analys.">
  
  <!-- Canonical URL -->
  <link rel="canonical" href="https://www.hack23.com/cia/politician/risk-analysis">
  
  <!-- Open Graph for social sharing -->
  <meta property="og:title" content="Politiker Risk analys">
  <meta property="og:description" content="Oberoende analys av svenska politiker">
  <meta property="og:image" content="https://www.hack23.com/cia/images/risk-dashboard.png">
  <meta property="og:url" content="https://www.hack23.com/cia/politician/risk-analysis">
  <meta property="og:type" content="website">
  
  <!-- Schema.org structured data -->
  <script type="application/ld+json">
  {
    "@context": "https://schema.org",
    "@type": "WebApplication",
    "name": "Citizen Intelligence Agency",
    "description": "Political transparency platform for Swedish democracy",
    "url": "https://www.hack23.com/cia/",
    "applicationCategory": "Political Analysis",
    "operatingSystem": "Web",
    "offers": {
      "@type": "Offer",
      "price": "0",
      "priceCurrency": "EUR"
    }
  }
  </script>
</head>
<body>
  <!-- Header with proper hierarchy -->
  <header>
    <h1>Politiker Risk analys</h1>
  </header>
  
  <main>
    <!-- Content with semantic HTML -->
    <article>
      <h2>Vad är riskpoäng?</h2>
      <p>Riskpoäng är en kvantitativ bedömning...</p>
    </article>
  </main>
</body>
</html>
```

### Keyword Research Strategy

```markdown
## Target Keywords for CIA Platform

### Primary Keywords (High volume, high intent):
- "svenska politiker" (Swedish politicians) - 8,100/month
- "riksdagen ledamöter" (parliament members) - 2,400/month
- "politisk transparens" (political transparency) - 880/month
- "politiker röstning" (politician voting) - 720/month

### Long-tail Keywords (Lower volume, high intent):
- "politiker riskbedömning" (politician risk assessment) - 90/month
- "finansiella kopplingar politiker" (financial connections politicians) - 50/month
- "riksdagen omröstningar" (parliament votes) - 320/month
- "politikers bisysslor" (politicians' side jobs) - 140/month

### Informational Keywords:
- "hur fungerar riksdagen" (how parliament works) - 1,600/month
- "vad gör en riksdagsledamot" (what does an MP do) - 880/month
- "svensk politik för dummies" (Swedish politics for dummies) - 260/month

## Content Strategy:
1. Create pillar pages for primary keywords
2. Build topic clusters around each pillar
3. Internal linking between related content
4. Regular updates (freshness signal)
5. Answer boxes optimization (featured snippets)
```

### Technical SEO Requirements

```xml
<!-- sitemap.xml -->
<?xml version="1.0" encoding="UTF-8"?>
<urlset xmlns="http://www.sitemaps.org/schemas/sitemap/0.9">
  <url>
    <loc>https://www.hack23.com/cia/</loc>
    <lastmod>2026-02-06</lastmod>
    <changefreq>daily</changefreq>
    <priority>1.0</priority>
  </url>
  <url>
    <loc>https://www.hack23.com/cia/politician/list</loc>
    <lastmod>2026-02-06</lastmod>
    <changefreq>daily</changefreq>
    <priority>0.9</priority>
  </url>
  <!-- More URLs -->
</urlset>
```

```text
# robots.txt
User-agent: *
Allow: /
Disallow: /admin/
Disallow: /api/
Disallow: /*?*  # No query string URLs

Sitemap: https://www.hack23.com/cia/sitemap.xml
```

### Performance Optimization for SEO

```java
// Vaadin - Code splitting and lazy loading
@Route(value = "politician", layout = MainLayout.class)
@PreserveOnRefresh
public class PoliticianView extends VerticalLayout implements HasUrlParameter<String> {
    
    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
        // Lazy load heavy components
        if (parameter != null) {
            addAttachListener(e -> loadPoliticianDetails(parameter));
        }
    }
    
    private void loadPoliticianDetails(String id) {
        // Load asynchronously to improve initial page load
        UI.getCurrent().access(() -> {
            Politician politician = politicianService.findById(id);
            add(createPoliticianProfile(politician));
        });
    }
}
```

### Core Web Vitals Targets

```markdown
## Performance Metrics (Google PageSpeed Insights)

### Largest Contentful Paint (LCP)
- Target: < 2.5 seconds
- Current: 2.1 seconds ✅
- Optimization: CDN, image optimization, code splitting

### First Input Delay (FID)
- Target: < 100 milliseconds
- Current: 45 milliseconds ✅
- Optimization: Minimize JavaScript execution

### Cumulative Layout Shift (CLS)
- Target: < 0.1
- Current: 0.05 ✅
- Optimization: Size attributes on images, reserve space for dynamic content

### First Contentful Paint (FCP)
- Target: < 1.8 seconds
- Current: 1.2 seconds ✅

### Time to Interactive (TTI)
- Target: < 3.8 seconds
- Current: 3.1 seconds ✅
```

## ISMS Compliance Mapping

### ISO 27001:2022 Annex A Controls

**A.5.37 - Documented operating procedures**
- Document SEO processes and standards
- Maintain content guidelines

### GDPR Considerations

**Article 5 - Principles**
- Data minimization in meta tags
- No personal data in URLs
- Privacy-friendly analytics (no tracking without consent)

**Article 13 - Information to be provided**
- Clear information about data collection in privacy policy
- Cookie consent for analytics

## Hack23 ISMS Policy References

- [Information Security Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Information_Security_Policy.md) - Privacy in SEO practices

## References

### SEO Resources
- [Google Search Central](https://developers.google.com/search)
- [Moz SEO Learning Center](https://moz.com/learn/seo)
- [Ahrefs Blog](https://ahrefs.com/blog/)

### Tools
- Google Search Console
- Google PageSpeed Insights
- Screaming Frog SEO Spider

## Remember

- **Content is king**: Quality content ranks well
- **Mobile-first**: Optimize for mobile devices
- **Page speed matters**: Core Web Vitals are ranking factors
- **Privacy compliance**: GDPR-friendly analytics only
- **Political neutrality**: SEO content maintains platform neutrality
- **Structured data**: Schema.org markup for rich results
