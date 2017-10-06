package org.pprls.ui.view.manager;

import com.google.common.eventbus.Subscribe;
import com.vaadin.server.Responsive;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.pprls.ui.event.DashboardEvent;
import org.pprls.ui.event.DashboardEventBus;
import org.pprls.ui.view.ViewTemplate;
import org.pprls.ui.view.common.AttachmentViewer;
import org.vaadin.alump.ckeditor.CKEditorConfig;
import org.vaadin.alump.ckeditor.CKEditorTextField;

public class DoneView extends ViewTemplate {

    private CKEditorTextField rtArea;
    private AttachmentViewer attachmentsViewer;

    public Component buildContent() {
        dashboardPanels = new CssLayout();
        dashboardPanels.addStyleName("dashboard-panels");
        Responsive.makeResponsive(dashboardPanels);

        dashboardPanels.addComponent(buildHeader());
        dashboardPanels.addComponent(buildDirectionsPanel());
        dashboardPanels.addComponent(buildAttachmentsPanel());
        dashboardPanels.addComponent(buildActiveTasksPanel());


        DashboardEventBus.register(this);

        return dashboardPanels;
    }

    private Component buildHeader() {
        HorizontalLayout header = new HorizontalLayout();
        header.addStyleName("viewheader");
        Responsive.makeResponsive(header);

        Label titleLabel = new Label("Εκκρεμότητες");
        titleLabel.setSizeUndefined();
        titleLabel.addStyleName(ValoTheme.LABEL_H1);
        titleLabel.addStyleName(ValoTheme.LABEL_NO_MARGIN);
        header.addComponents(titleLabel, buildToolbar());

        return header;
    }

    private Component buildToolbar() {
        HorizontalLayout toolbar = new HorizontalLayout();
        toolbar.addStyleName("toolbar");

        ComboBox<Object> movieSelect = new ComboBox<>();
        toolbar.addComponent(movieSelect);

        return toolbar;
    }

    public Component buildDirectionsPanel() {
        // Διαβιβαστικό
        CKEditorConfig config = new CKEditorConfig();
        config.useCompactTags();
        config.disableElementsPath();
        config.disableSpellChecker();
        config.setReadOnly(true);
        config.setWidth("100%");
        config.setHeight("100%");
        rtArea = new CKEditorTextField(config);
        rtArea.setSizeFull();
        rtArea.setCaption("Διαβιβαστικο");
        rtArea.setViewWithoutEditor(true);

        Component panel = createContentWrapper(rtArea);
        panel.addStyleName("notes");

        return panel;
    }

    public Component buildAttachmentsPanel() {
        attachmentsViewer = new AttachmentViewer();
        attachmentsViewer.setSizeFull();
        Component panel = createContentWrapper(attachmentsViewer);
        panel.addStyleName("notes");

        return panel;
    }


    public Component buildActiveTasksPanel() {
        ItemRowsPanel itemRowsPanel = new ItemRowsPanel(false);

        Component panel = createContentWrapper(itemRowsPanel);
        panel.removeStyleName("dashboard-panel-slot");
        panel.addStyleName("dashboard-panel-full-slot");
        //panel.setWidth("100% !important");
        return panel;
    }

    @Subscribe
    public void selectionNotification(final DashboardEvent.SelectItemEvent event) {
        if(event.isItemSelected()){
            rtArea.setValue(event.getFirstItem().getInstructions());
            attachmentsViewer.clear();
            attachmentsViewer.setAttachments(event.getFirstItem().getAttachments());
            attachmentsViewer.showAttachment();
        }else{
            rtArea.setValue("");
            attachmentsViewer.clear();
        }
    }
}
