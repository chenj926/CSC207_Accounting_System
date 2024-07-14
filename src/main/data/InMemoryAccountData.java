package data;

import entity.AccountFactory;
import use_case.UserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryUserRepository implements UserRepository {

    private final Map<String, AccountFactory> users;

    @Override
    public void save(AccountFactory user) {
        users.put(user.getIdentification(), user);
    }

    @Override
    public AccountFactory findById(String id) {
        return users.get(id);
    }

    @Override
    public List<AccountFactory> findAll() {
        return new ArrayList<>(users.values());
    }
}


