package org.pprls.ui.views.manager;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.TreeGrid;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.declarative.Design;
import com.vaadin.ui.themes.ValoTheme;
import org.pprls.ui.model.UnitRepository;

@DesignRoot
public class RejectItem extends Window {

    Button proposal, unknown;
    VerticalLayout vertical;
    TreeGrid<org.pprls.ui.model.Unit> organization;

    public RejectItem(){
        super("Απόρυψη");
        setIcon(VaadinIcons.BAN);

        Design.read("RejectItem.html", this);
        organization.setItems(UnitRepository.INSTANCE.getUnits(), org.pprls.ui.model.Unit::getChilds);
        organization.expand();
        organization.setStyleName();
        organization.addColumn(org.pprls.ui.model.Unit::getName);
        setContent(vertical);

        organization.addSelectionListener(selectionEvent -> proposal.setEnabled(!organization.getSelectedItems().isEmpty()));
        proposal.addClickListener(clickEvent -> propose());
    }

    private void propose(){

    }
}
