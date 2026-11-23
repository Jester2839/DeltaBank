package accounts.factories;

import accounts.CurrentAccount;
import accounts.services.AccountNumGeneratorService;
import person.AccountOwner;

import java.util.UUID;

public class CurrentAccountFactory {

    private final AccountNumGeneratorService accountNumGeneratorService;

    public CurrentAccountFactory(AccountNumGeneratorService accountNumGeneratorService) {
        this.accountNumGeneratorService = accountNumGeneratorService;
    }

    public CurrentAccount createCurrentAccount(AccountOwner accountOwner, double balance, String currency){

        String uuid = UUID.randomUUID().toString();

        String accountNumber = accountNumGeneratorService.accNumGen();

        return new CurrentAccount(uuid, accountOwner, accountNumber, balance, currency);
    }
}
