package accounts.factories;

import accounts.BusinessAccount;
import accounts.services.AccountNumGeneratorService;
import com.google.inject.Inject;
import person.AccountOwner;

import java.util.UUID;

public class BusinessAccountFactory {

    @Inject AccountNumGeneratorService accountNumGeneratorService;


    public BusinessAccount createBusinessAccount(AccountOwner accountOwner, double balance, String currency){

        String uuid = UUID.randomUUID().toString();

        String accountNumber = accountNumGeneratorService.accNumGen();

        return new BusinessAccount(uuid, accountOwner, accountNumber, balance, currency);
    }

}
