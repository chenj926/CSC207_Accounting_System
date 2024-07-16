package use_case;

import entity.AccountFactory;
import java.util.List;

public interface UserRepository {
    void save(AccountFactory user);
    AccountFactory findById(String id);
    List<AccountFactory> findAll();
}
