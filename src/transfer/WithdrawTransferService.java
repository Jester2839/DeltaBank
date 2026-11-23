package transfer;

import accounts.BankAccount;
import accounts.BusinessAccount;
import transactions.TransactionFactory;
import transactions.TransactionStatus;
import transactions.TransactionType;

public class WithdrawTransferService {

    private static final double BUSINESS_ACCOUNT_SERVICE_FEE = 0.01;

    private final TransferLoggerService transferLoggerService;
    private final TransactionFactory transactionFactory;
    private final GetWithdrawLimitService getWithdrawLimitService;

    public WithdrawTransferService(TransferLoggerService transferLoggerService, TransactionFactory transactionFactory, GetWithdrawLimitService getWithdrawLimitService) {
        this.transferLoggerService = transferLoggerService;
        this.transactionFactory = transactionFactory;
        this.getWithdrawLimitService = getWithdrawLimitService;
    }

    public void withdraw(BankAccount account, double amount) {
        double newBalance = account.getBalance() - amount;

        if(account instanceof BusinessAccount) {
            double serviceFee = amount * BUSINESS_ACCOUNT_SERVICE_FEE;

            newBalance -= serviceFee;
        }

        if(newBalance < getWithdrawLimitService.getWithdrawLimit(account))
        {
            transferLoggerService.log(transactionFactory.createTransaction(account.getAccountNumber(), null, amount, TransactionType.WITHDRAW, TransactionStatus.FAILED));
            throw new IllegalArgumentException("Cannot subtract negative amount");
        }

        account.setBalance(newBalance);

        transferLoggerService.log(transactionFactory.createTransaction(account.getAccountNumber(), null, amount, TransactionType.WITHDRAW, TransactionStatus.SUCCESS));
    }

}
