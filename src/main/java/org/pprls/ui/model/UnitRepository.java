package org.pprls.ui.model;

import java.util.LinkedList;
import java.util.List;

public enum UnitRepository {
    INSTANCE;

    private  List<Unit> unitList = new LinkedList();

    UnitRepository() {
        Unit fatherUnit = new Unit("ΟΠΕΚΕΠΕ");
        Unit secondUnit = new Unit("ΠΔ Ηπείρου Δυτικής Μακεδονίας");
        secondUnit.addFather(fatherUnit);
        fatherUnit.addChild(secondUnit);
        secondUnit.addChild(new Unit("ΝΜ Άρτας"));
        secondUnit.addChild(new Unit("ΝΜ Πρέβεζας"));
        unitList.add(fatherUnit);
    }

    public List<Unit> getUnits(){
        return unitList;
    }
}
