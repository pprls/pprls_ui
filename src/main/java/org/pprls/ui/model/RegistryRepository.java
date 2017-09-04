package org.pprls.ui.model;

import com.vaadin.ui.Notification;

import java.net.URL;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import static org.pprls.ui.model.AttachmentType.PDF;
import static org.pprls.ui.model.AttachmentType.RTF;

public enum RegistryRepository {
    INSTANCE;

    private  List<RegistryRecord> registryRecordList = new ArrayList<RegistryRecord>();

    private RegistryRepository() {
        try {
            RegistryRecord newRegistryRecord = new RegistryRecord(Direction.INCOMING, 100L, LocalDate.parse("2019-01-01"), Year.parse("2017"),"Διοικητική πράξη Α", "Χλιμίντζουρας Παναγιώτης", "ΠΔ Ηπείρου");
            newRegistryRecord.getAttachments().add(new Attachment<URL>(PDF, new URL("file:///N:/scanner/Color0211.pdf"), true));
            newRegistryRecord.getAttachments().add(new Attachment<String>(RTF, "Sample <strong>text</strong>", false));
            newRegistryRecord.getAttachments().add(new Attachment<URL>(PDF, new URL("file:///N:/scanner/Color0213.pdf"), true));
            registryRecordList.add(newRegistryRecord);
            newRegistryRecord = new RegistryRecord(Direction.INCOMING, 101L, LocalDate.parse("2019-01-02"), Year.parse("2017"),"Διοικητική πράξη B", "Θεόδωρος Κολοκοτρώνης", "ΠΔ Ηπείρου");
            newRegistryRecord.getAttachments().add(new Attachment<URL>(PDF, new URL("file:///N:/scanner/Color0211.pdf"), true));
            newRegistryRecord.getAttachments().add(new Attachment<String>(RTF, "Sample <strong>text</strong>", false));
            newRegistryRecord.getAttachments().add(new Attachment<URL>(PDF, new URL("file:///N:/scanner/Color0213.pdf"), true));
            registryRecordList.add(newRegistryRecord);
            newRegistryRecord = new RegistryRecord(Direction.INCOMING, 102L, LocalDate.parse("2019-01-03"), Year.parse("2017"),"Διοικητική πράξη Γ", "Νικηταράς ο Τουρκοφάγος", "ΠΔ Ηπείρου");
            newRegistryRecord.getAttachments().add(new Attachment<URL>(PDF, new URL("file:///N:/scanner/Color0211.pdf"), true));
            newRegistryRecord.getAttachments().add(new Attachment<String>(RTF, "Sample <strong>text</strong>", false));
            newRegistryRecord.getAttachments().add(new Attachment<URL>(PDF, new URL("file:///N:/scanner/Color0213.pdf"), true));
            registryRecordList.add(newRegistryRecord);
            newRegistryRecord = new RegistryRecord(Direction.OUTGOING, 103L, LocalDate.parse("2019-01-04"), Year.parse("2017"),"Διοικητική πράξη Δ", "Οδυσσέας Ανδρούτσος", "ΠΔ Ηπείρου");
            newRegistryRecord.getAttachments().add(new Attachment<URL>(PDF, new URL("file:///N:/scanner/Color0211.pdf"), true));
            newRegistryRecord.getAttachments().add(new Attachment<String>(RTF, "Sample <strong>text</strong>", false));
            newRegistryRecord.getAttachments().add(new Attachment<URL>(PDF, new URL("file:///N:/scanner/Color0213.pdf"), true));
            registryRecordList.add(newRegistryRecord);
            newRegistryRecord = new RegistryRecord(Direction.OUTGOING, 104L, LocalDate.parse("2019-01-05"), Year.parse("2017"),"Διοικητική πράξη Ε", "Ανδρέας Μιαούλης", "ΠΔ Ηπείρου");
            newRegistryRecord.getAttachments().add(new Attachment<URL>(PDF, new URL("file:///N:/scanner/Color0211.pdf"), true));
            newRegistryRecord.getAttachments().add(new Attachment<String>(RTF, "Sample <strong>text</strong>", false));
            newRegistryRecord.getAttachments().add(new Attachment<URL>(PDF, new URL("file:///N:/scanner/Color0213.pdf"), true));
            registryRecordList.add(newRegistryRecord);

        }catch(Exception ex){
            Notification.show("Fail to load Registry record Items", ex.getLocalizedMessage(), Notification.Type.ERROR_MESSAGE);
        }


    }

    public List<RegistryRecord> getRegistryRecords() {
        return registryRecordList;
    }

    public List<RegistryRecord> getRegistryRecords(long regNum, Year year) {
        List<RegistryRecord> result = new ArrayList<>();
        for(RegistryRecord registryRecord : registryRecordList) {
            if (registryRecord.getRegistryNumber()==regNum && registryRecord.getYear() == year) result.add(registryRecord);
        }
        return result;
    }

    public List<RegistryRecord> getRegistryRecords( String query) {
        List<RegistryRecord> result = new ArrayList<>();
        for(RegistryRecord registryRecord : registryRecordList) {
            String[] parameters = query.split("&");
            for(String parameter : parameters){
                String[] variable = parameter.split("=");
                if(!variable[1].isEmpty()){
                    switch(variable[0]){
                        case "REGNUMBER":
                            break;
                        case "YEAR" :
                            break;
                        case "FROM" :
                            LocalDate dt = new LocalDate(variable[0]);
                            if (registryRecord.getRegistryDate().isAfter() && registryRecord.getRegistryDate().isBefore(to)) result.add(registryRecord);
                            break;
                        case "TO" :
                            break;
                        case "KEYWORDS" :
                            break;
                    }
                }
            }
        }
        return result;
    }


    public List<RegistryRecord> save(RegistryRecord registryRecord){
        registryRecordList.add(registryRecord);
        return registryRecordList;
    }
}
