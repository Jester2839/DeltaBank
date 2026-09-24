package transfer;

import accounts.BankAccount;
import accounts.BusinessAccount;
import accounts.StudentAccount;

public class WithdrawTransferService {

    private static final double BUISNESS_ACCOUNT_SERVICE_FEE = 0.01;

    public void withdraw(BankAccount account, double amount){
        double newBalance = account.getBalance() - amount;

        if (account instanceof BusinessAccount){
            double serviceFee = amount * BUISNESS_ACCOUNT_SERVICE_FEE;

            newBalance -= serviceFee;
        }

        if(newBalance < 0) {
            throw new IllegalArgumentException("Nejde to do zaporu jako");
        }

        account.setBalance(newBalance);
    }

    private int getWithdrawLimit(BankAccount account){
        if (account instanceof StudentAccount){
            return  -5000;
        }

        return 0;
    }

}
