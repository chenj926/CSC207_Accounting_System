package entity;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

public class SharedAccount extends entity.UserAccount {
    Set<String> userIdentifications;
    protected static Map<Set<String>, UserAccount> sharedAccounts;

    public SharedAccount(String identification) {
        super(identification);
        userIdentifications.add(identification);
        System.out.println(identification + " added to account.");

    }
    public SharedAccount(float balance, float income, float outflow, String identification) {
        super(balance, income, outflow, identification);
        userIdentifications.add(identification);
        System.out.println(identification + " added to account.");
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