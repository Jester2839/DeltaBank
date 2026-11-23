package accounts;

import person.AccountOwner;

public class StudentAccount extends BankAccount{

    private String schoolName;

    public StudentAccount(String uuid, AccountOwner accountOwner, String accountNumber, double balance, String schoolName, String currency) {
        super(uuid, accountOwner, accountNumber, balance, currency);

        this.schoolName = schoolName;
    }

    public StudentAccount(String uuid, AccountOwner accountOwner, String accountNumber, String schoolName, String currency) {
        this(uuid, accountOwner, accountNumber, 0, schoolName, currency);
    }

    public String getSchoolName() {
        return schoolName;
    }
}
