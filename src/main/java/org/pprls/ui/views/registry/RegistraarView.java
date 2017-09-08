package org.pprls.ui.views.registry;

import com.vaadin.annotations.Theme;
import com.vaadin.data.Binder;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import org.pprls.ui.model.Item;
import org.pprls.ui.views.CreateNewItem;
import org.pprls.ui.views.manager.ItemDetailsPanel;
import org.pprls.ui.views.manager.ItemRowsPanel;
import org.pprls.ui.views.widgets.AttachmentViewer;

import java.net.MalformedURLException;
import java.util.ArrayList;

@Theme("mytheme")
public class RegistraarView extends VerticalLayout implements View {

    public RegistraarView() throws MalformedURLException {
        setMargin(false);
        setSizeFull();

        final HorizontalLayout mainLayout = new HorizontalLayout();
        mainLayout.setMargin(false);
        mainLayout.setSizeFull();
        this.addComponent(mainLayout);
        final VerticalLayout leftLayout = new VerticalLayout();
        final VerticalLayout tasksLayout = new VerticalLayout();

        leftLayout.setSizeFull();
        tasksLayout.setSizeFull();

        mainLayout.addComponent(leftLayout);
        mainLayout.addComponent(tasksLayout);

        final AttachmentViewer attachmentViewer = new AttachmentViewer();
        leftLayout.addComponentsAndExpand(attachmentViewer);
        final RegisterIncoming registerIncoming = new RegisterIncoming("Νέο εισερχομένο", VaadinIcons.ARROW_CIRCLE_RIGHT);
        registerIncoming.setVisible(false);
        leftLayout.addComponentsAndExpand(registerIncoming);

        // tasksLayout
        Button registerIncomingButton = new Button("Εισαγωγή εισερχομένου");
        registerIncomingButton.setIcon(VaadinIcons.ARROW_CIRCLE_RIGHT);
        Button registerOutgoingButton = new Button("Εισαγωγή εξερχομένου");
        registerOutgoingButton.setIcon(VaadinIcons.ARROW_CIRCLE_LEFT_O);
        RegistryPanel registryPanel = new RegistryPanel();

        tasksLayout.addComponentsAndExpand(registryPanel);
        HorizontalLayout buttonsLayout = new HorizontalLayout(registerIncomingButton, registerOutgoingButton);
        tasksLayout.addComponent(buttonsLayout);
        tasksLayout.setComponentAlignment(buttonsLayout, Alignment.MIDDLE_CENTER);

        registerIncomingButton.addClickListener(click -> {
            attachmentViewer.setVisible(false);
            registerIncoming.setVisible(true);
        });
        registerOutgoingButton.addClickListener(click -> {
            attachmentViewer.setVisible(false);
            registerIncoming.setVisible(true);
        });

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        Notification.show("Welcome to 1.1 Pencil");
    }

}