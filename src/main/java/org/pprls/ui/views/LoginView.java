package org.pprls.ui.views;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import org.pprls.ui.NavigatorUI;

@Theme("mytheme")
public class LoginView extends VerticalLayout implements View {

    public LoginView() {


        Button buttonManager = new Button("Προϊστάμενος");
        Button buttonEmployee = new Button("Υπάλληλος");
        Button buttonRegistraar = new Button("Πρωτοκολλάριος");

        buttonManager.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                UI.getCurrent().getNavigator().navigateTo(NavigatorUI.MANAGERVIEW);
            }
        });

        buttonEmployee.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                UI.getCurrent().getNavigator().navigateTo(NavigatorUI.EMPLOYEEVIEW);
            }
        });

        buttonRegistraar.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                UI.getCurrent().getNavigator().navigateTo(NavigatorUI.REGISTRAARVIEW);
            }
        });

        this.addComponent(buttonManager);
        this.addComponent(buttonEmployee);
        this.addComponent(buttonRegistraar);

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        Notification.show("Welcome to the Animal Farm");
    }
}
