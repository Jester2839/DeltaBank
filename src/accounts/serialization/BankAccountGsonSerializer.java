package accounts.serialization;

import accounts.BankAccount;
import com.google.gson.Gson;

import java.util.LinkedList;
import java.util.List;

public class BankAccountGsonSerializer implements BankAccountSerializer{

    Gson gson = new Gson();

    BankAccountSerializeFactory bankAccountSerializeFactory = new BankAccountSerializeFactory();

    public String serializeAll(List<BankAccount> bankAccounts){
        List<BankAccountSerialize> bankAccountSerializes = new LinkedList<>();

        for (BankAccount bankAccount : bankAccounts){
            bankAccountSerializes.add(bankAccountSerializeFactory.createBankAccountSerialize((bankAccount)));
        };

        return gson.toJson(bankAccountSerializes);
    }

    public String serialize(BankAccount bankAccount){
        BankAccountSerialize bankAccountSerialize = bankAccountSerializeFactory.createBankAccountSerialize((bankAccount));

        return gson.toJson(bankAccountSerialize);
    }

}
