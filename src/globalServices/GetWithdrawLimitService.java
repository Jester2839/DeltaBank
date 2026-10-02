package globalServices;

import accounts.BankAccount;
import accounts.StudentAccount;

public class GetWithdrawLimitService {

    private static final double STUDENT_ACCOUNT_OVERDRAFT_LIMIT = -5000;

    public double getWithdrawLimit(BankAccount sourceAccount){

        if (sourceAccount instanceof StudentAccount){
            return STUDENT_ACCOUNT_OVERDRAFT_LIMIT;
        }

        return 0;
    }
}
