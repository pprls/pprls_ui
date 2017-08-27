package org.pprls.ui.model;

public class Employee {
    private String name;
    private String last;
    private String fathers;

    public Employee() {
    }

    public Employee(String name, String last, String fathers) {
        this.name = name;
        this.last = last;
        this.fathers = fathers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getFathers() {
        return fathers;
    }

    public void setFathers(String fathers) {
        this.fathers = fathers;
    }

    public String getFullName(){
        return name+" "+last;
    }
}