package org.pprls.ui.view;

import com.vaadin.data.Binder;
import com.vaadin.ui.*;
import org.pprls.ui.domain.Employee;
import org.pprls.ui.domain.Item;
import org.pprls.ui.domain.SubjectRepository;
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
        Label employeePickLabel = new Label("Διάλεξε υπάλληλο που θα αναλάβει την εργασία");
        ComboBox<Employee> subjectCombobox =  new ComboBox();
        subjectCombobox.setItemCaptionGenerator(p -> p.getLast() + " "+ p.getName());
        subjectCombobox.setItems(SubjectRepository.INSTANCE.getSubjects());
        TextField subject = new TextField();
        subject.setWidth("100%");
        DateField deadLine = new DateField("Όρισε ώς πότε θα πρέπει να την έχει τελειώσει");
        Label actionPickLabel = new Label("Ενέργεια ");
        ComboBox<String> actionComboBox =  new ComboBox();
        actionComboBox.setItems(new String[]{"Ενέργεια", "Αρχείο"});
        HorizontalLayout actionDate = new HorizontalLayout(actionPickLabel, actionComboBox, deadLine);
        HorizontalLayout secondLine = new HorizontalLayout(employeePickLabel, subjectCombobox);
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

        subContent.addComponent(secondLine);
        subContent.addComponent(subject);
        subContent.addComponent(actionDate);
        subContent.addComponent(rtArea);
        subContent.addComponent(button);

        // Center it in the browser window
        center();

        actionComboBox.addSelectionListener(event -> {
            String actionSelection = event.getValue();
            newItem.setAction(actionSelection.toString());
        });

        subjectCombobox.addSelectionListener(event -> {
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
