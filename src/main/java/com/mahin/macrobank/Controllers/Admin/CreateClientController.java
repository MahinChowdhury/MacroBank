package com.mahin.macrobank.Controllers.Admin;

import com.mahin.macrobank.Models.DatabaseDriver;
import com.mahin.macrobank.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class CreateClientController implements Initializable {
    public TextField fName_fld;
    public TextField lName_fld;
    public TextField password_fld;
    public TextField accountId_fld;
    public TextField newBalance_fld;
    public Button createNew_btn;
    public Label error_lbl;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        addListener();
    }

    public void addListener(){
        createNew_btn.setOnAction(actionEvent -> {
                CreateClient();
                fName_fld.setText("");
                lName_fld.setText("");
                password_fld.setText("");
                accountId_fld.setText("");
                newBalance_fld.setText("");
                error_lbl.setText("Successfully Created.");
        });
    }

    public void CreateClient(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.now();
        String date = String.valueOf(now);

        Model.getInstance().getDatabaseDriver().insertClient(fName_fld.getText(),lName_fld.getText(),password_fld.getText(),date,accountId_fld.getText(),newBalance_fld.getText());
    }
}
