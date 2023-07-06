package com.mahin.macrobank.Controllers.Client;

import com.mahin.macrobank.Models.Model;
import com.mahin.macrobank.Models.Transaction;
import com.mahin.macrobank.Views.TransactionCellFactory;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionsController implements Initializable {
    public ListView<Transaction>transactions_list;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initAllTransactionsList();
        //System.out.println(Model.getInstance().getAllTransactions());
        transactions_list.setItems(Model.getInstance().getAllTransactions());
        transactions_list.setCellFactory(e-> new TransactionCellFactory());
    }

    private void initAllTransactionsList(){
        if(Model.getInstance().getAllTransactions().isEmpty()){
            Model.getInstance().setAllTransactions((String)Model.getInstance().getClient().accountIDProperty().get());
        }
    }
}