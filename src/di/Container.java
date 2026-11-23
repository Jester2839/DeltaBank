package di;

import accounts.factories.BusinessAccountFactory;
import accounts.factories.CurrentAccountFactory;
import accounts.factories.SavingAccountFactory;
import accounts.factories.StudentAccountFactory;
import accounts.serialization.BankAccountGsonSerializer;
import accounts.serialization.BankAccountJacksonSerializer;
import accounts.serialization.BankAccountSerializeFactory;
import accounts.services.AccountNumGeneratorService;
import accounts.services.BankAccountService;
import accounts.services.decorators.BankAccountWithLoggerService;
import accounts.services.decorators.BankAccountWithLoggerWithTimeService;
import notifier.ConsoleNotifierService;
import notifier.EmailNotifierService;
import person.factories.AccountOwnerFactory;
import person.services.AccountOwnerService;
import transactions.TransactionFactory;
import transfer.DepositTransferService;
import transfer.GetWithdrawLimitService;
import transfer.TransferLoggerService;
import transfer.TransferTransferService;
import transfer.WithdrawTransferService;

public class Container {

    // --- pomocne sluzby ---
    public AccountNumGeneratorService accountNumGeneratorService = new AccountNumGeneratorService();
    public GetWithdrawLimitService getWithdrawLimitService = new GetWithdrawLimitService();

    // --- notifiery ---
    public ConsoleNotifierService consoleNotifierService = new ConsoleNotifierService();
    public EmailNotifierService emailNotifierService = new EmailNotifierService();

    // --- factories ---
    public AccountOwnerFactory accountOwnerFactory = new AccountOwnerFactory();
    public BusinessAccountFactory businessAccountFactory = new BusinessAccountFactory(accountNumGeneratorService);
    public CurrentAccountFactory currentAccountFactory = new CurrentAccountFactory(accountNumGeneratorService);
    public SavingAccountFactory savingAccountFactory = new SavingAccountFactory(accountNumGeneratorService);
    public StudentAccountFactory studentAccountFactory = new StudentAccountFactory(accountNumGeneratorService);
    public TransactionFactory transactionFactory = new TransactionFactory();
    public BankAccountSerializeFactory bankAccountSerializeFactory = new BankAccountSerializeFactory();

    // --- ucty a majitele ---
    public AccountOwnerService accountOwnerService = new AccountOwnerService(accountOwnerFactory);
    public BankAccountService bankAccountService = new BankAccountService(businessAccountFactory, currentAccountFactory, savingAccountFactory, studentAccountFactory);

    // --- dekoratory ---
    public BankAccountWithLoggerService bankAccountWithLoggerService = new BankAccountWithLoggerService(bankAccountService);
    public BankAccountWithLoggerWithTimeService bankAccountWithLoggerWithTimeService = new BankAccountWithLoggerWithTimeService(bankAccountService);
    public BankAccountWithLoggerService bankAccountWithLoggerAndWithTimeService = new BankAccountWithLoggerService(bankAccountWithLoggerWithTimeService);

    // --- serializery ---
    public BankAccountGsonSerializer bankAccountGsonSerializer = new BankAccountGsonSerializer(bankAccountSerializeFactory);
    public BankAccountJacksonSerializer bankAccountJacksonSerializer = new BankAccountJacksonSerializer(bankAccountSerializeFactory);

    // --- transfery ---
    // jedna instance loggeru sdilena vsemi transfer sluzbami, aby historie byla kompletni
    public TransferLoggerService transferLoggerService = new TransferLoggerService();
    public DepositTransferService depositTransferService = new DepositTransferService(transferLoggerService, transactionFactory);
    public WithdrawTransferService withdrawTransferService = new WithdrawTransferService(transferLoggerService, transactionFactory, getWithdrawLimitService);
    public TransferTransferService transferTransferService = new TransferTransferService(transferLoggerService, transactionFactory, getWithdrawLimitService);
}
