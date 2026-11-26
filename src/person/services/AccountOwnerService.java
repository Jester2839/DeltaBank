package person.services;

import com.google.inject.Inject;
import person.AccountOwner;
import person.factories.AccountOwnerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class AccountOwnerService {

    @Inject
    AccountOwnerFactory accountOwnerFactory;


    List<AccountOwner> accountOwners = new LinkedList<>();

    public AccountOwner createAccountOwner(String name, String lastName)
    {
        AccountOwner owner = accountOwnerFactory.createAccountOwner(name, lastName);
        owner.setUuid(UUID.randomUUID().toString());

        accountOwners.add(owner);

        return owner;
    }

}