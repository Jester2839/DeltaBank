package accounts.serialization;

import accounts.BankAccount;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.util.LinkedList;
import java.util.List;

public class BankAccountJacksonSerializer implements BankAccountSerializer {

    // s tímto úkolem mi pomohlo AI

    private final XmlMapper xmlMapper;

    private final BankAccountSerializeFactory bankAccountSerializeFactory = new BankAccountSerializeFactory();

    public BankAccountJacksonSerializer() {
        xmlMapper = new XmlMapper();
        xmlMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    public String serializeAll(List<BankAccount> bankAccounts) {
        List<BankAccountSerialize> bankAccountSerializes = new LinkedList<>();

        for (BankAccount bankAccount : bankAccounts) {
            bankAccountSerializes.add(bankAccountSerializeFactory.createBankAccountSerialize(bankAccount));
        }

        try {
            return xmlMapper.writer().withRootName("bankAccounts").writeValueAsString(bankAccountSerializes);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Chyba při XML serializaci seznamu účtů", e);
        }
    }

    @Override
    public String serialize(BankAccount bankAccount) {
        BankAccountSerialize bankAccountSerialize = bankAccountSerializeFactory.createBankAccountSerialize(bankAccount);

        try {
            return xmlMapper.writer().withRootName("bankAccount").writeValueAsString(bankAccountSerialize);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Chyba při XML serializaci účtu", e);
        }
    }
}
