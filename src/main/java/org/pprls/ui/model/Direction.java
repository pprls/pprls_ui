package org.pprls.ui.model;

public enum Direction {
    INCOMING{
        @Override
        public String toString() {
            return "incoming";
        }
    },
    OUTGOING{
        @Override
        public String toString() {
            return "outgoing";
        }
    }
}
