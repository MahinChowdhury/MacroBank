package com.mahin.macrobank.Controllers.Client;

import com.mahin.macrobank.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientMenuController implements Initializable {

    public Button dashboard_btn;
    public Button transaction_btn;
    public Button logout_btn;
    public Button profile_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListener();
    }

    private void addListener(){
        dashboard_btn.setOnAction(actionEvent -> {
            Model.getInstance().getViewFactory().getClientSelectedMenuItem().set("Dashboard");
        });

        transaction_btn.setOnAction(actionEvent -> {
            Model.getInstance().getViewFactory().getClientSelectedMenuItem().set("Transactions");
        });
        profile_btn.setOnAction(actionEvent -> {
            Model.getInstance().getViewFactory().getClientSelectedMenuItem().set("Account");
        });
        logout_btn.setOnAction(actionEvent -> {
            Stage stage = (Stage)dashboard_btn.getScene().getWindow();
            Model.getInstance().getViewFactory().closeStage(stage);

            Model.getInstance().getViewFactory().ShowLoginWindow();
        });
    }
}
