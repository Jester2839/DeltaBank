package transactions;

import accounts.BankAccount;

import java.time.LocalDateTime;

public class Transaction {

    private String uuid;
    private LocalDateTime timestamp;
    private String sourceAccountNumber;
    private String targetAccountNumber;
    private double amount;
    private TransactionType type;
    private TransactionStatus status;


    public Transaction(String uuid, LocalDateTime timestamp, String sourceAccountNumber, String targetAccountNumber, double amount, TransactionType type, TransactionStatus status) {
        this.uuid = uuid;
        this.timestamp = timestamp;
        this.sourceAccountNumber = sourceAccountNumber;
        this.targetAccountNumber = targetAccountNumber;
        this.amount = amount;
        this.type = type;
        this.status = status;
    }

    public String getUuid() {
        return uuid;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public String getSourceAccountNumber() {
        return sourceAccountNumber;
    }
    public String getTargetAccountNumber() {
        return targetAccountNumber;
    }
    public double getAmount() {
        return amount;
    }
    public TransactionType getType() {
        return type;
    }
    public TransactionStatus getStatus() {
        return status;
    }


}
