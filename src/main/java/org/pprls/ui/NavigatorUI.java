package org.pprls.ui;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import org.pprls.ui.views.EmployeeView;
import org.pprls.ui.views.LoginView;
import org.pprls.ui.views.ManagerView;

import javax.servlet.annotation.WebServlet;

/**
 * Created by kapostolou on 18/8/2017.
 */
public class NavigatorUI extends UI {
    Navigator navigator;
    public static final String LOGINVIEW = "loginView";
    public static final String EMPLOYEEVIEW = "employeeView";
    public static final String MANAGERVIEW = "managerView";

    @Override
    protected void init(VaadinRequest request) {
        getPage().setTitle("Navigation Example");

        // Create a navigator to control the views
        navigator = new Navigator(this, this);

        // Create and register the views
        navigator.addView(EMPLOYEEVIEW, new EmployeeView());
        navigator.addView(MANAGERVIEW, new ManagerView());
        navigator.addView(LOGINVIEW, new LoginView());

        navigator.navigateTo(LOGINVIEW);
    }

    @WebServlet(urlPatterns = "/*", name = "PprlsUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = NavigatorUI.class, productionMode = false)
    public static class PprlsUIServlet extends VaadinServlet {
    }
}
