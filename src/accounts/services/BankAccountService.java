package accounts.services;

import accounts.BankAccount;
import accounts.factories.BusinessAccountFactory;
import accounts.factories.CurrentAccountFactory;
import accounts.factories.SavingAccountFactory;
import accounts.factories.StudentAccountFactory;
import pearson.AccountOwner;

import java.util.ArrayList;
import java.util.List;

public class BankAccountService {

    BusinessAccountFactory businessAccountFactory = new BusinessAccountFactory();
    CurrentAccountFactory currentAccountFactory = new CurrentAccountFactory();
    SavingAccountFactory savingAccountFactory = new SavingAccountFactory();
    StudentAccountFactory studentAccountFactory = new StudentAccountFactory();

    List<BankAccount> bankAccounts = new ArrayList<>();


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
