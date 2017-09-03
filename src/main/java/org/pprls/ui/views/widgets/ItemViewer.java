package org.pprls.ui.views.widgets;

import com.vaadin.ui.VerticalLayout;
import org.pprls.ui.model.Attachment;
import org.vaadin.alump.ckeditor.CKEditorTextField;

import java.util.List;

/**
 * Created by koapost on 26/8/2017.
 */
public class ItemViewer extends VerticalLayout {

    private final DirectionsViewer directionsViewer;
    private final AttachmentViewer attachmentsViewer;

    public ItemViewer(){
        setMargin(false);
        directionsViewer = new DirectionsViewer();
        addComponentsAndExpand(directionsViewer);
        attachmentsViewer = new AttachmentViewer();
        addComponentsAndExpand(attachmentsViewer);
        setExpandRatio(directionsViewer, 0.4f);
    }

    public CKEditorTextField getRtArea(){
        return directionsViewer.getRtArea();
    }

    public void setAttachments(List<Attachment> attachments) {
        attachmentsViewer.setAttachments(attachments);
        if(attachments.isEmpty()) attachmentsViewer.setEnabled(false);
        else attachmentsViewer.setEnabled(true);
    }
}



