package use_case;

import java.util.*;

public interface ShareAccountOutputBoundary {
    void prepareSuccessView(ShareAccountOutputData users);

    void prepareFailView(String error);
}