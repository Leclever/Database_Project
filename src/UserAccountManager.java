package hk.edu.polyu.comp.comp2411.project.user;

import hk.edu.polyu.comp.comp2411.project.user.User;

public class UserAccountManager {

    private User user;

    public UserAccountManager(User user) {
        this.user = user;
    }

    public void updateUserInformation(String newUsername, String newPassword, String newEmail, String newAddress) {
        user.setUsername(newUsername);
        user.setPassword(newPassword);
        user.setEmail(newEmail);
        user.setAddress(newAddress);
    }

    public void deactivateAccount() {
        user.setUsername("");
        user.setPassword("");
        user.setEmail("");
        user.setAddress("");
    }
}