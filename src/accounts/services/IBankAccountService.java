package accounts.services;

import accounts.BankAccount;
import pearson.AccountOwner;

import java.util.List;

public interface IBankAccountService {

    BankAccount createBussinesBankAccount(AccountOwner accountOwner, double balance);

    BankAccount createCurrentBankAccount(AccountOwner accountOwner, double balance);

    BankAccount createSavingBankAccount(AccountOwner accountOwner, double balance);

    BankAccount createStudentBankAccount(AccountOwner accountOwner, double balance, String school);

    List<BankAccount> getBankAccounts();
}
