package org.pprls.ui.domain;

import java.util.ArrayList;
import java.util.List;

public enum SubjectRepository {
    INSTANCE;

    private  List<Employee> subjectList = new ArrayList();

    private SubjectRepository() {
        subjectList.add(new Employee("Αποστόλου", "Κωνσταντίνος", "Νικήτας"));
        subjectList.add(new Employee("Αποστόλου", "Ιουλία", "Κωμσταντίνου"));
        subjectList.add(new Employee("Αποστόλου", "Μανώλης", "Κωνστντίνου"));
        subjectList.add(new Employee("Παπαμιχαήλ","Αννέτα","Εμμανουήλ"));

    }

    public List<Employee> getSubjects(){
        return subjectList;
    }
}
