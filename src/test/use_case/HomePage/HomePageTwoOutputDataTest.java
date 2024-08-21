package use_case.HomePage;

import org.junit.jupiter.api.Test;
import use_case.homepage.HomepageTwoOutputData;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class HomePageTwoOutputDataTest {

    @Test
    public void testConstructorAndGetter() {
        String[] basicUserInfo = {"John Doe", "john.doe@example.com", "User123"};

        // Create an instance of HomepageTwoOutputData using the constructor
        HomepageTwoOutputData outputData = new HomepageTwoOutputData(basicUserInfo);

        // Verify that the getter returns the correct information
        assertArrayEquals(basicUserInfo, outputData.getBasicUserInfo());
    }

    @Test
    public void testSetter() {
        String[] basicUserInfo = {"John Doe", "john.doe@example.com", "User123"};
        String[] newUserInfo = {"Jane Doe", "jane.doe@example.com", "User456"};

        // Create an instance of HomepageTwoOutputData using the constructor
        HomepageTwoOutputData outputData = new HomepageTwoOutputData(basicUserInfo);

        // Use the setter to update the user information
        outputData.setBasicUserInfo(newUserInfo);

        // Verify that the getter returns the updated information
        assertArrayEquals(newUserInfo, outputData.getBasicUserInfo());
    }
}
