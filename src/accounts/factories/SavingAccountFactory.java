package accounts.factories;

import accounts.SavingAccount;
import accounts.services.AccountNumGeneratorService;
import com.google.inject.Inject;
import person.AccountOwner;

import java.util.UUID;

public class SavingAccountFactory {

    @Inject
    AccountNumGeneratorService accountNumGeneratorService;

    public SavingAccount createSavingAccount(AccountOwner accountOwner, double balance, String currency){

        String uuid = UUID.randomUUID().toString();

        String accountNumber = accountNumGeneratorService.accNumGen();

        return new SavingAccount(uuid, accountOwner, accountNumber, balance, currency);
    }
}
