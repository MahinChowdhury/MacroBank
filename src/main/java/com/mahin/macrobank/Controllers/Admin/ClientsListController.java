package com.mahin.macrobank.Controllers.Admin;

import com.mahin.macrobank.Models.Client;
import com.mahin.macrobank.Models.Model;
import com.mahin.macrobank.Views.ClientCellFactory;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientsListController implements Initializable {

    public ListView<Client> clients_listview;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initClientsList();
        //System.out.println(Model.getInstance().getClientArray());
        clients_listview.setItems(Model.getInstance().getClientArray());
        clients_listview.setCellFactory(e->new ClientCellFactory());
    }

    private void initClientsList(){
        if(Model.getInstance().getClientArray().isEmpty()){
            Model.getInstance().parseAllClients();
        }
    }
}
