package accounts.services;
import accounts.BankAccount;

import java.time.LocalTime;
import java.util.function.Supplier;

public class CreateAccountLoggerService {

    private static final String START_MSG = "Create Bank Account started...";
    private static final String END_MSG = "Create Bank Account finished...";

    public <T extends BankAccount> T logCreation(Supplier<T> creator) {
        return executeLogging(creator, true, false);
    }

    public <T extends BankAccount> T logCreationWithTime(Supplier<T> creator) {
        return executeLogging(creator, false, true);
    }

    public <T extends BankAccount> T logCreationAll(Supplier<T> creator) {
        return executeLogging(creator, true, true);
    }

    private <T extends BankAccount> T executeLogging(Supplier<T> creator, boolean basic, boolean withTime) {
        IO.println("========");

        if (basic) IO.println(START_MSG);
        if (withTime) IO.println(START_MSG + LocalTime.now());

        T account = creator.get();

        if (withTime) IO.println(END_MSG + LocalTime.now());
        if (basic) IO.println(END_MSG);

        return account;
    }
}
