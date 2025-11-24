# Visualization Documentation Index

Complete visualization guides for all CIA JSON export types with color-coded Mermaid diagrams, implementation examples, and interactive patterns.

**Last Updated**: 2024-11-24  
**Version**: 1.0.0

## Overview

This directory contains comprehensive visualization documentation for rendering political intelligence data from the CIA JSON export system. Each guide includes:

- 🟦 **Data Structure Diagrams** - JSON schema visualization
- 🟩 **Data Flow Diagrams** - API to UI rendering pipeline  
- 🟨 **Component Architecture** - UI component relationships
- 🟥 **Interaction Flow** - User interaction state machines
- 💻 **Implementation Code** - D3.js, Chart.js, React, Vue.js examples
- 📱 **Responsive Design** - Mobile-first patterns
- ♿ **Accessibility** - WCAG 2.1 AA compliance
- ⚡ **Performance** - Optimization techniques

## Technology Stack

### Visualization Libraries
- **D3.js v7** - Network graphs, force layouts, custom visualizations
- **Chart.js v4** - Standard charts (line, bar, radar, doughnut)
- **Plotly.js** - 3D visualizations, statistical plots

### Frameworks
- **React 18** - Component-based UI
- **Vue.js 3** - Composition API patterns
- **Vanilla JavaScript** - Framework-agnostic examples

### Styling
- **Tailwind CSS** - Utility-first styling
- **CSS Grid & Flexbox** - Responsive layouts
- **CSS Custom Properties** - Theme system

## Visualization Guides

### Core Entity Visualizations (5 guides)

| Guide | Size | Description | Key Visualizations |
|-------|------|-------------|-------------------|
| [Politician Profile](./politician-profile.md) | 16KB | Complete politician dashboard | Profile cards, timelines, network graphs, performance gauges |
| [Party Performance](./party-performance.md) | 14KB | Party analytics dashboard | Electoral trends, coalition maps, momentum trackers |
| [Government Cabinet](./government-cabinet.md) | 13KB | Cabinet visualization | Org charts, coalition composition, minister scorecards |
| [Committee Network](./committee-network.md) | 12KB | Parliamentary committees | Committee hierarchies, membership matrices, productivity charts |
| [Intelligence Dashboard](./intelligence-dashboard.md) | 15KB | Intelligence products | Risk heat maps, influence networks, predictive models |

### Top-10 Ranking Visualizations (10 guides)

| Guide | Engagement | Description | Key Visualizations |
|-------|-----------|-------------|-------------------|
| [Electoral Risk](./top10-electoral-risk.md) | 🔥🔥🔥🔥🔥 | Politicians at risk of losing seats | Risk ladders, seat change maps, factor breakdowns |
| [Ethics Concerns](./top10-ethics-concerns.md) | 🔥🔥🔥🔥🔥 | Politicians under investigation | Investigation timelines, severity matrices, impact radars |
| [Most Influential](./top10-most-influential.md) | 🔥🔥🔥🔥 | Real power players | Power rankings, influence spiders, network positions |
| [Rising Stars](./top10-rising-stars.md) | 🔥🔥🔥🔥 | Young politicians to watch | Momentum trajectories, potential matrices, predictions |
| [Most Absent](./top10-most-absent.md) | 🔥🔥🔥🔥 | Worst attendance records | League tables, cost calculators, absence heatmaps |
| [Party Rebels](./top10-party-rebels.md) | 🔥🔥🔥🔥 | Independent thinkers | Loyalty spectrums, dissent timelines, defection meters |
| [Most Productive](./top10-most-productive.md) | 🔥🔥🔥 | Legislative workhorses | Leaderboards, output timelines, efficiency matrices |
| [Coalition Brokers](./top10-coalition-brokers.md) | 🔥🔥🔥 | Cross-party deal makers | Collaboration networks, bridge scores, co-sponsorship matrices |
| [Media Presence](./top10-media-presence.md) | 🔥🔥🔥🔥 | Darlings vs ghosts | Visibility spectrums, sentiment distributions, coverage treemaps |
| [Most Controversial](./top10-most-controversial.md) | 🔥🔥🔥🔥🔥 | Sweden's most divisive | Polarization indices, controversy timelines, sentiment heatmaps |

## Quick Start

### Installation

```bash
# Install required dependencies
npm install d3 chart.js react-chartjs-2

# Or with yarn
yarn add d3 chart.js react-chartjs-2
```

### Basic Usage

```javascript
// Fetch JSON data
const response = await fetch('https://cdn.cia.se/v1.0.0/politicians/active.json');
const data = await response.json();

// Render with D3.js
import * as d3 from 'd3';
// See individual guides for implementation

// Or use Chart.js
import { Line } from 'react-chartjs-2';
// See individual guides for React examples
```

## Color Coding Reference

### Mermaid Diagram Colors

- 🟦 **Blue (#e1f5ff)** - Data structures and JSON schemas
- 🟩 **Green (#d4f1d4)** - Data flow and processing pipelines
- 🟨 **Yellow (#fff3cd)** - Component architecture and UI structure
- 🟥 **Red (#f8d7da)** - User interactions and state management
- 🟪 **Purple (#e7d5f5)** - Advanced features and integrations

### Party Colors (Swedish Parliament)

```javascript
const partyColors = {
  'S': '#E8112d',   // Social Democrats (Red)
  'M': '#52BDEC',   // Moderates (Blue)
  'SD': '#DDDD00',  // Sweden Democrats (Yellow)
  'C': '#009933',   // Center Party (Green)
  'V': '#DA291C',   // Left Party (Red)
  'KD': '#000077',  // Christian Democrats (Dark Blue)
  'L': '#006AB3',   // Liberals (Blue)
  'MP': '#83CF39'   // Green Party (Light Green)
};
```

### Risk Level Colors

```javascript
const riskColors = {
  low: '#28a745',      // Green
  medium: '#ffc107',   // Yellow
  high: '#fd7e14',     // Orange
  critical: '#dc3545' // Red
};
```

## Common Patterns

### Responsive Container

```javascript
const ResponsiveChart = ({ data }) => {
  const containerRef = useRef(null);
  const [dimensions, setDimensions] = useState({ width: 0, height: 0 });

  useEffect(() => {
    const updateDimensions = () => {
      if (containerRef.current) {
        setDimensions({
          width: containerRef.current.offsetWidth,
          height: containerRef.current.offsetHeight
        });
      }
    };

    updateDimensions();
    window.addEventListener('resize', updateDimensions);
    return () => window.removeEventListener('resize', updateDimensions);
  }, []);

  return (
    <div ref={containerRef} className="chart-container">
      <SVGChart width={dimensions.width} height={dimensions.height} data={data} />
    </div>
  );
};
```

### Tooltip Component

```javascript
const Tooltip = ({ data, x, y, visible }) => {
  if (!visible) return null;

  return (
    <div 
      className="tooltip"
      style={{
        position: 'absolute',
        left: x + 10,
        top: y - 10,
        background: 'white',
        border: '1px solid #ccc',
        padding: '8px',
        borderRadius: '4px',
        pointerEvents: 'none'
      }}
    >
      <h4>{data.name}</h4>
      <p>Party: {data.party}</p>
      <p>Score: {data.score}</p>
    </div>
  );
};
```

## Accessibility Guidelines

### Keyboard Navigation

- **Tab** - Navigate between interactive elements
- **Enter/Space** - Activate buttons and selections
- **Arrow Keys** - Navigate within charts
- **Escape** - Close modals and tooltips

### Screen Reader Support

```javascript
<svg role="img" aria-label="Electoral risk visualization">
  <title>Electoral Risk Rankings for 2026</title>
  <desc>Bar chart showing 10 politicians with highest risk of losing their seats</desc>
  {/* Chart elements */}
</svg>
```

### Color Contrast

All visualizations meet WCAG 2.1 AA standards:
- Text contrast ratio ≥ 4.5:1
- Large text (18pt+) ≥ 3:1
- UI components ≥ 3:1

## Performance Best Practices

### Large Datasets

```javascript
// Use Canvas for 500+ elements
const useCanvas = data.length > 500;

if (useCanvas) {
  renderCanvas(data);
} else {
  renderSVG(data);
}
```

### Debouncing

```javascript
import { debounce } from 'lodash';

const handleFilterChange = debounce((value) => {
  setFilter(value);
  refetchData();
}, 300);
```

### Virtual Scrolling

```javascript
import { FixedSizeList } from 'react-window';

<FixedSizeList
  height={600}
  itemCount={data.length}
  itemSize={50}
  width="100%"
>
  {Row}
</FixedSizeList>
```

## Browser Support

- Chrome 90+
- Firefox 88+
- Safari 14+
- Edge 90+
- Mobile browsers (iOS Safari 14+, Chrome Mobile 90+)

## File Organization

```
visualizations/
├── README.md                          (This file)
├── politician-profile.md
├── party-performance.md
├── government-cabinet.md
├── committee-network.md
├── intelligence-dashboard.md
├── top10-electoral-risk.md
├── top10-ethics-concerns.md
├── top10-most-influential.md
├── top10-rising-stars.md
├── top10-most-absent.md
├── top10-party-rebels.md
├── top10-most-productive.md
├── top10-coalition-brokers.md
├── top10-media-presence.md
└── top10-most-controversial.md
```

## Contributing

When adding new visualization guides:

1. Follow the established structure (see any existing guide)
2. Include all 4 color-coded Mermaid diagrams
3. Provide complete code examples (D3.js + React/Vue)
4. Add accessibility annotations
5. Include performance considerations
6. Test on mobile devices
7. Validate color contrast ratios

## Related Documentation

- [Main README](../README.md) - JSON export system overview
- [Schema Specifications](../schemas/) - JSON data structures
- [Implementation Guide](../IMPLEMENTATION_GUIDE.md) - Backend setup
- [Curated Aggregates](../CURATED_AGGREGATES_SPEC.md) - Top-10 and role collections

## License

Apache 2.0 - See LICENSE file in repository root

## Support

- Issues: https://github.com/Hack23/cia/issues
- Documentation: https://github.com/Hack23/cia/wiki
- Community: https://github.com/Hack23/cia/discussions
