package accounts;

import accountsServices.AccountNumGeneratorService;
import pearson.AccountOwner;

public class BusinessAccountFactory {

    public BusinessAccount createBusinessAccount(AccountOwner accountOwner, double balance){

        AccountNumGeneratorService accountNumGeneratorService = new AccountNumGeneratorService();
        String accountNumber = accountNumGeneratorService.accNumGen();

        return new BusinessAccount(accountOwner, accountNumber, balance);
    }

}
