package org.pprls.ui.model;

import com.vaadin.ui.Notification;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.pprls.ui.model.AttachmentType.PDF;
import static org.pprls.ui.model.AttachmentType.RTF;

public enum DataSource {
    INSTANCE;

    private  Item item;
    private  List<Item> itemList = new ArrayList<Item>();
    private  List<Employee> employeeslist = new ArrayList();

    private DataSource() {
        try {
            Item newItem = new Item("Ενέργεια", "Σέκερης Γεώργιος", LocalDate.parse("2019-01-01"), "Διοικητική πράξη Α", 0.1, LocalDate.parse("2019-01-01"));
            newItem.setInstructions("<h2>Διοικητική πράξη <mark>Α</mark></h2>");
            newItem.getAttachments().add(new Attachment<URL>(PDF, new URL("file:///N:/scanner/Color0211.pdf")));
            newItem.getAttachments().add(new Attachment<String>(RTF, "Sample <strong>text</strong>"));
            newItem.getAttachments().add(new Attachment<URL>(PDF, new URL("file:///N:/scanner/Color0213.pdf")));
            itemList.add(newItem);
            newItem = new Item("Ενέργεια", "Ουζουνίδης Νικόλαος", LocalDate.parse("2019-01-02"), "Διοικητική πράξη Β", 0.5, LocalDate.now().plusDays(1));
            newItem.setInstructions("<h2>Διοικητική πράξη <mark>Β</mark></h2>");
            newItem.getAttachments().add(new Attachment<URL>(PDF, new URL("file:///N:/scanner/Color0208.pdf")));
            newItem.getAttachments().add(new Attachment<String>(RTF, "Sample <strong>text</strong>"));
            newItem.getAttachments().add(new Attachment<URL>(PDF, new URL("file:///N:/scanner/Color210.pdf")));
            itemList.add(newItem);
            newItem = new Item("Ενέργεια", "Λεοντίδης Λέων", LocalDate.parse("2019-01-03"), "Διοικητική πράξη Γ", 0.25, LocalDate.now());
            newItem.setInstructions("<h2>Διοικητική πράξη <mark>Γ</mark></h2>");
            newItem.getAttachments().add(new Attachment<URL>(PDF, new URL("file:///N:/scanner/Color0211.pdf")));
            newItem.getAttachments().add(new Attachment<String>(RTF, "Sample <strong>text</strong>"));
            newItem.getAttachments().add(new Attachment<URL>(PDF, new URL("file:///N:/scanner/Color0213.pdf")));
            itemList.add(newItem);
            newItem = new Item("Ενέργεια", "Χριστοδούλου Θεοδόσιος", LocalDate.parse("2019-01-04"), "Διοικητική πράξη Δ", 0.9, LocalDate.now().minusMonths(1));
            newItem.setInstructions("<h2>Διοικητική πράξη <mark>Δ</mark></h2>");
            newItem.getAttachments().add(new Attachment(PDF, new URL("file:///N:/scanner/Color0208.pdf")));
            newItem.getAttachments().add(new Attachment(PDF, new URL("file:///N:/scanner/Color0209.pdf")));
            newItem.getAttachments().add(new Attachment(PDF, new URL("file:///N:/scanner/Color0210.pdf")));
            itemList.add(newItem);
            newItem = new Item("Ενέργεια", "Στανέλλος Σπυρίδων Ιωάννου", LocalDate.parse("2019-01-05"), "Διοικητική πράξη Ε", 0.15, LocalDate.parse("2019-01-05"));
            newItem.setInstructions("<h2>Διοικητική πράξη <mark>Ε</mark></h2>");
            newItem.getAttachments().add(new Attachment(PDF, new URL("file:///N:/scanner/Color0211.pdf")));
            newItem.getAttachments().add(new Attachment(PDF, new URL("file:///N:/scanner/Color0212.pdf")));
            newItem.getAttachments().add(new Attachment(PDF, new URL("file:///N:/scanner/Color0213.pdf")));
            itemList.add(newItem);
        }catch(Exception ex){
            Notification.show("Fail to load Items", ex.getLocalizedMessage(), Notification.Type.ERROR_MESSAGE);
        }

        employeeslist.add(new Employee("Αποστόλου", "Κωνσταντίνος", "Νικήτας"));
        employeeslist.add(new Employee("Αποστόλου", "Ιουλία", "Κωμσταντίνου"));
        employeeslist.add(new Employee("Αποστόλου", "Μανώλης", "Κωνστντίνου"));
        employeeslist.add(new Employee("Παπαμιχαήλ","Αννέτα","Εμμανουήλ"));

    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<Item> getItems() {
        return itemList;
    }

    public List<Item> save(Item newItem){
        itemList.add(newItem);
        return itemList;
    }

    public List<Employee> getEmployees(){
        return employeeslist;
    }
}
