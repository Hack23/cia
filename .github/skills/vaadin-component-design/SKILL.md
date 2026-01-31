---
name: vaadin-component-design
description: Design Vaadin UI components with proper lifecycle, data binding, and responsive layouts for CIA platform
license: Apache-2.0
---

# Vaadin Component Design Skill

## Purpose
Create maintainable, responsive Vaadin UI components with proper lifecycle and data binding.

## When to Use
- ✅ Creating new UI views
- ✅ Designing forms and data grids
- ✅ Implementing user interactions
- ✅ Building responsive layouts

## Component Patterns
```java
@Route("politicians")
@PageTitle("Politicians | CIA")
public class PoliticianView extends VerticalLayout {
    
    private final PoliticianService service;
    private final Grid<Politician> grid = new Grid<>(Politician.class);
    
    public PoliticianView(PoliticianService service) {
        this.service = service;
        setSizeFull();
        configureGrid();
        add(createToolbar(), grid);
        updateList();
    }
    
    private void configureGrid() {
        grid.setSizeFull();
        grid.setColumns("firstName", "lastName", "party", "district");
        grid.addColumn(p -> p.getVotingRecords().size())
            .setHeader("Total Votes");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }
    
    private Component createToolbar() {
        TextField searchField = new TextField();
        searchField.setPlaceholder("Search...");
        searchField.addValueChangeListener(e -> updateList());
        
        Button addButton = new Button("Add Politician");
        addButton.addClickListener(e -> addPolitician());
        
        return new HorizontalLayout(searchField, addButton);
    }
}
```

## Data Binding
```java
public class PoliticianForm extends FormLayout {
    TextField firstName = new TextField("First Name");
    TextField lastName = new TextField("Last Name");
    ComboBox<Party> party = new ComboBox<>("Party");
    
    Binder<Politician> binder = new Binder<>(Politician.class);
    
    public PoliticianForm() {
        binder.forField(firstName)
            .asRequired("First name is required")
            .withValidator(name -> name.length() >= 2, "Name too short")
            .bind(Politician::getFirstName, Politician::setFirstName);
        
        add(firstName, lastName, party);
    }
}
```

## References
- Vaadin: https://vaadin.com/docs
- citizen-intelligence-agency/src/main/java/com/hack23/cia/web/