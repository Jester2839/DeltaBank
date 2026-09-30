package transfer;

import accounts.BankAccount;
import accounts.TransferFeePoint;

public class TransferTransferService {

    public void transfer(BankAccount sourceAccount, BankAccount targetAccount, double amount){
        if (sourceAccount == null || targetAccount == null) {
            throw new IllegalArgumentException("Zdrojovy i cilovy ucet musi byt zadan");
        }

        if (amount < 0) {
            throw new IllegalArgumentException("Castka prevodu nesmi byt zaporna");
        }

        if (sourceAccount == targetAccount) {
            throw new IllegalArgumentException("Nelze prevadet na stejny ucet");
        }

        double totalAmount = amount + getTransferFee(sourceAccount, amount);

        sourceAccount.setBalance(sourceAccount.getBalance() - totalAmount);
        targetAccount.setBalance(targetAccount.getBalance() + amount);
    }

    // poplatek se uctuje jen tem uctum, ktere si ho samy definuji
    private double getTransferFee(BankAccount sourceAccount, double amount){
        if (sourceAccount instanceof TransferFeePoint){
            TransferFeePoint feePoint = (TransferFeePoint) sourceAccount;

            return feePoint.calculateTransferFee(amount);
        }

        return 0;
    }

}

