package org.pprls.ui.view;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.Page;
import com.vaadin.server.Responsive;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.declarative.Design;
import org.pprls.ui.event.DashboardEvent;
import org.pprls.ui.event.DashboardEventBus;

@SuppressWarnings("serial")
@DesignRoot
public class LoginView extends VerticalLayout {

    Button signin;
    VerticalLayout loginPanel;
    TextField username;
    PasswordField password;


    public LoginView() {

        Design.read(this);

        Responsive.makeResponsive(loginPanel);
        signin.setClickShortcut(KeyCode.ENTER);
        signin.focus();

        signin.addClickListener(click -> DashboardEventBus.post(new DashboardEvent.UserLoginRequestedEvent(username.getValue(), password.getValue())));

        Notification notification = new Notification(
                "Καλως ήρθατε στην εφαρμογή Paperless");
        notification
                .setDescription("<span>Κάνετε χρήση της εφαρμογής χρησιμοποιώντας, τους κωδικούς εισαγωγής στον Η/Υ σας.</span>");
        notification.setHtmlContentAllowed(true);
        notification.setStyleName("tray dark small closable login-help");
        notification.setPosition(Position.BOTTOM_CENTER);
        notification.setDelayMsec(20000);
        notification.show(Page.getCurrent());
    }
}
