package com.company;

import java.io.Serializable;
import java.nio.file.attribute.UserPrincipalLookupService;

public class Account implements Serializable {
    private String name;
    private String password;
    private long amount;

    public Account(String name, String password, long amount) {
        this.name = name;
        this.password = password;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public boolean isPasswordRight(String password) {
        return this.password.equals(password);
    }

    @Override
    public String toString() {
        return "Account : " + name + " " + password + " " + amount;
    }

    public void printBalance() {
        System.out.println("Your balance : " + amount);
    }

    public void moneyDeposit(UserInput userInput) {

        System.out.println("");
        long increment = userInput.readLong("How much would you like to deposit?");
        amount = amount + increment;
        System.out.println("Money deposited successfully. Your new balance is: " + amount);
    }

    public void moneyWithdraw(UserInput userInput) {

        System.out.println("");
        long dec = userInput.readLong("How much would you like to withdraw?");
        if (dec < amount) {
            amount -= dec;
            System.out.println("Money withdrawn successfully. Your new balance is: " + amount);
        } else {
            System.out.println("You don't have that much money!");
        }

    }

    public void moneyTransfer(UserInput userInput, AccountRepo accountRepo) {
        String receiverInput = userInput.readString("Search for user to receive the money");
        Account acco = accountRepo.findAccountByName(receiverInput);
        if (acco != null) {
            System.out.println("User found.");
            long amountInput = userInput.readLong("How much would you like to transfer");
            if (amountInput <= amount) {
                acco.amount += amountInput;
                amount -= amountInput;
                System.out.println("Transferred succesfully!");
            } else {
                System.out.println("You don't have that much money!");
            }
        } else {
            System.out.println("Couldn't find anyone with that name!");
        }
    }


}
