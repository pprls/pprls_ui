package org.pprls.ui.views.manager;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.Button;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.declarative.Design;
import org.pprls.ui.model.UnitRepository;

@DesignRoot
public class RejectItem extends Window {

    Button proposal, unknown;
    VerticalLayout vertical;
    Tree<org.pprls.ui.model.Unit> organization;

    public RejectItem() {
        Design.read(this);
        organization.setItems(UnitRepository.INSTANCE.getUnits(), org.pprls.ui.model.Unit::getChilds);
        setContent(vertical);

        organization.addSelectionListener(selectionEvent -> proposal.setEnabled(!organization.getSelectedItems().isEmpty()));
        proposal.addClickListener(clickEvent -> propose());
    }

    private void propose() {

    }
}
