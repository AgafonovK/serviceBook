package com.xvr.serviceBook.form;

public class AppUserForm {

    private Long userId;
    private String userName;
    private String encryptedPassword;
    private boolean enabled;

    public AppUserForm() {
    }

    public AppUserForm(Long userId, String userName, String encryptedPassword, boolean enabled) {
        this.userId = userId;
        this.userName = userName;
        this.encryptedPassword = encryptedPassword;
        this.enabled = enabled;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "AppUserForm{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", encryptedPassword='" + encryptedPassword + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
