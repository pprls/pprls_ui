package org.pprls.ui.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by kapostolou on 18/8/2017.
 */
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String action;
    private String holder;
    private LocalDate accept;
    private String subject;
    private LocalDate deadLine;
    private String instructions;
    private Double progress;
    private List<Attachment> attachments;

    public Item(){
        this.action = "";
        this.holder = "";
        this.accept = LocalDate.ofEpochDay(0);
        this.subject = "";
        this.progress = 0.0;
        this.deadLine = LocalDate.ofEpochDay(0);
        this.attachments = new ArrayList<Attachment>();
    }

    public Item(String action, String holder, LocalDate accept, String subject, Double progress, LocalDate deadLine) {
        this.action = action;
        this.holder = holder;
        this.accept = accept;
        this.subject = subject;
        this.deadLine = deadLine;
        this.progress = progress;
        this.attachments = new ArrayList<Attachment>();
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

    public LocalDate getAccept() {
        return accept;
    }

    public void setAccept(LocalDate accept) {
        this.accept = accept;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public LocalDate getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDate deadLine) { this.deadLine = deadLine; }

    public String getInstructions() { return instructions; }

    public void setInstructions(String instructions) { this.instructions = instructions; }

    public Double getProgress() { return progress; }

    public void setProgress(Double progress) { this.progress = progress; }

    public List<Attachment> getAttachments() { return attachments; }



}
