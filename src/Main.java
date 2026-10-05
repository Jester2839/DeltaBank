import accounts.*;
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

    AccountOwner accountOwner = accountOwnerFactory.createAccountOwner("Petr", "Banan");
    accountOwner.setLastName("Bananos");


    BusinessAccountFactory businessAccountFactory = new BusinessAccountFactory();
    CurrentAccountFactory currentAccountFactory = new CurrentAccountFactory();
    SavingAccountFactory savingAccountFactory = new SavingAccountFactory();
    StudentAccountFactory studentAccountFactory = new StudentAccountFactory();

    BankAccount businessAccount = businessAccountFactory.createBusinessAccount(accountOwner, 1000);
    BankAccount currentAccount = currentAccountFactory.createCurrentAccount(accountOwner, 1000);
    BankAccount savingAccount = savingAccountFactory.createSavingAccount(accountOwner, 1000);
    BankAccount studenAccount = studentAccountFactory.createStudentAccount(accountOwner, 1000, "Delta");


    List<BankAccount> bankAccounts = new ArrayList<>();
    bankAccounts.add(currentAccount);
    bankAccounts.add(studenAccount);

    for (BankAccount account: bankAccounts){
        if (account instanceof InterestPoint){
            ((InterestPoint)account).calculateInterest();
        }
    }

    for (BankAccount account: bankAccounts){
        if(account instanceof  StudentAccount) {
            StudentAccount stdAccount = (StudentAccount) account;
            IO.println("school: " + stdAccount.getSchoolName());
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
    printBalance("current pred vyberem", currentAccount, consoleNotifierService);
    withdrawTransferService.withdraw(currentAccount, 100);
    printBalance("current po vyberu", currentAccount, consoleNotifierService);

    IO.println();

    // historie transakci ulozena v TransferLoggerService
    transferLoggerService.printAllTransactions();

    IO.println();
    transferLoggerService.printTransactionsByAccount(currentAccount.getAccountNumber());

}

private static void printBalance(String label, BankAccount bankAccount, NotifierService notifierService){
    notifierService.notify(label + ": " + bankAccount.getBalance());
}