package accounts;

import person.AccountOwner;

public class SavingAccount extends BankAccount implements InterestPoint{
    private static final float INTEREST_RATE = 0.05f;
    private static final float SUPER_ADD = 0.005f;

    public SavingAccount(String uuid, AccountOwner accountOwner, String accountNumber, String currency) {
        super(uuid, accountOwner, accountNumber, currency);
    }

    public SavingAccount(String uuid, AccountOwner accountOwner, String accountNumber, double balance, String currency) {
        super(uuid, accountOwner, accountNumber, balance, currency);
    }

    @Override
    public void calculateInterest(){
        double interest = getBalance() + INTEREST_RATE;
    }
}
