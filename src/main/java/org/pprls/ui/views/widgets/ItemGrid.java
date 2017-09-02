package org.pprls.ui.views.widgets;

import com.vaadin.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.server.SerializablePredicate;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.SingleSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.components.grid.HeaderCell;
import com.vaadin.ui.components.grid.HeaderRow;
import com.vaadin.ui.renderers.HtmlRenderer;
import com.vaadin.ui.themes.ValoTheme;
import org.pprls.ui.model.DataSource;
import org.pprls.ui.model.Item;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class ItemGrid extends Grid<Item> {

    private ListDataProvider<Item> dataProvider;
    private TextField subjectFilter;
    private TextField actionFilter;
    private TextField holderFilter;

    public ItemGrid() {
        addColumn(Item::getAction).setCaption("Ενέργεια").setId("action");
        addColumn(item -> getHolderWithProgress(item)).setId("holder")
                .setCaption("Κάτοχος")
                .setDescriptionGenerator(item -> getDateFrame(item))
                .setRenderer(new HtmlRenderer());
        addColumn(Item::getSubject).setCaption("Θέμα").setId("subject");
        setSizeFull();

        setupFilters();

        readItemsList();
    }

    public SingleSelect<Item> getSelection(){
        return asSingleSelect();
    }

    private void setupFilters(){
        HeaderRow clearfilterRow = prependHeaderRow();
        Button clear = new Button("clear filter");
        clear.setStyleName(ValoTheme.BUTTON_LINK);
        clearfilterRow.getCell("subject").setComponent(clear);
        HeaderRow filterRow = appendHeaderRow();
        HeaderCell actionCell = filterRow.getCell(getColumn("action"));
        actionFilter = new TextField();
        actionCell.setComponent(actionFilter);
        HeaderCell holderCell = filterRow.getCell(getColumn("holder"));
        holderFilter = new TextField();
        holderCell.setComponent(holderFilter);
        HeaderCell subjectCell = filterRow.getCell(getColumn("subject"));
        subjectFilter = new TextField();
        subjectCell.setComponent(subjectFilter);

        clear.addClickListener(click -> {
            actionFilter.clear();
            holderFilter.clear();
            subjectFilter.clear();
            dataProvider.clearFilters();
        });

        actionFilter.addValueChangeListener(event ->  addFilter());
        holderFilter.addValueChangeListener(event ->  addFilter());
        subjectFilter.addValueChangeListener(event ->  addFilter());
    }

    public void addFilter(){
        dataProvider.clearFilters();
        if(!actionFilter.getValue().trim().isEmpty()) dataProvider.addFilter(item -> item.getAction().matches(actionFilter.getValue()));
        if(!holderFilter.getValue().trim().isEmpty()) dataProvider.addFilter(item -> item.getHolder().matches(holderFilter.getValue()));
        if(!subjectFilter.getValue().trim().isEmpty()) dataProvider.addFilter(item -> item.getSubject().matches(subjectFilter.getValue()));
    }

    public void updateItemsList(Item newItem){
        List<Item> items =  DataSource.INSTANCE.save(newItem);
        dataProvider =  new ListDataProvider<>(items); // your List<YourObject>
        // filter list by custom filter
        ConfigurableFilterDataProvider<Item, Void, SerializablePredicate<Item>> filterItemDataProvider = dataProvider.withConfigurableFilter();
        setDataProvider(filterItemDataProvider);
    }

    public void readItemsList(){
        dataProvider =  new ListDataProvider<>(DataSource.INSTANCE.getItems()); // your List<YourObject>
        // filter list by custom filter
        ConfigurableFilterDataProvider<Item, Void, SerializablePredicate<Item>> filterItemDataProvider = dataProvider.withConfigurableFilter();
        setDataProvider(filterItemDataProvider);
    }

    public String getDateFrame(Item item){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.forLanguageTag("el-GR"));
//        return  "<html><p><strong>Ανάθεση</strong> "+ getAccept().format(formatter) +"</p>" +
//                "<p><strong>Καταληκτική</strong> "+ getDeadLine().format(formatter) +"</p></html>";
        String acceptDate = item.getAccept().equals(LocalDate.ofEpochDay(0))?"Εκκρεμεί":item.getAccept().format(formatter);
        return "Ανάθεση "+acceptDate+"\nΚαταληκτική "+item.getDeadLine().format(formatter);
    }

    public String getHolderWithProgress(Item item){
        String result;
        result = "<div class=\"progress\"   data-label=\"" ;
        result += item.getHolder();
        result += "\">  <span class=\"value\"  style=\"background-color: ";
        String color = "#4ca3ff";
        if(LocalDate.now().isEqual(item.getDeadLine()) ) color = "#ff914c";
        if(LocalDate.now().isAfter(item.getDeadLine()) ) color = "#ff4c76";
        result += color+"; width:"+item.getProgress()*100+"%;\"></span>\n</div>";
        return result;
    }
}
