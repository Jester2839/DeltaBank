import accounts.BankAccount;
import accounts.InterestPoint;
import accounts.serialization.BankAccountGsonSerializer;
import accounts.serialization.BankAccountJacksonSerializer;
import accounts.serialization.BankAccountSerializer;
import accounts.services.BankAccountService;
import accounts.services.CurrencyConverterService;
import accounts.services.IBankAccountService;
import accounts.services.decorators.BankAccountWithLoggerService;
import accounts.services.decorators.BankAccountWithLoggerWithTimeService;
import com.google.inject.Inject;
import di.Container;
import di.ContainerFactory;
import notifier.ConsoleNotifierService;
import notifier.EmailNotifierService;
import notifier.NotifierService;
import person.AccountOwner;
import person.services.AccountOwnerService;
import transfer.DepositTransferService;
import transfer.TransferLoggerService;
import transfer.TransferTransferService;
import transfer.WithdrawTransferService;

public class App {

    @Inject private AccountOwnerService accountOwnerService;
    @Inject private NotifierService notifierService;
    @Inject private CurrencyConverterService currencyConverterService;
    @Inject private BankAccountService bankAccountService;
    @Inject private ConsoleNotifierService consoleNotifierService;
    @Inject private EmailNotifierService emailNotifierService;
    @Inject private BankAccountGsonSerializer bankAccountGsonSerializer;
    @Inject private DepositTransferService depositTransferService;
    @Inject private WithdrawTransferService withdrawTransferService;
    @Inject private  TransferTransferService transferTransferService;
    @Inject private TransferLoggerService transferLoggerService;



    public void run(){
        // vytvorim container
        ContainerFactory containerFactory = new ContainerFactory();
        Container container = containerFactory.createContainer();



        AccountOwner accountOwner = this.accountOwnerService.createAccountOwner("Petr", "Banan");


        CurrencyConverterService currencyConverterService = this.currencyConverterService;


        IBankAccountService bankAccountService = this.bankAccountService;
//        IBankAccountService bankAccouontWithLoggerService = this.bankAccountWithLoggerService;
//        IBankAccountService bankAccouontWithLoggerWithTimeService = this.bankAccountWithLoggerWithTimeService;
//        IBankAccountService bankAccouontWithLoggerAndWithTimeService = container.bankAccountWithLoggerAndWithTimeService;

        IO.println("Original");
        BankAccount businessAccount = bankAccountService.createBussinesBankAccount(accountOwner, 10000, "CZK");
        IO.println("Ni nenapíše");
        IO.println("==========");
        IO.println("LOGGER");
        BankAccount currentAccount = bankAccountService.createCurrentBankAccount(accountOwner, 1000, "EUR");
        IO.println("==========");
        IO.println("TIME");
        BankAccount savingAccount = bankAccountService.createSavingBankAccount(accountOwner, 1000, "USD");
        IO.println("==========");
        IO.println("ALL");
        BankAccount studenAccount = bankAccountService.createStudentBankAccount(accountOwner, 1000, "DELTA", "EUR");
        IO.println("==========");

        // Zbytek Aplikace
        BankAccountSerializer bankAccountGsonSerializer = this.bankAccountGsonSerializer;

        String bankAccountGson = bankAccountGsonSerializer.serializeAll(bankAccountService.getBankAccounts());


        IO.println("");
        IO.println(bankAccountGson);





        for (BankAccount account: bankAccountService.getBankAccounts()){
            if (account instanceof InterestPoint){
                ((InterestPoint)account).calculateInterest();
            }
        }

        IO.println();

        // jedna instance loggeru sdilena vsemi transfer sluzbami, aby historie byla kompletni
        TransferLoggerService transferLoggerService = this.transferLoggerService;

        DepositTransferService depositTransferService = this.depositTransferService;
        WithdrawTransferService withdrawTransferService = this.withdrawTransferService;
        TransferTransferService transferTransferService = this.transferTransferService;

        // cely vystup jde pres notifier - staci vymenit implementaci a vystup vypada jinak
        NotifierService consoleNotifierService = this.consoleNotifierService;
        NotifierService emailNotifierService = this.emailNotifierService;

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
        //transferLoggerService.printTransactionsByAccount(currentAccount.getAccountNumber())
    }

    private static void printBalance(String label, BankAccount bankAccount, String targetCurrency, CurrencyConverterService converter, NotifierService notifierService){
        double originalBalance = bankAccount.getBalance();
        String accountCurrency = bankAccount.getCurrency();
        double convertedBalance = converter.convert(originalBalance, accountCurrency, targetCurrency);

        notifierService.notify(String.format("%s: %.2f %s (přepočet: %.2f %s)", label, originalBalance, accountCurrency, convertedBalance, targetCurrency));
    }
}

