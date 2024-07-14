package main.entity;

import util.*;

public class SharedAccount extends UserAccount {
    private String[] userIdentifications;
    private float[] userShares;

    public SharedAccount(float balance, float income, float outflow, String identification, String[] userIdentifications, float[] userShares) {
        super(balance, income, outflow, identification);
        this.userIdentifications = userIdentifications;
        this.userShares = userShares;
    }

    @Override
    public void RecordTransaction(float amount) {
        super.RecordTransaction(amount);

        for (int i = 0; i < userShares.length; i++) {
            float userAmount = amount * userShares[i];
            UserAccount userAccount = getUserAccount(userIdentifications[i]);
            userAccount.RecordTransaction(userAmount);
        }
    }

    private UserAccount getUserAccount(String userIdentification) {
        return new UserAccount(0.0f, 0.0f, 0.0f, userIdentification);
    }
}