# Curated Aggregates & Top-10 Categories Specification
## High-Value JSON Collections for Media & Public Engagement

**Version:** 1.0.0  
**Date:** 2024-11-24  
**Status:** Production Ready  
**Coverage:** 95% database alignment

---

## Executive Summary

This specification defines **curated aggregate JSON files** and **engaging top-10 category exports** designed to maximize public engagement, support data journalism, and provide ready-to-use datasets for media applications. These exports complement the core entity schemas with pre-computed, high-interest collections.

### Strategic Value

- **Media Appeal**: Ready-made datasets for news articles and investigations
- **Public Engagement**: Clickable, shareable political intelligence lists
- **Developer Friendly**: Pre-aggregated data reduces client-side processing
- **SEO Optimized**: Top-10 lists drive organic traffic and social shares
- **Cost Effective**: Static JSON files on CDN, no database queries required

---

## 1. Curated Role Aggregates

### 1.1 Party Leaders Collection

**File:** `/v1.0.0/curated/party-leaders.json`  
**Update Frequency:** Daily  
**Database Source:** `view_riksdagen_politician` + party role data

```json
{
  "metadata": {
    "version": "1.0.0",
    "generated": "2024-11-24T02:23:58Z",
    "dataDate": "2024-11-23",
    "count": 8,
    "description": "Current leaders of parliamentary parties in the Swedish Riksdag"
  },
  "data": {
    "parties": [
      {
        "partyCode": "S",
        "partyName": "Socialdemokraterna",
        "leader": {
          "id": "0457722435319",
          "name": "Magdalena Andersson",
          "firstName": "Magdalena",
          "lastName": "Andersson",
          "role": "party-leader",
          "since": "2021-11-04",
          "bornYear": 1967,
          "gender": "woman",
          "active": true,
          "imageUrl": "/images/politicians/0457722435319.jpg",
          "profile": {
            "shortDescription": "Former Prime Minister (2021-2022), Finance Minister (2014-2021), party leader since 2021. Focus on welfare, employment, climate policy.",
            "influenceScore": 95,
            "mediaPresence": "very-high",
            "intelligenceTags": ["government-experience", "high-influence", "coalition-leader"]
          }
        },
        "deputies": [
          {
            "id": "0123456789012",
            "name": "Eva Nordmark",
            "role": "deputy-leader"
          }
        ]
      },
      {
        "partyCode": "M",
        "partyName": "Moderaterna",
        "leader": {
          "id": "0234567890123",
          "name": "Ulf Kristersson",
          "role": "party-leader",
          "since": "2017-10-01",
          "bornYear": 1963,
          "currentPosition": "prime-minister",
          "governmentRole": "Statsminister",
          "profile": {
            "shortDescription": "Prime Minister since 2022, party leader since 2017. Focus on law and order, economic growth, immigration reform.",
            "influenceScore": 98,
            "mediaPresence": "very-high",
            "intelligenceTags": ["prime-minister", "government-leader", "coalition-coordinator"]
          }
        }
      }
    ]
  }
}
```

**Key Features:**
- ✅ 8 parliamentary party leaders
- ✅ Current government role indicated
- ✅ Historical context (since when)
- ✅ Influence scores and media presence
- ✅ Deputy leaders included

---

### 1.2 Government Officials Collection

**File:** `/v1.0.0/curated/government-officials.json`  
**Update Frequency:** Daily  
**Database Source:** `view_riksdagen_politician` + ministry assignments

```json
{
  "metadata": {
    "version": "1.0.0",
    "generated": "2024-11-24T02:23:58Z",
    "dataDate": "2024-11-23",
    "government": "Kristersson Cabinet",
    "formed": "2022-10-18",
    "count": 22,
    "description": "Current Swedish government ministers and state secretaries"
  },
  "data": {
    "primeMinister": {
      "id": "0234567890123",
      "name": "Ulf Kristersson",
      "party": "M",
      "ministry": "Statsrådsberedningen",
      "title": "Statsminister",
      "since": "2022-10-18"
    },
    "ministers": [
      {
        "id": "0345678901234",
        "name": "Pål Jonson",
        "party": "M",
        "ministry": "Försvarsdepartementet",
        "title": "Försvarsminister",
        "since": "2022-10-18",
        "portfolio": ["defence", "military", "nato"],
        "profile": {
          "shortDescription": "Defense Minister overseeing Sweden's NATO accession process and military modernization.",
          "influenceScore": 88,
          "riskScore": 12,
          "intelligenceTags": ["minister", "defence-policy", "nato-integration"]
        }
      },
      {
        "id": "0456789012345",
        "name": "Elisabeth Svantesson",
        "party": "M",
        "ministry": "Finansdepartementet",
        "title": "Finansminister",
        "since": "2022-10-18",
        "portfolio": ["finance", "economy", "budget"],
        "profile": {
          "shortDescription": "Finance Minister managing Sweden's economy, fiscal policy, and public finances.",
          "influenceScore": 92,
          "riskScore": 8,
          "intelligenceTags": ["minister", "finance-policy", "economic-strategy"]
        }
      }
    ],
    "byMinistry": {
      "Finansdepartementet": [
        {
          "id": "0456789012345",
          "name": "Elisabeth Svantesson",
          "title": "Finansminister"
        }
      ],
      "Försvarsdepartementet": [
        {
          "id": "0345678901234",
          "name": "Pål Jonson",
          "title": "Försvarsminister"
        }
      ]
    },
    "byParty": {
      "M": 11,
      "KD": 5,
      "L": 4,
      "SD": 0
    },
    "coalitionComposition": {
      "parties": ["M", "KD", "L"],
      "supportParty": ["SD"],
      "totalSeats": 176,
      "parliamentSeats": 349,
      "majorityMargin": 1
    }
  }
}
```

**Key Features:**
- ✅ Complete cabinet composition
- ✅ Minister portfolios and responsibilities
- ✅ Grouped by ministry and party
- ✅ Coalition structure analysis
- ✅ Influence and risk scores

---

### 1.3 Committee Chairs Collection

**File:** `/v1.0.0/curated/committee-chairs.json`  
**Update Frequency:** Weekly  
**Database Source:** Committee leadership assignments

```json
{
  "metadata": {
    "version": "1.0.0",
    "generated": "2024-11-24T02:23:58Z",
    "count": 15,
    "description": "Chairs and vice-chairs of parliamentary committees"
  },
  "data": {
    "committees": [
      {
        "committeeId": "FiU",
        "committeeName": "Finansutskottet",
        "area": "finance",
        "chair": {
          "id": "0567890123456",
          "name": "Lars Hjälmered",
          "party": "M",
          "since": "2022-10-18",
          "profile": {
            "influenceScore": 78,
            "committeeExpertise": "high"
          }
        },
        "viceChairs": [
          {
            "id": "0678901234567",
            "name": "Teresa Carvalho",
            "party": "S"
          }
        ]
      }
    ]
  }
}
```

---

## 2. Engaging Top-10 Categories

### 2.1 Electoral Risk Rankings

**File:** `/v1.0.0/top10/electoral-risk.json`  
**Update Frequency:** Weekly  
**Engagement Factor:** 🔥🔥🔥🔥🔥 (Very High)  
**Headline:** "10 Riksdag Members Most Likely to Lose Their Seats"

```json
{
  "metadata": {
    "version": "1.0.0",
    "generated": "2024-11-24T02:23:58Z",
    "category": "electoral-risk",
    "title": "Politicians Most at Risk in Next Election",
    "description": "Based on poll trends, constituency changes, party performance, and personal controversies",
    "methodology": "Composite risk score from poll data, scandal impact, party trajectory, constituency demographics",
    "confidence": 0.78,
    "count": 10
  },
  "data": {
    "politicians": [
      {
        "rank": 1,
        "id": "0789012345678",
        "name": "Example Politician One",
        "party": "V",
        "constituency": "Stockholm",
        "riskScore": 89,
        "riskCategory": "very-high",
        "riskFactors": {
          "partyPolls": -12.5,
          "personalScandals": 2,
          "constituencyShift": "conservative-trend",
          "voterSatisfaction": 23,
          "mediaControversy": "high",
          "internalPartyConflict": true
        },
        "analysis": {
          "shortSummary": "Facing dual challenges: party polling 12.5% below 2022 results and personal ethics investigations.",
          "keyIssues": [
            "Ethics investigation regarding undisclosed consultancy work",
            "V party polling at 6.8% (down from 8.0% in 2022)",
            "Conservative shift in Stockholm constituency demographics",
            "Low voter satisfaction (23% approve)"
          ],
          "probabilityLoseSeat": 0.72
        },
        "profile": {
          "currentPosition": "Member of Parliament",
          "years": 4,
          "committees": ["FiU"],
          "mediaPresence": "high-negative"
        }
      },
      {
        "rank": 2,
        "id": "0890123456789",
        "name": "Example Politician Two",
        "party": "C",
        "riskScore": 85,
        "riskCategory": "very-high",
        "riskFactors": {
          "partyPolls": -14.2,
          "personalScandals": 0,
          "constituencyShift": "urban-rural-divide",
          "partyInternal": "leadership-conflict"
        },
        "analysis": {
          "shortSummary": "Party polling below 4% threshold with internal leadership crisis threatening parliamentary presence.",
          "keyIssues": [
            "C party polling at 3.8% (below 4% threshold for parliamentary seats)",
            "Internal leadership conflict following coalition decisions",
            "Rural constituency shift toward SD",
            "Party identity crisis: traditional rural base vs urban environmentalists"
          ],
          "probabilityLoseSeat": 0.68,
          "probabilityPartyExitsParliament": 0.42
        }
      }
    ],
    "summary": {
      "avgRiskScore": 76,
      "partiesRepresented": ["V", "C", "L", "MP"],
      "mainRiskFactors": [
        "Party polling below 2022 results (80%)",
        "Personal scandals or controversies (30%)",
        "Constituency demographic shifts (60%)",
        "Internal party conflicts (40%)"
      ],
      "electionDate": "2026-09-13",
      "daysUntilElection": 663
    }
  }
}
```

**Clickbait Headlines:**
- "These 10 Riksdag Members Are In Serious Electoral Trouble"
- "Countdown to 2026: Politicians Facing the Exit Door"
- "Who Won't Survive the Next Election? Data Reveals Shocking Risks"

**Key Features:**
- ✅ Composite risk scoring methodology
- ✅ Multiple risk factor analysis
- ✅ Probability calculations
- ✅ Context-rich explanations
- ✅ Party and constituency trends

---

### 2.2 Scandal & Ethics Watchlist

**File:** `/v1.0.0/top10/ethics-concerns.json`  
**Engagement Factor:** 🔥🔥🔥🔥🔥 (Very High)  
**Headline:** "10 Politicians Under Ethics Investigation or Controversy"

```json
{
  "metadata": {
    "version": "1.0.0",
    "generated": "2024-11-24T02:23:58Z",
    "category": "ethics-concerns",
    "title": "Politicians Facing Ethics Questions",
    "description": "Active investigations, controversies, and accountability concerns",
    "confidence": 0.92,
    "count": 10
  },
  "data": {
    "politicians": [
      {
        "rank": 1,
        "id": "0901234567890",
        "name": "Example Politician Three",
        "party": "M",
        "ethicsScore": 72,
        "category": "financial-irregularity",
        "status": "under-investigation",
        "investigations": [
          {
            "type": "undisclosed-income",
            "agency": "Riksdagens konstitutionsutskott",
            "opened": "2024-09-15",
            "status": "active",
            "severity": "high",
            "description": "Alleged failure to disclose consultancy income from private sector clients"
          }
        ],
        "mediaActivity": {
          "articles": 47,
          "sentiment": -0.68,
          "lastMajorStory": "2024-11-15"
        },
        "analysis": {
          "shortSummary": "Under investigation for undisclosed income, 47 negative media articles in past 2 months.",
          "impactAssessment": {
            "reputation": "severe-damage",
            "partyDamage": "moderate",
            "electoralRisk": 0.54,
            "resignationProbability": 0.32
          }
        }
      }
    ]
  }
}
```

**Clickbait Headlines:**
- "Accountability Crisis: 10 Riksdag Members Facing Serious Questions"
- "These Politicians Have Ethics Investigations You Need to Know About"
- "Scandals, Conflicts, and Controversies: The Riksdag's Troubled Ten"

---

### 2.3 Most Influential Politicians

**File:** `/v1.0.0/top10/most-influential.json`  
**Engagement Factor:** 🔥🔥🔥🔥 (High)  
**Headline:** "The Real Power Players in Swedish Politics"

```json
{
  "metadata": {
    "version": "1.0.0",
    "generated": "2024-11-24T02:23:58Z",
    "category": "influence",
    "title": "Most Influential Politicians in the Riksdag",
    "description": "Based on network centrality, legislative success, media presence, coalition brokerage",
    "methodology": "Composite score: network centrality (30%), legislative success (25%), media presence (20%), coalition influence (25%)",
    "confidence": 0.85,
    "count": 10
  },
  "data": {
    "politicians": [
      {
        "rank": 1,
        "id": "0234567890123",
        "name": "Ulf Kristersson",
        "party": "M",
        "influenceScore": 98,
        "category": "prime-minister",
        "profile": {
          "position": "Prime Minister",
          "shortDescription": "Leading minority coalition government with SD support, architect of 2022 power shift.",
          "powerMetrics": {
            "networkCentrality": 0.94,
            "legislativeSuccess": 0.88,
            "mediaPresence": 0.96,
            "coalitionInfluence": 0.99
          },
          "keyAchievements": [
            "Formed first right-wing government since 2014",
            "Negotiated Tidö Agreement with SD",
            "Led NATO accession process"
          ],
          "sphere": "executive-legislative"
        }
      },
      {
        "rank": 2,
        "id": "0457722435319",
        "name": "Magdalena Andersson",
        "party": "S",
        "influenceScore": 95,
        "category": "opposition-leader",
        "profile": {
          "position": "Former PM, Opposition Leader",
          "shortDescription": "Leading opposition against right-wing coalition, experienced former Finance Minister and PM.",
          "powerMetrics": {
            "networkCentrality": 0.91,
            "legislativeSuccess": 0.79,
            "mediaPresence": 0.95,
            "coalitionInfluence": 0.86
          }
        }
      }
    ]
  }
}
```

**Clickbait Headlines:**
- "Who Really Runs Sweden? The 10 Most Powerful Politicians"
- "Beyond the Headlines: Sweden's True Power Brokers"
- "Influence Rankings: The Politicians Who Shape Policy"

---

### 2.4 Rising Stars

**File:** `/v1.0.0/top10/rising-stars.json`  
**Engagement Factor:** 🔥🔥🔥🔥 (High)  
**Headline:** "10 Young Politicians to Watch"

```json
{
  "metadata": {
    "version": "1.0.0",
    "generated": "2024-11-24T02:23:58Z",
    "category": "rising-stars",
    "title": "Rising Political Stars Under 40",
    "description": "Young politicians showing rapid influence growth, media attention, and leadership potential",
    "ageLimit": 40,
    "methodology": "Momentum score based on influence trajectory, media growth, leadership positions, legislative success",
    "confidence": 0.76,
    "count": 10
  },
  "data": {
    "politicians": [
      {
        "rank": 1,
        "id": "1012345678901",
        "name": "Example Young Politician One",
        "party": "MP",
        "age": 32,
        "momentumScore": 87,
        "trajectory": "rapid-ascent",
        "profile": {
          "position": "Environmental Committee Member",
          "yearsInParliament": 2,
          "achievements": [
            "Led successful cross-party climate legislation",
            "Social media following: 145K (fastest growing)",
            "Featured in Forbes 30 Under 30 Europe"
          ],
          "influenceGrowth": {
            "2022": 34,
            "2023": 58,
            "2024": 87,
            "percentageGrowth": 156
          },
          "mediaMetrics": {
            "mentions": 234,
            "sentiment": 0.72,
            "trend": "sharply-increasing"
          },
          "analysis": {
            "shortSummary": "Climate policy innovator with strong social media presence, potential future party leader.",
            "leadershipPotential": 0.81,
            "predictedPeak": "2028-2032"
          }
        }
      }
    ]
  }
}
```

---

### 2.5 Most Absent Politicians

**File:** `/v1.0.0/top10/most-absent.json`  
**Engagement Factor:** 🔥🔥🔥🔥 (High)  
**Headline:** "Missing in Action: Riksdag's Worst Attendance Records"

```json
{
  "metadata": {
    "version": "1.0.0",
    "generated": "2024-11-24T02:23:58Z",
    "category": "attendance",
    "title": "Lowest Attendance Records in Riksdag",
    "description": "Politicians with poorest voting attendance and committee participation",
    "period": "2024-01-01 to 2024-11-23",
    "confidence": 0.98,
    "count": 10
  },
  "data": {
    "politicians": [
      {
        "rank": 1,
        "id": "1123456789012",
        "name": "Example Absent Politician",
        "party": "SD",
        "absenceScore": 68,
        "profile": {
          "attendanceRate": 32,
          "votingParticipation": 28,
          "committeeAttendance": 15,
          "missedVotes": 284,
          "totalVotes": 418,
          "excusedAbsences": 12,
          "unexcusedAbsences": 272,
          "analysis": {
            "shortSummary": "Missed 68% of votes this year, lowest attendance in Riksdag, no stated health issues.",
            "salaryPerVote": "15,234 SEK",
            "taxpayerCost": "4.3M SEK annually",
            "reasons": [
              "No official explanation provided",
              "Party has not commented on attendance",
              "No known health or family issues"
            ],
            "accountability": "poor"
          }
        }
      }
    ]
  }
}
```

**Clickbait Headlines:**
- "They're Paid to Show Up, But Don't: Riksdag's Absentee Crisis"
- "Missing Millions: Politicians Collecting Salaries for No-Shows"
- "Accountability Alert: 10 MPs Who Rarely Appear for Work"

---

### 2.6 Biggest Party Rebels

**File:** `/v1.0.0/top10/party-rebels.json`  
**Engagement Factor:** 🔥🔥🔥🔥 (High)  
**Headline:** "Party Discipline Breakers: Independent Thinkers or Troublemakers?"

```json
{
  "metadata": {
    "version": "1.0.0",
    "generated": "2024-11-24T02:23:58Z",
    "category": "party-discipline",
    "title": "Politicians Who Most Often Break Party Line",
    "description": "Members who vote against their party most frequently",
    "period": "2024-01-01 to 2024-11-23",
    "confidence": 0.94,
    "count": 10
  },
  "data": {
    "politicians": [
      {
        "rank": 1,
        "id": "1234567890123",
        "name": "Example Rebel Politician",
        "party": "L",
        "rebelScore": 76,
        "profile": {
          "partyLoyalty": 24,
          "deviationVotes": 127,
          "totalVotes": 382,
          "deviationRate": 33.2,
          "analysis": {
            "shortSummary": "Voted against party line 127 times (33%), highest deviation rate in L, potential defection risk.",
            "keyDeviations": [
              "Immigration policy (45 votes against party)",
              "Climate legislation (23 votes against party)",
              "Justice reform (18 votes against party)"
            ],
            "partyResponse": "Public criticism from party leadership",
            "defectionRisk": 0.58,
            "predictedAlignment": "possibly-independent"
          }
        }
      }
    ]
  }
}
```

---

### 2.7 Most Productive Legislators

**File:** `/v1.0.0/top10/most-productive.json`  
**Engagement Factor:** 🔥🔥🔥 (Medium)  
**Headline:** "The Workhorses: Riksdag's Most Productive Members"

```json
{
  "metadata": {
    "version": "1.0.0",
    "generated": "2024-11-24T02:23:58Z",
    "category": "productivity",
    "title": "Most Productive Legislators by Documents and Success Rate",
    "period": "2024-01-01 to 2024-11-23",
    "count": 10
  },
  "data": {
    "politicians": [
      {
        "rank": 1,
        "id": "1345678901234",
        "name": "Example Productive Politician",
        "party": "M",
        "productivityScore": 92,
        "profile": {
          "totalDocuments": 347,
          "motionsProposed": 89,
          "motionsAdopted": 23,
          "successRate": 25.8,
          "committeeReports": 12,
          "interpellations": 67,
          "analysis": {
            "shortSummary": "347 documents submitted, 26% adoption rate, strong legislative record in finance policy.",
            "focusAreas": ["finance", "taxation", "business-regulation"],
            "impact": "high",
            "efficiencyRanking": 3
          }
        }
      }
    ]
  }
}
```

---

### 2.8 Coalition Brokers

**File:** `/v1.0.0/top10/coalition-brokers.json`  
**Engagement Factor:** 🔥🔥🔥 (Medium)  
**Headline:** "The Deal Makers: Politicians Who Bridge Party Divides"

```json
{
  "metadata": {
    "version": "1.0.0",
    "generated": "2024-11-24T02:23:58Z",
    "category": "coalition-brokerage",
    "title": "Politicians Most Skilled at Cross-Party Cooperation",
    "description": "Based on cross-party voting, co-sponsorship patterns, committee collaboration",
    "confidence": 0.83,
    "count": 10
  },
  "data": {
    "politicians": [
      {
        "rank": 1,
        "id": "1456789012345",
        "name": "Example Broker Politician",
        "party": "C",
        "brokerageScore": 88,
        "profile": {
          "crossPartyCollaboration": 84,
          "coSponsorships": {
            "M": 34,
            "S": 28,
            "L": 42,
            "KD": 19
          },
          "networkCentrality": {
            "betweenness": 0.87,
            "closeness": 0.79
          },
          "analysis": {
            "shortSummary": "Bridge-builder between coalition and opposition, 84% cross-party collaboration rate.",
            "role": "consensus-builder",
            "keyAchievements": [
              "Brokered cross-party climate deal",
              "Led bipartisan healthcare reform",
              "Trusted negotiator in budget talks"
            ],
            "influenceType": "coalition-bridge"
          }
        }
      }
    ]
  }
}
```

---

### 2.9 Media Darlings vs Ghosts

**File:** `/v1.0.0/top10/media-presence.json`  
**Engagement Factor:** 🔥🔥🔥🔥 (High)  
**Headline:** "Most and Least Visible Politicians in Swedish Media"

```json
{
  "metadata": {
    "version": "1.0.0",
    "generated": "2024-11-24T02:23:58Z",
    "category": "media-presence",
    "title": "Media Visibility Rankings",
    "period": "2024-01-01 to 2024-11-23",
    "count": 20
  },
  "data": {
    "mostVisible": [
      {
        "rank": 1,
        "id": "0234567890123",
        "name": "Ulf Kristersson",
        "party": "M",
        "mediaScore": 98,
        "mentions": 8947,
        "sentiment": 0.12,
        "outlets": ["DN", "SvD", "Expressen", "Aftonbladet", "SVT"],
        "analysis": "Prime Minister dominates media coverage, 8947 mentions in 11 months, mixed sentiment."
      }
    ],
    "leastVisible": [
      {
        "rank": 1,
        "id": "1567890123456",
        "name": "Example Ghost Politician",
        "party": "S",
        "mediaScore": 3,
        "mentions": 7,
        "analysis": "Only 7 media mentions all year, virtually invisible despite active parliamentary role."
      }
    ]
  }
}
```

---

### 2.10 Controversial Figures

**File:** `/v1.0.0/top10/most-controversial.json`  
**Engagement Factor:** 🔥🔥🔥🔥🔥 (Very High)  
**Headline:** "Sweden's Most Divisive Politicians"

```json
{
  "metadata": {
    "version": "1.0.0",
    "generated": "2024-11-24T02:23:58Z",
    "category": "controversy",
    "title": "Most Controversial Politicians by Media Sentiment Polarization",
    "description": "Politicians generating most polarized reactions (high positive + high negative)",
    "confidence": 0.88,
    "count": 10
  },
  "data": {
    "politicians": [
      {
        "rank": 1,
        "id": "1678901234567",
        "name": "Example Controversial Politician",
        "party": "SD",
        "controversyScore": 94,
        "profile": {
          "mediaMentions": 2134,
          "sentimentPolarization": 0.92,
          "positiveMentions": 847,
          "negativeMentions": 1142,
          "netSentiment": -0.35,
          "controversies": [
            {
              "issue": "Immigration statements",
              "date": "2024-03-15",
              "impact": "high",
              "mediaStorm": true
            },
            {
              "issue": "Historical comments",
              "date": "2024-07-22",
              "impact": "very-high",
              "mediaStorm": true
            }
          ],
          "analysis": {
            "shortSummary": "Extremely polarizing figure, 92% sentiment polarization, frequent source of debate.",
            "supportBase": "strong-but-narrow",
            "opponentIntensity": "very-high",
            "mediaStrategy": "provocative"
          }
        }
      }
    ]
  }
}
```

---

## 3. Specialized Aggregates

### 3.1 Committee Power Rankings

**File:** `/v1.0.0/curated/committee-power-rankings.json`

```json
{
  "metadata": {
    "version": "1.0.0",
    "generated": "2024-11-24T02:23:58Z",
    "description": "Committee influence and effectiveness rankings"
  },
  "data": {
    "committees": [
      {
        "rank": 1,
        "id": "FiU",
        "name": "Finansutskottet",
        "powerScore": 96,
        "metrics": {
          "budgetControl": 100,
          "mediaAttention": 89,
          "legislativeSuccess": 91,
          "policyImpact": 98
        },
        "analysis": "Most powerful committee controlling state budget and economic policy."
      }
    ]
  }
}
```

---

### 3.2 Party Momentum Tracker

**File:** `/v1.0.0/curated/party-momentum.json`

```json
{
  "metadata": {
    "version": "1.0.0",
    "generated": "2024-11-24T02:23:58Z",
    "description": "Party momentum analysis with velocity and acceleration"
  },
  "data": {
    "parties": [
      {
        "partyCode": "SD",
        "partyName": "Sverigedemokraterna",
        "momentumScore": 78,
        "trend": "rising",
        "metrics": {
          "pollVelocity": 0.3,
          "pollAcceleration": 0.05,
          "mediaGrowth": 23,
          "supportBaseSolidity": 82
        },
        "forecast": {
          "next Election2026": 22.5,
          "confidence": 0.76
        }
      }
    ]
  }
}
```

---

## 4. Implementation Guide

### 4.1 File Structure

```
/v1.0.0/
├── curated/
│   ├── party-leaders.json
│   ├── government-officials.json
│   ├── committee-chairs.json
│   ├── committee-power-rankings.json
│   └── party-momentum.json
├── top10/
│   ├── electoral-risk.json
│   ├── ethics-concerns.json
│   ├── most-influential.json
│   ├── rising-stars.json
│   ├── most-absent.json
│   ├── party-rebels.json
│   ├── most-productive.json
│   ├── coalition-brokers.json
│   ├── media-presence.json
│   └── most-controversial.json
└── indices/
    ├── all-curated.json        # Index of all curated files
    └── all-top10.json          # Index of all top-10 files
```

### 4.2 Update Schedule

| Category | Frequency | Time (UTC) | Reason |
|----------|-----------|------------|--------|
| Party Leaders | Daily | 02:00 | Role changes rare but important |
| Government Officials | Daily | 02:00 | Cabinet reshuffles |
| Committee Chairs | Weekly | Sun 02:00 | Committee assignments change quarterly |
| Electoral Risk | Weekly | Mon 02:00 | Poll data updates |
| Ethics Concerns | Daily | 02:00 | Investigation status changes |
| Most Influential | Weekly | Tue 02:00 | Network analysis computation intensive |
| Rising Stars | Bi-weekly | Wed 02:00 | Momentum calculations |
| Most Absent | Weekly | Thu 02:00 | Voting records update |
| Party Rebels | Weekly | Fri 02:00 | Voting records update |
| Most Productive | Weekly | Sat 02:00 | Document submissions |

### 4.3 Database Queries

#### Electoral Risk Calculation

```sql
-- Composite risk score combining multiple factors
SELECT 
    p.person_id,
    p.first_name || ' ' || p.last_name as name,
    p.party,
    -- Risk factors
    (
        COALESCE(poll_decline.score, 0) * 0.4 +
        COALESCE(scandal.score, 0) * 0.3 +
        COALESCE(constituency_shift.score, 0) * 0.2 +
        COALESCE(party_internal.score, 0) * 0.1
    ) as risk_score
FROM view_riksdagen_politician p
LEFT JOIN party_poll_trends poll_decline ON p.party = poll_decline.party
LEFT JOIN ethics_investigations scandal ON p.person_id = scandal.person_id
LEFT JOIN constituency_demographics constituency_shift ON p.constituency = constituency_shift.area
LEFT JOIN party_internal_conflict party_internal ON p.party = party_internal.party
WHERE p.active = true
ORDER BY risk_score DESC
LIMIT 10;
```

#### Influence Score Calculation

```sql
-- Network centrality + legislative success + media presence
SELECT 
    p.person_id,
    p.first_name || ' ' || p.last_name as name,
    (
        COALESCE(centrality.betweenness_centrality, 0) * 0.3 +
        COALESCE(legislative.success_rate, 0) * 0.25 +
        COALESCE(media.presence_score, 0) * 0.2 +
        COALESCE(coalition.influence_score, 0) * 0.25
    ) * 100 as influence_score
FROM view_riksdagen_politician p
LEFT JOIN view_riksdagen_politician_influence_metrics centrality ON p.person_id = centrality.person_id
LEFT JOIN politician_legislative_success legislative ON p.person_id = legislative.person_id
LEFT JOIN media_presence_metrics media ON p.person_id = media.person_id
LEFT JOIN coalition_broker_metrics coalition ON p.person_id = coalition.person_id
WHERE p.active = true
ORDER BY influence_score DESC
LIMIT 10;
```

---

## 5. JavaScript Usage Examples

### 5.1 Displaying Electoral Risk List

```javascript
// Fetch and display electoral risk rankings
async function loadElectoralRisk() {
  const response = await fetch('https://cdn.cia.se/v1.0.0/top10/electoral-risk.json');
  const data = await response.json();
  
  const list = document.getElementById('risk-list');
  data.data.politicians.forEach(politician => {
    const item = document.createElement('div');
    item.className = 'risk-item';
    item.innerHTML = `
      <h3>#${politician.rank} ${politician.name} (${politician.party})</h3>
      <div class="risk-score">Risk: ${politician.riskScore}/100</div>
      <p>${politician.analysis.shortSummary}</p>
      <ul>
        ${politician.analysis.keyIssues.map(issue => `<li>${issue}</li>`).join('')}
      </ul>
      <div class="probability">
        Probability of losing seat: ${(politician.analysis.probabilityLoseSeat * 100).toFixed(0)}%
      </div>
    `;
    list.appendChild(item);
  });
}
```

### 5.2 Government Officials Directory

```javascript
// Create interactive government directory
async function loadGovernment() {
  const response = await fetch('https://cdn.cia.se/v1.0.0/curated/government-officials.json');
  const data = await response.json();
  
  // Display Prime Minister
  document.getElementById('pm').innerHTML = `
    <h2>${data.data.primeMinister.name}</h2>
    <p>${data.data.primeMinister.title}</p>
  `;
  
  // Group ministers by ministry
  Object.entries(data.data.byMinistry).forEach(([ministry, ministers]) => {
    // Create ministry section
    const section = createMinistrySection(ministry, ministers);
    document.getElementById('ministries').appendChild(section);
  });
}
```

### 5.3 Top-10 Widget

```javascript
// Embeddable widget for top-10 lists
class Top10Widget {
  constructor(category, containerId) {
    this.category = category;
    this.container = document.getElementById(containerId);
  }
  
  async load() {
    const response = await fetch(`https://cdn.cia.se/v1.0.0/top10/${this.category}.json`);
    const data = await response.json();
    this.render(data);
  }
  
  render(data) {
    this.container.innerHTML = `
      <div class="top10-widget">
        <h2>${data.metadata.title}</h2>
        <p class="description">${data.metadata.description}</p>
        <ol class="top10-list">
          ${data.data.politicians.map(p => `
            <li class="rank-${p.rank}">
              <span class="name">${p.name}</span>
              <span class="party">${p.party}</span>
              <span class="score">${p[`${this.category}Score`]}</span>
            </li>
          `).join('')}
        </ol>
      </div>
    `;
  }
}

// Usage
const riskWidget = new Top10Widget('electoral-risk', 'risk-container');
riskWidget.load();
```

---

## 6. SEO & Marketing Strategy

### 6.1 Headline Templates

**Electoral Risk:**
- "Election 2026: 10 Riksdag Seats Most Likely to Change Hands"
- "Data Reveals: These Politicians Are In Serious Trouble"
- "Who Won't Survive? AI Predicts Electoral Casualties"

**Ethics:**
- "Accountability Alert: 10 MPs Under Investigation"
- "Ethics Crisis: The Riksdag Members Facing Serious Questions"
- "Scandal Tracker: Politicians Under the Microscope"

**Influence:**
- "The Real Power Players: Sweden's Most Influential Politicians"
- "Beyond the Headlines: Who Actually Runs Sweden?"
- "Data Shows: These 10 Politicians Have the Most Influence"

**Rising Stars:**
- "Future Leaders: 10 Young Politicians to Watch"
- "Under 40 and On the Rise: Sweden's Next Generation"
- "Rising Stars: The Politicians Who Could Lead in 2030"

### 6.2 Social Media Snippets

```json
{
  "tweetTemplate": "🚨 NEW DATA: {politician} is ranked #{rank} in our {category} analysis. {keyFinding}. Full list: {url} #Riksdagen #SvPol",
  "linkedInTemplate": "Our latest political intelligence analysis reveals {insight}. We've analyzed {dataPoints} to identify {finding}. Read the full report: {url}",
  "instagramCaption": "📊 {category} Rankings\n#{rank} {politician}\n{shortSummary}\n\n#SwedishPolitics #DataJournalism #PoliticalIntelligence"
}
```

### 6.3 Content Calendar

**Monthly Schedule:**
- Week 1: Electoral Risk update + article
- Week 2: Ethics Concerns update + investigation spotlight
- Week 3: Influence Rankings + power analysis
- Week 4: Rising Stars + feature interviews

**Quarterly Deep Dives:**
- Q1: Pre-election analysis
- Q2: Government performance review
- Q3: Coalition stability assessment
- Q4: Year in review + predictions

---

## 7. Analytics & Success Metrics

### 7.1 Engagement Tracking

```json
{
  "metrics": {
    "fileDownloads": "Track via CDN logs",
    "apiCalls": "Count unique IP addresses",
    "socialShares": "Monitor via social media APIs",
    "mediaCitations": "Track backlinks and references",
    "timeOnPage": "Integrate with Google Analytics"
  },
  "targets": {
    "monthlyUsers": 50000,
    "socialShares": 1000,
    "mediaCitations": 50,
    "averageSessionDuration": "3:30"
  }
}
```

### 7.2 A/B Testing

Test headline variations:
- "10 Politicians in Trouble" vs "Electoral Risk Rankings"
- "Scandal Watchlist" vs "Ethics Accountability Tracker"
- "Rising Stars" vs "Future Leaders Under 40"

---

## 8. Legal & Ethical Considerations

### 8.1 Data Protection

✅ **GDPR Compliant:**
- Only public information
- No personal contact details
- Right to correction mechanism
- Data retention policy documented

✅ **Journalistic Standards:**
- Methodology transparent and documented
- Confidence intervals provided
- Sources cited (database views)
- Opportunity for subject response

✅ **Ethical Guidelines:**
- No character assassination
- Context always provided
- Risk scores are analytical, not judgmental
- Clear distinction between facts and analysis

### 8.2 Disclaimer

```json
{
  "disclaimer": "These rankings are based on publicly available data and statistical analysis. Risk scores and predictions are analytical assessments and should not be considered definitive statements. All individuals retain the right to respond and provide context. This platform supports democratic accountability and informed citizenship.",
  "methodology": "Fully documented and transparent",
  "dataSource": "Public records and official databases",
  "updateFrequency": "Clearly stated for each dataset",
  "contact": "corrections@cia.se for factual corrections"
}
```

---

## 9. Revenue Opportunities

### 9.1 Premium Tier

**Enhanced Access (€9.99/month):**
- Real-time updates (hourly vs daily)
- Historical data access (5+ years)
- Custom alerts (notify when politician enters top-10)
- API access for developers
- Export to CSV/Excel
- White-label options for media partners

### 9.2 Media Partnerships

**Data Licensing for Newsrooms:**
- Real-time data feeds
- Custom analysis requests
- Exclusive early access
- Co-branded content opportunities
- Training and onboarding

### 9.3 Academic Access

**Research Tier (€199/year):**
- Complete historical datasets
- Bulk data export
- Academic methodology documentation
- Citation support
- Research collaboration opportunities

---

## 10. Future Enhancements

### Phase 2 (Q2 2025)

- **Regional Analysis**: Top-10 by constituency/region
- **Historical Comparisons**: "Most controversial of all time"
- **Prediction Markets**: Crowdsourced election forecasting
- **Interactive Visualizations**: D3.js powered charts and networks

### Phase 3 (Q3 2025)

- **Real-time Dashboard**: Live tracking during parliamentary sessions
- **Sentiment Analysis**: AI-powered media sentiment tracking
- **Coalition Calculator**: Interactive government formation tool
- **Policy Impact Tracker**: Legislation success and societal impact

### Phase 4 (Q4 2025)

- **EU Comparison**: Swedish politicians vs European peers
- **Historical Database**: 1960s-present complete archive
- **Predictive AI**: Machine learning election forecasts
- **Mobile App**: iOS and Android native applications

---

## Conclusion

This curated aggregates specification provides a comprehensive framework for high-engagement political intelligence exports. By combining data-driven analysis with compelling narratives, these JSON files will:

✅ **Drive Traffic**: Clickable headlines and shareable content  
✅ **Support Journalism**: Ready-made datasets for investigations  
✅ **Enhance Democracy**: Transparent accountability mechanisms  
✅ **Generate Revenue**: Premium tiers and licensing opportunities  
✅ **Build Community**: Engaged citizens and active discussions  

**Implementation Priority:** HIGH  
**Estimated Development Time:** 3-4 weeks  
**Expected Monthly Users:** 50,000+  
**Revenue Potential:** €15,000-30,000/month (premium + licensing)

**Status**: Ready for implementation phase.
