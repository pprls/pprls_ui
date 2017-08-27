package org.pprls.ui.views;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;

import java.util.ArrayList;

@Theme("mytheme")
public class EmployeeView extends VerticalLayout implements View {

    public EmployeeView() {
        setSizeFull();

        final HorizontalLayout layout2 = new HorizontalLayout();

        this.addComponent(layout2);

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

        buttonAssignTask.addClickListener(new Button.ClickListener(){

            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                // Create a sub-window and set the content
                Window subWindow = new Window("Ανάθεση εργασίας στη διεύθυνση");
                VerticalLayout subContent = new VerticalLayout();
                subWindow.setContent(subContent);

                // Put some components in it
                subContent.addComponent(new Label("Meatball sub"));
                subContent.addComponent(new Button("Awlright"));

                // Center it in the browser window
                subWindow.center();

                // Open it in the UI
                UI.getCurrent().addWindow(subWindow);
            }
        });
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        Notification.show("Welcome to the Animal Farm");
    }
}
