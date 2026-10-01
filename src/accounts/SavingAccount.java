package accounts;

import pearson.AccountOwner;

public class SavingAccount extends BankAccount implements InterestPoint{
    private static final float INTEREST_RATE = 0.05f;
    private static final float SUPER_ADD = 0.005f;

    public SavingAccount(String uuid, AccountOwner accountOwner, String accountNumber) {
        super(uuid, accountOwner, accountNumber);
    }

    public SavingAccount(String uuid, AccountOwner accountOwner, String accountNumber, double balance) {
        super(uuid, accountOwner, accountNumber, balance);
    }

    @Override
    public void calculateInterest(){
        double interest = getBalance() + INTEREST_RATE;
    }
}
