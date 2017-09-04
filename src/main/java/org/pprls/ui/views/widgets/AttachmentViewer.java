package org.pprls.ui.views.widgets;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ClientConnector;
import com.vaadin.server.StreamResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import com.whitestein.vaadin.widgets.wtpdfviewer.WTPdfViewer;
import org.pprls.ui.model.Attachment;
import org.vaadin.alump.ckeditor.CKEditorConfig;
import org.vaadin.alump.ckeditor.CKEditorTextField;

import javax.validation.constraints.AssertTrue;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class AttachmentViewer extends Panel{

    private List<Attachment> attachments;

    private int index = 0;
    private CKEditorTextField rtArea = new CKEditorTextField();
    private WTPdfViewer pdfArea = new WTPdfViewer ();
    private final Button prev = new Button();
    private final Button next = new Button();

    public AttachmentViewer(){
        super("Συνημμένα");

        final CKEditorConfig config = new CKEditorConfig();
        config.useCompactTags();
        config.disableElementsPath();
        config.setResizeDir(CKEditorConfig.RESIZE_DIR.HORIZONTAL);
        config.disableSpellChecker();
        config.setReadOnly(true);
        config.setWidth("100%");
        rtArea.setConfig(config);
        rtArea.setSizeFull();
        rtArea.setVisible(false);

        //PdfViewer
        pdfArea.setVisible(true);
        pdfArea.setSizeFull();

        prev.setIcon(VaadinIcons.ARROW_CIRCLE_LEFT);
        next.setIcon(VaadinIcons.ARROW_CIRCLE_RIGHT);

        HorizontalLayout navLayout = new HorizontalLayout();
        navLayout.setWidth("100%");
        navLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        navLayout.addComponents(prev);
        navLayout.addComponents(next);

        VerticalLayout layout = new VerticalLayout();
        layout.addComponentsAndExpand(rtArea);
        layout.addComponentsAndExpand(pdfArea);
        layout.addComponent(navLayout);

        setContent(layout);
        //this.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        //this.setMargin(false);
        //this.setSizeFull();

        prev.setEnabled(false);
        next.setEnabled(false);
        // Next Prev click listeners
        prev.addClickListener(click -> showAttachment(prev()));
        next.addClickListener(click -> showAttachment(next()));
    }

    private void showAttachment( int i) {
        if(!attachments.isEmpty()) {
            if(attachments.get(i).isSigned()) {
                this.setIcon(new ThemeResource("img/signature.png"));
                this.setDescription(attachments.get(i).getSignaturesInfo());
            }
            else {
                this.setIcon(null);
                this.setDescription(null);
            }

            switch (attachments.get(i).getType()) {
                case PDF:
                    Attachment<URL> attachmentU = attachments.get(i);
                    rtArea.setVisible(false);
                    pdfArea.setVisible(true);
                    try {
                        InputStream dataStream = attachmentU.getValue().openStream();
                        pdfArea.setResource(new StreamResource(new InputStreamSource(dataStream), attachmentU.getValue().getFile()));
                    } catch (IOException e) {
                        Notification.show("Failed to open pdf file : "+attachmentU.getValue().getFile(), e.getLocalizedMessage(), Notification.Type.ERROR_MESSAGE);
                    }
                    break;
                case RTF:
                    Attachment<String> attachmentS = attachments.get(i);
                    pdfArea.setVisible(false);
                    rtArea.setVisible(true);
                    String text = attachmentS.getValue();
                    rtArea.setValue(text);
                    break;
            }
        }
    }

    private int prev() {
        return index = index == 0 ? attachments.size() - 1 : index - 1;
    }

    private int next() {
        return index = index == attachments.size()-1? 0 : index + 1;
    }

    public void setAttachments(List<Attachment> attachments){
        index = 0;
        this.attachments = attachments;
        showAttachment(index);
    }

    public void setEnabled(boolean flag){
        super.setEnabled(flag);
        pdfArea.setEnabled(flag);
        rtArea.setEnabled(flag);
        next.setEnabled(flag);
        prev.setEnabled(flag);
    }
}
