public class UserAccountManager {

    private User user;

    public UserAccountManager(User user) {
        this.user = user;
    }

    public void updateUserInformation(String newUsername, String newPassword, String newEmail, String newAddress, String newType) {
        user.setUsername(newUsername);
        user.setPassword(newPassword);
        user.setEmail(newEmail);
        user.setAddress(newAddress);
        user.setType(newType);
    }

    public void deactivateAccount() {
        user.setUsername("");
        user.setPassword("");
        user.setEmail("");
        user.setAddress("");
        user.setType("");
    }
}