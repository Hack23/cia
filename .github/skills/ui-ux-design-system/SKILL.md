---
name: ui-ux-design-system
description: Design system management, Vaadin component library patterns, consistent UI/UX, accessibility integration
license: Apache-2.0
---

# UI/UX Design System Skill

## Purpose

This skill defines the design system for the CIA platform, ensuring consistent Vaadin component usage, accessibility compliance, responsive design patterns, and a cohesive user experience for political data visualization. It provides reusable patterns for the web-widgets module.

## When to Use This Skill

Apply this skill when:
- ✅ Creating new Vaadin views or components
- ✅ Modifying existing UI layouts or navigation
- ✅ Implementing data visualization dashboards
- ✅ Adding interactive political data charts
- ✅ Ensuring WCAG 2.1 AA accessibility compliance
- ✅ Designing responsive layouts for mobile devices
- ✅ Reviewing UI pull requests for consistency

Do NOT use for:
- ❌ Backend service logic without UI impact
- ❌ Database schema changes
- ❌ Build or deployment pipeline changes

## Design Principles

1. **Political Neutrality** — UI must not favor any party through color, placement, or emphasis
2. **Data First** — Prioritize clarity of political data over decorative elements
3. **Accessibility** — WCAG 2.1 AA compliance for all components
4. **Consistency** — Reuse design system components, avoid one-off styling
5. **Performance** — Lazy load data, minimize DOM complexity
6. **Responsive** — Support desktop (primary), tablet, and mobile viewports

## Component Library Patterns

### Layout Components

```java
// ✅ Standard page layout pattern
public class PoliticianOverviewView extends VerticalLayout {

    public PoliticianOverviewView() {
        setSizeFull();
        setPadding(true);
        setSpacing(true);

        // Page header with breadcrumb
        add(createPageHeader("Politician Overview"));

        // Content area with responsive grid
        HorizontalLayout content = new HorizontalLayout();
        content.setSizeFull();
        content.add(createFilterPanel(), createDataGrid());
        content.setFlexGrow(1, createDataGrid());
        add(content);
    }
}
```

### Data Grid Pattern

```java
// ✅ Consistent grid configuration
public Grid<PoliticianSummary> createPoliticianGrid() {
    Grid<PoliticianSummary> grid = new Grid<>(PoliticianSummary.class, false);

    // Column configuration with consistent ordering
    grid.addColumn(PoliticianSummary::getFirstName)
        .setHeader("First Name").setSortable(true).setAutoWidth(true);
    grid.addColumn(PoliticianSummary::getLastName)
        .setHeader("Last Name").setSortable(true).setAutoWidth(true);
    grid.addColumn(PoliticianSummary::getParty)
        .setHeader("Party").setSortable(true).setAutoWidth(true);
    grid.addColumn(new NumberRenderer<>(PoliticianSummary::getAttendanceRate,
        "%.1f%%")).setHeader("Attendance").setSortable(true);

    // Lazy loading
    grid.setPageSize(50);
    grid.setMultiSort(true);

    // Accessibility
    grid.getElement().setAttribute("aria-label", "Politician data table");

    return grid;
}
```

### Chart Components

```java
// ✅ Politically neutral color scheme for party charts
public static final Map<String, String> PARTY_COLORS = Map.of(
    "S", "#E8112d",    // Socialdemokraterna
    "M", "#52BDEC",    // Moderaterna
    "SD", "#DDDD00",   // Sverigedemokraterna
    "C", "#009933",    // Centerpartiet
    "V", "#DA291C",    // Vänsterpartiet
    "KD", "#000077",   // Kristdemokraterna
    "L", "#006AB3",    // Liberalerna
    "MP", "#83CF39"    // Miljöpartiet
);

// Always include party label, never rely on color alone (accessibility)
```

### Notification Pattern

```java
// ✅ Consistent notification usage
public class NotificationHelper {

    public static void showSuccess(String message) {
        Notification.show(message, 3000, Notification.Position.BOTTOM_START)
            .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
    }

    public static void showError(String message) {
        Notification.show(message, 5000, Notification.Position.BOTTOM_START)
            .addThemeVariants(NotificationVariant.LUMO_ERROR);
    }

    public static void showInfo(String message) {
        Notification.show(message, 3000, Notification.Position.BOTTOM_START)
            .addThemeVariants(NotificationVariant.LUMO_PRIMARY);
    }
}
```

## Responsive Design

### Breakpoints

| Breakpoint | Width | Layout Adaptation |
|-----------|-------|-------------------|
| Desktop | ≥ 1024px | Full sidebar + content grid |
| Tablet | 768–1023px | Collapsible sidebar, stacked panels |
| Mobile | < 768px | Bottom navigation, single column |

### Responsive Pattern

```java
// ✅ Responsive layout using Vaadin's FormLayout
FormLayout form = new FormLayout();
form.setResponsiveSteps(
    new FormLayout.ResponsiveStep("0", 1),     // Mobile: 1 column
    new FormLayout.ResponsiveStep("768px", 2),  // Tablet: 2 columns
    new FormLayout.ResponsiveStep("1024px", 3)  // Desktop: 3 columns
);
```

## Accessibility Integration

### WCAG 2.1 AA Requirements

```java
// ✅ Accessible component patterns
public class AccessibleDashboard extends VerticalLayout {

    public AccessibleDashboard() {
        // Landmark roles
        getElement().setAttribute("role", "main");
        getElement().setAttribute("aria-label", "Political Dashboard");

        // Heading hierarchy
        H1 title = new H1("Dashboard");
        title.getElement().setAttribute("tabindex", "0");
        add(title);

        // Data tables with proper headers
        Grid<Data> grid = createGrid();
        grid.getElement().setAttribute("aria-label", "Voting records");

        // Charts with text alternatives
        Div chart = createChart();
        chart.getElement().setAttribute("aria-label",
            "Bar chart showing voting attendance by party");
        chart.getElement().setAttribute("role", "img");
    }
}
```

### Accessibility Checklist

- ✅ Color contrast ratio ≥ 4.5:1 for normal text
- ✅ Color contrast ratio ≥ 3:1 for large text
- ✅ All interactive elements keyboard navigable
- ✅ ARIA labels on all custom components
- ✅ Form fields have associated labels
- ✅ Focus indicators visible (never `outline: none`)
- ✅ Skip navigation link for keyboard users
- ✅ Text alternatives for all charts and images
- ✅ Error messages associated with form fields
- ✅ No information conveyed by color alone

## Naming Conventions

### CSS Class Names

```
.cia-[component]-[modifier]

Examples:
.cia-dashboard-header
.cia-politician-grid
.cia-chart-container
.cia-filter-panel--collapsed
.cia-nav-item--active
```

### View Class Names

```
[Entity][Action]View.java

Examples:
PoliticianOverviewView.java
CommitteeDetailView.java
VotingHistoryView.java
PartyComparisonView.java
```

## Design System Governance

### Adding New Components

1. Check if an existing Vaadin component meets the need
2. Check if a CIA design system pattern exists
3. If custom component needed, document in this skill
4. Include accessibility attributes from the start
5. Add responsive breakpoint handling
6. Write Playwright UI tests for the component

### Component Review Criteria

- ✅ Follows existing design system patterns
- ✅ Uses standard CIA color scheme
- ✅ Meets WCAG 2.1 AA accessibility
- ✅ Responsive across all breakpoints
- ✅ Politically neutral presentation
- ✅ Lazy loads data for large datasets
- ✅ Has appropriate ARIA labels

## References

- [Vaadin Design System](https://vaadin.com/docs/latest/ds/overview)
- [WCAG 2.1 Guidelines](https://www.w3.org/TR/WCAG21/)
- [Vaadin Accessibility Guide](https://vaadin.com/docs/latest/accessibility)
- [Swedish Riksdag Party Information](https://www.riksdagen.se/en/parties/)
