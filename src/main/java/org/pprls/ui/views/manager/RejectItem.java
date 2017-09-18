package org.pprls.ui.views.manager;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;
import com.vaadin.ui.declarative.Design;
import org.pprls.ui.model.UnitRepository;

@DesignRoot
public class RejectItem extends Window {

    Button proposal, unknown;
    VerticalLayout vertical;
    Tree<org.pprls.ui.model.Unit> organization;

    public RejectItem(){
        super("Απόρυψη");
        setIcon(VaadinIcons.BAN);

        Design.read("RejectItem.html", this);
        organization.setItems(UnitRepository.INSTANCE.getUnits(), org.pprls.ui.model.Unit::getChilds);
        setContent(vertical);

        organization.addSelectionListener(selectionEvent -> proposal.setEnabled(!organization.getSelectedItems().isEmpty()));
        proposal.addClickListener(clickEvent -> propose());
    }

    private void propose(){

    }
}
