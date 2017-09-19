package org.pprls.ui.views.manager;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.Window;
import com.vaadin.ui.declarative.Design;
import com.vaadin.ui.Button;

@DesignRoot
public class AcceptItem extends Window {
    protected Button accept;

    public AcceptItem() {
        Design.read(this);
    }
}
