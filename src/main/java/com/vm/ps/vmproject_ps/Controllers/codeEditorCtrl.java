package com.vm.ps.vmproject_ps.Controllers;

import com.vm.ps.vmproject_ps.DataMemView;
import com.vm.ps.vmproject_ps.Memory;
import com.vm.ps.vmproject_ps.ULA;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

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
    public TableView tableMem;
    public TableColumn<DataMemView, String> M0Column;
    public TableColumn<DataMemView, String> M3Column;
    public TableColumn<DataMemView, String> M6Column;
    private List<TextArea> codeSet = new ArrayList<>();
    private ULA controlUnity = new ULA();
    ObservableList<DataMemView> memView = FXCollections.observableArrayList(
            new DataMemView(controlUnity.getMem().dataMemory.get(0),controlUnity.getMem().dataMemory.get(1),controlUnity.getMem().dataMemory.get(2))
    );
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        M0Column.setCellValueFactory(new PropertyValueFactory<DataMemView, String>("memColumn0"));
        M3Column.setCellValueFactory(new PropertyValueFactory<DataMemView, String>("memColumn1"));
        M6Column.setCellValueFactory(new PropertyValueFactory<DataMemView, String>("memColumn2"));
        for(int i=3; i<controlUnity.getMem().dataMemory.size(); i+=3){
            if(i+1>=controlUnity.getMem().dataMemory.size()||i+2>=controlUnity.getMem().dataMemory.size())
                break;
            memView.add(new DataMemView(controlUnity.getMem().dataMemory.get(i),
                    controlUnity.getMem().dataMemory.get(i+1),
                    controlUnity.getMem().dataMemory.get(i+2)));
        }
        tableMem.setItems(memView);
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
        controlUnity.read(code);
    }
    public void codeNextStep(MouseEvent mouseEvent) {
    }
}