package accounts;

import accountsServices.AccountNumGeneratorService;
import pearson.AccountOwner;

public class StudentAccountFactory {

    public StudentAccount createStudentAccount(AccountOwner accountOwner, double balance, String schoolName){

        AccountNumGeneratorService accountNumGeneratorService = new AccountNumGeneratorService();
        String accountNumber = accountNumGeneratorService.accNumGen();

        return new StudentAccount(accountOwner, accountNumber, balance, schoolName);
    }
}
