package use_case.HomePage;

import org.junit.jupiter.api.Test;
import use_case.homepage.HomepageTwoInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomePageTwoInputDataTest {

    @Test
    public void testConstructorAndGetters() {
        String id = "sharedUser123";
        HomepageTwoInputData inputData = new HomepageTwoInputData(id);

        assertEquals(id, inputData.getId());
    }
}
