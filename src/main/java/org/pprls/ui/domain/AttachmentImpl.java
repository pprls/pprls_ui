package org.pprls.ui.domain;

public class AttachmentImpl<T> implements Attachment<T> {
    private AttachmentType type;
    private T value;
    private boolean signed;

    public AttachmentImpl(AttachmentType type, T value, boolean signed) {
        this.type = type;
        this.value = value;
        this.signed = signed;
    }

    @Override
    public AttachmentType getType() {
        return type;
    }

    @Override
    public void setType(AttachmentType type) {
        this.type = type;
    }

    @Override
    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public boolean isSigned() {
        return signed;
    }

    public void setSigned(boolean signed) {
        this.signed = signed;
    }

    @Override
    public String getSignaturesInfo(){
        return "Signed by K. Apostolou";
    }
}
