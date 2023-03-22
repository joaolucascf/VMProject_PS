package com.vm.ps.vmproject_ps.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class codeEditorCtrl{
    @FXML
    public Button newFileBtn;
    public TabPane tabPaneEditor;
    public Button deleteFileBtn;
    private List<TextArea> codeSet = new ArrayList<>();
    public void newFile(MouseEvent mouseEvent) {
        tabPaneEditor.getTabs().add(createDefaultTab());
    }
    public Tab createDefaultTab(){
        Tab newTab = new Tab("New Tab");
        TextArea emptyTextArea = new TextArea();
        codeSet.add(emptyTextArea);
        newTab.setContent(emptyTextArea);
        return newTab;
    }
    public void closeFile(MouseEvent mouseEvent) {
        int i=0;
        Iterator<Tab> tabIterator = tabPaneEditor.getTabs().iterator();
        while(tabIterator.hasNext()){
            Tab selectedTab = tabIterator.next();
            if(selectedTab.isSelected()){
                tabPaneEditor.getTabs().remove(selectedTab);
                codeSet.remove(i);
                return;
            }
            i++;
        }
    }

    public void openFile(MouseEvent mouseEvent) throws IOException {
        Desktop desktop = Desktop.getDesktop();
        desktop.open(new File("C://"));
    }

    public void saveFile(MouseEvent mouseEvent) {
    }

    public void runCode(MouseEvent mouseEvent) {
        int i=0;
        TextArea codeToRun = null;
        Iterator<Tab> tabIterator = tabPaneEditor.getTabs().iterator();
        while(tabIterator.hasNext()){
            Tab selectedTab = tabIterator.next();
            if(selectedTab.isSelected()){
                codeToRun = codeSet.get(i);
                break;
            }
            i++;
        }
        String code = codeToRun.getText();
    }

    public void codeNextStep(MouseEvent mouseEvent) {
    }
}