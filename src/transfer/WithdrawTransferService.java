package transfer;

import accounts.BankAccount;
import accounts.BusinessAccount;
import globalServices.GetWithdrawLimitService;
import globalServices.TransferLoggerService;
import transactions.TransactionFactory;
import transactions.TransactionStatus;
import transactions.TransactionType;

public class WithdrawTransferService {

    private static final double BUSINESS_ACCOUNT_SERVICE_FEE = 0.01;

    private final TransferLoggerService transferLoggerService;
    private final TransactionFactory transactionFactory = new TransactionFactory();

    public WithdrawTransferService(TransferLoggerService transferLoggerService) {
        this.transferLoggerService = transferLoggerService;
    }

    public void withdraw(BankAccount account, double amount) {
        double newBalance = account.getBalance() - amount;

        if(account instanceof BusinessAccount) {
            double serviceFee = amount * BUSINESS_ACCOUNT_SERVICE_FEE;

            newBalance -= serviceFee;
        }

        GetWithdrawLimitService getWithdrawLimitService = new GetWithdrawLimitService();
        if(newBalance < getWithdrawLimitService.getWithdrawLimit(account))
        {
            // u vyberu penize odchazeji ven, proto je cilovy ucet prazdny (null)
            transferLoggerService.log(transactionFactory.createTransaction(account.getAccountNumber(), null, amount, TransactionType.WITHDRAW, TransactionStatus.FAILED));
            throw new IllegalArgumentException("Cannot subtract negative amount");
        }

        account.setBalance(newBalance);

        transferLoggerService.log(transactionFactory.createTransaction(account.getAccountNumber(), null, amount, TransactionType.WITHDRAW, TransactionStatus.SUCCESS));
    }

}
