package hk.edu.polyu.comp.comp2411.project.user;

import hk.edu.polyu.comp.comp2411.project.user.User;

public class PasswordReset {
    private User user;

    public PasswordReset(User user) {
        this.user = user;
    }

    public void resetPassword(String newPassword) {
        user.setPassword(newPassword);
    }
}