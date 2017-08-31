package org.pprls.ui.views;

import com.vaadin.data.Binder;
import com.vaadin.ui.*;
import org.pprls.ui.model.DataSource;
import org.pprls.ui.model.Employee;
import org.pprls.ui.model.Item;
import org.vaadin.alump.ckeditor.CKEditorConfig;
import org.vaadin.alump.ckeditor.CKEditorTextField;

import java.time.LocalDate;

public class CreateNewItem extends Window {

    private Item newItem = new Item();

    public CreateNewItem(){
        setCaption("Ανάθεση εργασίας στη διεύθυνση");
        setModal(true);
        VerticalLayout subContent = new VerticalLayout();
        setContent(subContent);

        // Put some components in it
        Label actionPickLabel = new Label("Ενέργεια ");
        ComboBox<String> actionComboBox =  new ComboBox();
        actionComboBox.setItems(new String[]{"Ενέργεια", "Αρχείο"});
        HorizontalLayout firstLine = new HorizontalLayout(actionPickLabel, actionComboBox);
        Label employeePickLabel = new Label("Διαλεξε υπάλληλο που θα αναλάβει την εργασία");
        ComboBox<Employee> employeeComboBox =  new ComboBox();
        employeeComboBox.setItemCaptionGenerator(p -> p.getLast() + " "+ p.getName());
        employeeComboBox.setItems(DataSource.INSTANCE.getEmployees());
        TextField subject = new TextField();
        subject.setWidth("100%");
        DateField deadLine = new DateField("Όρισε ώς πότε θα πρέπει να την έχει τελειώσει");
        deadLine.setWidth("100%");
        HorizontalLayout secondLine = new HorizontalLayout(employeePickLabel, employeeComboBox);
        CKEditorTextField rtArea;
        // Διαββιβαστικό
        CKEditorConfig config = new CKEditorConfig();
        config.useCompactTags();
        config.disableElementsPath();
        config.setResizeDir(CKEditorConfig.RESIZE_DIR.HORIZONTAL);
        config.disableSpellChecker();
        rtArea = new CKEditorTextField(config);
        rtArea.setSizeFull();
        Button button = new Button("Ανάθεσε");
        button.addClickListener(click -> close());

        subContent.addComponent(firstLine);
        subContent.addComponent(secondLine);
        subContent.addComponent(subject);
        subContent.addComponent(deadLine);
        subContent.addComponent(rtArea);
        subContent.addComponent(button);

        // Center it in the browser window
        center();

        actionComboBox.addSelectionListener(event -> {
            String actionSelection = event.getValue();
            newItem.setAction(actionSelection.toString());
        });

        employeeComboBox.addSelectionListener(event -> {
            Employee employeeSelection = event.getValue();
            newItem.setHolder(employeeSelection.getLast()+" "+employeeSelection.getName());
        });

        //bind Item
        Binder<Item> binder = new Binder(Item.class);
        binder.bind(subject, Item::getSubject, Item::setSubject);
        binder.bind(deadLine, Item::getDeadLine, Item::setDeadLine);
        binder.bind(rtArea, Item::getInstructions, Item::setInstructions);

        binder.setBean(newItem);
    }

    @Override
    public void close() {
        newItem.setAccept(LocalDate.now());
        super.close();
    }

    public Item getItem(){
        return newItem;
    }
}
