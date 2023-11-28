public class PasswordReset {
    private User user;

    public PasswordReset(User user) {
        this.user = user;
    }

    public void resetPassword(String newPassword) {
        user.setPassword(newPassword);
    }
}