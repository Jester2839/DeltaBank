package accounts;

import accountsServices.AccountNumGeneratorService;
import pearson.AccountOwner;

import java.util.UUID;

public class BusinessAccountFactory {

    public BusinessAccount createBusinessAccount(AccountOwner accountOwner, double balance){

        String uuid = UUID.randomUUID().toString();

        AccountNumGeneratorService accountNumGeneratorService = new AccountNumGeneratorService();
        String accountNumber = accountNumGeneratorService.accNumGen();

        return new BusinessAccount(uuid, accountOwner, accountNumber, balance);
    }

}
