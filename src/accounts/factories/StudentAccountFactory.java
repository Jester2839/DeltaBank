package accounts.factories;

import accounts.StudentAccount;
import accounts.services.AccountNumGeneratorService;
import person.AccountOwner;

import java.util.UUID;

public class StudentAccountFactory {

    private final AccountNumGeneratorService accountNumGeneratorService;

    public StudentAccountFactory(AccountNumGeneratorService accountNumGeneratorService) {
        this.accountNumGeneratorService = accountNumGeneratorService;
    }

    public StudentAccount createStudentAccount(AccountOwner accountOwner, double balance, String schoolName, String currency){

        String uuid = UUID.randomUUID().toString();

        String accountNumber = accountNumGeneratorService.accNumGen();

        return new StudentAccount(uuid, accountOwner, accountNumber, balance, schoolName, currency);
    }
}
