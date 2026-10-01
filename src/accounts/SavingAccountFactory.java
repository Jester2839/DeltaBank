package accounts;

import accountsServices.AccountNumGeneratorService;
import pearson.AccountOwner;

public class SavingAccountFactory {

    public SavingAccount createSavingAccount(AccountOwner accountOwner, double balance){

        AccountNumGeneratorService accountNumGeneratorService = new AccountNumGeneratorService();
        String accountNumber = accountNumGeneratorService.accNumGen();

        return new SavingAccount(accountOwner, accountNumber, balance);
    }
}
