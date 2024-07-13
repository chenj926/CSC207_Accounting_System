package main.entity;

import util.*;

public class SharedAccount extends UserAccount {
    Set<String> userIdentifications;
    private float[] userShares;

    public SharedAccount(string id_a, string id_b) {
        userIdentifications.add(id_a);
        userIdentifications.add(id_b);
        System.out.println(id_a + id_b + " added to account.");

        // super();
        // this.userIdentifications = userIdentifications;
        // this.userShares = userShares;
    }

    @Override
    public void recordTransaction(float amount, user_id) {
        // recording to shared account
        super.RecordTransaction(amount);
        // record spendings in personal account as well
        UserAccount userAccount = getUserAccount(userid);
        userAccount.RecordTransaction(userAmount);

        //for (int i = 0; i < userShares.length; i++) {
        //    float userAmount = amount * userShares[i];
        //    UserAccount userAccount = getUserAccount(userIdentifications[i]);
        //    userAccount.RecordTransaction(userAmount);
        }
    }

}