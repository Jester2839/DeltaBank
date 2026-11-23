package accounts;

import person.AccountOwner;

public class CurrentAccount extends BankAccount {


    public CurrentAccount(String uuid, AccountOwner accountOwner, String accountNumber, String currency) {
        super(uuid, accountOwner, accountNumber, currency);
    }

    public CurrentAccount(String uuid, AccountOwner accountOwner, String accountNumber, double balance, String currency) {
        super(uuid, accountOwner, accountNumber, balance, currency);
    }
}
