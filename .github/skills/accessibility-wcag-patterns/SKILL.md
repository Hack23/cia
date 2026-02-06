---
name: accessibility-wcag-patterns
description: WCAG 2.1 AA compliance, ARIA attributes, keyboard navigation, screen reader optimization for accessible political data platforms
license: Apache-2.0
---

# Accessibility WCAG Patterns

## Purpose

This skill provides guidance for implementing WCAG 2.1 Level AA accessibility standards in the CIA platform, ensuring political transparency data is accessible to all users including those with disabilities. Covers ARIA attributes, keyboard navigation, screen reader optimization, and automated accessibility testing.

## When to Use

### ✅ Use this skill when:
- Designing new UI components
- Implementing forms and interactive elements
- Creating data visualizations and charts
- Building responsive layouts
- Reviewing UI for accessibility compliance
- Fixing accessibility violations
- Writing automated accessibility tests
- Conducting accessibility audits

### ❌ Don't use this skill for:
- General UI design patterns (use vaadin-component-design)
- Performance optimization (use code-quality-checks)
- Security (use input-validation, secure-code-review)
- Backend implementation (use spring-framework-patterns)

## Patterns & Examples

### WCAG 2.1 Compliance Checklist

**Level A (Must Have)**:
- [ ] 1.1.1: Non-text content has text alternatives
- [ ] 2.1.1: All functionality available via keyboard
- [ ] 2.4.1: Bypass blocks (skip navigation)
- [ ] 3.1.1: Language of page identified
- [ ] 4.1.2: Name, role, value for UI components

**Level AA (Target)**:
- [ ] 1.4.3: Contrast ratio at least 4.5:1 for normal text
- [ ] 1.4.5: Images of text avoided (use actual text)
- [ ] 2.4.6: Headings and labels describe topic/purpose
- [ ] 2.4.7: Visible focus indicator
- [ ] 3.2.4: Consistent identification of components

### Vaadin Accessibility Patterns

```java
// Accessible form with proper labels and ARIA
@Route("politician/edit")
public class PoliticianEditView extends VerticalLayout {
    
    public PoliticianEditView() {
        // Page title for screen readers
        getElement().setAttribute("role", "main");
        getElement().setAttribute("aria-label", "Edit Politician Profile");
        
        H1 title = new H1("Edit Politician Profile");
        add(title);
        
        // Accessible form fields
        TextField nameField = new TextField("Full Name");
        nameField.setRequired(true);
        nameField.setErrorMessage("Name is required");
        nameField.setAriaLabel("Politician full name");
        
        TextArea bioField = new TextArea("Biography");
        bioField.setMaxLength(1000);
        bioField.setHelperText("Maximum 1000 characters");
        bioField.setAriaLabel("Politician biography");
        
        // Accessible select with clear labels
        Select<String> partySelect = new Select<>();
        partySelect.setLabel("Political Party");
        partySelect.setItems("Social Democrats", "Moderates", "Sweden Democrats");
        partySelect.setRequiredIndicatorVisible(true);
        
        // Accessible buttons with clear labels
        Button saveButton = new Button("Save Changes");
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        saveButton.setAriaLabel("Save politician profile changes");
        saveButton.setIcon(new Icon(VaadinIcon.CHECK));
        
        Button cancelButton = new Button("Cancel");
        cancelButton.setAriaLabel("Cancel editing and return to profile");
        cancelButton.setIcon(new Icon(VaadinIcon.CLOSE));
        
        // Accessible button group
        HorizontalLayout buttons = new HorizontalLayout(saveButton, cancelButton);
        buttons.getElement().setAttribute("role", "group");
        buttons.getElement().setAttribute("aria-label", "Form actions");
        
        add(nameField, bioField, partySelect, buttons);
    }
}
```

### Accessible Data Table

```java
// Accessible grid with ARIA labels
Grid<Politician> grid = new Grid<>(Politician.class, false);
grid.setAriaLabel("Politicians List");

// Column headers with sort indicators
grid.addColumn(Politician::getName)
    .setHeader("Name")
    .setSortable(true)
    .setAriaLabel("Politician name, sortable");

grid.addColumn(Politician::getParty)
    .setHeader("Party")
    .setSortable(true);

grid.addColumn(politician -> {
    Span badge = new Span(String.format("%.0f", politician.getRiskScore()));
    badge.getElement().getThemeList().add(
        politician.getRiskScore() > 70 ? "badge error" : "badge success"
    );
    // ARIA label for screen readers
    badge.getElement().setAttribute("aria-label", 
        String.format("Risk score %d out of 100", 
            Math.round(politician.getRiskScore())));
    return badge;
})
.setHeader("Risk Score")
.setAriaLabel("Risk assessment score");

// Keyboard navigation
grid.setSelectionMode(Grid.SelectionMode.SINGLE);
grid.addFocusListener(e -> {
    // Announce selection to screen readers
    Notification.show("Use arrow keys to navigate, Enter to select");
});
```

### Color Contrast Compliance

```css
/* WCAG AA Contrast Ratios */
:root {
  /* Text on white background (4.5:1 minimum) */
  --text-primary: #212121;        /* 16.1:1 - Excellent */
  --text-secondary: #616161;      /* 5.9:1 - Good */
  
  /* Text on colored backgrounds */
  --risk-high-bg: #c62828;        /* Red */
  --risk-high-text: #ffffff;      /* 6.3:1 - Pass */
  
  --risk-medium-bg: #f57c00;      /* Orange */
  --risk-medium-text: #000000;    /* 6.7:1 - Pass */
  
  --risk-low-bg: #2e7d32;         /* Green */
  --risk-low-text: #ffffff;       /* 4.5:1 - Pass */
  
  /* Interactive elements (focus indicators) */
  --focus-outline: 3px solid #1976d2;
  --focus-outline-offset: 2px;
}

/* Visible focus indicators */
button:focus,
a:focus,
input:focus,
select:focus,
textarea:focus {
  outline: var(--focus-outline);
  outline-offset: var(--focus-outline-offset);
}

/* Skip navigation link */
.skip-link {
  position: absolute;
  top: -40px;
  left: 0;
  background: #000;
  color: #fff;
  padding: 8px;
  text-decoration: none;
  z-index: 100;
}

.skip-link:focus {
  top: 0;
}
```

### Screen Reader Optimization

```html
<!-- Landmark regions -->
<header role="banner">
  <h1>Citizen Intelligence Agency</h1>
  <nav role="navigation" aria-label="Main navigation">
    <!-- Navigation items -->
  </nav>
</header>

<main role="main" aria-labelledby="page-title">
  <h1 id="page-title">Politician Risk Dashboard</h1>
  
  <!-- Live region for dynamic updates -->
  <div aria-live="polite" aria-atomic="true" class="sr-only">
    <!-- Announcements for screen readers -->
  </div>
  
  <!-- Search form -->
  <form role="search" aria-label="Search politicians">
    <label for="search-input">Search by name or party</label>
    <input 
      id="search-input" 
      type="search" 
      aria-describedby="search-help"
    />
    <span id="search-help" class="helper-text">
      Enter at least 3 characters to search
    </span>
  </form>
  
  <!-- Data visualization -->
  <figure role="img" aria-labelledby="chart-title">
    <h2 id="chart-title">Risk Score Distribution</h2>
    <canvas id="risk-chart" aria-describedby="chart-desc"></canvas>
    <figcaption id="chart-desc">
      Bar chart showing risk score distribution across 349 politicians.
      Highest concentration in 40-60 range.
    </figcaption>
  </figure>
</main>

<footer role="contentinfo">
  <!-- Footer content -->
</footer>
```

### Keyboard Navigation Patterns

```javascript
// Custom keyboard navigation for complex widgets
class AccessibleRiskSlider {
  constructor(element) {
    this.element = element;
    this.value = 50;
    this.min = 0;
    this.max = 100;
    
    // ARIA attributes
    element.setAttribute('role', 'slider');
    element.setAttribute('aria-valuemin', this.min);
    element.setAttribute('aria-valuemax', this.max);
    element.setAttribute('aria-valuenow', this.value);
    element.setAttribute('aria-label', 'Filter by risk score');
    element.setAttribute('tabindex', '0');
    
    // Keyboard event handlers
    element.addEventListener('keydown', this.handleKeyDown.bind(this));
  }
  
  handleKeyDown(event) {
    let newValue = this.value;
    
    switch(event.key) {
      case 'ArrowRight':
      case 'ArrowUp':
        newValue = Math.min(this.value + 1, this.max);
        event.preventDefault();
        break;
      case 'ArrowLeft':
      case 'ArrowDown':
        newValue = Math.max(this.value - 1, this.min);
        event.preventDefault();
        break;
      case 'Home':
        newValue = this.min;
        event.preventDefault();
        break;
      case 'End':
        newValue = this.max;
        event.preventDefault();
        break;
      case 'PageUp':
        newValue = Math.min(this.value + 10, this.max);
        event.preventDefault();
        break;
      case 'PageDown':
        newValue = Math.max(this.value - 10, this.min);
        event.preventDefault();
        break;
    }
    
    if (newValue !== this.value) {
      this.setValue(newValue);
    }
  }
  
  setValue(value) {
    this.value = value;
    this.element.setAttribute('aria-valuenow', value);
    // Update visual representation
    this.updateVisual();
    // Trigger change event
    this.element.dispatchEvent(new CustomEvent('change', { detail: value }));
  }
}
```

## ISMS Compliance Mapping

### ISO 27001:2022 Annex A Controls

**A.7.1 - Physical and environmental security**
- Digital accessibility ensures equal access to information

**A.5.1 - Policies for information security**
- Accessibility policy as part of information security

### GDPR Considerations

**Article 9 - Processing of special categories**
- Accessibility features must not discriminate
- Equal access to personal data for data subjects with disabilities

### Legal Requirements

**EU Web Accessibility Directive**
- Public sector websites must meet WCAG 2.1 Level AA
- Accessibility statements required
- Compliance monitoring and reporting

## Hack23 ISMS Policy References

- [Information Security Policy](https://github.com/Hack23/ISMS-PUBLIC/blob/main/Information_Security_Policy.md) - Inclusive access principles

## References

### Standards & Guidelines
- [WCAG 2.1](https://www.w3.org/WAI/WCAG21/quickref/) - Quick reference
- [ARIA Authoring Practices](https://www.w3.org/WAI/ARIA/apg/) - Design patterns
- [WebAIM](https://webaim.org/) - Accessibility resources

### Testing Tools
- [axe DevTools](https://www.deque.com/axe/devtools/) - Browser extension
- [WAVE](https://wave.webaim.org/) - Web accessibility evaluation
- [Pa11y](https://pa11y.org/) - Automated testing

## Remember

- **Accessibility is not optional**: WCAG 2.1 AA is the baseline
- **Test with real users**: Include users with disabilities in testing
- **Keyboard-first design**: All functionality via keyboard
- **Semantic HTML**: Use proper elements for structure
- **Progressive enhancement**: Core content accessible without JavaScript
- **Automated testing**: Integrate accessibility checks in CI/CD
