package com.mahin.macrobank.Models;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Client {

    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty accountID;
    private final StringProperty dateCreated;
    private final StringProperty balance;
    private final StringProperty total;

    private final StringProperty income;
    private final StringProperty expense;

    public Client(String firstName, String lastName, String accountID, String dateCreated, String balance, String total,String income,String expense) {
        this.firstName = new SimpleStringProperty(this,firstName);
        this.lastName = new SimpleStringProperty(this,lastName);
        this.accountID = new SimpleStringProperty(this,accountID);
        this.dateCreated = new SimpleStringProperty(this,dateCreated);
        this.balance = new SimpleStringProperty(this,balance);
        this.total = new SimpleStringProperty(this,total);
        this.income = new SimpleStringProperty(this,income);
        this.expense = new SimpleStringProperty(this,expense);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public String getIncome() {
        return income.get();
    }

    public StringProperty incomeProperty() {
        return income;
    }

    public void setIncome(String income) {
        this.income.set(income);
    }

    public String getExpense() {
        return expense.get();
    }

    public StringProperty expenseProperty() {
        return expense;
    }

    public void setExpense(String expense) {
        this.expense.set(expense);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
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

    public String getDateCreated() {
        return dateCreated.get();
    }

    public StringProperty dateCreatedProperty() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated.set(dateCreated);
    }

    public String getBalance() {
        return balance.get();
    }

    public StringProperty balanceProperty() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance.set(balance);
    }

    public String getTotal() {
        return total.get();
    }

    public StringProperty totalProperty() {
        return total;
    }

    public void setTotal(String total) {
        this.total.set(total);
    }
}
