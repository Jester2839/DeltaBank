package transactions;

import java.time.LocalDateTime;
import java.util.UUID;

public class TransactionFactory {

    public Transaction createTransaction(String sourceAccountNumber, String targetAccountNumber, double amount, TransactionType type, TransactionStatus status){

        String uuid = UUID.randomUUID().toString();
        LocalDateTime timestamp = LocalDateTime.now();

        return new Transaction(uuid, timestamp, sourceAccountNumber, targetAccountNumber, amount, type, status);
    }

}
