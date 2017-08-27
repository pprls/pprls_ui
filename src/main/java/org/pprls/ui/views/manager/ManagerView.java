package org.pprls.ui.views.manager;

import com.vaadin.annotations.Theme;
import com.vaadin.data.Binder;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.ui.components.grid.HeaderCell;
import com.vaadin.ui.components.grid.HeaderRow;
import org.pprls.ui.model.Item;
import org.pprls.ui.model.DataSource;
import org.pprls.ui.views.CreateNewItem;

import java.net.MalformedURLException;
import java.util.List;

@Theme("mytheme")
public class ManagerView extends VerticalLayout implements View {

    private SingleSelect<Item> itemSelection;

    public ManagerView() throws MalformedURLException {
        setMargin(false);
        setSizeFull();

        final HorizontalLayout mainLayout = new HorizontalLayout();
        mainLayout.setMargin(false);
        mainLayout.setSizeFull();
        this.addComponent(mainLayout);
        final VerticalLayout docsLayout = new VerticalLayout();
        final VerticalLayout tasksLayout = new VerticalLayout();

        docsLayout.setSizeFull();
        tasksLayout.setSizeFull();

        mainLayout.addComponent(docsLayout);
        mainLayout.addComponent(tasksLayout);

        final AssignView assignView = new AssignView();
        docsLayout.addComponentsAndExpand(assignView);

        // tasksLayout
        Button buttonAssignTask = new Button("Ανάθεση εργασίας στη διεύθυνση");
        buttonAssignTask.setWidth("100%");
        Grid<Item> taskGrid = new Grid<>();
        itemSelection = taskGrid.asSingleSelect();
        taskGrid.setSizeFull();
        List<Item> items = DataSource.INSTANCE.getItems();
        ListDataProvider<Item> provider = new ListDataProvider<>(items);
        taskGrid.setDataProvider(provider);
        taskGrid.addColumn(Item::getAction).setCaption("γιά");
        taskGrid.addColumn(Item::getSubject).setCaption("Θέμα");
        HeaderRow filterRow = taskGrid.appendHeaderRow();
        HeaderCell actionCell = filterRow.getCell(taskGrid.getColumns().get(0));
        TextField actionFilter = new TextField();
        actionCell.setComponent(actionFilter);
        HeaderCell subjectCell = filterRow.getCell(taskGrid.getColumns().get(1));
        TextField subjectFilter = new TextField();
        subjectCell.setComponent(subjectFilter);

        Button pendingButton = new Button("Εκκρεμότητες Διεύθυνσης");
        pendingButton.setWidth("100%");
        Button pastButton = new Button("Παρελθόντα Ολοκληρωμένα");
        pastButton.setWidth("100%");
        Button searchCaseButton = new Button("Αναζήτηση Υπόθεσης");
        searchCaseButton.setWidth("100%");
        Button protocolButton = new Button("Πρωτόκολλο");
        protocolButton.setWidth("100%");

        tasksLayout.addComponent(buttonAssignTask);
        tasksLayout.addComponent(taskGrid);
        tasksLayout.addComponent(pendingButton);
        tasksLayout.addComponent(pastButton);
        tasksLayout.addComponent(searchCaseButton);
        tasksLayout.addComponent(protocolButton);

        tasksLayout.setExpandRatio(taskGrid, 1f);

        // Listeners
        actionFilter.addValueChangeListener(valueChangeEvent ->  {
                String value = (String)valueChangeEvent.getValue();
                if(value.trim().isEmpty()){
                    provider.clearFilters();
                }else {
                    provider.setFilter(item -> item.getAction().matches(value));
                }
            });

        buttonAssignTask.addClickListener(click -> {
            // Create a sub-window and set the content
            CreateNewItem createNewItem = new CreateNewItem();
            UI.getCurrent().addWindow(createNewItem);
        });

        // when i change selection on grid  this is what I do
        final Binder<Item> itemBinder = new Binder<>(Item.class);
        itemBinder.bind(assignView.getRtArea(), "instructions");
        itemSelection.addValueChangeListener(event -> {
            itemBinder.setBean(itemSelection.getValue());
            if(!itemSelection.isEmpty()) assignView.setAttachments(itemSelection.getValue().getAttachments()); // the if is there to cover the deselect
        });
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        Notification.show("Welcome to 1.1 Pencil");
    }
}
