package org.pprls.ui.view.registry;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.*;
import com.vaadin.ui.declarative.Design;

import java.time.format.DateTimeFormatter;

@DesignRoot
public class SearchConditions extends Panel {

    private TextField registryNumber;
    private TextArea keywords;
    private DateField year;
    private DateField from;
    private DateField to;
    private Button searchButton;

    public SearchConditions() {
        super("Κριτήρια");
        Design.read(this);

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
        });
    }
}
