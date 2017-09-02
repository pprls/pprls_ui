package org.pprls.ui.views.manager;

import com.vaadin.annotations.Theme;
import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import org.pprls.ui.model.Item;
import org.pprls.ui.views.CreateNewItem;
import org.pprls.ui.views.widgets.ItemGrid;

import java.net.MalformedURLException;
import java.util.ArrayList;

@Theme("mytheme")
public class ManagerView extends VerticalLayout implements View {

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
        ItemGrid itemGrid = new ItemGrid();

        Button pendingButton = new Button("Εκκρεμότητες Διεύθυνσης");
        pendingButton.setWidth("100%");
        Button pastButton = new Button("Παρελθόντα Ολοκληρωμένα");
        pastButton.setWidth("100%");
        Button searchCaseButton = new Button("Αναζήτηση Υπόθεσης");
        searchCaseButton.setWidth("100%");
        Button protocolButton = new Button("Πρωτόκολλο");
        protocolButton.setWidth("100%");

        tasksLayout.addComponent(buttonAssignTask);
        tasksLayout.addComponent(itemGrid);
        tasksLayout.addComponent(pendingButton);
        tasksLayout.addComponent(pastButton);
        tasksLayout.addComponent(searchCaseButton);
        tasksLayout.addComponent(protocolButton);

        tasksLayout.setExpandRatio(itemGrid, 1f);


        buttonAssignTask.addClickListener(click -> {
            // Create a sub-window and set the content
            CreateNewItem createNewItem = new CreateNewItem();
            UI.getCurrent().addWindow(createNewItem);
            createNewItem.addCloseListener(event -> itemGrid.updateItemsList(createNewItem.getItem()));
        });

        final Binder<Item> itemBinder = new Binder<>(Item.class);
        itemBinder.forField(assignView.getRtArea())
                .bind("instructions");

        // when I change selection on grid  this is what I do
        SingleSelect<Item> itemSelection = itemGrid.getSelection();
        itemGrid.getSelection().addValueChangeListener(event -> {
            itemBinder.setBean(itemSelection.getValue());
            if (!itemSelection.isEmpty())
                assignView.setAttachments(itemSelection.getValue().getAttachments()); // the if is there to cover the deselect
            else assignView.setAttachments(new ArrayList<>());
        });
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        Notification.show("Welcome to 1.1 Pencil");
    }

}
