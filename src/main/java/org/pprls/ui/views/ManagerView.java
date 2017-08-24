package org.pprls.ui.views;

import com.vaadin.annotations.Theme;
import com.vaadin.data.Binder;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.ui.components.grid.HeaderCell;
import com.vaadin.ui.components.grid.HeaderRow;
import com.vaadin.ui.themes.ValoTheme;
import org.pprls.ui.model.Attachment;
import org.pprls.ui.model.Item;
import org.pprls.ui.model.ItemGenerator;
import org.vaadin.alump.ckeditor.CKEditorConfig;
import org.vaadin.alump.ckeditor.CKEditorTextField;
import pl.pdfviewer.PdfViewer;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Theme("mytheme")
public class ManagerView extends VerticalLayout implements View {

    private SingleSelect<Item> itemSelection;
    private int index = 0;

    public ManagerView() throws MalformedURLException {
        setSizeFull();

        final HorizontalLayout mainLayout = new HorizontalLayout();
        mainLayout.setSizeFull();
        this.addComponent(mainLayout);
        final VerticalLayout docsLayout = new VerticalLayout();
        final VerticalLayout tasksLayout = new VerticalLayout();

        docsLayout.setSizeFull();
        tasksLayout.setSizeFull();

        mainLayout.addComponent(docsLayout);
        mainLayout.addComponent(tasksLayout);

        // Διαββιβαστικό
        CKEditorConfig config = new CKEditorConfig();
        config.useCompactTags();
        config.disableElementsPath();
        config.setResizeDir(CKEditorConfig.RESIZE_DIR.HORIZONTAL);
        config.disableSpellChecker();
        config.setReadOnly(true);
        config.setWidth("100%");
        final CKEditorTextField rtArea = new CKEditorTextField(config);
        rtArea.setSizeFull();

        Button prev = new Button();
        prev.setIcon(VaadinIcons.ARROW_CIRCLE_LEFT);
        Button next = new Button();
        next.setIcon(VaadinIcons.ARROW_CIRCLE_RIGHT);

        HorizontalLayout navLayout = new HorizontalLayout();
        navLayout.setWidth("100%");
        navLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        navLayout.addComponents(prev);
        navLayout.addComponents(next);

        //PdfViewer
        final PdfViewer pdfArea = new PdfViewer();
        pdfArea.setSizeFull();

        final VerticalLayout attachmentsLayout = new VerticalLayout();
        attachmentsLayout.setMargin(false);
        attachmentsLayout.setSizeFull();

        attachmentsLayout.addComponentsAndExpand(pdfArea);
        attachmentsLayout.addComponent(navLayout);

        // docsLayout
        docsLayout.addComponent(rtArea);
        docsLayout.addComponentsAndExpand(attachmentsLayout);

        final HorizontalLayout buttonLayout  = new HorizontalLayout();
        buttonLayout.setSizeFull();
        docsLayout.addComponent(buttonLayout);
        Button buttonDecline = new Button("Απορρίπτω");
        buttonDecline.setSizeFull();
        Button buttonAssign = new Button("Αναθέτω/ενεργώ");
        buttonAssign.setStyleName(ValoTheme.BUTTON_PRIMARY);
        buttonAssign.setSizeFull();
        buttonLayout.addComponent(buttonDecline);
        buttonLayout.addComponent(buttonAssign);

        docsLayout.setExpandRatio(rtArea, 3.0f);
        docsLayout.setExpandRatio(attachmentsLayout, 6.0f);
        docsLayout.setExpandRatio(buttonLayout, 1.0f);

        // tasksLayout
        Button buttonAssignTask = new Button("Ανάθεση εργασίας στη διεύθυνση");
        buttonAssignTask.setWidth("100%");
        Grid<Item> taskGrid = new Grid<>();
        itemSelection = taskGrid.asSingleSelect();
        taskGrid.setSizeFull();
        List<Item> items = ItemGenerator.INSTANCE.createList();
        ListDataProvider<Item> provider = new ListDataProvider<>(items);
        taskGrid.setDataProvider(provider);
        taskGrid.addColumn(Item::getAction).setCaption("γιά");
        taskGrid.addColumn(Item::getSubject).setCaption("Θέμα");
        HeaderRow filterRow = taskGrid.appendHeaderRow();
        HeaderCell actionCell = filterRow.getCell(taskGrid.getColumns().get(0));
        TextField actionFilter = new TextField();
        actionCell.setComponent(actionFilter);
        HeaderCell subjectCell = filterRow.getCell(taskGrid.getColumns().get(1));
        TextField subjectFilter = new TextField();
        subjectCell.setComponent(subjectFilter);

        Button pendingButton = new Button("Εκκρεμότητες Διεύθυνσης");
        pendingButton.setWidth("100%");
        Button pastButton = new Button("Παρελθόντα Ολοκληρωμένα");
        pastButton.setWidth("100%");
        Button searchCaseButton = new Button("Αναζήτηση Υπόθεσης");
        searchCaseButton.setWidth("100%");
        Button protocolButton = new Button("Πρωτόκολλο");
        protocolButton.setWidth("100%");

        tasksLayout.addComponent(buttonAssignTask);
        tasksLayout.addComponent(taskGrid);
        tasksLayout.addComponent(pendingButton);
        tasksLayout.addComponent(pastButton);
        tasksLayout.addComponent(searchCaseButton);
        tasksLayout.addComponent(protocolButton);

        tasksLayout.setExpandRatio(taskGrid, 1f);

        // Listeners
        actionFilter.addValueChangeListener(valueChangeEvent ->  {
                String value = (String)valueChangeEvent.getValue();
                if(value.trim().isEmpty()){
                    provider.clearFilters();
                }else {
                    provider.setFilter(item -> item.getAction().matches(value));
                }
            });

        buttonAssignTask.addClickListener(click -> {
            // Create a sub-window and set the content
            Window subWindow = new Window("Ανάθεση εργασίας στη διεύθυνση");
            subWindow.setModal(true);
            VerticalLayout subContent = new VerticalLayout();
            subWindow.setContent(subContent);

            // Put some components in it
            subContent.addComponent(new Label("Meatball sub"));
            subContent.addComponent(new Button("Awlright"));

            // Center it in the browser window
            subWindow.center();

            // Open it in the UI
            UI.getCurrent().addWindow(subWindow);
        });

        // when i change selection on grid  this is what I do
        final Binder<Item> itemBinder = new Binder<>(Item.class);
        itemBinder.bind(rtArea, "instructions");
        itemSelection.addValueChangeListener(event -> {
            itemBinder.readBean(itemSelection.getValue());
            index = 0;
            if(!itemSelection.getValue().getAttachments().isEmpty()) {
                setAttachment(pdfArea, itemSelection, index);
            }
        });
//        final Binder<Attachment> attachmentBinder = new Binder<>(Attachment.class);
//        attachmentBinder.forField(pdfArea, Attachment::getValue, Attachment::setValue);

        // Next Prev click listeners
        prev.addClickListener(click -> setAttachment(pdfArea, itemSelection, prev()));
        next.addClickListener(click -> setAttachment(pdfArea, itemSelection, next()));
    }

    private void setAttachment(PdfViewer pdfArea, SingleSelect<Item> itemSelection, int i) {
        Attachment<URL> attachment = itemSelection.getValue().getAttachments().get(i);
        String filename = attachment.getValue().getFile();
        pdfArea.setFile(new File(filename));
    }

    private int prev() {
        return index = index == 0 ? itemSelection.getValue().getAttachments().size() - 1 : index - 1;
    }

    private int next() {
        return index = index == itemSelection.getValue().getAttachments().size()-1? 0 : index +1;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        Notification.show("Welcome to 1.1 Pencil");
    }
}
