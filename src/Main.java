import accounts.*;
import accounts.serialization.*;
import accounts.services.BankAccountService;
import accounts.services.CreateAccountLoggerService;
import globalServices.TransferLoggerService;
import notifier.ConsoleNotifierService;
import notifier.EmailNotifierService;
import notifier.NotifierService;
import pearson.AccountOwner;
import pearson.AccountOwnerFactory;
import transfer.DepositTransferService;
import transfer.TransferTransferService;
import transfer.WithdrawTransferService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {

    AccountOwnerFactory accountOwnerFactory = new AccountOwnerFactory();
    BankAccountService bankAccountService = new BankAccountService();
    CreateAccountLoggerService logger = new CreateAccountLoggerService();

    AccountOwner accountOwner = accountOwnerFactory.createAccountOwner("Petr", "Banan");
    accountOwner.setLastName("Bananos");


    BankAccount businessAccount = logger.logCreation(() -> bankAccountService.createBussinesBankAccount(accountOwner, 1000));
    BankAccount currentAccount = logger.logCreationWithTime(() -> bankAccountService.createCurrentBankAccount(accountOwner, 1000));
    BankAccount savingAccount = logger.logCreationAll(() -> bankAccountService.createSavingBankAccount(accountOwner, 1000));
    BankAccount studenAccount = logger.logCreationAll(() -> bankAccountService.createStudentBankAccount(accountOwner, 1000, "Delta"));


    BankAccountSerializer bankAccountGsonSerializer = new BankAccountGsonSerializer();
    BankAccountSerializer bankAccountJacksonSerializer = new BankAccountJacksonSerializer();

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
    TransferLoggerService transferLoggerService = new TransferLoggerService();

    DepositTransferService depositTransferService = new DepositTransferService(transferLoggerService);
    WithdrawTransferService withdrawTransferService = new WithdrawTransferService(transferLoggerService);
    TransferTransferService transferTransferService = new TransferTransferService(transferLoggerService);

    // cely vystup jde pres notifier - staci vymenit implementaci a vystup vypada jinak
    NotifierService consoleNotifierService = new ConsoleNotifierService();
    NotifierService emailNotifierService = new EmailNotifierService();

    consoleNotifierService.notify("--- Prevod 100 z business account (poplatek 0.3%) ---");
    printBalance("business pred", businessAccount, consoleNotifierService);
    printBalance("current pred", currentAccount, consoleNotifierService);
    transferTransferService.transfer(businessAccount, currentAccount, 100);
    printBalance("business po", businessAccount, consoleNotifierService);
    printBalance("current po", currentAccount, consoleNotifierService);
    emailNotifierService.notify("Prevod 100 z business account na current account probehl");

    IO.println();

    consoleNotifierService.notify("--- Prevod 200 ze student account (bez poplatku) ---");
    transferTransferService.transfer(studenAccount, savingAccount, 200);
    printBalance("student po", studenAccount, consoleNotifierService);
    printBalance("saving po", savingAccount, consoleNotifierService);

    IO.println();

    consoleNotifierService.notify("--- Neplatny vstup (prevod na stejny ucet) ---");
    try {
        transferTransferService.transfer(currentAccount, currentAccount, 50);
    } catch (IllegalArgumentException e) {
        emailNotifierService.notify("CHYBA: " + e.getMessage());
    }

    IO.println();

    consoleNotifierService.notify("--- Vklad 500 na current account ---");
    printBalance("current pred vkladem", currentAccount, consoleNotifierService);
    depositTransferService.deposit(currentAccount, 500);
    printBalance("current po vkladu", currentAccount, consoleNotifierService);

    IO.println();

    consoleNotifierService.notify("--- Vyber 100 z current account ---");
    printBalance("current pred vyberem", studenAccount, consoleNotifierService);
    withdrawTransferService.withdraw(studenAccount, 100);
    printBalance("current po vyberu", studenAccount, consoleNotifierService);

    IO.println();

    // historie transakci ulozena v TransferLoggerService
    transferLoggerService.printAllTransactions();

    IO.println();
    //transferLoggerService.printTransactionsByAccount(currentAccount.getAccountNumber());

}

private static void printBalance(String label, BankAccount bankAccount, NotifierService notifierService){
    notifierService.notify(label + ": " + bankAccount.getBalance());
}