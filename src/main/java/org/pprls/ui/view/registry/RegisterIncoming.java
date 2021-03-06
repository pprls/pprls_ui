package org.pprls.ui.view.registry;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Resource;
import com.vaadin.ui.*;
import com.vaadin.ui.declarative.Design;

import java.time.LocalDate;
import java.time.Year;

@DesignRoot
public class RegisterIncoming extends Panel {

    VerticalLayout vertical;
    ComboBox from, classification, composer;
    Button addComposer, toButton, ccButton, internalButton, externalButton, register;
    ListSelect composersList, toList;
    PopupView subject, attachmentDescription, comments;

    public RegisterIncoming(String title, Resource icon){
        super(title);
        setIcon(icon);

        Design.read(this);
        subject.setContent(new PopupTextFieldContent("Θέμα", "Γράψε το Θέμα"));
        attachmentDescription.setContent(new PopupTextFieldContent("Συνημμένα", "Γράψε την περιγραφή συνημμένων εδώ"));
        comments.setContent(new PopupTextFieldContent("Παρατηρήσεις", "Γράψε τις παρατηρήσεις σου"));
        setContent(vertical);

        register.addClickListener(click ->openBarcode());
    }

    private void openBarcode(){
        RegisterResults resultWindow = new RegisterResults("Αριθμός πρωτοκόλλου", VaadinIcons.EYE);
        resultWindow.setRegData("ΠΔ Ηπείρου Δυτικής Μακεδονίας", 234L, LocalDate.now(), Year.now());
        UI.getCurrent().addWindow(resultWindow);
    }

    private static class PopupTextFieldContent implements PopupView.Content {
        private final HorizontalLayout layout;
        private final RichTextArea description = new RichTextArea();

        private PopupTextFieldContent(String caption, String value) {
            description.setCaption(caption);
            description.setValue(value);
            layout = new HorizontalLayout(description);
        }

        @Override
        public final Component getPopupComponent() {
            return layout;
        }

        @Override
        public final String getMinimizedValueAsHTML() {
            return description.getValue();
        }
    }
}
