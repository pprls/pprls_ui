package org.pprls.ui.view.registry;

import com.vaadin.contextmenu.GridContextMenu;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.datefield.DateResolution;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.HtmlRenderer;
import org.pprls.ui.domain.RegistryRecord;
import org.pprls.ui.domain.RegistryRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RegistryPanel extends VerticalLayout {

    private final HorizontalLayout fistline = new HorizontalLayout();
    private final TextArea keywords = new TextArea("Λέξεις κλειδιά");
    private final HorizontalLayout thirdline = new HorizontalLayout();
    private final HorizontalLayout fourthline = new HorizontalLayout();
    private final HorizontalLayout fifthline = new HorizontalLayout();
    private final TextField registryNumber;
    private final DateField year;
    private final DateField from;
    private final DateField to;
    private final ComboBox composer;
    private final TextField correspondant;
    private final ComboBox postageType;
    private final TextField postageId;
    private final DateField postageDate;
    private final ComboBox classification;
    private final TextField correspondantRegistryNumber;
    private final DateField correspondantRegistryDate;
    private final Button searchButton;
    private final Grid<RegistryRecord> results;

    public RegistryPanel() {
        setMargin(false);
        keywords.setRows(2);
        keywords.setWidth("100%");
        keywords.setDescription("Λέξεις ή φράσεις, χωρισμένες με κόμματα");

        addComponent(fistline);
        addComponents(keywords);
        addComponent(thirdline);
        addComponent(fourthline);
        addComponent(fifthline);

        registryNumber = new TextField("Αριθμός Πρωτοκόλλου");
        year = new DateField("Έτος");
        year.setValue(LocalDate.now());
        year.setResolution(DateResolution.YEAR);
        from = new DateField("από");
        to = new DateField("έως");

        fistline.addComponentsAndExpand(registryNumber, year, from, to);

        composer = new ComboBox("Συντάκτης");
        correspondant = new TextField("Αποστολέας/Παραλήπτης");

        thirdline.addComponentsAndExpand(composer, correspondant);

        postageType = new ComboBox("Τρόπος αποστολής");
        postageId = new TextField("Κωδικός αναφοράς");
        postageDate = new DateField("Ημ/νία αποστολής παραλαβής");

        fourthline.addComponentsAndExpand(postageType, postageId, postageDate);

        classification = new ComboBox("Διαβάθμιση");
        correspondantRegistryNumber = new TextField("Αριθ. Πρωτ. Αποστολέα");
        correspondantRegistryDate = new DateField("Ημ/νία Πρωτ. Αποστολέα");
        searchButton = new Button("Αναζήτηση");

        fifthline.addComponentsAndExpand(classification, correspondantRegistryNumber, correspondantRegistryDate, searchButton);
        fifthline.setComponentAlignment(searchButton, Alignment.BOTTOM_CENTER);

        results = new Grid<>("Αποστελέσματα");
        results.addColumn(p -> VaadinIcons.valueOf(p.getDirection().toString()).getHtml(), new HtmlRenderer());
        results.addColumn(RegistryRecord::getRegistryNumber).setCaption("Αρ. Πρωτ.").setId("registryNumber");
        results.addColumn(RegistryRecord::getRegistryDate).setCaption("Ημ/νία").setId("registryDate");
        results.addColumn(RegistryRecord::getYear).setCaption("Έτος").setId("year");
        results.addColumn(RegistryRecord::getDescription).setCaption("Θέμα").setId("subject");
        results.addColumn(RegistryRecord::getFrom).setCaption("Από").setId("from");
        results.addColumn(RegistryRecord::getTo).setCaption("Πρός").setId("to");
        results.setSizeFull();
        results.setItems(RegistryRepository.INSTANCE.getRegistryRecords());
        results.setFrozenColumnCount(4);
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
        addComponentsAndExpand(results);

        searchButton.addClickListener(click -> {
            String query = "";
            try {
                query += "REGNUMBER=" + registryNumber.getValue();
            } catch (Exception ex) {
            }
            ;
            try {
                query += "&YEAR=" + year.getValue().getYear();
            } catch (Exception ex) {
            }
            ;
            try {
                query += "&FROM=" + from.getValue().format(DateTimeFormatter.ofPattern("dd-mm-yyyy"));
            } catch (Exception ex) {
            }
            ;
            try {
                query += "&TO=" + to.getValue().format(DateTimeFormatter.ofPattern("dd-mm-yyyy"));
            } catch (Exception ex) {
            }
            ;
            try {
                query += "&KEYWORDS=" + keywords.getValue();
            } catch (Exception ex) {
            }
            ;
            results.setItems(RegistryRepository.INSTANCE.getRegistryRecords(query));
        });
    }
}
