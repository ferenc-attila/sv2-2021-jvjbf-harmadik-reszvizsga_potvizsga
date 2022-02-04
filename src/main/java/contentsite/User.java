package contentsite;

import java.util.Objects;

public class User {

    private String userName;
    private int password;
    private boolean premiumMember;
    private boolean isLogIn;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = (userName + password).hashCode();
    }

    public void upgradeForPremium() {
        this.premiumMember = true;
    }

    public void setLogIn(boolean value) {
        this.isLogIn = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return password == user.password && userName.equals(user.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, password);
    }

    public String getUserName() {
        return userName;
    }

    public int getPassword() {
        return password;
    }

    public boolean isPremiumMember() {
        return premiumMember;
    }

    public boolean isLogIn() {
        return isLogIn;
    }
}
