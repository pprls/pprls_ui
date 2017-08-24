package org.pprls.ui.model;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kapostolou on 18/8/2017.
 */
public class Item {

    private String action;
    private String holder;
    private String accept;
    private String subject;
    private String deadLine;
    private String instructions;
    private List<Attachment> attachments;

    public Item(String action, String holder, String accept, String subject, String deadLine) {
        this.action = action;
        this.holder = holder;
        this.accept = accept;
        this.subject = subject;
        this.deadLine = deadLine;
        attachments = new ArrayList<Attachment>();
    }

    public Item() {
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) { this.deadLine = deadLine; }

    public String getInstructions() { return instructions; }

    public void setInstructions(String instructions) { this.instructions = instructions; }

    public List<Attachment> getAttachments() { return attachments; }

    public void clearAttachments() { this.attachments.clear(); }
}
