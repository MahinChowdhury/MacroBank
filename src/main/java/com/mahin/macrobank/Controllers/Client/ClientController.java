package com.mahin.macrobank.Controllers.Client;

import com.mahin.macrobank.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientController implements Initializable {

    public BorderPane client_parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().addListener((observableValue, oldVal, newVal) -> {
            switch (newVal){
                case "Transactions" -> client_parent.setCenter(Model.getInstance().getViewFactory().getTransactionView());
                case "Account" -> client_parent.setCenter(Model.getInstance().getViewFactory().getAccountView());
                default -> client_parent.setCenter(Model.getInstance().getViewFactory().getDashboardView());
            }
        });
    }
}
