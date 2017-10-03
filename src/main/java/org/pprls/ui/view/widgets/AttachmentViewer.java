package org.pprls.ui.view.widgets;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.StreamResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.*;
import com.whitestein.vaadin.widgets.wtpdfviewer.WTPdfViewer;
import org.pprls.ui.domain.Attachment;
import org.vaadin.alump.ckeditor.CKEditorConfig;
import org.vaadin.alump.ckeditor.CKEditorTextField;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AttachmentViewer extends Panel{

    private List<Attachment> attachments = new ArrayList<>();

    private int index = 0;
    private CKEditorTextField rtArea = new CKEditorTextField();
    private WTPdfViewer pdfArea = new WTPdfViewer ();
    private final Button prev = new Button();
    private final Button next = new Button();

    public AttachmentViewer(){
        super("Συνημμενα");

        final CKEditorConfig config = new CKEditorConfig();
        config.useCompactTags();
        config.disableElementsPath();
        config.setResizeDir(CKEditorConfig.RESIZE_DIR.HORIZONTAL);
        config.disableSpellChecker();
        config.setReadOnly(true);
        config.setWidth("100%");
        rtArea.setConfig(config);
        rtArea.setSizeFull();
        rtArea.setViewWithoutEditor(true);

        //PdfViewer
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

        hide(true);
        // Next Prev click listeners
        prev.addClickListener(click -> showAttachment(prev()));
        next.addClickListener(click -> showAttachment(next()));
    }

    public void showAttachment() {
        hide(false);
        if(attachments.size()>0){
            prev.setEnabled(true);
            next.setEnabled(true);
        }else{
            prev.setEnabled(false);
            next.setEnabled(false);
        }
        showAttachment(0);
    }

    private void showAttachment( int i) {
        if(i<attachments.size()) {
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
                        InputStream emptyStream = new ByteArrayInputStream(new String("").getBytes());
                        pdfArea.setResource(new StreamResource(new InputStreamSource(emptyStream), "empty"));
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
        this.attachments.clear();
        this.attachments.addAll(attachments);
    }

    public List<Attachment> getAttachments(){
        return this.attachments;
    }

    public void hide(boolean flag){
        rtArea.setVisible(!flag);
        pdfArea.setVisible(!flag);
        prev.setVisible(!flag);
        next.setVisible(!flag);
    }

    public void clear(){
        rtArea.setValue("");
        InputStream emptyStream = new ByteArrayInputStream(new String("").getBytes());
        pdfArea.setResource(new StreamResource(new InputStreamSource(emptyStream), "empty"));
        this.setIcon(null);
        this.setDescription(null);
        this.attachments.clear();
        hide(true);
    }
}
