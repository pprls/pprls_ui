package org.pprls.ui.views.registry;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Resource;
import com.vaadin.shared.ui.datefield.DateResolution;
import com.vaadin.ui.*;

import java.time.LocalDate;

public class RegisterIncoming extends Panel {

    TextField registryNumber, subject, attachmentDescription;
    RichTextArea  description;
    DateField registryDate, registryYear;
    ComboBox from, classification, composer, to;
    Button addComposer, toButton, ccButton, internalButton, externalButton;
    ListSelect composersList, toList;

    public RegisterIncoming(String title, Resource icon){
        super(title);
        setIcon(icon);

        registryNumber = new TextField();
        registryNumber.setSizeFull();
        registryDate = new DateField();
        registryDate.setValue(LocalDate.now());
        registryDate.setSizeFull();
        registryYear = new DateField();
        registryYear.setValue(LocalDate.now());
        registryYear.setSizeFull();
        registryYear.setResolution(DateResolution.YEAR);
        from = new ComboBox();
        from.setSizeFull();
        classification = new ComboBox();
        classification.setSizeFull();
        composer = new ComboBox();
        addComposer = new Button(VaadinIcons.USER_CHECK);
        composersList = new ListSelect<>();
        composersList.setRows(3);
        composersList.setWidth("100%");
        to = new ComboBox();
        toButton = new Button(VaadinIcons.ENVELOPE);
        ccButton = new Button(VaadinIcons.EYE);
        internalButton = new Button(VaadinIcons.HOME);
        externalButton = new Button(VaadinIcons.TRUCK);
        toList = new ListSelect<>();
        toList.setRows(10);
        toList.setWidth("100%");
        subject = new TextField();
        subject.setSizeFull();
        attachmentDescription = new TextField();
        attachmentDescription.setSizeFull();
        description = new RichTextArea();
        description.setSizeFull();


        HorizontalLayout firstLine = new HorizontalLayout(registryNumber, registryDate, registryYear);
        firstLine.setSizeFull();
        HorizontalLayout secondLine = new HorizontalLayout(from, classification);
        secondLine.setSizeFull();
        HorizontalLayout thirdLine = new HorizontalLayout();
        thirdLine.addComponentsAndExpand(composer);
        thirdLine.addComponent(addComposer);
        HorizontalLayout fourthLine = new HorizontalLayout();
        fourthLine.addComponentsAndExpand(to);
        fourthLine.addComponents(toButton, ccButton, internalButton, externalButton);

        VerticalLayout vertical = new VerticalLayout(firstLine, secondLine, thirdLine, composersList, fourthLine, toList, subject, attachmentDescription);
        vertical.addComponentsAndExpand(description);
        setContent(vertical);
    }
}
