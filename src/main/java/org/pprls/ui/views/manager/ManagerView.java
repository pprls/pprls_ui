package org.pprls.ui.views.manager;

import com.vaadin.annotations.Theme;
import com.vaadin.data.Binder;
import com.vaadin.data.Converter;
import com.vaadin.data.Result;
import com.vaadin.data.ValueContext;
import com.vaadin.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.SerializablePredicate;
import com.vaadin.ui.*;
import com.vaadin.ui.components.grid.HeaderCell;
import com.vaadin.ui.components.grid.HeaderRow;
import com.vaadin.ui.renderers.HtmlRenderer;
import com.vaadin.ui.renderers.ProgressBarRenderer;
import org.pprls.ui.model.Attachment;
import org.pprls.ui.model.Item;
import org.pprls.ui.model.DataSource;
import org.pprls.ui.views.CreateNewItem;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

@Theme("mytheme")
public class ManagerView extends VerticalLayout implements View {

    private final Grid<Item> taskGrid = new Grid<>();

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
        taskGrid.addColumn(Item::getAction).setCaption("Ενέργεια");
        taskGrid.addColumn(Item::getAccept).setCaption("Ημερομηνία Ανάθεσης");
        taskGrid.addColumn(item -> item.getHolderWithProgress()).setCaption("Κάτοχος").setRenderer(new HtmlRenderer());
        taskGrid.addColumn(Item::getSubject).setCaption("Θέμα");
        taskGrid.addColumn(Item::getDeadLine).setCaption("Καταληκτική");
        SingleSelect<Item> itemSelection = taskGrid.asSingleSelect();
        taskGrid.setSizeFull();
        readItemsList();
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
//                String value = (String)valueChangeEvent.getValue();
//                if(value.trim().isEmpty()){
//                    taskGrid..clearFilters();
//                }else {
//                    provider.setFilter(item -> item.getAction().matches(value));
//                }
            });

        buttonAssignTask.addClickListener(click -> {
            // Create a sub-window and set the content
            CreateNewItem createNewItem = new CreateNewItem();
            UI.getCurrent().addWindow(createNewItem);
            createNewItem.addCloseListener(event -> updateItemsList(createNewItem.getItem()));
        });

        final Binder<Item> itemBinder = new Binder<>(Item.class);
        itemBinder.forField(assignView.getRtArea())
                .bind("instructions");

        // when I change selection on grid  this is what I do
        itemSelection.addValueChangeListener(event -> {
            itemBinder.setBean(itemSelection.getValue());
            if(!itemSelection.isEmpty()) assignView.setAttachments(itemSelection.getValue().getAttachments()); // the if is there to cover the deselect
            else assignView.setAttachments(new ArrayList<>());
        });
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        Notification.show("Welcome to 1.1 Pencil");
    }

    public void updateItemsList(Item newItem){
            List<Item> items =  DataSource.INSTANCE.save(newItem);
            ListDataProvider<Item> dataProvider =  new ListDataProvider<>(items); // your List<YourObject>
            // filter list by custom filter
            ConfigurableFilterDataProvider<Item, Void, SerializablePredicate<Item>> filterItemDataProvider = dataProvider.withConfigurableFilter();
            taskGrid.setDataProvider(filterItemDataProvider);
    }

    public void readItemsList(){
        List<Item> items =  DataSource.INSTANCE.getItems();
        ListDataProvider<Item> dataProvider =  new ListDataProvider<>(items); // your List<YourObject>
        // filter list by custom filter
        ConfigurableFilterDataProvider<Item, Void, SerializablePredicate<Item>> filterItemDataProvider = dataProvider.withConfigurableFilter();
        taskGrid.setDataProvider(filterItemDataProvider);
    }
}
