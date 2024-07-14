package entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SharedAccount extends UserAccount {
    private Set<String> sharedUserIdentifications;

    // adds first id to account
    public SharedAccount(String shareAccountIdentification) {
        super(null, null, shareAccountIdentification); // SharedAccount have own shared account id
        this.sharedUserIdentifications = new HashSet<>();
    }

    public Set<String> getSharedUserIdentifications() {
        return new HashSet<>(sharedUserIdentifications);
    }

    public void addUserIdentification(String identification) {
        sharedUserIdentifications.add(identification);
    }

    public void removeUserIdentification(String identification) {
        sharedUserIdentifications.remove(identification);
    }

//    @Override
//    public String getIdentification() {
//        System.out.println("Please use getSharedUserIdentifications method.");
//        return null;
//    }
}



/*
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
 */