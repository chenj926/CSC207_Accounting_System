package use_case.homepage;

public class HomepageTwoOutputData {
    private String[] basicUserInfo;

    public HomepageTwoOutputData(String[] basicUserInfo) {
        this.basicUserInfo = basicUserInfo;
    }

    public String[] getBasicUserInfo() {
        return this.basicUserInfo;
    }

    public void setBasicUserInfo(String[] basicUserInfo) {
        this.basicUserInfo = basicUserInfo;
    }

}
