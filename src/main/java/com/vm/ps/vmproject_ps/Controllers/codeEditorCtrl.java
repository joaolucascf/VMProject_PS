package com.vm.ps.vmproject_ps.Controllers;

import com.vm.ps.vmproject_ps.ULA;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.List;

public class codeEditorCtrl implements Initializable{
    @FXML
    public Button newFileBtn;
    public TabPane tabPaneEditor;
    public Button deleteFileBtn;
    public ListView<String> memList;
    public TextField SWField;
    public TextField PCField;
    public TextField FField;
    public TextField TField;
    public TextField SField;
    public TextField BField;
    public TextField LField;
    public TextField XField;
    public TextField AField;
    private List<TextArea> codeSet = new ArrayList<>();
    private ULA controlUnity = new ULA();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        memList.getItems().clear();
        memList.getItems().addAll(controlUnity.getMem().dataMemory);
    }
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
        controlUnity.read(code, memList);
    }
    public void codeNextStep(MouseEvent mouseEvent) {
    }
}