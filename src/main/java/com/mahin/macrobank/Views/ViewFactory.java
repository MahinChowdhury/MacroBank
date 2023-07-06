package com.mahin.macrobank.Views;

import com.mahin.macrobank.Controllers.Admin.AdminController;
import com.mahin.macrobank.Controllers.Client.ClientController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ViewFactory {

    private AccountType loginAccountType;
    private final StringProperty clientSelectedMenuItem;
    private final StringProperty adminSelectedMenuItem;
    private AnchorPane dashboardView;
    private AnchorPane transactionView;
    private AnchorPane accountView;

    private AnchorPane createClientView;
    private AnchorPane clientsListView;
    private AnchorPane depositView;

    public AccountType getLoginAccountType() {
        return loginAccountType;
    }

    public void setLoginAccountType(AccountType loginAccountType) {
        this.loginAccountType = loginAccountType;
    }

    public ViewFactory(){
        this.loginAccountType = AccountType.CLIENT;
        this.clientSelectedMenuItem = new SimpleStringProperty("");
        this.adminSelectedMenuItem = new SimpleStringProperty("");
    }

    public StringProperty getClientSelectedMenuItem() {
        return clientSelectedMenuItem;
    }
    public StringProperty getAdminSelectedMenuItem() {
        return adminSelectedMenuItem;
    }

    public AnchorPane getDashboardView(){

        if(dashboardView == null) {
            try {
                dashboardView = new FXMLLoader(getClass().getResource("/Fxml/Dashboard.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return dashboardView;
    }

    public AnchorPane getTransactionView(){
        if(transactionView == null){
            try {
                transactionView = new FXMLLoader(getClass().getResource("/Fxml/Transactions.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return transactionView;
    }
    public AnchorPane getAccountView(){
        if(accountView == null){
            try {
                accountView = new FXMLLoader(getClass().getResource("/Fxml/Profile.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return accountView;
    }

    public AnchorPane getCreateClientView(){
        if(createClientView == null){
            try {
                createClientView = new FXMLLoader(getClass().getResource("/Fxml/CreateClient.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return createClientView;
    }
    public AnchorPane getClientListView(){
        if(clientsListView == null){
            try {
                clientsListView = new FXMLLoader(getClass().getResource("/Fxml/ClientsList.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return clientsListView;
    }

    public AnchorPane getDepositView(){
        if(depositView == null){
            try {
                depositView = new FXMLLoader(getClass().getResource("/Fxml/Deposit.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return depositView;
    }

    public void ShowLoginWindow(){

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
        crateStage(loader);
    }

    public void ShowClientWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Client.fxml"));
        ClientController clientController = new ClientController();
        loader.setController(clientController);
        crateStage(loader);
    }

    public void ShowAdminWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin.fxml"));
        AdminController adminController = new AdminController();
        loader.setController(adminController);
        crateStage(loader);
    }

    public void ShowMessageWindow(String senderAdress,String messageText){
        StackPane pane = new StackPane();
        HBox hBox = new HBox(5);
        hBox.setAlignment(Pos.CENTER);
        Label sender = new Label(senderAdress);
        Label message = new Label(messageText);
        hBox.getChildren().addAll(sender,message);
        pane.getChildren().add(hBox);
        Scene scene = new Scene(pane,300,100);
        Stage stage = new Stage();
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/bankIcon.png"))));
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Message");
        stage.setScene(scene);
        stage.show();
    }

    private void crateStage(FXMLLoader loader){
        Scene scene = null;

        try {
            scene = new Scene(loader.load());
        }catch (Exception e){
            e.printStackTrace();
        }

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/bankIcon.png"))));
        stage.setResizable(false);
        stage.setTitle("MACRO");
        stage.show();
    }

    public void closeStage(Stage stage){
        stage.close();
    }


}
