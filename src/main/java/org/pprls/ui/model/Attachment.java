package org.pprls.ui.model;

public class Attachment<T> {
    private AttachmentType type;
    private T value;
    private boolean signed;

    public Attachment(AttachmentType type, T value, boolean signed) {
        this.type = type;
        this.value = value;
        this.signed = signed;
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

    public boolean isSigned() {
        return signed;
    }

    public void setSigned(boolean signed) {
        this.signed = signed;
    }

    public String getSignaturesInfo(){
        return "Signed by K. Apostolou";
    }
}
