package org.pprls.ui.view.registry;

import com.vaadin.addon.onoffswitch.OnOffSwitch;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.contextmenu.GridContextMenu;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;
import com.vaadin.ui.components.grid.HeaderCell;
import com.vaadin.ui.components.grid.HeaderRow;
import com.vaadin.ui.declarative.Design;
import com.vaadin.ui.renderers.HtmlRenderer;
import com.vaadin.ui.themes.ValoTheme;
import org.pprls.ui.domain.Direction;
import org.pprls.ui.domain.RegistryRecord;
import org.pprls.ui.domain.RegistryRepository;

import java.time.Year;

@DesignRoot
public class SearchResults extends Panel {

    private ListDataProvider<RegistryRecord> dataProvider;
    Grid<RegistryRecord> results;
    private TextField registryNumberFilter;
    private DateField registryDateFilter;
    private DateField yearFilter;
    private TextField descriptionFilter;
    private TextField recipientFilter;
    private TextField correspondentFilter;

    public SearchResults() {
        super("ΕΓΓΡΑΦΕΣ ΠΡΩΤΟΚΟΛΛΟΥ");

        Design.read(this);

        results.addColumn(p -> VaadinIcons.valueOf(p.getDirection().toString()).getHtml(), new HtmlRenderer()).setId("direction");
        results.addColumn( p -> (p.getAttachments().size()>0?VaadinIcons.FILE_TEXT:VaadinIcons.BAN).getHtml(), new HtmlRenderer()).setId("attachments");
        results.setColumnOrder("direction", "attachments", "registryNumber", "registryDate", "year", "description", "correspondent", "recipient");
        results.setItems(RegistryRepository.INSTANCE.getRegistryRecords());
        GridContextMenu<RegistryRecord> contextMenu = new GridContextMenu<>(results);
        contextMenu.addGridHeaderContextMenuListener(event -> {
            contextMenu.removeItems();
            contextMenu.addItem("Εμφάνιση όλων", VaadinIcons.EYE, selectedMenuItem -> {
                for (Grid.Column col : results.getColumns()) {
                    col.setHidden(false);
                }
            });
            contextMenu.addItem("Απόκρυψη", VaadinIcons.EYE_SLASH, selectedMenuItem -> {
                event.getColumn().setHidden(true);
            });
        });
        setupFilters();
    }

    private void setupFilters() {
        HeaderRow clearfilterRow = results.prependHeaderRow();
        Button clear = new Button("Εκκαθάριση φίλτρων");
        clear.setStyleName(ValoTheme.BUTTON_LINK);
        clearfilterRow.getCell("recipient").setComponent(clear);

        OnOffSwitch onOffSwitch = new OnOffSwitch(false);
        HeaderCell joinedCell = clearfilterRow.join(clearfilterRow.getCell("direction"), clearfilterRow.getCell("attachments"));
        joinedCell.setComponent(onOffSwitch);

        HeaderRow filterRow = results.getHeaderRow(0);
        ComboBox directionFilter = new ComboBox();
        directionFilter.setDescription("Ολοι οι τύποι");
        directionFilter.setTextInputAllowed(false);
        directionFilter.setWidth("100px");
        DirectionOptions[] directionOptions = {DirectionOptions.INCOMING, DirectionOptions.OUTGOING};
        directionFilter.setItems(directionOptions);
        directionFilter.setItemCaptionGenerator(o -> ((DirectionOptions)o).getText());
        directionFilter.setItemIconGenerator(o -> ((DirectionOptions)o).getIcon());
        directionFilter.addSelectionListener(event -> addFilter());
        HeaderCell directionCell = filterRow.getCell(results.getColumn("registryNumber"));
        directionCell.setComponent(directionFilter);

        ComboBox attachmentsFilter = new ComboBox();
        attachmentsFilter.setDescription("Ανεξαρτήτως ψηφιοποίησησης");
        attachmentsFilter.setTextInputAllowed(false);
        attachmentsFilter.setWidth("100px");
        DigitisationOptions[] attachmentOptions = {DigitisationOptions.WITH, DigitisationOptions.WITHOUT};
        attachmentsFilter.setItems(attachmentOptions);
        attachmentsFilter.setItemCaptionGenerator(o -> ((DigitisationOptions)o).getText());
        attachmentsFilter.setItemIconGenerator(o -> ((DigitisationOptions)o).getIcon());
        attachmentsFilter.addSelectionListener(event -> addFilter());
        HeaderCell holderCell = filterRow.getCell(results.getColumn("registryDate"));
        holderCell.setComponent(attachmentsFilter);

        clear.addClickListener(click -> {
            directionFilter.clear();
            attachmentsFilter.clear();
            registryNumberFilter.clear();
            registryDateFilter.clear();
            yearFilter.clear();
            correspondentFilter.clear();
            recipientFilter.clear();

            dataProvider.clearFilters();
        });
    }

    public void addFilter() {
        dataProvider.clearFilters();
        if (!registryNumberFilter.getValue().trim().isEmpty())
            dataProvider.addFilter(registryRecord -> registryRecord.getRegistryNumber()==Long.parseLong(registryNumberFilter.getValue()));
        if (yearFilter.getValue()!=null)
            dataProvider.addFilter(registryRecord -> 0==registryRecord.getYear().compareTo(Year.of(yearFilter.getValue().getYear())));
        if (!descriptionFilter.getValue().trim().isEmpty())
            dataProvider.addFilter(registryRecord -> registryRecord.getDescription().matches(descriptionFilter.getValue()));
    }

    private  enum DigitisationOptions {
        WITH("", VaadinIcons.FILE_TEXT),
        WITHOUT("", VaadinIcons.BAN);


        private String text = "";
        private VaadinIcons icon = null;

        DigitisationOptions(String text, VaadinIcons icon){
            this.text=text;
            this.icon = icon;
        }

        public String getText() {
            return text;
        }

        public VaadinIcons getIcon() {
            return icon;
        }
    }

    private  enum DirectionOptions {
        INCOMING("", VaadinIcons.valueOf(Direction.INCOMING.toString())),
        OUTGOING("", VaadinIcons.valueOf(Direction.OUTGOING.toString()));


        private String text = "";
        private VaadinIcons icon = null;

        DirectionOptions(String text, VaadinIcons icon){
            this.text=text;
            this.icon = icon;
        }

        public String getText() {
            return text;
        }

        public VaadinIcons getIcon() {
            return icon;
        }
    }
}
