package accounts;

import pearson.AccountOwner;

public class CurrentAccount extends BankAccount {


    public CurrentAccount(String uuid, AccountOwner accountOwner, String accountNumber) {
        super(uuid, accountOwner, accountNumber);
    }

    public CurrentAccount(String uuid, AccountOwner accountOwner, String accountNumber, double balance) {
        super(uuid, accountOwner, accountNumber, balance);
    }
}
