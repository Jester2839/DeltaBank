package accounts;

import pearson.AccountOwner;

public class StudentAccount extends BankAccount{

    private String schoolName;

    public StudentAccount(String uuid, AccountOwner accountOwner, String accountNumber, double balance, String schoolName) {
        super(uuid, accountOwner, accountNumber, balance);

        this.schoolName = schoolName;
    }

    public StudentAccount(String uuid, AccountOwner accountOwner, String accountNumber, String schoolName) {
        this(uuid, accountOwner, accountNumber, 0, schoolName);
    }

    public String getSchoolName() {
        return schoolName;
    }
}
