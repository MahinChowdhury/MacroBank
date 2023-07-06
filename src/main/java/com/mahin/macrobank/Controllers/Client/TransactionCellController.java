package com.mahin.macrobank.Controllers.Client;

import com.mahin.macrobank.Models.Model;
import com.mahin.macrobank.Models.Transaction;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionCellController implements Initializable {

    public FontAwesomeIconView in_arrow;
    public FontAwesomeIconView out_arrow;
    public Label trans_date_lbl;
    public Label sender_lbl;
    public Label receiver_lbl;
    public Label amount_lbl;
    public Button message_btn;

    private final Transaction transaction;

    public TransactionCellController(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //System.out.println("Call");

         sender_lbl.setText(transaction.senderProperty().getName());
         receiver_lbl.setText(transaction.receiverProperty().getName());
         amount_lbl.setText(transaction.amountProperty().getName());
         trans_date_lbl.setText(transaction.dateProperty().getName());

        message_btn.setOnAction(actionEvent -> Model.getInstance().getViewFactory().ShowMessageWindow(transaction.senderProperty().getName(),transaction.messageProperty().getName()));
    }

}
