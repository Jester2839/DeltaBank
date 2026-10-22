package accounts.services.decorators;

import accounts.BankAccount;
import accounts.services.IBankAccountService;
import pearson.AccountOwner;

import java.time.LocalTime;
import java.util.List;

public class BankAccountWithLoggerWithTimeService implements IBankAccountService {

    IBankAccountService bankAccountService;

    public BankAccountWithLoggerWithTimeService(IBankAccountService bankAccountService){
        this.bankAccountService = bankAccountService;
    }

    @Override
    public BankAccount createBussinesBankAccount(AccountOwner accountOwner, double balance) {
        this.logStart();
        BankAccount bankAccount = this.bankAccountService.createBussinesBankAccount(accountOwner, balance);
        this.logEnd();

        return null;
    }

    @Override
    public BankAccount createCurrentBankAccount(AccountOwner accountOwner, double balance) {
        this.logStart();
        BankAccount bankAccount = this.bankAccountService.createCurrentBankAccount(accountOwner, balance);
        this.logEnd();

        return null;
    }

    @Override
    public BankAccount createSavingBankAccount(AccountOwner accountOwner, double balance) {
        this.logStart();
        BankAccount bankAccount = this.bankAccountService.createSavingBankAccount(accountOwner, balance);
        this.logEnd();

        return null;
    }

    @Override
    public BankAccount createStudentBankAccount(AccountOwner accountOwner, double balance, String school) {
        this.logStart();
        BankAccount bankAccount = this.bankAccountService.createStudentBankAccount(accountOwner, balance, school);
        this.logEnd();

        return null;
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
