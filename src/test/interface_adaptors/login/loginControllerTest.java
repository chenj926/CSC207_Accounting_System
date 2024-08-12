package interface_adaptors.login;

import interface_adaptors.login.user_account.UserAccountLoginController;
import org.junit.jupiter.api.Test;
import use_case.login.user_account.UserAccountLoginInputData;



class LoginControllerTest {
    private UserAccountLoginController controller;


    @Test
    void testExecute() {
        String id = "testuser";
        String password = "testpassword";

        controller.execute(id, password);

        UserAccountLoginInputData expectedInputData = new UserAccountLoginInputData(id, password);
    }
}