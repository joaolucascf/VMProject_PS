package com.vm.ps.vmproject_ps.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

public class codeEditorCtrl{
    @FXML
    public Button newFileBtn;
    public TabPane tabPaneEditor;
    public Button deleteFileBtn;
    public void newFile(MouseEvent mouseEvent) {
        tabPaneEditor.getTabs().add(createDefaultTab());
    }
    public Tab createDefaultTab(){
        Tab newTab = new Tab("New Tab");
        TextArea emptyTextArea = new TextArea();
        newTab.setContent(emptyTextArea);
        return newTab;
    }
    public void closeFile(MouseEvent mouseEvent) {
        Iterator<Tab> tabIterator = tabPaneEditor.getTabs().iterator();
        while(tabIterator.hasNext()){
            Tab selectedTab = tabIterator.next();
            if(selectedTab.isSelected()){
                tabPaneEditor.getTabs().remove(selectedTab);
                return;
            }
        }
    }
}