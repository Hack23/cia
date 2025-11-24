# 📊 JSON Export System - Quick Start Guide

## Overview

The CIA JSON Export System provides **comprehensive political intelligence data** in structured JSON format, optimized for static CDN hosting and JavaScript consumption.

## 🎯 What You Get

### Data Coverage
- **349 Politicians** with complete profiles, voting records, and risk assessments
- **8 Parliamentary Parties** with electoral data, coalition dynamics, and predictions
- **11 Government Ministries** with budget, performance, and policy tracking
- **15 Riksdag Committees** with productivity metrics and decision analysis
- **Intelligence Products**: Risk assessments, trend analysis, coalition stability, voting patterns, predictive analytics

### Update Frequency
- **Daily updates** at 2 AM UTC
- **Version controlled** with semantic versioning
- **CDN cached** for 24 hours with instant cache invalidation

---

## 🚀 Quick Access (After Implementation)

### Base URL
```
https://cdn.cia.se/v1.0.0/
```

### Common Endpoints
```javascript
// All active politicians
GET /v1.0.0/politicians/index.json

// Individual politician profile
GET /v1.0.0/politicians/profiles/{personId}.json

// Politicians by party
GET /v1.0.0/politicians/by-party/s.json

// All parliamentary parties
GET /v1.0.0/parties/parliamentary.json

// Party analytics
GET /v1.0.0/parties/analytics/voting-cohesion.json

// Risk assessments
GET /v1.0.0/intelligence/risk-assessments.json

// Coalition stability
GET /v1.0.0/intelligence/coalition-stability.json
```

---

## 💻 Usage Examples

### Vanilla JavaScript
```javascript
// Fetch all politicians
fetch('https://cdn.cia.se/v1.0.0/politicians/index.json')
  .then(res => res.json())
  .then(data => {
    console.log(`Loaded ${data.metadata.recordCount} politicians`);
    data.data.forEach(pol => {
      console.log(`${pol.attributes.fullName} (${pol.attributes.party})`);
    });
  });
```

### React Hook
```jsx
import { useState, useEffect } from 'react';

function usePoliticians() {
  const [politicians, setPoliticians] = useState([]);
  const [loading, setLoading] = useState(true);
  
  useEffect(() => {
    fetch('https://cdn.cia.se/v1.0.0/politicians/active.json')
      .then(res => res.json())
      .then(data => {
        setPoliticians(data.data);
        setLoading(false);
      });
  }, []);
  
  return { politicians, loading };
}
```

### Vue.js Composition API
```vue
<script setup>
import { ref, onMounted } from 'vue';

const parties = ref([]);

onMounted(async () => {
  const response = await fetch('https://cdn.cia.se/v1.0.0/parties/parliamentary.json');
  const data = await response.json();
  parties.value = data.data;
});
</script>
```

---

## 📁 File Structure

```
v1.0.0/
├── metadata.json                    # Export metadata
├── politicians/
│   ├── index.json                  # All politicians (2.5 MB)
│   ├── active.json                 # Active only (2.2 MB)
│   ├── by-party/
│   │   ├── s.json                  # Social Democrats
│   │   ├── m.json                  # Moderates
│   │   └── ...
│   └── profiles/
│       └── {personId}.json         # Individual profiles
├── parties/
│   ├── index.json                  # All parties (500 KB)
│   ├── parliamentary.json          # Parliamentary only
│   └── analytics/
│       ├── voting-cohesion.json
│       └── electoral-trends.json
├── ministries/
│   ├── index.json
│   └── profiles/
│       └── {ministryId}.json
├── committees/
│   ├── index.json
│   └── profiles/
│       └── {committeeId}.json
└── intelligence/
    ├── risk-assessments.json       # Daily risk updates
    ├── trend-analysis.json         # Trend detection
    ├── coalition-stability.json    # Government stability
    └── predictive-analytics.json   # Election forecasts
```

---

## 🔧 Implementation Status

### ✅ Completed
- [x] Complete JSON schema specifications
- [x] Documentation with Mermaid diagrams
- [x] CDN deployment guide
- [x] JavaScript usage examples
- [x] Deployment automation script

### 🚧 In Progress
- [ ] Java export service implementation
- [ ] Database view integration
- [ ] Automated daily updates
- [ ] CDN deployment

### ⏳ Planned
- [ ] GraphQL API layer
- [ ] Real-time updates via WebSocket
- [ ] Historical data archives
- [ ] Advanced filtering and search

---

## 📚 Documentation

| Document | Description | Link |
|----------|-------------|------|
| **README** | Main overview and architecture | [README.md](./README.md) |
| **Implementation Guide** | Step-by-step implementation | [IMPLEMENTATION_GUIDE.md](./IMPLEMENTATION_GUIDE.md) |
| **Politician Schema** | Detailed politician JSON format | [schemas/politician-schema.md](./schemas/politician-schema.md) |
| **Party Schema** | Party profile format | [schemas/party-schema.md](./schemas/party-schema.md) |
| **Ministry Schema** | Ministry data format | [schemas/ministry-schema.md](./schemas/ministry-schema.md) |
| **Committee Schema** | Committee information format | [schemas/committee-schema.md](./schemas/committee-schema.md) |
| **Intelligence Schema** | Intelligence products format | [schemas/intelligence-schema.md](./schemas/intelligence-schema.md) |

---

## 🎨 Schema Highlights

### Multi-Level Descriptions
Every entity includes three description levels optimized for different UI contexts:

```json
{
  "descriptions": {
    "short": "Tweet-length summary (140 chars)",
    "long": "Paragraph description (500 chars)",
    "detailed": "Comprehensive overview (2000 chars)"
  }
}
```

### Intelligence Tags
Analytical classifications for filtering and categorization:

```json
{
  "intelligenceTags": [
    "coalition-broker",
    "policy-expert-economics",
    "high-media-presence",
    "committee-leader",
    "rising-influence"
  ]
}
```

### Rich Metadata
Comprehensive metadata for validation and caching:

```json
{
  "metadata": {
    "version": "1.0.0",
    "generated": "2024-11-24T02:23:58Z",
    "schema": "politician-profile",
    "recordCount": 349,
    "dataDate": "2024-11-23"
  }
}
```

---

## 💰 Cost Estimate

### AWS S3 + CloudFront
- **Storage**: ~10 GB → $0.25/month
- **Bandwidth**: 1 TB/month → $15/month
- **Requests**: 1M GET → $0.40/month
- **Total**: ~$16/month

### Cloudflare Pages (Recommended)
- **Storage**: Unlimited
- **Bandwidth**: Unlimited
- **Cost**: **FREE**

---

## 🔐 Security & Privacy

- ✅ Only public data included
- ✅ No personal contact information
- ✅ GDPR compliant
- ✅ HTTPS-only access
- ✅ Rate limiting at CDN level
- ✅ No tracking or analytics

---

## 📞 Support

For questions or issues:
1. Review documentation in `/json-export-specs/`
2. Check implementation guide
3. See [CONTRIBUTING.md](../CONTRIBUTING.md)

---

## 📝 License

Apache License 2.0 - Same as CIA platform

---

**Version**: 1.0.0  
**Status**: Specifications Complete, Implementation Pending  
**Last Updated**: 2024-11-24
