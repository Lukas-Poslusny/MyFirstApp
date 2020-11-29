package cz.educanet.webik;

public class Users {
    private String username;
    private String password;

    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String changeUsername(String newUsername) {
        return this.username = newUsername;
    }

    public String changePassword(String newPassword) {
        return this.password = newPassword;
    }
}
