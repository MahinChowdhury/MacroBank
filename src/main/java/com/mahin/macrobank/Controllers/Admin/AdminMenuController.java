package com.mahin.macrobank.Controllers.Admin;

import com.mahin.macrobank.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminMenuController implements Initializable {
    public Button createClient_btn;
    public Button clients_btn;
    public Button deposit_btn;
    public Button logout_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListener();
    }

    private void addListener(){
        createClient_btn.setOnAction(actionEvent -> {
            Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set("CreateClient");
        });
        clients_btn.setOnAction(actionEvent -> {
            Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set("Clients");
        });
        deposit_btn.setOnAction(actionEvent -> {
            Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set("Deposit");
        });
        logout_btn.setOnAction(actionEvent -> {
            Stage stage = (Stage)deposit_btn.getScene().getWindow();
            Model.getInstance().getViewFactory().closeStage(stage);

            Model.getInstance().getViewFactory().ShowLoginWindow();
        });
    }
}
