package accounts.services;

import accounts.BankAccount;
import person.AccountOwner;

import java.util.List;

public interface IBankAccountService {

    BankAccount createBussinesBankAccount(AccountOwner accountOwner, double balance, String currency);

    BankAccount createCurrentBankAccount(AccountOwner accountOwner, double balance, String currency);

    BankAccount createSavingBankAccount(AccountOwner accountOwner, double balance, String currency);

    BankAccount createStudentBankAccount(AccountOwner accountOwner, double balance, String school, String currency);

    List<BankAccount> getBankAccounts();
}
