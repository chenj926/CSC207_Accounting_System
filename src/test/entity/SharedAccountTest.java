package entity;

import java.util.Set;

public class SharedAccountTest {
    public static void main(String[] args) {
        SharedAccount sharedAccount = new SharedAccount("sharedTestID", "irrelevantPassword");

        assert "sharedTestID".equals(sharedAccount.getIdentification()) : "Identification doesn't match";
        assert sharedAccount.getSharedUserIdentifications().isEmpty() : "Shared user identifications should initially be empty";

        sharedAccount.addUserIdentification("user1");
        sharedAccount.addUserIdentification("user2");

        Set<String> sharedUsers = sharedAccount.getSharedUserIdentifications();
        assert sharedUsers.size() == 2 : "There should be 2 shared user identifications";
        assert sharedUsers.contains("user1") : "Shared user identifications should contain 'user1'";
        assert sharedUsers.contains("user2") : "Shared user identifications should contain 'user2'";

        sharedAccount.removeUserIdentification("user1");

        sharedUsers = sharedAccount.getSharedUserIdentifications();
        assert sharedUsers.size() == 1 : "There should be 1 shared user identification";
        assert !sharedUsers.contains("user1") : "Shared user identifications should not contain 'user1'";
        assert sharedUsers.contains("user2") : "Shared user identifications should contain 'user2'";

        System.out.println("All tests passed.");
    }
}
