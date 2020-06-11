package com.company;

public class Main {
    AccountRepo accountRepo = new AccountRepo("accounts.dat");
    UserInput userInput = new UserInput();

    public static void main(String[] args) {
        Main main = new Main();
        main.startMachine();
    }

    public void startMachine() {
        startMenu();
    }

    public void startMenu() {
        while (true) {
            System.out.println("");
            System.out.println("******** Welcome! ********");
            System.out.println("* Login (l)              *");
            System.out.println("* Create new account (c) *");
            System.out.println("* Shut down (s)          *");
            System.out.println("**************************");
            System.out.println("");
            String input = userInput.readString("l, c or s");
            if (!input.isBlank() && input.toLowerCase().charAt(0) == 'l') {
                login();
            } else if (!input.isBlank() && input.toLowerCase().charAt(0) == 'c') {
                newUser();
            } else if (!input.isBlank() && input.toLowerCase().charAt(0) == 's') {
                System.out.println("");
                System.out.println("Shut down!");
                accountRepo.saveAccounts();
                break;
            } else {
                System.out.println("Enter l, c or s!");
            }
        }

    }


    public void newUser() {
        while (true) {
            System.out.println("Create new account");
            String name = userInput.readString("Your name");
            String password = userInput.readString("Set password");
            long amount = userInput.readLong("Cash amount");
            Account account = new Account(name, password, amount);
            accountRepo.addAccount(account);
            String saveUser = userInput.readString("Save account (s)");
            if (!saveUser.isBlank() && saveUser.charAt(0) == 's') {
                accountRepo.addAccount(account);
                break;
            }
        }
    }

    public void mainMenu(Account account) {
        while (true) {
            System.out.println("");
            System.out.println("********* Menu ********");
            System.out.println("* View balance (b)    *");
            System.out.println("* Deposit (d)         *");
            System.out.println("* Withdraw (w)        *");
            System.out.println("* Credit transfer (t) *");
            System.out.println("* Log out (x)         *");
            System.out.println("***********************");
            System.out.println("");
            String input = userInput.readString("Pick an action");
            if (!input.isBlank() && input.toLowerCase().charAt(0) == 'b') {
                account.printBalance();
            } else if (!input.isBlank() && input.toLowerCase().charAt(0) == 'd') {
                account.moneyDeposit(userInput);
            } else if (!input.isBlank() && input.toLowerCase().charAt(0) == 'w') {
                account.moneyWithdraw(userInput);
            } else if (!input.isBlank() && input.toLowerCase().charAt(0) == 't') {
                account.moneyTransfer(userInput, accountRepo);
            } else if (!input.isBlank() && input.toLowerCase().charAt(0) == 'x') {
                break;
            } else {
                System.out.println("Pick an action");
            }

        }

    }

    public void login() {
        Account account;
        while (true) {
            String name = userInput.readString("Name");
            account = accountRepo.returnRealAccount(name);
            if (account != null) {
                break;
            }
            else System.out.println("Incorrect username");
        }
        while (true) {
            String password = userInput.readString("Password");
            if (account.isPasswordRight(password)) {
                break;
            }
            else System.out.print("Incorrect password");
        }
        System.out.println("You have logged in succesfully");
        mainMenu(account);
    }
}

