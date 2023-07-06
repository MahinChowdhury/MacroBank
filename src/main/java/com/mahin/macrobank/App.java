package com.mahin.macrobank;

import com.mahin.macrobank.Models.DatabaseDriver;
import com.mahin.macrobank.Models.Model;
import com.mahin.macrobank.Views.ViewFactory;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Model.getInstance().getViewFactory().ShowLoginWindow();

    }
}
