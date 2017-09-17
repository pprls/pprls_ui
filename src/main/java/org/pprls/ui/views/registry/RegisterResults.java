package org.pprls.ui.views.registry;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.server.Resource;
import com.vaadin.ui.*;
import com.vaadin.ui.declarative.Design;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.util.Locale;

@DesignRoot
public class RegisterResults extends Window {

    Label data, unitDescription;
    BrowserFrame label;


    public RegisterResults(String title, Resource icon){
        super(title);
        setIcon(icon);
        Design.read("RegisterResults.html", this);
    }

    public void setRegData(String unitDesc, long regNumber, LocalDate date, Year year){
        unitDescription.setValue(unitDesc);
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.forLanguageTag("el_GR"));
        data.setValue(regNumber+"/"+ df.format(date)+"/"+year.toString());
    }

}
