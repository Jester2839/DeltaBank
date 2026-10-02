package accounts;

import globalServices.AccountNumGeneratorService;
import pearson.AccountOwner;

import java.util.UUID;

public class SavingAccountFactory {

    public SavingAccount createSavingAccount(AccountOwner accountOwner, double balance){

        String uuid = UUID.randomUUID().toString();

        AccountNumGeneratorService accountNumGeneratorService = new AccountNumGeneratorService();
        String accountNumber = accountNumGeneratorService.accNumGen();

        return new SavingAccount(uuid, accountOwner, accountNumber, balance);
    }
}
