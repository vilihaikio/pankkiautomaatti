package com.company;

import java.io.*;
import java.util.Vector;

public class AccountRepo implements Serializable {
    private Vector<Account> accounts;
    private String path;

    public AccountRepo(String path) {
        this.accounts = new Vector<>();
        this.path = path;
        readAccounts();
    }

    public Account returnRealAccount (String name) {
        for (Account account : accounts ) {
            if (account.getName().equals(name)) {
                return account;
            }
        }
        return null;
    }

    public Vector<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Vector<Account> accounts) {
        this.accounts = accounts;
    }

    public void addAccount(Account account) {
        accounts.addElement(account);
    }

    public Account findAccountByName(String nameString) {
        for (Account acc : accounts) {
            if (acc.getName().equals(nameString)) {
                return acc;
            }
        }
        return null;
    }

    public void saveAccounts() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path))) {
            for (Account account : accounts) {
                objectOutputStream.writeObject(account);
            }
        } catch (IOException e) {
            System.out.println("Accounts saved!");
        }
    }

    public void readAccounts() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path))) {
            Account account = (Account) objectInputStream.readObject();
            while (account != null) {
                accounts.addElement(account);
                account = (Account) objectInputStream.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Accounts read");
        }
    }
}
