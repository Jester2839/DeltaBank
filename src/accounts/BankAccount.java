package accounts;

// 2010
// 2102405518

import notifier.ConsoleNotifierService;
import notifier.EmailNotifierService;
import notifier.NotifierService;
import pearson.AccountOwner;

import java.util.UUID;

public abstract class BankAccount {

    private String uuid;
    private AccountOwner accountOwner;
    private String accountNumber;
    private double balance;
    private NotifierService notifierService = new EmailNotifierService();


    // 2 konstruktory pro to aby se pri vytvoreni objektu mohl ale nemusel zadavat balance
    public BankAccount(String uuid, AccountOwner accountOwner, String accountNumber) {
        this.uuid = uuid;
        this.accountOwner = accountOwner;
        this.accountNumber = accountNumber;
        this.balance = 0;
    }
    public BankAccount(String uuid, AccountOwner accountOwner, String accountNumber, double balance) {
        this(uuid, accountOwner, accountNumber);

        this.balance = balance;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

}
