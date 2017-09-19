package org.pprls.ui.views.manager;

import com.vaadin.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.server.SerializablePredicate;
import com.vaadin.ui.*;
import com.vaadin.ui.components.grid.HeaderCell;
import com.vaadin.ui.components.grid.HeaderRow;
import com.vaadin.ui.renderers.HtmlRenderer;
import com.vaadin.ui.themes.ValoTheme;
import org.pprls.ui.model.Item;
import org.pprls.ui.model.ItemRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class ItemRowsPanel extends Panel {

    private Grid<Item> grid = new Grid<>();
    private ListDataProvider<Item> dataProvider;
    private TextField subjectFilter;
    private TextField actionFilter;
    private TextField holderFilter;
    private boolean pending = true;

    public ItemRowsPanel() {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.addComponentsAndExpand(grid);
        setContent(layout);
        grid.addColumn(Item::getAction).setCaption("Ενέργεια").setId("action");
        grid.addColumn(item -> getHolderWithProgress(item)).setId("holder")
                .setCaption("Κάτοχος")
                .setDescriptionGenerator(item -> getDateFrame(item))
                .setRenderer(new HtmlRenderer());
        grid.addColumn(Item::getSubject).setCaption("Θέμα").setId("subject");
        grid.setSizeFull();
        setupFilters();
        readItemsList();

        Button pendingButton = new Button("Εκκρεμότητες Διεύθυνσης");
        pendingButton.setWidth("100%");
        pendingButton.setVisible(!pending);
        Button pastButton = new Button("Παρελθόντα Ολοκληρωμένα");
        pastButton.setWidth("100%");
        pastButton.setVisible(pending);
        layout.addComponent(pendingButton);
        layout.addComponent(pastButton);
        pendingButton.addClickListener(click -> toggle(pendingButton, pastButton));
        pastButton.addClickListener(click -> toggle(pendingButton, pastButton));
    }

    private void toggle(Button pendingButton, Button pastButton) {
        pending = !pending;
        pendingButton.setVisible(!pending);
        pastButton.setVisible(pending);
        readItemsList();
    }

    public SingleSelect<Item> getSelection() {
        return grid.asSingleSelect();
    }

    private void setupFilters() {
        HeaderRow clearfilterRow = grid.prependHeaderRow();
        Button clear = new Button("Εκκαθάριση φίλτρων");
        clear.setStyleName(ValoTheme.BUTTON_LINK);
        clearfilterRow.getCell("subject").setComponent(clear);
        HeaderRow filterRow = grid.appendHeaderRow();
        HeaderCell actionCell = filterRow.getCell(grid.getColumn("action"));
        actionFilter = new TextField();
        actionCell.setComponent(actionFilter);
        HeaderCell holderCell = filterRow.getCell(grid.getColumn("holder"));
        holderFilter = new TextField();
        holderCell.setComponent(holderFilter);
        HeaderCell subjectCell = filterRow.getCell(grid.getColumn("subject"));
        subjectFilter = new TextField();
        subjectCell.setComponent(subjectFilter);

        clear.addClickListener(click -> {
            actionFilter.clear();
            holderFilter.clear();
            subjectFilter.clear();
            dataProvider.clearFilters();
        });

        actionFilter.addValueChangeListener(event -> addFilter());
        holderFilter.addValueChangeListener(event -> addFilter());
        subjectFilter.addValueChangeListener(event -> addFilter());
    }

    public void addFilter() {
        dataProvider.clearFilters();
        if (!actionFilter.getValue().trim().isEmpty())
            dataProvider.addFilter(item -> item.getAction().matches(actionFilter.getValue()));
        if (!holderFilter.getValue().trim().isEmpty())
            dataProvider.addFilter(item -> item.getHolder().matches(holderFilter.getValue()));
        if (!subjectFilter.getValue().trim().isEmpty())
            dataProvider.addFilter(item -> item.getSubject().matches(subjectFilter.getValue()));
    }

    public void updateItemsList(Item newItem) {
        List<Item> items = ItemRepository.INSTANCE.save(newItem);
        dataProvider = new ListDataProvider<>(items); // your List<YourObject>
        // filter list by custom filter
        ConfigurableFilterDataProvider<Item, Void, SerializablePredicate<Item>> filterItemDataProvider = dataProvider.withConfigurableFilter();
        grid.setDataProvider(filterItemDataProvider);
    }

    public void readItemsList() {
        dataProvider = new ListDataProvider<>(ItemRepository.INSTANCE.getItems(pending)); // your List<YourObject>
        // filter list by custom filter
        ConfigurableFilterDataProvider<Item, Void, SerializablePredicate<Item>> filterItemDataProvider = dataProvider.withConfigurableFilter();
        grid.setDataProvider(filterItemDataProvider);
    }

    public String getDateFrame(Item item) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.forLanguageTag("el-GR"));
//        return  "<html><p><strong>Ανάθεση</strong> "+ getAccept().format(formatter) +"</p>" +
//                "<p><strong>Καταληκτική</strong> "+ getDeadLine().format(formatter) +"</p></html>";
        String acceptDate = item.getAccept().equals(LocalDate.ofEpochDay(0)) ? "Εκκρεμεί" : item.getAccept().format(formatter);
        return "Ανάθεση " + acceptDate + "\nΚαταληκτική " + item.getDeadLine().format(formatter);
    }

    public String getHolderWithProgress(Item item) {
        String result;
        result = "<div class=\"progress\"   data-label=\"";
        result += item.getHolder();
        result += "\">  <span class=\"value\"  style=\"background-color: ";
        String color = "#4ca3ff";
        if (LocalDate.now().isEqual(item.getDeadLine())) color = "#ff914c";
        if (LocalDate.now().isAfter(item.getDeadLine())) color = "#ff4c76";
        result += color + "; width:" + item.getProgress() * 100 + "%;\"></span>\n</div>";
        return result;
    }
}
