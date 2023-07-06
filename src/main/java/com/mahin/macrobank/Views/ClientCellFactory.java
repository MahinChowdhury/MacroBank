package com.mahin.macrobank.Views;

import com.mahin.macrobank.Controllers.Admin.ClientCellContainer;
import com.mahin.macrobank.Controllers.Admin.ClientsListController;
import com.mahin.macrobank.Models.Client;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class ClientCellFactory extends ListCell<Client> {

    protected void updateItem(Client client,boolean empty){
        super.updateItem(client,empty);

        if(empty){
            setText(null);
            setGraphic(null);
        }
        else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/ClientCell.fxml"));
            ClientCellContainer controller = new ClientCellContainer(client);
            loader.setController(controller);
            setText(null);
            try {
                setGraphic(loader.load());
            }catch (Exception e){
                System.out.println("Wrong");
                e.printStackTrace();
            }
        }

    }

}
