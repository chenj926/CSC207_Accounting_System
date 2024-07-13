package entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class SharedAccountTest {
    @Test
    public void testAddAccount(){
        SharedAccount sharedAccount = new SharedAccount("a", "b"):
        String newAccount = "c";
        sharedAccount.AddAccount(newAccount);

        /*Check if Account successfully added*/
        assertTrue((sharedAccount.identifications).contains(newAccount));
    }
}