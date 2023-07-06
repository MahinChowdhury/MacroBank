package com.mahin.macrobank.Controllers;

import com.mahin.macrobank.Models.Model;
import com.mahin.macrobank.Views.AccountType;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public Button login_btn;
    public Label choice_promt_text;
    public Label error_lb;
    public ChoiceBox<AccountType> account_selector;
    public TextField username_fld;
    public TextField password_fld;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        account_selector.setItems(FXCollections.observableArrayList(AccountType.CLIENT,AccountType.ADMIN));
        account_selector.setValue(Model.getInstance().getViewFactory().getLoginAccountType());
        account_selector.valueProperty().addListener(observable -> Model.getInstance().getViewFactory().setLoginAccountType(account_selector.getValue()));
        login_btn.setOnAction(actionEvent -> onLogin());
    }

    public void onLogin(){

        Stage stage = (Stage) login_btn.getScene().getWindow();

        if(Model.getInstance().getViewFactory().getLoginAccountType() == AccountType.CLIENT){
            Model.getInstance().evaluateClientCred(username_fld.getText(),password_fld.getText());
            if(Model.getInstance().isClientLoginSuccess()){
                Model.getInstance().getViewFactory().closeStage(stage);
                Model.getInstance().getViewFactory().ShowClientWindow();
            }
            else{
                username_fld.setText("");
                password_fld.setText("");
                error_lb.setText("Username or Password in incorrect.");
            }
        }
        else{

            Model.getInstance().evaluateAdminCred(username_fld.getText(),password_fld.getText());

            if(Model.getInstance().isAdminLoginSuccess()){
                Model.getInstance().getViewFactory().closeStage(stage);
                Model.getInstance().getViewFactory().ShowAdminWindow();
            }
            else{
                username_fld.setText("");
                password_fld.setText("");
                error_lb.setText("Username or Password in incorrect.");
            }
        }
    }
}
