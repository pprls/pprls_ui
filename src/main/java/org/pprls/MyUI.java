package org.pprls;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

import java.util.ArrayList;

/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();

        final HorizontalLayout layout2 = new HorizontalLayout();

        layout.addComponent(layout2);

        final VerticalLayout layout3 = new VerticalLayout();

        final VerticalLayout layout4 = new VerticalLayout();

        layout2.addComponent(layout3);
        layout2.addComponent(layout4);

        final TextArea textArea = new TextArea();

        final Panel panel = new Panel();

        final HorizontalLayout layout5  = new HorizontalLayout();

        layout3.addComponent(textArea);
        layout3.addComponent(panel);
        layout3.addComponent(layout5);

        Button buttonDecline = new Button("Απορρίπτω");
        Button buttonAssign = new Button("Αναθέτω/ενεργώ");

        layout5.addComponent(buttonDecline);
        layout5.addComponent(buttonAssign);

        Button buttonAssignTask = new Button("Ανάθεση εργασίας στη διεύθυνση");

        HorizontalLayout layout6 = new HorizontalLayout();

        TextField textField = new TextField();
        Button buttonFilter = new Button("Φιλτράρισμα");

        layout6.addComponent(textField);
        layout6.addComponent(buttonFilter);
        Grid<String> grid = new Grid<>();
        grid.setItems(new ArrayList<>());

        Button pendingButton = new Button("Εκκρεμότητες Διεύθυνσης");
        Button pastButton = new Button("Παρελθόντα Ολοκληρωμένα");
        Button searchCaseButton = new Button("Αναζήτηση Υπόθεσης");
        Button protocolButton = new Button("Πρωτόκολλο");

        layout4.addComponent(buttonAssignTask);
        layout4.addComponent(layout6);
        layout4.addComponent(grid);
        layout4.addComponent(pendingButton);
        layout4.addComponent(pastButton);
        layout4.addComponent(searchCaseButton);
        layout4.addComponent(protocolButton);



        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
