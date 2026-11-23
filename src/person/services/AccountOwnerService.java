package person.services;

import person.AccountOwner;
import person.factories.AccountOwnerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class AccountOwnerService {

    private final AccountOwnerFactory accountOwnerFactory;

    public AccountOwnerService(AccountOwnerFactory accountOwnerFactory) {
        this.accountOwnerFactory = accountOwnerFactory;
    }

    List<AccountOwner> accountOwners = new LinkedList<>();

    public AccountOwner createAccountOwner(String name, String lastName)
    {
        AccountOwner owner = accountOwnerFactory.createAccountOwner(name, lastName);
        owner.setUuid(UUID.randomUUID().toString());

        accountOwners.add(owner);

        return owner;
    }

}