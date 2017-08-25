package org.pprls.ui.model;

public class Attachment<T> {
    private AttachmentType type;
    private T value;

    public Attachment(AttachmentType type, T value) {
        this.type = type;
        this.value = value;
    }

    public AttachmentType getType() {
        return type;
    }

    public void setType(AttachmentType type) {
        this.type = type;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
