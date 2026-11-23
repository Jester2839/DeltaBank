package accounts;

// 2010
// 2102405518

import person.AccountOwner;

public abstract class BankAccount {

    private String uuid;
    private AccountOwner accountOwner;
    private String accountNumber;
    private double balance;
    private String currency;


    // 2 konstruktory pro to aby se pri vytvoreni objektu mohl ale nemusel zadavat balance
    public BankAccount(String uuid, AccountOwner accountOwner, String accountNumber, String currency) {
        this.uuid = uuid;
        this.accountOwner = accountOwner;
        this.accountNumber = accountNumber;
        this.balance = 0;
        this.currency = currency;
    }
    public BankAccount(String uuid, AccountOwner accountOwner, String accountNumber, double balance, String currency) {
        this(uuid, accountOwner, accountNumber, currency);

        this.balance = balance;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public double getBalance() {
        return balance;
    }
    public String getCurrency() {
        return currency;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

}
