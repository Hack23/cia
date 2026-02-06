---
name: data-visualization-principles
description: Chart selection, color theory, dashboard design, and data storytelling principles for political transparency data
license: Apache-2.0
---

# Data Visualization Principles

## Purpose

This skill provides guidance for creating effective data visualizations in the CIA platform, including chart type selection, color theory for accessibility, dashboard layout, and data storytelling techniques for political transparency data.

## When to Use

### ✅ Use this skill when:
- Designing dashboards and reports
- Selecting appropriate chart types
- Choosing color palettes
- Creating comparative visualizations
- Displaying time-series data
- Showing relationships and networks
- Building interactive visualizations
- Presenting complex political data

### ❌ Don't use this skill for:
- UI component implementation (use vaadin-component-design)
- Accessibility compliance (use accessibility-wcag-patterns)
- Backend data processing (use data-science-for-intelligence)
- Security (use secure-code-review)

## Patterns & Examples

### Chart Type Selection Guide

| Data Type | Best Chart | Use When | Avoid |
|-----------|------------|----------|-------|
| **Comparison** | Bar chart | Comparing categories | Pie chart for >5 items |
| **Trend over time** | Line chart | Showing temporal patterns | Bar chart for continuous time |
| **Distribution** | Histogram | Showing frequency distribution | Line chart |
| **Correlation** | Scatter plot | Showing relationships | Bar chart |
| **Composition** | Stacked bar/area | Showing parts of whole | Multiple pie charts |
| **Geographic** | Choropleth map | Regional comparisons | 3D visualizations |
| **Network** | Force-directed graph | Showing connections | Complex nested structures |

### Political Risk Dashboard Example

```java
// Vaadin Chart.js integration
@Route("dashboard/risk")
public class RiskDashboardView extends VerticalLayout {
    
    public RiskDashboardView() {
        // KPI Cards at top
        HorizontalLayout kpis = createKPICards();
        
        // Main charts grid
        HorizontalLayout chartsRow1 = new HorizontalLayout();
        chartsRow1.add(
            createRiskDistributionChart(),
            createRiskTrendChart()
        );
        
        HorizontalLayout chartsRow2 = new HorizontalLayout();
        chartsRow2.add(
            createPartyComparisonChart(),
            createTopRisksTable()
        );
        
        add(kpis, chartsRow1, chartsRow2);
    }
    
    private Component createRiskDistributionChart() {
        Chart chart = new Chart(ChartType.BAR);
        chart.getConfiguration().setTitle("Risk Score Distribution");
        
        Configuration config = chart.getConfiguration();
        
        // Data
        DataSeries series = new DataSeries("Politicians");
        series.add(new DataSeriesItem("0-20", 45));
        series.add(new DataSeriesItem("21-40", 89));
        series.add(new DataSeriesItem("41-60", 134));
        series.add(new DataSeriesItem("61-80", 67));
        series.add(new DataSeriesItem("81-100", 14));
        
        // Color scheme - traffic light semantics
        PlotOptionsSeries options = new PlotOptionsSeries();
        options.setColorByPoint(true);
        series.setPlotOptions(options);
        
        // Accessible colors
        config.setColors(
            new SolidColor("#2e7d32"),  // Green (low risk)
            new SolidColor("#689f38"),  // Light green
            new SolidColor("#f57c00"),  // Orange (medium)
            new SolidColor("#e64a19"),  // Dark orange
            new SolidColor("#c62828")   // Red (high risk)
        );
        
        config.addSeries(series);
        
        // Axis labels
        XAxis x = new XAxis();
        x.setTitle("Risk Score Range");
        config.addxAxis(x);
        
        YAxis y = new YAxis();
        y.setTitle("Number of Politicians");
        config.addyAxis(y);
        
        return chart;
    }
}
```

### Color Palette for Political Data

```css
/* CIA Platform Color System */

/* Primary Semantic Colors */
:root {
  /* Risk levels - WCAG AA compliant */
  --risk-critical: #b71c1c;  /* Red - High contrast */
  --risk-high: #d32f2f;
  --risk-medium: #f57c00;     /* Orange */
  --risk-low: #388e3c;        /* Green */
  --risk-minimal: #1976d2;    /* Blue */
  
  /* Political party colors (Sweden) */
  --party-s: #ed1b34;         /* Social Democrats - Red */
  --party-m: #52bdec;         /* Moderates - Light Blue */
  --party-sd: #dddd00;        /* Sweden Democrats - Yellow */
  --party-c: #009933;         /* Centre - Green */
  --party-v: #da291c;         /* Left - Dark Red */
  --party-kd: #000077;        /* Christian Democrats - Blue */
  --party-l: #006ab3;         /* Liberals - Blue */
  --party-mp: #83cf39;        /* Green Party - Light Green */
  
  /* Data visualization scale */
  --viz-gradient-start: #e3f2fd;
  --viz-gradient-mid: #1976d2;
  --viz-gradient-end: #0d47a1;
  
  /* Neutral palette for secondary data */
  --neutral-1: #f5f5f5;
  --neutral-2: #e0e0e0;
  --neutral-3: #9e9e9e;
  --neutral-4: #616161;
  --neutral-5: #212121;
}

/* Chart.js custom color scheme */
.political-chart {
  --chart-primary: #1976d2;
  --chart-secondary: #388e3c;
  --chart-tertiary: #f57c00;
  --chart-quaternary: #7b1fa2;
}
```

### Dashboard Layout Principles

```markdown
## F-Pattern Layout (Eye Tracking)

┌─────────────────────────────────────────┐
│  Logo    Navigation           User      │  ← Primary scan (horizontal)
├─────────────────────────────────────────┤
│  KPI 1    KPI 2    KPI 3    KPI 4      │  ← Key metrics (horizontal)
├─────────────────────────────────────────┤
│                                         │
│  ┌───────────────┐  ┌───────────────┐  │
│  │               │  │               │  │
│  │  Main Chart   │  │  Trend Line   │  │  ← Primary content
│  │               │  │               │  │
│  └───────────────┘  └───────────────┘  │
│                                         │
│  ┌───────────────┐  ┌───────────────┐  │
│  │  Comparison   │  │  Top List     │  │  ← Secondary content
│  │  Bar Chart    │  │  Table        │  │
│  └───────────────┘  └───────────────┘  │
└─────────────────────────────────────────┘

### Dashboard Best Practices:
✅ Most important info top-left
✅ KPIs in horizontal row
✅ Primary chart larger than secondary
✅ Related charts adjacent
✅ Consistent spacing (8px grid)
✅ Responsive breakpoints (mobile-first)

❌ Don't overcrowd (max 6 charts)
❌ Don't use 3D effects
❌ Don't mix too many chart types
❌ Don't use decorative elements
```

## ISMS Compliance Mapping

### ISO 27001:2022 Annex A Controls

**A.5.37 - Documented operating procedures**
- Document visualization standards
- Maintain design system documentation

**A.8.16 - Monitoring activities**
- Dashboard KPIs include security metrics
- Risk visualizations support threat detection

### GDPR Considerations

**Article 5 - Principles**
- Data minimization in visualizations
- Avoid displaying unnecessary personal data
- Aggregate where possible

## Hack23 ISMS Policy References

- [Information Security Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Information_Security_Policy.md) - Responsible data display

## References

### Visualization Resources
- [The Visual Display of Quantitative Information](https://www.edwardtufte.com/) - Edward Tufte
- [Information Dashboard Design](https://www.stephen-few.com/) - Stephen Few
- [Chart.js Documentation](https://www.chartjs.org/docs/)

### CIA Documentation
- [DATA_MODEL.md](/DATA_MODEL.md) - Available data for visualization

## Remember

- **Clarity over decoration**: Remove chart junk
- **Color has meaning**: Use semantic colors consistently
- **Context is key**: Always provide axis labels and legends
- **Accessibility first**: Ensure WCAG AA color contrast
- **Tell a story**: Guide users through insights
- **Less is more**: Don't overcrowd dashboards
