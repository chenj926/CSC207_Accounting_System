package interface_adaptors.homepage;

public class HomepageTwoState {
    private String id;
    private String username;
    private String totalIncome;
    private String totalOutflow;
    private String totalBalance;
    private String[] basicUserInfo;
    private String err;

    // getters
    public String getId() {
        return this.id;
    }
    public String getUsername() {
        return this.username;
    }
    public String getTotalIncome() {
        return this.totalIncome;
    }
    public String getTotalOutflow() {
        return this.totalOutflow;
    }
    public String getTotalBalance() {
        return this.totalBalance;
    }
    public String[] getBasicUserInfo() {
        return this.basicUserInfo;
    }
    public String getErr() {
        return this.err;
    }

    // setters
    public void setId(String id) {
        this.id = id;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setTotalIncome(String totalIncome) {
        this.totalIncome = totalIncome;
    }
    public void setTotalOutflow(String totalOutflow) {
        this.totalOutflow = totalOutflow;
    }
    public void setTotalBalance(String totalBalance) {
        this.totalBalance = totalBalance;
    }
    public void setBasicUserInfo(String[] basicUserInfo) {
        this.basicUserInfo = basicUserInfo;
    }
    public void setErr(String err) {
        this.err = err;
    }
}

