package transfer;

import accounts.BankAccount;
import accounts.BusinessAccount;
import accounts.StudentAccount;

public class WithdrawTransferService {

    private static final double BUSINESS_ACCOUNT_SERVICE_FEE = 0.01;

    public void withdraw(BankAccount account, double amount) {
        double newBalance = account.getBalance() - amount;

        if(account instanceof BusinessAccount) {
            double serviceFee = amount * BUSINESS_ACCOUNT_SERVICE_FEE;

            newBalance -= serviceFee;
        }

        GetWithdrawLimitService getWithdrawLimitService = new GetWithdrawLimitService();
        if(newBalance < getWithdrawLimitService.getWithdrawLimit(account))
        {
            throw new IllegalArgumentException("Cannot subtract negative amount");
        }

        account.setBalance(newBalance);
    }

}
