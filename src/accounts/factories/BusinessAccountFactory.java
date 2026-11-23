package accounts.factories;

import accounts.BusinessAccount;
import accounts.services.AccountNumGeneratorService;
import person.AccountOwner;

import java.util.UUID;

public class BusinessAccountFactory {

    private final AccountNumGeneratorService accountNumGeneratorService;

    public BusinessAccountFactory(AccountNumGeneratorService accountNumGeneratorService) {
        this.accountNumGeneratorService = accountNumGeneratorService;
    }

    public BusinessAccount createBusinessAccount(AccountOwner accountOwner, double balance){

        String uuid = UUID.randomUUID().toString();

        String accountNumber = accountNumGeneratorService.accNumGen();

        return new BusinessAccount(uuid, accountOwner, accountNumber, balance);
    }

}
