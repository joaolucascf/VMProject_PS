package com.vm.ps.vmproject_ps;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Initialize extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Initialize.class.getResource("codeEditor.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("SIC/XE Simulator");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}