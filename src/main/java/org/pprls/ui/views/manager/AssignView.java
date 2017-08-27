package org.pprls.ui.views.manager;

import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.pprls.ui.model.Attachment;
import org.pprls.ui.views.widgets.ItemViewer;
import org.vaadin.alump.ckeditor.CKEditorTextField;

import java.util.List;

public class AssignView extends VerticalLayout {

    private final ItemViewer itemViewer;

    public AssignView() {
        itemViewer = new ItemViewer();
        this.addComponentsAndExpand(itemViewer);

        final HorizontalLayout buttonLayout  = new HorizontalLayout();
        buttonLayout.setWidth("100%");
        Button buttonDecline = new Button("Απορρίπτω");
        buttonDecline.setWidth("100%");
        Button buttonAssign = new Button("Αναθέτω/ενεργώ");
        buttonAssign.setStyleName(ValoTheme.BUTTON_PRIMARY);
        buttonAssign.setWidth("100%");
        buttonLayout.addComponent(buttonDecline);
        buttonLayout.addComponent(buttonAssign);

        this.addComponent(buttonLayout);
    }

    public CKEditorTextField getRtArea() {
        return itemViewer.getRtArea();
    }

    public void setAttachments(List<Attachment> attachments) {
        itemViewer.setAttachments(attachments);
    }
}
