package accounts;

import pearson.AccountOwner;

public class BusinessAccount extends BankAccount{
    public BusinessAccount(AccountOwner accountOwner, String accountNumber) {
        super(accountOwner, accountNumber);
    }

    public BusinessAccount(AccountOwner accountOwner, String accountNumber, double balance) {
        super(accountOwner, accountNumber, balance);
    }
}
