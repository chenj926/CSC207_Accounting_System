package interface_adaptors.homepage;

public class HomepageTwoState {
    protected String id;
//    protected String username;
    protected String totalIncome;
    protected String totalOutflow;
    protected String totalBalance;
    protected String[] basicUserInfo;
    protected String err;

    // getters
    public String getId() {
        return this.id;
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
