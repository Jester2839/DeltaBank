package transfer;

import accounts.BankAccount;
import accounts.TransferFeePoint;
import globalServices.GetWithdrawLimitService;
import globalServices.TransferLoggerService;
import transactions.TransactionFactory;
import transactions.TransactionStatus;
import transactions.TransactionType;

public class TransferTransferService {

    // studentsky ucet jako jediny muze pri prevodu jit do zaporu, a to az do -5000

    private final TransferLoggerService transferLoggerService;
    private final TransactionFactory transactionFactory = new TransactionFactory();

    public TransferTransferService(TransferLoggerService transferLoggerService) {
        this.transferLoggerService = transferLoggerService;
    }

    public void transfer(BankAccount sourceAccount, BankAccount targetAccount, double amount){
        if (sourceAccount == null || targetAccount == null) {
            logTransaction(sourceAccount, targetAccount, amount, TransactionStatus.FAILED);
            throw new IllegalArgumentException("Zdrojovy i cilovy ucet musi byt zadan");
        }

        if (amount < 0) {
            logTransaction(sourceAccount, targetAccount, amount, TransactionStatus.FAILED);
            throw new IllegalArgumentException("Castka prevodu nesmi byt zaporna");
        }

        if (sourceAccount == targetAccount) {
            logTransaction(sourceAccount, targetAccount, amount, TransactionStatus.FAILED);
            throw new IllegalArgumentException("Nelze prevadet na stejny ucet");
        }

        double totalAmount = amount + getTransferFee(sourceAccount, amount);
        double newSourceBalance = sourceAccount.getBalance() - totalAmount;

        GetWithdrawLimitService getWithdrawLimitService = new GetWithdrawLimitService();
        // ucet nesmi jit do zaporu, vyjimkou je studentsky ucet s povolenym limitem
        if (newSourceBalance < getWithdrawLimitService.getWithdrawLimit(sourceAccount)) {
            logTransaction(sourceAccount, targetAccount, amount, TransactionStatus.FAILED);
            throw new IllegalArgumentException("Na uctu neni dostatek prostredku pro prevod");
        }

        sourceAccount.setBalance(newSourceBalance);
        targetAccount.setBalance(targetAccount.getBalance() + amount);

        logTransaction(sourceAccount, targetAccount, amount, TransactionStatus.SUCCESS);
    }

    // zaznamena transakci do historie; chybejici (null) ucet zustava v logu prazdny
    private void logTransaction(BankAccount sourceAccount, BankAccount targetAccount, double amount, TransactionStatus status){
        String sourceAccountNumber = sourceAccount != null ? sourceAccount.getAccountNumber() : null;
        String targetAccountNumber = targetAccount != null ? targetAccount.getAccountNumber() : null;

        transferLoggerService.log(transactionFactory.createTransaction(sourceAccountNumber, targetAccountNumber, amount, TransactionType.TRANSFER, status));
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

