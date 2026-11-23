package accounts;

import person.AccountOwner;

public class BusinessAccount extends BankAccount implements TransferFeePoint{

    private static final double TRANSFER_FEE_RATE = 0.003;

    public BusinessAccount(String uuid, AccountOwner accountOwner, String accountNumber, String currency) {
        super(uuid, accountOwner, accountNumber, currency);
    }

    public BusinessAccount(String uuid, AccountOwner accountOwner, String accountNumber, double balance, String currency) {
        super(uuid, accountOwner, accountNumber, balance, currency);
    }

    @Override
    public double calculateTransferFee(double amount) {
        return amount * TRANSFER_FEE_RATE;
    }
}

