package org.pprls.ui.domain;

import java.util.ArrayList;
import java.util.List;

public class Unit {
    private Unit father;
    private String name;
    private List<Unit> childs = new ArrayList<>();

    public Unit(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public List<Unit> getChilds(){
        return childs;
    }

    public void addChild(Unit child){
        child.addFather(this);
        childs.add(child);
    }

    public void addFather(Unit father){
        this.father = father;
    }

    public String toString(){
        return name;
    }
}
