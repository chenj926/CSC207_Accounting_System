package interface_adaptors;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserCreationFailedTest {

    @Test
    void testUserCreationFailedException() {
        String errorMessage = "User creation failed due to invalid input.";
        UserCreationFailed exception = assertThrows(UserCreationFailed.class, () -> {
            throw new UserCreationFailed(errorMessage);
        });

        assertEquals(errorMessage, exception.getMessage());
    }
}

