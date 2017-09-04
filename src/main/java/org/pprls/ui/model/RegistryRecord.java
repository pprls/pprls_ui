package org.pprls.ui.model;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class RegistryRecord {

    private Direction direction;
    private long registryNumber;
    private LocalDate registryDate;
    private Year year;
    private String description;
    private String from;
    private String to;
    private List<Attachment> attachments = new ArrayList<Attachment>();

    public RegistryRecord(Direction direction, long registryNumber, LocalDate registryDate, Year year, String description, String from, String to){
        this.direction = direction;
        this.registryNumber = registryNumber;
        this.registryDate = registryDate;
        this.year = year;
        this.description = description;
        this.from = from;
        this.to = to;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public long getRegistryNumber() {
        return registryNumber;
    }

    public void setRegistryNumber(long registryNumber) {
        this.registryNumber = registryNumber;
    }

    public LocalDate getRegistryDate() {
        return registryDate;
    }

    public void setRegistryDate(LocalDate registryDate) {
        this.registryDate = registryDate;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }
}
