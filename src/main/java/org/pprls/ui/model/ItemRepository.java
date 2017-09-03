package org.pprls.ui.model;

import com.vaadin.ui.Notification;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.pprls.ui.model.AttachmentType.PDF;
import static org.pprls.ui.model.AttachmentType.RTF;

public enum ItemRepository {
    INSTANCE;

    private  List<Item> itemList = new ArrayList<Item>();

    private ItemRepository() {
        try {
            Item newItem = new Item("Ενέργεια", "Σέκερης Γεώργιος", LocalDate.parse("2019-01-01"), "Διοικητική πράξη Α\nline 2\nLine 3\nLine 4\nLine 5\nLine 7\nline 8", 0.1, LocalDate.parse("2019-01-01"));
            newItem.setInstructions("<h2>Διοικητική πράξη <mark>Α</mark></h2>Διοικητική πράξη Α<br>line 2<br>Line 3<br>Line 4<br>Line 5<br>Line 7<br>line 8");
            newItem.getAttachments().add(new Attachment<URL>(PDF, new URL("file:///N:/scanner/Color0211.pdf"), true));
            newItem.getAttachments().add(new Attachment<String>(RTF, "Sample <strong>text</strong>", false));
            newItem.getAttachments().add(new Attachment<URL>(PDF, new URL("file:///N:/scanner/Color0213.pdf"), true));
            itemList.add(newItem);
            newItem = new Item("Ενέργεια", "Ουζουνίδης Νικόλαος", LocalDate.parse("2019-01-02"), "Διοικητική πράξη Β", 0.5, LocalDate.now().plusDays(1));
            newItem.setInstructions("<h2>Διοικητική πράξη <mark>Β</mark></h2>");
            newItem.getAttachments().add(new Attachment<URL>(PDF, new URL("file:///N:/scanner/Color0208.pdf"), false));
            newItem.getAttachments().add(new Attachment<String>(RTF, "Sample <strong>text</strong>", true));
            newItem.getAttachments().add(new Attachment<URL>(PDF, new URL("file:///N:/scanner/Color210.pdf"), false));
            itemList.add(newItem);
            newItem = new Item("Ενέργεια", "Λεοντίδης Λέων", LocalDate.parse("2019-01-03"), "Διοικητική πράξη Γ", 0.25, LocalDate.now());
            newItem.setInstructions("<h2>Διοικητική πράξη <mark>Γ</mark></h2>");
            newItem.getAttachments().add(new Attachment<URL>(PDF, new URL("file:///N:/scanner/Color0211.pdf"), true));
            newItem.getAttachments().add(new Attachment<String>(RTF, "Sample <strong>text</strong>", false));
            newItem.getAttachments().add(new Attachment<URL>(PDF, new URL("file:///N:/scanner/Color0213.pdf"), true));
            itemList.add(newItem);
            newItem = new Item("Ενέργεια", "Χριστοδούλου Θεοδόσιος", LocalDate.parse("2019-01-04"), "Διοικητική πράξη Δ", 0.9, LocalDate.now().minusMonths(1));
            newItem.setInstructions("<h2>Διοικητική πράξη <mark>Δ</mark></h2>");
            newItem.getAttachments().add(new Attachment(PDF, new URL("file:///N:/scanner/Color0208.pdf"), false));
            newItem.getAttachments().add(new Attachment(PDF, new URL("file:///N:/scanner/Color0209.pdf"), true));
            newItem.getAttachments().add(new Attachment(PDF, new URL("file:///N:/scanner/Color0210.pdf"), false));
            itemList.add(newItem);
            newItem = new Item("Ενέργεια", "Στανέλλος Σπυρίδων Ιωάννου", LocalDate.parse("2019-01-05"), "Διοικητική πράξη Ε", 0.15, LocalDate.parse("2019-01-05"));
            newItem.setInstructions("<h2>Διοικητική πράξη <mark>Ε</mark></h2>");
            newItem.getAttachments().add(new Attachment(PDF, new URL("file:///N:/scanner/Color0211.pdf"), true));
            newItem.getAttachments().add(new Attachment(PDF, new URL("file:///N:/scanner/Color0212.pdf"), false));
            newItem.getAttachments().add(new Attachment(PDF, new URL("file:///N:/scanner/Color0213.pdf"), true));
            itemList.add(newItem);
            newItem = new Item("Ενέργεια", "Αθανάσιος Τσακάλωφ", LocalDate.parse("2019-01-05"), "Διοικητική πράξη Ε", 0.15, LocalDate.parse("2019-01-05"));
            newItem.setInstructions("<h2>Διοικητική πράξη <mark>Ε</mark></h2>");
            newItem.getAttachments().add(new Attachment(PDF, new URL("file:///N:/scanner/Color0211.pdf"), true));
            newItem.getAttachments().add(new Attachment(PDF, new URL("file:///N:/scanner/Color0212.pdf"), false));
            newItem.getAttachments().add(new Attachment(PDF, new URL("file:///N:/scanner/Color0213.pdf"), true));
            newItem.setProgress(1.0);
            newItem.setPending(false);
            itemList.add(newItem);
        }catch(Exception ex){
            Notification.show("Fail to load Items", ex.getLocalizedMessage(), Notification.Type.ERROR_MESSAGE);
        }


    }

    public List<Item> getItems(boolean pending) {
        List<Item> result = new ArrayList<>();
        for(Item item : itemList) {
            if (item.isPending()==pending) result.add(item);
        }
        return result;
    }

    public List<Item> save(Item newItem){
        itemList.add(newItem);
        return itemList;
    }
}
