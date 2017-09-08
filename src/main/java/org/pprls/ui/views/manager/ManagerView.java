package org.pprls.ui.views.manager;

import com.vaadin.annotations.Theme;
import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import org.pprls.ui.model.Item;
import org.pprls.ui.views.CreateNewItem;
import org.pprls.ui.views.registry.RegistryPanel;

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

        final ItemDetailsPanel itemDetailsPanel = new ItemDetailsPanel("Item Details");
        itemDetailsPanel.setVisible(true);
        docsLayout.addComponentsAndExpand(itemDetailsPanel);
        final RegistryPanel registryPanel = new RegistryPanel();
        registryPanel.setVisible(false);
        docsLayout.addComponentsAndExpand(registryPanel);

        // tasksLayout
        Button buttonAssignTask = new Button("Ανάθεση εργασίας στη διεύθυνση");
        buttonAssignTask.setWidth("100%");
        ItemRowsPanel itemRowsPanel = new ItemRowsPanel();
        Button searchCaseButton = new Button("Αναζήτηση Υπόθεσης");
        searchCaseButton.setWidth("100%");
        Button protocolButton = new Button("Πρωτόκολλο");
        protocolButton.setWidth("100%");

        tasksLayout.addComponent(buttonAssignTask);
        tasksLayout.addComponentsAndExpand(itemRowsPanel);
        tasksLayout.addComponent(searchCaseButton);
        tasksLayout.addComponent(protocolButton);

        buttonAssignTask.addClickListener(click -> {
            // Create a sub-window and set the content
            CreateNewItem createNewItem = new CreateNewItem();
            UI.getCurrent().addWindow(createNewItem);
            createNewItem.addCloseListener(event -> itemRowsPanel.updateItemsList(createNewItem.getItem()));
        });

        final Binder<Item> itemBinder = new Binder<>(Item.class);
        itemBinder.forField(itemDetailsPanel)
                .bind("instructions");

        // when I change selection on grid  this is what I do
        SingleSelect<Item> itemSelection = itemRowsPanel.getSelection();
        itemRowsPanel.getSelection().addValueChangeListener(event -> {
            itemDetailsPanel.setVisible(true);
            registryPanel.setVisible(false);
            itemBinder.setBean(itemSelection.getValue());
            if (!itemSelection.isEmpty())
                itemDetailsPanel.setAttachments(itemSelection.getValue().getAttachments()); // the if is there to cover the deselect
            else itemDetailsPanel.setAttachments(new ArrayList<>());
        });

        protocolButton.addClickListener(click -> {
            itemDetailsPanel.setVisible(false);
            registryPanel.setVisible(true);
        });
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        Notification.show("Welcome to 1.1 Pencil");
    }

}
