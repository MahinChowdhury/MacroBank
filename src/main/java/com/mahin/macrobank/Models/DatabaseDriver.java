package com.mahin.macrobank.Models;

import com.mongodb.client.*;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.Iterator;

import static com.mongodb.client.model.Filters.eq;


public class DatabaseDriver {

    MongoClient client;
    MongoDatabase db;
    public DatabaseDriver(){
        this.client = MongoClients.create("mongodb://localhost:27017/");
        this.db = client.getDatabase("MacroBank");
    }

    //Client Section

    public Document findClient(String userID,String password){
        MongoCollection<Document> col = db.getCollection("Clients");

        Document doc = col.find(eq("UserID",userID)).first();

        return doc;
    }

    public void insertClient(String fname,String lname,String pass,String date,String userid,String balance){
        MongoCollection<Document> col = db.getCollection("Clients");

        col.insertOne(new Document().append("FirstName",fname).append("LastName",lname).append("Password",pass)
                .append("UserID",userid).append("Date",date).append("Balance",balance).append("total","0").append("income","0").append("expense","0"));

    }

    public MongoCollection<Document> getTrasaction(){
        MongoCollection<Document> col = db.getCollection("Clients");
        return col;
    }

    public void moneySend(String sender,String receiver,int amount){
        MongoCollection<Document> col = db.getCollection("Clients");

        Document from = col.find(eq("UserID",sender)).first();
        Document to = col.find(eq("UserID",receiver)).first();

        String senderBalance = (String) from.get("Balance");

        int cur = Integer.parseInt(senderBalance);
        cur -= amount;
        senderBalance = Integer.toString(cur);

        col.updateOne(eq("UserID",sender), Updates.set("Balance",senderBalance));

        String exStr = (String) from.get("expense");
        int current_ex = Integer.parseInt(exStr);
        current_ex += amount;
        exStr = Integer.toString(current_ex);

        col.updateOne(eq("UserID",sender), Updates.set("expense",exStr));

        String receiverBalance = (String) to.get("Balance");

        cur = Integer.parseInt(receiverBalance);
        cur += amount;
        receiverBalance = Integer.toString(cur);

        col.updateOne(eq("UserID",receiver), Updates.set("Balance",receiverBalance));

        String inStr = (String) to.get("income");
        int current_in = Integer.parseInt(inStr);
        current_in += amount;
        inStr = Integer.toString(current_in);

        col.updateOne(eq("UserID",receiver), Updates.set("income",inStr));

        String totStr = (String) to.get("total");
        int total = Integer.parseInt(totStr);
        total += amount;
        totStr = Integer.toString(total);

        col.updateOne(eq("UserID",receiver), Updates.set("total",totStr));
    }

    public void insetTransactionDB(String sender,String receiver,int amount,String message){

        MongoCollection<Document> col = db.getCollection("Transactions");

        String date = String.valueOf(LocalDate.now());

        col.insertOne(new Document().append("Sender",sender).append("Receiver",receiver).append("Amount",Integer.toString(amount))
                .append("Date",date).append("Message",message));

    }

    //Admin section

    public Document findAdmin(String userID,String password){
        MongoCollection<Document> col = db.getCollection("Admins");

        Document doc = col.find(eq("UserID",userID)).first();

        return doc;
    }

    public Document searchClient(String username){

        MongoCollection<Document> col = db.getCollection("Clients");

        Document doc = col.find(eq("UserID",username)).first();
        return doc;
    }

    public void depositDB(String userid,int amount){

        MongoCollection<Document> col = db.getCollection("Clients");

        Document doc = col.find(eq("UserID",userid)).first();

        String balance = (String) doc.get("Balance");

        int bal = Integer.parseInt(balance);
        bal += amount;

        balance = Integer.toString(bal);

        col.updateOne(eq("UserID", userid), Updates.set("Balance", balance));
    }

    //Utility Methods

}
