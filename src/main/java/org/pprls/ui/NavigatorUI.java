package org.pprls.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import org.apache.log4j.Logger;
import org.pprls.ui.views.EmployeeView;
import org.pprls.ui.views.LoginView;
import org.pprls.ui.views.manager.ManagerView;
import org.pprls.ui.views.registry.RegistraarView;

import javax.servlet.annotation.WebServlet;
import java.net.MalformedURLException;

/**
 * Created by kapostolou on 18/8/2017.
 */
@Theme("mytheme")
public class NavigatorUI extends UI {
    Navigator navigator;
    public static final String LOGINVIEW = "loginView";
    public static final String EMPLOYEEVIEW = "employeeView";
    public static final String MANAGERVIEW = "managerView";
    public static final String REGISTRAARVIEW = "registraarView";

    private final static Logger log =
            Logger.getLogger(NavigatorUI.class.getName());

    @Override
    protected void init(VaadinRequest request) {
        log.debug("UI Started");
        getPage().setTitle("pprls");

        // Create a navigator to control the views
        navigator = new Navigator(this, this);

        // Create and register the views
        navigator.addView(EMPLOYEEVIEW, new EmployeeView());
        try {
            navigator.addView(MANAGERVIEW, new ManagerView());
            navigator.addView(REGISTRAARVIEW, new RegistraarView());
        } catch (MalformedURLException e) {
            Notification.show("Failed to open manager view with exception : "+e.getLocalizedMessage());
        }

        navigator.addView(LOGINVIEW, new LoginView());

        navigator.navigateTo(LOGINVIEW);
    }

    @WebServlet(urlPatterns = "/*", name = "PprlsUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = NavigatorUI.class, productionMode = false)
    public static class PprlsUIServlet extends VaadinServlet {
    }
}
