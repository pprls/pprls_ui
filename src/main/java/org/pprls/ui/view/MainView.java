package org.pprls.ui.view;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.declarative.Design;
import org.pprls.ui.PprlsNavigator;

/*
 * Dashboard MainView is a simple HorizontalLayout that wraps the menu on the
 * left and creates a simple container for the navigator on the right.
 */
@SuppressWarnings("serial")
@DesignRoot
public class MainView extends HorizontalLayout {

    CssLayout content;

    public MainView() {

        addComponent(new DashboardMenu());

        Design.read(this);

        new PprlsNavigator(content);
    }
}
