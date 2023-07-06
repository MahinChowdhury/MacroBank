package com.mahin.macrobank.Controllers.Admin;

import com.mahin.macrobank.Models.Client;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientCellContainer implements Initializable {

    public Label fName_lbl;
    public Label lName_lbl;
    public Label pAddress_lbl;
    public Label crateDate_lbl;
    public Button delete_btn;

    private final Client client;
    public ClientCellContainer(Client client) {
        this.client = client;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fName_lbl.setText(client.firstNameProperty().getName());
        lName_lbl.setText(client.lastNameProperty().getName());
        pAddress_lbl.setText(client.accountIDProperty().getName());
        crateDate_lbl.setText(client.dateCreatedProperty().getName());

        //System.out.println(client.firstNameProperty().getName());
    }
}
