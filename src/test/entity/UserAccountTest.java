package entity;

public class UserAccountTest {
    @Test
    public void testRecordTransaction() {
        UserAccount account = new UserAccount();
        account.recordTransaction(300.0);
        assertEquals(300.0, account.totalCurrentBalance);
    }
}