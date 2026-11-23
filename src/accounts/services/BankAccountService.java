package accounts.services;

import accounts.BankAccount;
import accounts.factories.BusinessAccountFactory;
import accounts.factories.CurrentAccountFactory;
import accounts.factories.SavingAccountFactory;
import accounts.factories.StudentAccountFactory;
import person.AccountOwner;

import java.util.ArrayList;
import java.util.List;

public class BankAccountService implements IBankAccountService {

    private final BusinessAccountFactory businessAccountFactory;
    private final CurrentAccountFactory currentAccountFactory;
    private final SavingAccountFactory savingAccountFactory;
    private final StudentAccountFactory studentAccountFactory;

    List<BankAccount> bankAccounts = new ArrayList<>();

    public BankAccountService(BusinessAccountFactory businessAccountFactory, CurrentAccountFactory currentAccountFactory, SavingAccountFactory savingAccountFactory, StudentAccountFactory studentAccountFactory) {
        this.businessAccountFactory = businessAccountFactory;
        this.currentAccountFactory = currentAccountFactory;
        this.savingAccountFactory = savingAccountFactory;
        this.studentAccountFactory = studentAccountFactory;
    }

    public BankAccount createBussinesBankAccount(AccountOwner accountOwner, double balance) {
        BankAccount bussinesAccount = businessAccountFactory.createBusinessAccount(accountOwner, balance);
        this.bankAccounts.add(bussinesAccount);

        return bussinesAccount;
    }

    public BankAccount createCurrentBankAccount(AccountOwner accountOwner, double balance) {
        BankAccount currentAccount = currentAccountFactory.createCurrentAccount(accountOwner, balance);
        this.bankAccounts.add(currentAccount);

        return currentAccount;
    }

    public BankAccount createSavingBankAccount(AccountOwner accountOwner, double balance) {
        BankAccount savingAccount = savingAccountFactory.createSavingAccount(accountOwner, balance);
        this.bankAccounts.add(savingAccount);

        return savingAccount;
    }

    public BankAccount createStudentBankAccount(AccountOwner accountOwner, double balance, String school) {
        BankAccount studentAccount = studentAccountFactory.createStudentAccount(accountOwner, balance, school);
        this.bankAccounts.add(studentAccount);

        return studentAccount;
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }
}
