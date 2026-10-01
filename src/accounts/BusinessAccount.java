package accounts;

import pearson.AccountOwner;

public class BusinessAccount extends BankAccount implements TransferFeePoint{

    private static final double TRANSFER_FEE_RATE = 0.003;

    public BusinessAccount(String uuid, AccountOwner accountOwner, String accountNumber) {
        super(uuid, accountOwner, accountNumber);
    }

    public BusinessAccount(String uuid, AccountOwner accountOwner, String accountNumber, double balance) {
        super(uuid, accountOwner, accountNumber, balance);
    }

    @Override
    public double calculateTransferFee(double amount) {
        return amount * TRANSFER_FEE_RATE;
    }
}

