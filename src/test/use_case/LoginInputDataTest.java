package use_case;

import org.junit.jupiter.api.Test;
import use_case.Login.LoginInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginInputDataTest {

    @Test
    public void testConstructorAndGetters() {
        String password = "password123";
        String identification = "user123";

        LoginInputData inputData = new LoginInputData(password, identification);

        assertEquals(password, inputData.getPassword());
        assertEquals(identification, inputData.getIdentification());
    }
}


