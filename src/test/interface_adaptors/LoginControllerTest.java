package interface_adaptors;

import org.junit.jupiter.api.Test;
import use_case.LoginInputBoundary;
import use_case.LoginInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginControllerTest {

    @Test
    public void testLogIn_Success() {
        // Arrange
        String password = "password123";
        String identification = "user123";
        LoginInputData inputData = new LoginInputData(password, identification);

        // Mock the login use case
        LoginInputBoundary loginInputBoundary = (loginInput) -> {
            assertEquals(password, loginInput.getPassword());
            assertEquals(identification, loginInput.getIdentification());
            // Simulate a successful login
        };
    }

    @Test
    public void testLogIn_Failure() {
        // Arrange
        String password = "wrongpassword";
        String identification = "user123";
        LoginInputData inputData = new LoginInputData(password, identification);

        // Mock the login use case
        LoginInputBoundary loginInputBoundary = (loginInput) -> {
            assertEquals(password, loginInput.getPassword());
            assertEquals(identification, loginInput.getIdentification());
            // Simulate a failed login
            throw new RuntimeException("Login failed");
        };

        LoginController loginController = new LoginController(loginInputBoundary);

    }
}