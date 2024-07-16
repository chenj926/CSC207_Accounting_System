package use_case;

public class LogoutOutputData {
    private String fail;

    //Constructor
    public LogoutOutputData(String fail) {
        this.fail = fail;
    }

    //getter
    public String getFail() {
        return fail;
    }

    //setter
    public void setFail(String fail) {
        this.fail = fail;
    }
}
