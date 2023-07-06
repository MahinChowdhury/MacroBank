package com.mahin.macrobank.Models;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Transaction {
    private final StringProperty sender;
    private final StringProperty receiver;

    private final StringProperty amount;
    private final StringProperty date;
    private final StringProperty message;


    public Transaction(String sender, String receiver, String amount, String date, String message) {
        this.sender = new SimpleStringProperty(this,sender);
        this.receiver = new SimpleStringProperty(this,receiver);
        this.amount = new SimpleStringProperty(this,amount);
        this.date = new SimpleStringProperty(this,date);
        this.message = new SimpleStringProperty(this,message);
    }

    public String getSender() {
        return sender.get();
    }

    public StringProperty senderProperty() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender.set(sender);
    }

    public String getReceiver() {
        return receiver.get();
    }

    public StringProperty receiverProperty() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver.set(receiver);
    }

    public String getAmount() {
        return amount.get();
    }

    public StringProperty amountProperty() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount.set(amount);
    }

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getMessage() {
        return message.get();
    }

    public StringProperty messageProperty() {
        return message;
    }

    public void setMessage(String message) {
        this.message.set(message);
    }
}
