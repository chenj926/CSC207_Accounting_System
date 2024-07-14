package entity;

public class AccountFactory {
    private String username;
    private String password;
    private String identification;

    public AccountFactory(String username, String password, String identification) {
        this.username = username;
        this.password = password;
        this.identification = identification;
    }

    // getters
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getIdentification() {
        return identification;
    }

    // setters
    public void setIdentification(String identification) {
        this.identification = identification;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }


}