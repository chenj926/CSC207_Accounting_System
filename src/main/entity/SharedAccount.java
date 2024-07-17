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
        return this.sharedUserIdentifications;
    }

    public void addUserIdentification(String identification) {
        sharedUserIdentifications.add(identification);
    }

    public void removeUserIdentification(String identification) {
        sharedUserIdentifications.remove(identification);
    }
    public void setSharedUserIdentifications(Set<String> sharedUserIdentifications) {
        this.sharedUserIdentifications = sharedUserIdentifications;
    }

//    @Override
//    public String getIdentification() {
//        System.out.println("Please use getSharedUserIdentifications method.");
//        return null;
//    }
}