package accounts;

import accountsServices.AccountNumGeneratorService;
import pearson.AccountOwner;

public class CurrentAccountFactory {

    public CurrentAccount createCurrentAccount(AccountOwner accountOwner, double balance){

        AccountNumGeneratorService accountNumGeneratorService = new AccountNumGeneratorService();
        String accountNumber = accountNumGeneratorService.accNumGen();

        return new CurrentAccount(accountOwner, accountNumber, balance);
    }
}
