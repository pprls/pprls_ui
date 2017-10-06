package org.pprls.ui.view.document;

import com.vaadin.server.Responsive;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.pprls.ui.event.DashboardEventBus;
import org.pprls.ui.view.ViewTemplate;
import org.pprls.ui.view.manager.ItemRowsPanel;
import org.pprls.ui.view.registry.SearchConditions;
import org.pprls.ui.view.registry.SearchResults;
import org.pprls.ui.view.common.AttachmentViewer;

public class DocumentView extends ViewTemplate {

    public Component buildContent() {
        dashboardPanels = new CssLayout();
        dashboardPanels.addStyleName("dashboard-panels");
        Responsive.makeResponsive(dashboardPanels);

        //dashboardPanels.addComponent(buildHeader());
        dashboardPanels.addComponent(buildAttachmentViewerPanel());
//        dashboardPanels.addComponent(buildSearchConditionsPanel());
        dashboardPanels.addComponent(buildSearchResultsPanel());


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

    public Component buildAttachmentViewerPanel() {
        AttachmentViewer attachmentViewer = new AttachmentViewer();
        attachmentViewer.setSizeFull();
        Component panel = createContentWrapper(attachmentViewer);
        panel.removeStyleName("dashboard-panel-slot");
        panel.addStyleName("dashboard-panel-vertical-full-slot");

        return panel;
    }

    public Component buildSearchConditionsPanel() {
        SearchConditions conditions = new SearchConditions();
        conditions.setSizeFull();
        Component panel = createContentWrapper(conditions);
        panel.addStyleName("notes");

        return panel;
    }

    public Component buildSearchResultsPanel() {
        SearchResults searchResults = new SearchResults();
        searchResults.setSizeFull();
        Component panel = createContentWrapper(searchResults);
        panel.removeStyleName("dashboard-panel-slot");
        panel.addStyleName("dashboard-panel-vertical-full-slot");

        return panel;
    }

    public Component buildActiveTasksPanel() {
        ItemRowsPanel itemRowsPanel = new ItemRowsPanel(true);

        Component panel = createContentWrapper(itemRowsPanel);
        panel.removeStyleName("dashboard-panel-slot");
        panel.addStyleName("dashboard-panel-full-slot");
        return panel;
    }
}
