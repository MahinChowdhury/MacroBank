package com.mahin.macrobank.Models;

import com.mahin.macrobank.Views.AccountType;
import com.mahin.macrobank.Views.ViewFactory;

import java.time.LocalDate;
import java.util.Iterator;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.bson.Document;
import static com.mongodb.client.model.Filters.eq;

public class Model {

    private final ViewFactory viewFactory;
    private final DatabaseDriver databaseDriver;
    private static Model model;

    private Client client;
    private boolean clientLoginSuccess;
    private boolean adminLoginSuccess;
    private AccountType loginAccountType = AccountType.CLIENT;
    private ObservableList<Client>clientArray;
    private ObservableList<Transaction> latestTransactions;
    private ObservableList<Transaction> allTransactions;



    private Model(){
        this.viewFactory = new ViewFactory();
        this.databaseDriver = new DatabaseDriver();
        this.clientLoginSuccess = false;
        this.adminLoginSuccess = false;
        this.client = new Client(null,null,null,null,"0", "0","0","0");
        this.clientArray = FXCollections.observableArrayList();
        this.latestTransactions = FXCollections.observableArrayList();
        this.allTransactions = FXCollections.observableArrayList();
    }

    public boolean isAdminLoginSuccess() {
        return adminLoginSuccess;
    }

    public void setAdminLoginSuccess(boolean adminLoginSuccess) {
        this.adminLoginSuccess = adminLoginSuccess;
    }

    public static synchronized Model getInstance(){
        if(model == null){
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory(){
        return viewFactory;
    }

    public DatabaseDriver getDatabaseDriver(){
        return databaseDriver;
    }

    public AccountType getLoginAccountType() {
        return loginAccountType;
    }

    public void setLoginAccountType(AccountType loginAccountType) {
        this.loginAccountType = loginAccountType;
    }

    // Client Section


    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public boolean isClientLoginSuccess() {
        return clientLoginSuccess;
    }

    public void setClientLoginSuccess(boolean clientLoginSuccess) {
        this.clientLoginSuccess = clientLoginSuccess;
    }


    //Database Section

    public void evaluateClientCred(String pAddress,String password){

        Document doc = databaseDriver.findClient(pAddress,password);

        if(doc != null && doc.get("Password").equals(password)){
            this.client.firstNameProperty().set((String) doc.get("FirstName"));
            this.client.lastNameProperty().set((String) doc.get("LastName"));
            this.client.accountIDProperty().set((String) doc.get("UserID"));
            this.client.balanceProperty().set((String)doc.get("Balance"));
            this.client.dateCreatedProperty().set((String)doc.get("Date"));
            this.client.totalProperty().set((String)doc.get("total"));
            this.client.incomeProperty().set((String)doc.get("income"));
            this.client.expenseProperty().set((String)doc.get("expense"));
            this.clientLoginSuccess = true;
        }
    }

    public void evaluateAdminCred(String pAddress,String password){

        Document doc = databaseDriver.findAdmin(pAddress,password);

        if(doc != null && doc.get("Password").equals(password)){
            this.adminLoginSuccess = true;
        }
    }

    public ObservableList<Client> getClientArray() {
        return clientArray;
    }

    public void parseAllClients(){

        MongoCollection<Document> col = databaseDriver.db.getCollection("Clients");

        try (MongoCursor<Document> cursor = col.find().cursor()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                //System.out.println(doc);

                String fName = (String) doc.get("FirstName");
                String lName = ((String) doc.get("LastName"));
                String userid = ((String) doc.get("UserID"));
                String balance = ((String) doc.get("Balance"));
                String total = ((String) doc.get("total"));
                String date = ((String) doc.get("Date"));
                String income = (String) doc.get("income");
                String expense = (String) doc.get("expense");

                clientArray.add(new Client(fName, lName, userid, date, balance, total,income,expense));
            }
        }
    }

    public void parseTransactions(ObservableList<Transaction> transactions ,int lt,String id){

        MongoCollection<Document> col = databaseDriver.db.getCollection("Transactions");
        int cnt = 0;
        try (MongoCursor<Document> cursor = col.find().cursor()) {
            while (cursor.hasNext()) {

                if(cnt==lt) break;

                Document doc = cursor.next();

                String sender = (String) doc.get("Sender");
                String receiver = (String) doc.get("Receiver");
                String amount = (String) doc.get("Amount");
                String date = (String) doc.get("Date");
                String message = (String) doc.get("Message");

                if(sender.equals(id) || receiver.equals(id)){
                    transactions.add(new Transaction(sender,receiver,amount,date,message));
                    cnt++;
                }
            }
        }
    }

    public void sendMoney(String sender,String receiver,int amount,String message){
        this.databaseDriver.moneySend(sender,receiver,amount);
        this.databaseDriver.insetTransactionDB(sender,receiver,amount,message);
    }

    public void setLatestTransactions(String id){
        parseTransactions(this.latestTransactions,5,id);
    }
    public ObservableList<Transaction> getLatestTransactions(){
        return latestTransactions;
    }
    public void setAllTransactions(String id){
        parseTransactions(this.allTransactions,20,id);
    }
    public ObservableList<Transaction> getAllTransactions(){
        return allTransactions;
    }

    public ObservableList<Client> searchClient(String userid){
        ObservableList<Client>searchResult = FXCollections.observableArrayList();
        Document doc = databaseDriver.searchClient(userid);

        String fName = (String) doc.get("FirstName");
        String lName = ((String) doc.get("LastName"));
        String user = ((String) doc.get("UserID"));
        String balance = ((String) doc.get("Balance"));
        String total = ((String) doc.get("total"));
        String date = ((String) doc.get("Date"));
        String income = (String) doc.get("income");
        String expense = (String) doc.get("expense");

        searchResult.add(new Client(fName,lName,user,date,balance,total,income,expense));

        return searchResult;
    }
}