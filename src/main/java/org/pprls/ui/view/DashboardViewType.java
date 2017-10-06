package org.pprls.ui.view;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.server.Resource;
import org.pprls.ui.view.dashboard.DashboardView;
import org.pprls.ui.view.document.DocumentView;
import org.pprls.ui.view.manager.DoneView;
import org.pprls.ui.view.manager.PendingView;
import org.pprls.ui.view.registry.RegisterIncomingView;
import org.pprls.ui.view.registry.RegistraarView;

public enum DashboardViewType {
    DASHBOARD("dashboard", "Πίνακας εποπτείας", DashboardView.class, VaadinIcons.OFFICE, true),
    ACTIVETASKS("activeTasks", "Εκκρεμότητες", PendingView.class, VaadinIcons.TASKS, false),
    DONETASKS("doneTasks", "Ολοκληρωμένα", DoneView.class, VaadinIcons.LEVEL_DOWN, false),
    REGISTRY("registry", "Πρωτόκολλο", RegistraarView.class, VaadinIcons.BOOK, false),
    INCOMING("incoming", "Εισερχόμενο", RegisterIncomingView.class, VaadinIcons.BOOK, false),
    OUTGOING("document", "Σύνταξη εγγράφου (εξερχόμενο)", DocumentView.class, VaadinIcons.FILE_TEXT, false);

    private final String viewName, viewUiName;
    private final Class<? extends View> viewClass;
    private final Resource icon;
    private final boolean stateful;

    private DashboardViewType(final String viewName, final String viewUiName,
            final Class<? extends View> viewClass, final Resource icon,
            final boolean stateful) {
        this.viewName = viewName;
        this.viewUiName = viewUiName;
        this.viewClass = viewClass;
        this.icon = icon;
        this.stateful = stateful;
    }

    public boolean isStateful() {
        return stateful;
    }

    public String getViewName() {
        return viewName;
    }

    public Class<? extends View> getViewClass() {
        return viewClass;
    }

    public Resource getIcon() {
        return icon;
    }

    public static DashboardViewType getByViewName(final String viewName) {
        DashboardViewType result = null;
        for (DashboardViewType viewType : values()) {
            if (viewType.getViewName().equals(viewName)) {
                result = viewType;
                break;
            }
        }
        return result;
    }

    public String getViewUiName() {
        return viewUiName;
    }
}
