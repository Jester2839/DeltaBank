import accounts.*;
import pearson.AccountOwner;
import transfer.DepositTransferService;
import transfer.WithdrawTransferService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {

    AccountOwner accountOwner = new AccountOwner("Petr", "Banan");
    accountOwner.setLastName("Bananos");

    BankAccount bankAccount = new CurrentAccount(accountOwner, "123", 100);
    BankAccount studenAccount = new StudentAccount(accountOwner, "123", 300, "Delta");
    BankAccount savingAccount = new SavingAccount(accountOwner, "123", 1000);
    BankAccount businessAccount = new BusinessAccount(accountOwner, "123", 1000);


    List<BankAccount> bankAccounts = new ArrayList<>();
    bankAccounts.add(bankAccount);
    bankAccounts.add(studenAccount);

    for (BankAccount account: bankAccounts){
        if (account instanceof InterestPoint){
            ((InterestPoint)account).calculateInterest();
        }
    }

    for (BankAccount account: bankAccounts){
        if(account instanceof  StudentAccount) {
            StudentAccount stdAccount = (StudentAccount) account;
            IO.println("school: " + stdAccount.getSchoolName());
        }
    }

    IO.println();

    DepositTransferService depositTransferService = new DepositTransferService();
    WithdrawTransferService withdrawTransferService = new WithdrawTransferService();



}

private static void printBalnace(BankAccount bankAccount){
    IO.println("blance " + bankAccount.getBalance());
}