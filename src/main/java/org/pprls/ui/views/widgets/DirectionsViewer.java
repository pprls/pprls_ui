package org.pprls.ui.views.widgets;

import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import org.pprls.ui.model.Attachment;
import org.vaadin.alump.ckeditor.CKEditorConfig;
import org.vaadin.alump.ckeditor.CKEditorTextField;

import java.util.List;

/**
 * Created by koapost on 26/8/2017.
 */
public class DirectionsViewer extends Panel {

    private final CKEditorTextField rtArea;

    public DirectionsViewer(){
        super("Διαβιβαστικό");

        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setMargin(false);

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
        layout.addComponent(rtArea);

        setContent(layout);

    }

    public CKEditorTextField getRtArea(){
        return rtArea;
    }

}



