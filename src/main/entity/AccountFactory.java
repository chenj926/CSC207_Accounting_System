package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AccountFactory {

    protected static Map<String, List<String>> allAccounts;

    public AccountFactory(String username, String password, String identification) {
        if (allAccounts.containsKey(identification)) {
            System.out.println("The identification '" + identification + "' exists in the system, please choose another identification");
        } else {
            List<String> list = new ArrayList<>();
            list.add(username);
            list.add(password);
            allAccounts.put(identification, list);
        }
    }
}