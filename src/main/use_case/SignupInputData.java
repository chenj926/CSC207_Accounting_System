package use_case;

import util.*;

public class SignupInputData {
    private final String username;
    private final String password;
    private final String identification;

    // Constructor
    public SignupInputData(String username, String password, String identification){
        this.username = username;
        this.password = password;
        this.identification = identification;
    }

    // getter
    public String getUsername {
        return this.username;
    }
    public String getPassword {
        return this.password;
    }
    public String getIdentification {
        return this.identification;
    }
}