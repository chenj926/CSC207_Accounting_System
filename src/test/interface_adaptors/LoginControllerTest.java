package interface_adaptors;

import org.junit.jupiter.api.Test;
import use_case.LoginInputBoundary;
import use_case.LoginInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginControllerTest {

    @Test
    public void testLogIn() {
        // Arrange
        String password = "password123";
        String identification = "user123";
        LoginInputData inputData = new LoginInputData(password, identification);

        // Mock the login use case
        LoginInputBoundary loginInputBoundary = (loginInput) -> {
            assertEquals(password, loginInput.getPassword());
            assertEquals(identification, loginInput.getIdentification());
            // Simulate a successful login
            return;
        };

        LoginController loginController = new LoginController(loginInputBoundary);

        // Act
        loginController.logIn(inputData);

        // Assert
        // No additional assertions needed, as the mock LoginInputBoundary
        // already checks the input data.
    }

    @Test
    public void testLogInFailure() {
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

        // Act
        try {
            loginController.logIn(inputData);
            assertTrue(false, "Expected RuntimeException to be thrown");
        } catch (RuntimeException e) {
            assertEquals("Login failed", e.getMessage());
        }
    }
}