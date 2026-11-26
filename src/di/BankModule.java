package di;

import accounts.serialization.BankAccountGsonSerializer;
import accounts.serialization.BankAccountSerializer;
import accounts.services.BankAccountService;
import accounts.services.IBankAccountService;
import com.google.inject.AbstractModule;
import notifier.EmailNotifierService;
import notifier.NotifierService;

public class BankModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(NotifierService.class).to(EmailNotifierService.class);
        bind(BankAccountSerializer.class).to(BankAccountGsonSerializer.class);
    }
}
