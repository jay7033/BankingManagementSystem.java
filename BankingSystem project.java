 import java.util.ArrayList;
import java.util.Scanner;

class Account {
    private int accountId;
    private String accountHolderName;
    private double balance;

    public Account(int accountId, String accountHolderName, double initialBalance) {
        this.accountId = accountId;
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited $" + amount + ". New balance: $" + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew $" + amount + ". New balance: $" + balance);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
        }
    }

    @Override
    public String toString() {
        return "Account ID: " + accountId + ", Holder: " + accountHolderName + ", Balance: $" + balance;
    }
}

class Bank {
    private ArrayList<Account> accounts;
    private int nextAccountId;

    public Bank() {
        accounts = new ArrayList<>();
        nextAccountId = 1;
    }

    public void createAccount(String accountHolderName, double initialBalance) {
        Account newAccount = new Account(nextAccountId++, accountHolderName, initialBalance);
        accounts.add(newAccount);
        System.out.println("Account created successfully. " + newAccount);
    }

    public Account findAccount(int accountId) {
        for (Account account : accounts) {
            if (account.getAccountId() == accountId) {
                return account;
            }
        }
        return null;
    }

    public void listAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
        } else {
            for (Account account : accounts) {
                System.out.println(account);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank();

        while (true) {
            System.out.println("\n--- Bank Management System ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Check Balance");
            System.out.println("5. List All Accounts");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter account holder name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter initial balance: ");
                    double balance = scanner.nextDouble();
                    bank.createAccount(name, balance);
                    break;
                case 2:
                    System.out.print("Enter account ID: ");
                    int depId = scanner.nextInt();
                    Account depAccount = bank.findAccount(depId);
                    if (depAccount != null) {
                        System.out.print("Enter deposit amount: ");
                        double depAmount = scanner.nextDouble();
                        depAccount.deposit(depAmount);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter account ID: ");
                    int withId = scanner.nextInt();
                    Account withAccount = bank.findAccount(withId);
                    if (withAccount != null) {
                        System.out.print("Enter withdrawal amount: ");
                        double withAmount = scanner.nextDouble();
                        withAccount.withdraw(withAmount);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter account ID: ");
                    int balId = scanner.nextInt();
                    Account balAccount = bank.findAccount(balId);
                    if (balAccount != null) {
                        System.out.println("Balance: $" + balAccount.getBalance());
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 5:
                    bank.listAccounts();
                    break;
                case 6:
                    System.out.println("Exiting system.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
