import accounts.*;
import accounts.serialization.*;
import accounts.services.BankAccountService;
import accounts.services.CreateAccountLoggerService;
import accounts.services.CurrencyConverterService;
import accounts.services.IBankAccountService;
import accounts.services.decorators.BankAccountWithLoggerService;
import accounts.services.decorators.BankAccountWithLoggerWithTimeService;
import di.Container;
import di.ContainerFactory;
import transfer.TransferLoggerService;
import notifier.ConsoleNotifierService;
import notifier.EmailNotifierService;
import notifier.NotifierService;
import person.AccountOwner;
import person.factories.AccountOwnerFactory;
import transfer.DepositTransferService;
import transfer.TransferTransferService;
import transfer.WithdrawTransferService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {

    // vytvorim container
    ContainerFactory containerFactory = new ContainerFactory();
    Container container = containerFactory.createContainer();




    //AccountOwnerFactory accountOwnerFactory = new AccountOwnerFactory();
    //CreateAccountLoggerService logger = new CreateAccountLoggerService();

    AccountOwner accountOwner = container.accountOwnerService.createAccountOwner("Petr", "Banan");
    accountOwner.setLastName("Bananos");

    CurrencyConverterService currencyConverterService = container.currencyConverterService;

    //Moje řešení
    //-------------------------------------
    /*
    BankAccount businessAccount = logger.logCreation(() -> bankAccountService.createBussinesBankAccount(accountOwner, 1000));
    BankAccount currentAccount = logger.logCreationWithTime(() -> bankAccountService.createCurrentBankAccount(accountOwner, 1000));
    BankAccount savingAccount = logger.logCreationAll(() -> bankAccountService.createSavingBankAccount(accountOwner, 1000));
    BankAccount studenAccount = logger.logCreationAll(() -> bankAccountService.createStudentBankAccount(accountOwner, 1000, "Delta"));
    */
    //---------------------------------------------

    // Učitelovo řešení
    //-----------------------------------------------------
    IBankAccountService bankAccountService = container.bankAccountService;
    IBankAccountService bankAccouontWithLoggerService = container.bankAccountWithLoggerService;
    IBankAccountService bankAccouontWithLoggerWithTimeService = container.bankAccountWithLoggerWithTimeService;
    IBankAccountService bankAccouontWithLoggerAndWithTimeService = container.bankAccountWithLoggerAndWithTimeService;

    IO.println("Original");
    BankAccount businessAccount = bankAccountService.createBussinesBankAccount(accountOwner, 10000, "CZK");
    IO.println("Ni nenapíše");
    IO.println("==========");
    IO.println("LOGGER");
    BankAccount currentAccount = bankAccouontWithLoggerService.createCurrentBankAccount(accountOwner, 1000, "EUR");
    IO.println("==========");
    IO.println("TIME");
    BankAccount savingAccount = bankAccouontWithLoggerWithTimeService.createSavingBankAccount(accountOwner, 1000, "USD");
    IO.println("==========");
    IO.println("ALL");
    BankAccount studenAccount = bankAccouontWithLoggerAndWithTimeService.createStudentBankAccount(accountOwner, 1000, "DELTA", "EUR");
    IO.println("==========");
    //--------------------------------------------------

    // Zbytek Aplikace
    BankAccountSerializer bankAccountGsonSerializer = container.bankAccountGsonSerializer;
    BankAccountSerializer bankAccountJacksonSerializer = container.bankAccountJacksonSerializer;

    String bankAccountGson = bankAccountGsonSerializer.serializeAll(bankAccountService.getBankAccounts());
    String bankAccountJackson = bankAccountJacksonSerializer.serializeAll(bankAccountService.getBankAccounts());


    IO.println("");
    IO.println(bankAccountGson);
    IO.println(bankAccountJackson);




    for (BankAccount account: bankAccountService.getBankAccounts()){
        if (account instanceof InterestPoint){
            ((InterestPoint)account).calculateInterest();
        }
    }

    IO.println();

    // jedna instance loggeru sdilena vsemi transfer sluzbami, aby historie byla kompletni
    TransferLoggerService transferLoggerService = container.transferLoggerService;

    DepositTransferService depositTransferService = container.depositTransferService;
    WithdrawTransferService withdrawTransferService = container.withdrawTransferService;
    TransferTransferService transferTransferService = container.transferTransferService;

    // cely vystup jde pres notifier - staci vymenit implementaci a vystup vypada jinak
    NotifierService consoleNotifierService = container.consoleNotifierService;
    NotifierService emailNotifierService = container.emailNotifierService;

    consoleNotifierService.notify("--- Prevod 100 z business account (poplatek 0.3%) ---");
    printBalance("business pred", businessAccount, "GBP", currencyConverterService, consoleNotifierService);
    printBalance("current pred", currentAccount, "GBP", currencyConverterService, consoleNotifierService);
    transferTransferService.transfer(businessAccount, currentAccount, 100);
    printBalance("business po", businessAccount, "GBP", currencyConverterService, consoleNotifierService);
    printBalance("current po", currentAccount, "GBP", currencyConverterService, consoleNotifierService);
    emailNotifierService.notify("Prevod 100 z business account na current account probehl");

    IO.println();

    consoleNotifierService.notify("--- Prevod 200 ze student account (bez poplatku) ---");
    transferTransferService.transfer(studenAccount, savingAccount, 200);
    printBalance("student po", studenAccount, "GBP", currencyConverterService, consoleNotifierService);
    printBalance("saving po", savingAccount, "GBP", currencyConverterService, consoleNotifierService);

    IO.println();

    consoleNotifierService.notify("--- Neplatny vstup (prevod na stejny ucet) ---");
    try {
        transferTransferService.transfer(currentAccount, currentAccount, 50);
    } catch (IllegalArgumentException e) {
        emailNotifierService.notify("CHYBA: " + e.getMessage());
    }

    IO.println();

    consoleNotifierService.notify("--- Vklad 500 na current account ---");
    printBalance("current pred vkladem", currentAccount, "GBP", currencyConverterService, consoleNotifierService);
    depositTransferService.deposit(currentAccount, 500);
    printBalance("current po vkladu", currentAccount, "GBP", currencyConverterService, consoleNotifierService);

    IO.println();

    consoleNotifierService.notify("--- Vyber 100 z current account ---");
    printBalance("current pred vyberem", studenAccount, "GBP", currencyConverterService, consoleNotifierService);
    withdrawTransferService.withdraw(studenAccount, 100);
    printBalance("current po vyberu", studenAccount, "GBP", currencyConverterService, consoleNotifierService);

    IO.println();

    // historie transakci ulozena v TransferLoggerService
    transferLoggerService.printAllTransactions();

    IO.println();
    //transferLoggerService.printTransactionsByAccount(currentAccount.getAccountNumber());

}

private static void printBalance(String label, BankAccount bankAccount, String targetCurrency, CurrencyConverterService converter, NotifierService notifierService){
    double originalBalance = bankAccount.getBalance();
    String accountCurrency = bankAccount.getCurrency();
    double convertedBalance = converter.convert(originalBalance, accountCurrency, targetCurrency);

    notifierService.notify(String.format("%s: %.2f %s (přepočet: %.2f %s)", label, originalBalance, accountCurrency, convertedBalance, targetCurrency));
}