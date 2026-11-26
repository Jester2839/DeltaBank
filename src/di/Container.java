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
import accounts.services.CurrencyConverterService;
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


}
