package org.pprls.ui.views.registry;

import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.datefield.DateResolution;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.HtmlRenderer;
import com.vaadin.ui.renderers.ImageRenderer;
import org.pprls.ui.model.Item;
import org.pprls.ui.model.RegistryRecord;
import org.pprls.ui.model.RegistryRepository;

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
        Grid.Column<RegistryRecord, ThemeResource> imageColumn = results.addColumn(
                p -> new ThemeResource("img/"+p.getDirection()+".png"),
                new ImageRenderer());
        results.addColumn(RegistryRecord::getRegistryNumber).setCaption("Αρ. Πρωτ.").setId("registryNumber");
        results.addColumn(RegistryRecord::getRegistryDate).setCaption("Ημ/νία").setId("registryDate");
        results.addColumn(RegistryRecord::getYear).setCaption("Έτος").setId("year");
        results.addColumn(RegistryRecord::getDescription).setCaption("Θέμα").setId("subject");
        results.addColumn(RegistryRecord::getFrom).setCaption("Από").setId("from");
        results.addColumn(RegistryRecord::getTo).setCaption("Έως").setId("to");
        results.setSizeFull();
        results.setItems(RegistryRepository.INSTANCE.getRegistryRecords());
        results.setFrozenColumnCount(4);
        addComponentsAndExpand(results);

        searchButton.addClickListener(click -> {
            String query = "REGNUMBER="+registryNumber.getValue();
            query += "&YEAR="+year.getValue();
            query += "&FROM="+from.getValue().format(DateTimeFormatter.ofPattern("dd-mm-yyyy"));
            query += "&TO="+to.getValue().format(DateTimeFormatter.ofPattern("dd-mm-yyyy"));
            query += "&KEYWORDS="+keywords.getValue();
            RegistryRepository.INSTANCE.getRegistryRecords(query);
        });
    }
}
