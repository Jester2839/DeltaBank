package accounts.factories;

import accounts.CurrentAccount;
import accounts.services.AccountNumGeneratorService;
import com.google.inject.Inject;
import person.AccountOwner;

import java.util.UUID;

public class CurrentAccountFactory {

    @Inject
    AccountNumGeneratorService accountNumGeneratorService;

    public CurrentAccount createCurrentAccount(AccountOwner accountOwner, double balance, String currency){

        String uuid = UUID.randomUUID().toString();

        String accountNumber = accountNumGeneratorService.accNumGen();

        return new CurrentAccount(uuid, accountOwner, accountNumber, balance, currency);
    }
}
