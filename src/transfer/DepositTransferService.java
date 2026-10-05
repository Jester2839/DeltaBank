package transfer;

import accounts.BankAccount;
import accounts.StudentAccount;
import globalServices.TransferLoggerService;
import transactions.TransactionFactory;
import transactions.TransactionStatus;
import transactions.TransactionType;

public class DepositTransferService {

    private static final double STUDENT_ACCOUNT_DEPOSIT_BONUS = 0.005;

    private final TransferLoggerService transferLoggerService;
    private final TransactionFactory transactionFactory = new TransactionFactory();

    public DepositTransferService(TransferLoggerService transferLoggerService) {
        this.transferLoggerService = transferLoggerService;
    }

    public void deposit(BankAccount bankAccount, double amount) {
        double newBalance = bankAccount.getBalance() + amount;

        if (bankAccount instanceof StudentAccount) {
            double depositBonus = amount * STUDENT_ACCOUNT_DEPOSIT_BONUS;

            newBalance += depositBonus;
        }

        bankAccount.setBalance(newBalance);

        // u vkladu penize prichazeji z venku, proto je zdrojovy ucet prazdny (null)
        transferLoggerService.log(transactionFactory.createTransaction(null, bankAccount.getAccountNumber(), amount, TransactionType.DEPOSIT, TransactionStatus.SUCCESS));
    }
}
