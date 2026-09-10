import accounts.BankAccount;
import accounts.CurrentAccount;
import accounts.StudentAccount;
import pearson.AccountOwner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {

    AccountOwner accountOwner = new AccountOwner("Petr", "Banan");
    accountOwner.setLastName("Bananos");

    BankAccount bankAccount = new CurrentAccount(accountOwner, "123", 100);
    BankAccount studenAccount = new StudentAccount(accountOwner, "123", 300, "Delta");


    List<BankAccount> bankAccounts = new ArrayList<>();
    bankAccounts.add(bankAccount);
    bankAccounts.add(studenAccount);

    for (BankAccount account: bankAccounts){
        if(account instanceof  StudentAccount) {
            StudentAccount stdAccount = (StudentAccount) account;
            IO.println("school: " + stdAccount.getSchoolName());
        }
    }



    studenAccount.add(400);
    studenAccount.add(100);
    studenAccount.add(750);

    printBalnace(studenAccount);

    studenAccount.sub(150);
    studenAccount.sub(20);
    studenAccount.sub(1000);

    printBalnace(studenAccount);
}

private static void printBalnace(BankAccount bankAccount){
    IO.println("blance " + bankAccount.getBalance());
}