package org.pprls.ui.domain;

import com.vaadin.icons.VaadinIcons;

public enum Direction {
    INCOMING{
        @Override
        public String toString() {
            return VaadinIcons.ARROW_CIRCLE_RIGHT.toString();
        }
    },
    OUTGOING{
        @Override
        public String toString() {
            return VaadinIcons.ARROW_CIRCLE_LEFT_O.toString();
        }
    }
}
