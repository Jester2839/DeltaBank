package accounts.services.decorators;

import accounts.BankAccount;
import accounts.services.IBankAccountService;
import person.AccountOwner;

import java.time.LocalTime;
import java.util.List;

public class BankAccountWithLoggerWithTimeService implements IBankAccountService {

    IBankAccountService bankAccountService;

    public BankAccountWithLoggerWithTimeService(IBankAccountService bankAccountService){
        this.bankAccountService = bankAccountService;
    }

    @Override
    public BankAccount createBussinesBankAccount(AccountOwner accountOwner, double balance, String currency) {
        this.logStart();
        BankAccount bankAccount = this.bankAccountService.createBussinesBankAccount(accountOwner, balance, currency);
        this.logEnd();

        return bankAccount;
    }

    @Override
    public BankAccount createCurrentBankAccount(AccountOwner accountOwner, double balance, String currency) {
        this.logStart();
        BankAccount bankAccount = this.bankAccountService.createCurrentBankAccount(accountOwner, balance, currency);
        this.logEnd();

        return bankAccount;
    }

    @Override
    public BankAccount createSavingBankAccount(AccountOwner accountOwner, double balance, String currency) {
        this.logStart();
        BankAccount bankAccount = this.bankAccountService.createSavingBankAccount(accountOwner, balance, currency);
        this.logEnd();

        return bankAccount;
    }

    @Override
    public BankAccount createStudentBankAccount(AccountOwner accountOwner, double balance, String school, String currency) {
        this.logStart();
        BankAccount bankAccount = this.bankAccountService.createStudentBankAccount(accountOwner, balance, school, currency);
        this.logEnd();

        return bankAccount;
    }

    @Override
    public List<BankAccount> getBankAccounts() {
        return List.of();
    }

    public void logStart(){
        IO.println("Create Bank Account started..." + LocalTime.now());
    }
    public void logEnd(){
        IO.println("Create Bank Account started..." + LocalTime.now());
    }
}
