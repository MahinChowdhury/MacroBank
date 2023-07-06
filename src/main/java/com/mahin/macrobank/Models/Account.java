package com.mahin.macrobank.Models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Account {

    private final StringProperty owner;
    private final StringProperty accountID;
    private final DoubleProperty balance;

    protected Account(StringProperty owner, StringProperty accountID, DoubleProperty balance) {
        this.owner = new SimpleStringProperty(this,"Owner");
        this.accountID = new SimpleStringProperty(this,"accountID");
        this.balance = new SimpleDoubleProperty(this,"Balance");
    }

    public String getOwner() {
        return owner.get();
    }

    public StringProperty ownerProperty() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner.set(owner);
    }

    public String getAccountID() {
        return accountID.get();
    }

    public StringProperty accountIDProperty() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID.set(accountID);
    }

    public double getBalance() {
        return balance.get();
    }

    public DoubleProperty balanceProperty() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance.set(balance);
    }
}
