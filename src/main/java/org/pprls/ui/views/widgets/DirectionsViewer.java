package org.pprls.ui.views.widgets;

import com.vaadin.data.HasValue;
import com.vaadin.shared.Registration;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.alump.ckeditor.CKEditorConfig;
import org.vaadin.alump.ckeditor.CKEditorTextField;

/**
 * Created by koapost on 26/8/2017.
 */
public class DirectionsViewer extends Panel implements HasValue {

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
        //rtArea.setViewWithoutEditor(true);
        layout.addComponent(rtArea);

        setContent(layout);

    }

    @Override
    public void setValue(Object o) {
        rtArea.setValue((String)o);
    }

    @Override
    public Object getValue() {
        return rtArea.getValue();
    }

    @Override
    public void setRequiredIndicatorVisible(boolean b) {
        rtArea.setRequiredIndicatorVisible(b);
    }

    @Override
    public boolean isRequiredIndicatorVisible() {
        return rtArea.isRequiredIndicatorVisible();
    }

    @Override
    public void setReadOnly(boolean b) {
        rtArea.setReadOnly(b);
    }

    @Override
    public boolean isReadOnly() {
        return rtArea.isReadOnly();
    }

    @Override
    public Registration addValueChangeListener(ValueChangeListener valueChangeListener) {
        return rtArea.addValueChangeListener(valueChangeListener);
    }
}



