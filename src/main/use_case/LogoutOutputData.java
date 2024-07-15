package use_case;

public class LogoutOutputData {
    private boolean fail;

    //Constructor
    public LogoutOutputData(boolean fail) {
        this.fail = fail;
    }

    //Getters
    public boolean isFail() {return this.fail;}

    //Setter
    public void setFail(boolean fail) {
        this.fail = fail;
    }
}

