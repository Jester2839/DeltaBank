package accounts.services;

import accounts.BankAccount;
import accounts.factories.BusinessAccountFactory;
import accounts.factories.CurrentAccountFactory;
import accounts.factories.SavingAccountFactory;
import accounts.factories.StudentAccountFactory;
import com.google.inject.Inject;
import person.AccountOwner;

import java.util.ArrayList;
import java.util.List;

public class BankAccountService implements IBankAccountService {

    @Inject BusinessAccountFactory businessAccountFactory;
    @Inject CurrentAccountFactory currentAccountFactory;
    @Inject SavingAccountFactory savingAccountFactory;
    @Inject StudentAccountFactory studentAccountFactory;

    List<BankAccount> bankAccounts = new ArrayList<>();

    @Override
    public BankAccount createBussinesBankAccount(AccountOwner accountOwner, double balance, String currency) {
        BankAccount bussinesAccount = businessAccountFactory.createBusinessAccount(accountOwner, balance, currency);
        this.bankAccounts.add(bussinesAccount);

        return bussinesAccount;
    }

    @Override
    public BankAccount createCurrentBankAccount(AccountOwner accountOwner, double balance, String currency) {
        BankAccount currentAccount = currentAccountFactory.createCurrentAccount(accountOwner, balance, currency);
        this.bankAccounts.add(currentAccount);

        return currentAccount;
    }

    @Override
    public BankAccount createSavingBankAccount(AccountOwner accountOwner, double balance, String currency) {
        BankAccount savingAccount = savingAccountFactory.createSavingAccount(accountOwner, balance, currency);
        this.bankAccounts.add(savingAccount);

        return savingAccount;
    }

    public BankAccount createStudentBankAccount(AccountOwner accountOwner, double balance, String school, String currency) {
        BankAccount studentAccount = studentAccountFactory.createStudentAccount(accountOwner, balance, school, currency);
        this.bankAccounts.add(studentAccount);

        return studentAccount;
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }
}
