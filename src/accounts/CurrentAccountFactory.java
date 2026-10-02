package accounts;

import globalServices.AccountNumGeneratorService;
import pearson.AccountOwner;

import java.util.UUID;

public class CurrentAccountFactory {

    public CurrentAccount createCurrentAccount(AccountOwner accountOwner, double balance){

        String uuid = UUID.randomUUID().toString();

        AccountNumGeneratorService accountNumGeneratorService = new AccountNumGeneratorService();
        String accountNumber = accountNumGeneratorService.accNumGen();

        return new CurrentAccount(uuid, accountOwner, accountNumber, balance);
    }
}
