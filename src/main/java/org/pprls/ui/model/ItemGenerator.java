package org.pprls.ui.model;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public enum ItemGenerator {
    INSTANCE;

    @org.jetbrains.annotations.NotNull
    public List<Item> createList() throws MalformedURLException {
        List<Item> list = new ArrayList<Item>();
        Item newItem = new Item("Ενέργεια", "", "1/1/2019", "Διοικητική πράξη Α", "1/1/2019");
        newItem.setInstructions("<h2>Διοικητική πράξη <mark>Α</mark></h2>");
        newItem.getAttachments().add(new Attachment<URL>("pdf",new URL("file:///N:/scanner/Color0211.pdf")));
        newItem.getAttachments().add(new Attachment<String>("rtText","Sample <strong>text</strong>"));
        newItem.getAttachments().add(new Attachment<URL>("pdf",new URL("file:///N:/scanner/Color0213.pdf")));
        list.add(newItem);
        newItem = new Item("Ενέργεια", "", "2/1/2019", "Διοικητική πράξη Β", "1/1/2019");
        newItem.setInstructions("<h2>Διοικητική πράξη <mark>Β</mark></h2>");
        newItem.getAttachments().add(new Attachment<URL>("pdf",new URL("file:///N:/scanner/Color0208.pdf")));
        newItem.getAttachments().add(new Attachment<String>("rtText","Sample <strong>text</strong>"));
        newItem.getAttachments().add(new Attachment<URL>("pdf",new URL("file:///N:/scanner/Color210.pdf")));
        list.add(newItem);
        newItem = new Item("Ενέργεια", "", "3/1/2019", "Διοικητική πράξη Γ", "1/1/2019");
        newItem.setInstructions("<h2>Διοικητική πράξη <mark>Γ</mark></h2>");
        newItem.getAttachments().add(new Attachment<URL>("pdf",new URL("file:///N:/scanner/Color0211.pdf")));
        newItem.getAttachments().add(new Attachment<String>("rtText","Sample <strong>text</strong>"));
        newItem.getAttachments().add(new Attachment<URL>("pdf",new URL("file:///N:/scanner/Color0213.pdf")));
        list.add(newItem);
        newItem = new Item("Ενέργεια", "", "4/1/2019", "Διοικητική πράξη Δ", "1/1/2019");
        newItem.setInstructions("<h2>Διοικητική πράξη <mark>Δ</mark></h2>");
        newItem.getAttachments().add(new Attachment("pdf",new URL("file:///N:/scanner/Color0208.pdf")));
        newItem.getAttachments().add(new Attachment("pdf",new URL("file:///N:/scanner/Color0209.pdf")));
        newItem.getAttachments().add(new Attachment("pdf",new URL("file:///N:/scanner/Color0210.pdf")));
        list.add(newItem);
        newItem = new Item("Ενέργεια", "", "5/1/2019", "Διοικητική πράξη Ε", "1/1/2019");
        newItem.setInstructions("<h2>Διοικητική πράξη <mark>Ε</mark></h2>");
        newItem.getAttachments().add(new Attachment("pdf",new URL("file:///N:/scanner/Color0211.pdf")));
        newItem.getAttachments().add(new Attachment("pdf",new URL("file:///N:/scanner/Color0212.pdf")));
        newItem.getAttachments().add(new Attachment("pdf",new URL("file:///N:/scanner/Color0213.pdf")));
        list.add(newItem);
        return list;
    }
}
