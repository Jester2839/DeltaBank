package accounts.factories;

import accounts.SavingAccount;
import accounts.services.AccountNumGeneratorService;
import person.AccountOwner;

import java.util.UUID;

public class SavingAccountFactory {

    private final AccountNumGeneratorService accountNumGeneratorService;

    public SavingAccountFactory(AccountNumGeneratorService accountNumGeneratorService) {
        this.accountNumGeneratorService = accountNumGeneratorService;
    }

    public SavingAccount createSavingAccount(AccountOwner accountOwner, double balance){

        String uuid = UUID.randomUUID().toString();

        String accountNumber = accountNumGeneratorService.accNumGen();

        return new SavingAccount(uuid, accountOwner, accountNumber, balance);
    }
}
