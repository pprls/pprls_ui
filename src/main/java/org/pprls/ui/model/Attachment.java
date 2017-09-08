package org.pprls.ui.model;

public interface Attachment<T> {

    AttachmentType getType();

    void setType(AttachmentType type);

    T getValue();

    boolean isSigned();

    String getSignaturesInfo();
}
