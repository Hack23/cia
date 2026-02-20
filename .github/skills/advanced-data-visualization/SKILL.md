---
name: advanced-data-visualization
description: Advanced chart types, D3.js/Vaadin Charts patterns, political data visualization, time series analysis
license: Apache-2.0
---

# Advanced Data Visualization Skill

## Purpose

Guide the design and implementation of effective data visualizations for the CIA political intelligence platform, covering chart selection, color theory, accessibility, and domain-specific patterns for Swedish political data.

## When to Use

- ✅ Designing dashboards for politician performance metrics
- ✅ Visualizing voting patterns and party alignment trends
- ✅ Creating time series charts for legislative activity
- ✅ Building network graphs for political relationships
- ✅ Displaying financial data and risk assessments

Do NOT use for:
- ❌ Basic form layouts (use vaadin-component-design skill)
- ❌ Static report generation (use data-science-for-intelligence skill)

## Chart Selection Guide

### Political Data Chart Matrix

| Data Type | Recommended Chart | CIA Use Case |
|-----------|------------------|--------------|
| Party vote distribution | Stacked bar / Donut | Riksdag vote breakdown |
| Voting trends over time | Line / Area chart | Politician attendance trends |
| Committee composition | Treemap / Sunburst | Committee member distribution |
| Politician comparison | Radar / Parallel coords | Multi-metric comparison |
| Geographic data | Choropleth map | Regional election results |
| Relationships | Force-directed graph | Political network analysis |
| Financial flows | Sankey diagram | Government budget allocation |
| Risk assessment | Heatmap / Gauge | Politician risk scoring |

## Vaadin Charts Integration

### Basic Chart Component

```java
@Route("politician-dashboard")
public class PoliticianDashboardView extends VerticalLayout {

    private Chart createVotingTrendChart(List<VotingRecord> records) {
        Chart chart = new Chart(ChartType.LINE);
        Configuration conf = chart.getConfiguration();
        conf.setTitle("Voting Participation Over Time");

        XAxis xAxis = new XAxis();
        xAxis.setType(AxisType.DATETIME);
        conf.addxAxis(xAxis);

        YAxis yAxis = new YAxis();
        yAxis.setTitle("Participation %");
        yAxis.setMin(0);
        yAxis.setMax(100);
        conf.addyAxis(yAxis);

        DataSeries series = new DataSeries("Attendance");
        for (VotingRecord record : records) {
            series.add(new DataSeriesItem(
                record.getDate().toInstant(),
                record.getParticipationRate()
            ));
        }
        conf.addSeries(series);

        return chart;
    }
}
```

### Party Color Scheme

```java
public final class SwedishPartyColors {
    // Official Swedish party colors for consistent visualization
    public static final String SOCIALDEMOKRATERNA = "#ED1B34";  // S - Red
    public static final String MODERATERNA = "#52BDEC";         // M - Blue
    public static final String SVERIGEDEMOKRATERNA = "#DDDD00"; // SD - Yellow
    public static final String CENTERPARTIET = "#009933";       // C - Green
    public static final String VANSTERPARTIET = "#DA291C";      // V - Dark Red
    public static final String KRISTDEMOKRATERNA = "#000077";   // KD - Dark Blue
    public static final String LIBERALERNA = "#006AB3";         // L - Light Blue
    public static final String MILJOPARTIET = "#83CF39";        // MP - Light Green

    private SwedishPartyColors() {}
}
```

## Time Series Analysis Visualization

### Trend Detection Patterns

```java
// Annotate significant events on time series
private void addAnnotations(Configuration conf, List<PoliticalEvent> events) {
    PlotBand[] bands = events.stream()
        .filter(e -> e.getSignificance() > 0.7)
        .map(e -> {
            PlotBand band = new PlotBand();
            band.setFrom(e.getStartDate().toEpochMilli());
            band.setTo(e.getEndDate().toEpochMilli());
            band.setColor(new SolidColor(255, 200, 200, 0.3));
            band.setLabel(new Label(e.getDescription()));
            return band;
        })
        .toArray(PlotBand[]::new);

    conf.getxAxis().setPlotBands(bands);
}
```

### Moving Average Overlay

```java
private DataSeries calculateMovingAverage(List<DataPoint> data, int window) {
    DataSeries maSeries = new DataSeries("Moving Avg (" + window + "d)");
    maSeries.setPlotOptions(new PlotOptionsLine());

    for (int i = window - 1; i < data.size(); i++) {
        double sum = 0;
        for (int j = i - window + 1; j <= i; j++) {
            sum += data.get(j).getValue();
        }
        maSeries.add(new DataSeriesItem(
            data.get(i).getTimestamp(), sum / window
        ));
    }
    return maSeries;
}
```

## Accessibility Requirements

### WCAG 2.1 AA Compliance for Charts

1. **Color independence** — never rely solely on color to convey meaning
2. **Text alternatives** — provide data tables alongside charts
3. **Sufficient contrast** — minimum 4.5:1 ratio for text, 3:1 for graphical elements
4. **Keyboard navigation** — ensure chart interactions are keyboard-accessible
5. **Screen reader support** — include ARIA labels and descriptions

```java
chart.getElement().setAttribute("aria-label",
    "Line chart showing voting participation trend for " + politicianName);
chart.getElement().setAttribute("role", "img");
```

## Dashboard Layout Principles

1. **Most important data first** — place key metrics at top-left
2. **Progressive disclosure** — summary → detail on interaction
3. **Consistent time ranges** — align all time series to the same period
4. **Responsive design** — charts should resize for mobile viewing
5. **Performance** — lazy-load charts below the fold, limit data points to 1000

## Security Considerations

- **Sanitize labels** — escape HTML in chart labels from user/API data
- **Data aggregation** — anonymize individual-level data where required by GDPR
- **Rate limit exports** — prevent bulk data extraction via chart export features
