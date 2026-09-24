package accounts;

import pearson.AccountOwner;

public class StudentAccount extends BankAccount{

    private String schoolName;

    public StudentAccount(AccountOwner accountOwner, String accountNumber, double balance, String schoolName) {
        super(accountOwner, accountNumber, balance);

        this.schoolName = schoolName;
    }

    public StudentAccount(AccountOwner accountOwner, String accountNumber, String schoolName) {
        this(accountOwner, accountNumber, 0, schoolName);
    }

    @Override
    public void add(double amount) {

        double bonusAmount = amount * 0.05;

        super.add(bonusAmount);
        super.add(amount);
    }

    public String getSchoolName() {
        return schoolName;
    }

    @Override
    public void sub(double amount) {

        if(getBalance() - amount < -5000){
            throw new IllegalArgumentException("Amount cannot go under 5000");
        }

        super.sub(amount);
    }
}
