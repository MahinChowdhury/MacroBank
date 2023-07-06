package com.mahin.macrobank.Views;

import com.mahin.macrobank.Controllers.Admin.ClientCellContainer;
import com.mahin.macrobank.Controllers.Client.TransactionCellController;
import com.mahin.macrobank.Controllers.Client.TransactionsController;
import com.mahin.macrobank.Models.Client;
import com.mahin.macrobank.Models.Transaction;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class TransactionCellFactory extends ListCell<Transaction> {

    protected void updateItem(Transaction transaction,boolean empty){
        super.updateItem(transaction,empty);

        if(empty){
            setText(null);
            setGraphic(null);
        }
        else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/TransactionCell.fxml"));
            TransactionCellController controller = new TransactionCellController(transaction);
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
