package accounts;

import accountsServices.AccountNumGeneratorService;
import pearson.AccountOwner;

import java.util.UUID;

public class StudentAccountFactory {

    public StudentAccount createStudentAccount(AccountOwner accountOwner, double balance, String schoolName){

        String uuid = UUID.randomUUID().toString();

        AccountNumGeneratorService accountNumGeneratorService = new AccountNumGeneratorService();
        String accountNumber = accountNumGeneratorService.accNumGen();

        return new StudentAccount(uuid, accountOwner, accountNumber, balance, schoolName);
    }
}
