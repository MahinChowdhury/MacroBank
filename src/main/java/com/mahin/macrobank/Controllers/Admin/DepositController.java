package com.mahin.macrobank.Controllers.Admin;

import com.mahin.macrobank.Models.Client;
import com.mahin.macrobank.Models.Model;
import com.mahin.macrobank.Views.ClientCellFactory;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DepositController implements Initializable {

    public Button search_btn;
    public ListView<Client> result_listview;
    public TextField amount_fld;
    public Button deposit_btn;
    public TextField address_fld;
    public Label deposit_verdict;

    private Client client;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        search_btn.setOnAction(actionEvent -> onClientSearch());
        deposit_btn.setOnAction(actionEvent -> onDepost());
    }

    private void onClientSearch(){
        ObservableList<Client> searchResults = Model.getInstance().searchClient(address_fld.getText());
        result_listview.setItems(searchResults);
        result_listview.setCellFactory(e -> new ClientCellFactory());

        client = searchResults.get(0);
    }

    private void onDepost(){
        int amount = Integer.parseInt(amount_fld.getText());
        Model.getInstance().getDatabaseDriver().depositDB(address_fld.getText(),amount);
        amount_fld.setText("");
        deposit_verdict.setText("Deposit done successfully.");

    }

}
