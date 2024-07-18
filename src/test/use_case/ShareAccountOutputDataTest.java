package use_case;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashSet;
import java.util.Set;

public class ShareAccountOutputDataTest {

    @Test
    public void testConstructorAndGetters() {
        Set<String> ids = new HashSet<>();
        ids.add("id1");
        ids.add("id2");

        ShareAccountOutputData shareAccountOutputData = new ShareAccountOutputData("sharedAccountID", ids, true);

        assertEquals("Share account ID doesn't match", "sharedAccountID", shareAccountOutputData.getShareAccountId());
        assertEquals("IDs don't match", ids, shareAccountOutputData.getId());
        assertTrue("shareAccountFailed flag doesn't match", shareAccountOutputData.isShareAccountFailed());
    }

    @Test
    public void testConstructorAndGettersShareAccountNotFailed() {
        Set<String> ids = new HashSet<>();
        ids.add("id1");
        ids.add("id2");

        ShareAccountOutputData shareAccountOutputData = new ShareAccountOutputData("sharedAccountID", ids, false);

        assertEquals("Share account ID doesn't match", "sharedAccountID", shareAccountOutputData.getShareAccountId());
        assertEquals("IDs don't match", ids, shareAccountOutputData.getId());
        assertFalse("shareAccountFailed flag doesn't match", shareAccountOutputData.isShareAccountFailed());
    }
}

