package org.pprls.ui.views.manager;

import com.vaadin.data.HasValue;
import com.vaadin.shared.Registration;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.pprls.ui.model.Attachment;
import org.pprls.ui.views.widgets.AttachmentViewer;
import org.pprls.ui.views.widgets.DirectionsViewer;

import java.util.List;

public class ItemDetailsPanel extends CustomComponent implements HasValue {

    private final DirectionsViewer directionsViewer;
    private final AttachmentViewer attachmentsViewer;

    public ItemDetailsPanel(String message) {
        VerticalLayout panelContent = new VerticalLayout();
        setCompositionRoot(panelContent);
        panelContent.setMargin(false);
        directionsViewer = new DirectionsViewer();
        panelContent.addComponentsAndExpand(directionsViewer);
        attachmentsViewer = new AttachmentViewer();
        panelContent.addComponentsAndExpand(attachmentsViewer);
        panelContent.setExpandRatio(directionsViewer, 0.4f);

        final HorizontalLayout buttonLayout  = new HorizontalLayout();
        buttonLayout.setWidth("100%");
        Button buttonDecline = new Button("Απορρίπτω");
        buttonDecline.setWidth("100%");
        Button buttonAssign = new Button("Αναθέτω/ενεργώ");
        buttonAssign.setStyleName(ValoTheme.BUTTON_PRIMARY);
        buttonAssign.setWidth("100%");
        buttonLayout.addComponent(buttonDecline);
        buttonLayout.addComponent(buttonAssign);

        panelContent.addComponent(buttonLayout);


        buttonDecline.addClickListener(clickEvent -> UI.getCurrent().addWindow(new RejectItem()));
    }

    public void setAttachments(List<Attachment> attachments) {
        attachmentsViewer.setAttachments(attachments);
    }

    @Override
    public void setValue(Object o) {
        directionsViewer.setValue(o);
    }

    @Override
    public Object getValue() {
        return directionsViewer.getValue();
    }

    @Override
    public void setRequiredIndicatorVisible(boolean b) {
        directionsViewer.setRequiredIndicatorVisible(b);
    }

    @Override
    public boolean isRequiredIndicatorVisible() {
        return directionsViewer.isRequiredIndicatorVisible();
    }

    @Override
    public void setReadOnly(boolean b) {
        directionsViewer.setReadOnly(b);
    }

    @Override
    public boolean isReadOnly() {
        return directionsViewer.isReadOnly();
    }

    @Override
    public Registration addValueChangeListener(ValueChangeListener valueChangeListener) {
        return directionsViewer.addValueChangeListener(valueChangeListener);
    }
}
