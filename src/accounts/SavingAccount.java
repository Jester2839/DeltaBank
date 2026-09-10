package accounts;

import pearson.AccountOwner;

public class SavingAccount extends BankAccount{

    public SavingAccount(AccountOwner accountOwner, String accountNumber) {
        super(accountOwner, accountNumber);
    }

    public SavingAccount(AccountOwner accountOwner, String accountNumber, double balance) {
        super(accountOwner, accountNumber, balance);
    }

    @Override
    public void add(double amount) {

        double bonusAmount = amount * 0.005;

        super.add(bonusAmount);
        super.add(amount);
    }
}
