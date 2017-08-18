package org.pprls.ui.views;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import org.pprls.ui.model.Item;

import java.util.Arrays;
import java.util.List;

@Theme("mytheme")
public class ManagerView extends VerticalLayout implements View {

    public ManagerView() {
        setSizeFull();

        final HorizontalLayout horizLayout = new HorizontalLayout();

        this.addComponent(horizLayout);

        final VerticalLayout verLayout = new VerticalLayout();

        final VerticalLayout layout4 = new VerticalLayout();

        horizLayout.addComponent(verLayout);
        horizLayout.addComponent(layout4);

        final RichTextArea rtarea = new RichTextArea();
        final TextArea pdfarea = new TextArea();

        final HorizontalLayout buttonLayout  = new HorizontalLayout();

        verLayout.addComponent(rtarea);
        verLayout.addComponent(pdfarea);

        verLayout.addComponent(buttonLayout);
        Button buttonDecline = new Button("Απορρίπτω");
        Button buttonAssign = new Button("Αναθέτω/ενεργώ");
        buttonLayout.addComponent(buttonDecline);
        buttonLayout.addComponent(buttonAssign);



        Button buttonAssignTask = new Button("Ανάθεση εργασίας στη διεύθυνση");

        HorizontalLayout layout6 = new HorizontalLayout();

        TextField textField = new TextField();
        Button buttonFilter = new Button("Φιλτράρισμα");

        layout6.addComponent(textField);
        layout6.addComponent(buttonFilter);
        Grid<Item> grid = new Grid<>();
        List<Item> items = Arrays.asList(
                new Item("Ενέργεια", "", "1/1/2019", "Διοικητική πράξη", "1/1/2019"));
        grid.setItems(items);
        grid.addColumn(Item::getAccept).setCaption("γιά");
        grid.addColumn(Item::getSubject).setCaption("Θέμα");

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
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        Notification.show("Welcome to 1.1 Pencil");
    }
}
