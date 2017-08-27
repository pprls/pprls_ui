package org.pprls.ui.views;

import com.vaadin.shared.ui.window.WindowMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.pprls.ui.model.DataSource;
import org.pprls.ui.model.Employee;

public class CreateNewItem extends Window {
    public CreateNewItem(){
        setCaption("Ανάθεση εργασίας στη διεύθυνση");
        setModal(true);
        VerticalLayout subContent = new VerticalLayout();
        setContent(subContent);

        // Put some components in it
        Label employeePickLabel = new Label("Διαλεξε υπάλληλο που θα αναλάβει την εργασία");
        ComboBox<Employee> employee =  new ComboBox();
        employee.setItemCaptionGenerator(Employee::getFullName);
        employee.setItems(DataSource.INSTANCE.getEmployees());
        TextField subject = new TextField();
        subject.setWidth("100%");
        DateField deadline = new DateField("Όρισε ώς πότε θα πρέπει να την έχει τελιώσει");
        deadline.setWidth("100%");
        HorizontalLayout firstLine = new HorizontalLayout(employeePickLabel, employee);
        Button button = new Button("Ανάθεσε");


        subContent.addComponent(firstLine);
        subContent.addComponent(subject);
        subContent.addComponent(deadline);
        subContent.addComponent(button);

        // Center it in the browser window
        center();
    }
}
