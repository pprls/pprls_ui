package org.pprls.ui.views.widgets;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.pprls.ui.model.Attachment;
import org.vaadin.alump.ckeditor.CKEditorConfig;
import org.vaadin.alump.ckeditor.CKEditorTextField;

import java.util.List;

/**
 * Created by koapost on 26/8/2017.
 */
public class ItemViewer extends VerticalLayout {

    private final CKEditorTextField rtArea;
    private final AttachmentViewer attachmentsViewer;

    public ItemViewer(){
        setMargin(false);
        Label messageTitle = new Label("Διαβιβαστικό");
        messageTitle.setStyleName(ValoTheme.LABEL_H2);
        this.addComponent(messageTitle);

        // Διαββιβαστικό
        CKEditorConfig config = new CKEditorConfig();
        config.useCompactTags();
        config.disableElementsPath();
        config.setResizeDir(CKEditorConfig.RESIZE_DIR.HORIZONTAL);
        config.disableSpellChecker();
        config.setReadOnly(true);
        config.setWidth("100%");
        rtArea = new CKEditorTextField(config);
        rtArea.setViewWithoutEditor(true);
        rtArea.setSizeFull();
        this.addComponent(rtArea);

        attachmentsViewer = new AttachmentViewer();
        this.addComponentsAndExpand(attachmentsViewer);

        this.setExpandRatio(rtArea, 0.3f);
    }

    public CKEditorTextField getRtArea(){
        return rtArea;
    }

    public void setAttachments(List<Attachment> attachments) {
        attachmentsViewer.setAttachments(attachments);
        if(attachments.isEmpty()) attachmentsViewer.setEnabled(false);
        else attachmentsViewer.setEnabled(true);
    }
}



