package com.mahin.macrobank.Controllers.Client;

import com.mahin.macrobank.Models.Model;
import com.mahin.macrobank.Models.Transaction;
import com.mahin.macrobank.Views.TransactionCellFactory;

import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    public Label usernameLabel;
    public Label cashLabel;
    public Label totalTransLabel;
    public Label incomeLabel;
    public Label expenseLabel;
    public ListView<Transaction> transaction_listview;
    public TextField amount_field;
    public TextArea message_field;
    public Button sendmoney_btn;
    public Label dateLabel;
    public TextField payee_field;
    public Text userid_lbl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bindData();
        initLatestTransactionsList();
        transaction_listview.setItems(Model.getInstance().getLatestTransactions());
        transaction_listview.setCellFactory(e-> new TransactionCellFactory());

        sendmoney_btn.setOnAction(actionEvent -> onSendMoney());
    }
    private void bindData(){
        usernameLabel.textProperty().bind(Bindings.concat(Model.getInstance().getClient().firstNameProperty()).concat(" ").concat(Model.getInstance().getClient().lastNameProperty()));
        dateLabel.setText("Today, " + LocalDate.now());
        cashLabel.textProperty().bind(Bindings.concat("$").concat(Model.getInstance().getClient().balanceProperty()));
        userid_lbl.textProperty().bind(Model.getInstance().getClient().accountIDProperty());
        totalTransLabel.textProperty().bind(Bindings.concat("$").concat(Model.getInstance().getClient().totalProperty()));
        incomeLabel.textProperty().bind(Bindings.concat("+").concat(Model.getInstance().getClient().incomeProperty()));
        expenseLabel.textProperty().bind(Bindings.concat("-").concat(Model.getInstance().getClient().expenseProperty()));
    }
    private void initLatestTransactionsList(){
        if(Model.getInstance().getLatestTransactions().isEmpty()){
            Model.getInstance().setLatestTransactions(userid_lbl.getText());
        }
    }

    private void onSendMoney(){
        String pAdress = payee_field.getText();
        int amount = Integer.parseInt(amount_field.getText());
        String message = message_field.getText();
        String sender = Model.getInstance().getClient().accountIDProperty().get();

        Model.getInstance().sendMoney(sender,pAdress,amount,message);

        payee_field.setText("");
        amount_field.setText("");
        message_field.setText("");

        int cur = Integer.parseInt(Model.getInstance().getClient().balanceProperty().get());
        cur -= amount;
        String value = Integer.toString(cur);
        Model.getInstance().getClient().balanceProperty().set(value);

        cur = Integer.parseInt(Model.getInstance().getClient().totalProperty().get());
        cur += amount;
        value = Integer.toString(cur);
        Model.getInstance().getClient().totalProperty().set(value);

        cur = Integer.parseInt(Model.getInstance().getClient().expenseProperty().get());
        cur += amount;
        value = Integer.toString(cur);
        Model.getInstance().getClient().expenseProperty().set(value);

    }
}