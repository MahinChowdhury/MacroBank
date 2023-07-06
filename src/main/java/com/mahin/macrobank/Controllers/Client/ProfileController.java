package com.mahin.macrobank.Controllers.Client;

import com.mahin.macrobank.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
    public Text lname_fld;
    public Text name_fld;
    public Text id_fld;
    public Text current_fld;
    public Text total_fld;
    public Text income_fld;
    public Text expense_fld;
    public Text date_fld;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name_fld.setText(Model.getInstance().getClient().getFirstName());
        lname_fld.setText(Model.getInstance().getClient().getLastName());
        id_fld.setText(Model.getInstance().getClient().getAccountID());
        current_fld.setText(Model.getInstance().getClient().getBalance());
        total_fld.setText(Model.getInstance().getClient().getTotal());
        income_fld.setText(Model.getInstance().getClient().getIncome());
        expense_fld.setText(Model.getInstance().getClient().getExpense());
        date_fld.setText(Model.getInstance().getClient().getDateCreated());
    }
}
