package accounts.serialization;

import accounts.BankAccount;

import java.util.List;

public class BankAccountJSONSerializator {

    BankAccountSerializeFactory bankAccountSerializeFactory = new BankAccountSerializeFactory();
    public String serializeAll(List<BankAccount> bankAccounts) {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int i = 0; i < bankAccounts.size(); i++) {
            if (i > 0) {
                builder.append(",");
            }
            builder.append(this.serialize(bankAccounts.get(i)));
        }
        builder.append("]");
        return builder.toString();
    }

    public String serialize(BankAccount bankAccount){
        BankAccountSerialize bankAccountSerialize = bankAccountSerializeFactory.createBankAccountSerialize(bankAccount);

        StringBuilder builder = new StringBuilder();
        builder.append("{");
        builder.append("\"bankAccountNumber\":");
        builder.append("\"" + bankAccountSerialize.accountNumber + "\"");
        builder.append("}");
        return builder.toString();
    }

}
