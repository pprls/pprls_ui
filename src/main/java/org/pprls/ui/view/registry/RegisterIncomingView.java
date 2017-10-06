package org.pprls.ui.view.registry;

import com.vaadin.annotations.Theme;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Responsive;
import com.vaadin.ui.*;
import org.pprls.ui.event.DashboardEventBus;
import org.pprls.ui.view.ViewTemplate;

@Theme("mytheme")
public class RegisterIncomingView extends ViewTemplate {

    public Component buildContent() {
        dashboardPanels = new CssLayout();
        dashboardPanels.addStyleName("dashboard-panels");
        Responsive.makeResponsive(dashboardPanels);

        dashboardPanels.addComponent(new RegisterIncoming("Νέο εισερχομένο", VaadinIcons.ARROW_CIRCLE_RIGHT));

        DashboardEventBus.register(this);

        return dashboardPanels;
    }
}
