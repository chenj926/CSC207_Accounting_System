package use_case;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashSet;
import java.util.Set;

public class ShareAccountInputDataTest {

    @Test
    public void testConstructorAndGetters() {
        Set<String> sharedUserIds = new HashSet<>();
        sharedUserIds.add("user1");
        sharedUserIds.add("user2");

        ShareAccountInputData shareAccountInputData = new ShareAccountInputData("shareAccountID", sharedUserIds);

        assertEquals("Share account ID doesn't match", "shareAccountID", shareAccountInputData.getShareAccountIdentification());
        assertEquals("Shared user identifications don't match", sharedUserIds, shareAccountInputData.getSharedUserIdentifications());
    }
}

