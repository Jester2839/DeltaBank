package di;

import accounts.CurrentAccount;
import accounts.SavingAccount;
import accounts.factories.BusinessAccountFactory;
import accounts.factories.CurrentAccountFactory;
import accounts.factories.SavingAccountFactory;
import accounts.factories.StudentAccountFactory;
import accounts.services.BankAccountService;
import person.factories.AccountOwnerFactory;
import person.services.AccountOwnerService;

public class Container {

    AccountOwnerFactory accountOwnerFactory = new AccountOwnerFactory();
    BusinessAccountFactory businessAccountFactory = new BusinessAccountFactory();
    CurrentAccountFactory currentAccountFactory = new CurrentAccountFactory();
    SavingAccountFactory savingAccountFactory = new SavingAccountFactory();
    StudentAccountFactory studentAccountFactory = new StudentAccountFactory();


    public AccountOwnerService accountOwnerService = new AccountOwnerService(accountOwnerFactory);
    public BankAccountService bankAccountService = new BankAccountService(businessAccountFactory, currentAccountFactory, savingAccountFactory, studentAccountFactory);
}
