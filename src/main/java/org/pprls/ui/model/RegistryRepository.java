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

        }catch(Exception ex){
            Notification.show("Fail to load Registry record Items", ex.getLocalizedMessage(), Notification.Type.ERROR_MESSAGE);
        }


    }

    public List<RegistryRecord> getRegistryRecords() {
        return registryRecordList;
    }

    public List<RegistryRecord> getItems(boolean pending) {
        List<RegistryRecord> result = new ArrayList<>();
        for(RegistryRecord registryRecord : registryRecordList) {
//            if (registryRecord.isPending()==pending) result.add(registryRecord);
        }
        return result;
    }

    public List<RegistryRecord> save(RegistryRecord registryRecord){
        registryRecordList.add(registryRecord);
        return registryRecordList;
    }
}
