package entity;

import java.time.LocalDate;
import java.util.Set;

public class SharedAccount extends entity.UserAccount {
    Set<String> userIdentifications;

    public SharedAccount(String id_a, String id_b) {
        userIdentifications.add(id_a);
        userIdentifications.add(id_b);
        System.out.println(id_a + id_b + " added to account.");

    }

    // add new account to shared account
    public void addAccount(String new_id) {
        userIdentifications.add(new_id);
    }

    @Override
    public void recordTransaction(String identification, float transactionAmount,
                                  LocalDate transactionDate, String transactionDescription,
                                  String recurrence, boolean periodic){
        // recording to shared account, enter personal account id for identification
        super.recordTransaction(identification, transactionAmount, transactionDate, transactionDescription, recurrence, periodic);
        // record spendings in personal account as well
        UserAccount userAccount = UserAccount.userAccounts.get(identification);
        userAccount.recordTransaction(identification, transactionAmount, transactionDate, transactionDescription, recurrence, periodic);

    }

}