package accounts;

import pearson.AccountOwner;

public class BusinessAccount extends BankAccount implements TransferFeePoint{

    private static final double TRANSFER_FEE_RATE = 0.003;

    public BusinessAccount(AccountOwner accountOwner, String accountNumber) {
        super(accountOwner, accountNumber);
    }

    public BusinessAccount(AccountOwner accountOwner, String accountNumber, double balance) {
        super(accountOwner, accountNumber, balance);
    }

    @Override
    public double calculateTransferFee(double amount) {
        return amount * TRANSFER_FEE_RATE;
    }
}

