package com.iuh.minhthanghuunghia.lotusflowernt.Model;

/**
 * Created by ThinkPad on 10/14/2017.
 */

public class User {
    private String username;
    private String password;
    private String email;
    private String nickname;
    private String address;
    private String gender;
    private String about;
    private byte[] bitmapAvatar;
    private byte[] bitmapCover;

    public byte[] getBitmapCover() {
        return bitmapCover;
    }

    public void setBitmapCover(byte[] bitmapCover) {
        this.bitmapCover = bitmapCover;
    }

    public byte[] getBitmapAvatar() {
        return bitmapAvatar;
    }

    public void setBitmapAvatar(byte[] bitmapAvatar) {
        this.bitmapAvatar = bitmapAvatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public User(String username) {
        this.username = username;
    }

    public User(String username, String password, String email, String nickname, String address, String gender, String about) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.address = address;
        this.gender = gender;
        this.about = about;
    }

    public User(String username, String email, String nickname, String address, String gender, String about) {
        this.username = username;
        this.email = email;
        this.nickname = nickname;
        this.address = address;
        this.gender = gender;
        this.about = about;
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (username != null ? !username.equals(user.username) : user.username != null)
            return false;
        return email != null ? email.equals(user.email) : user.email == null;

    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", address='" + address + '\'' +
                ", gender='" + gender + '\'' +
                ", about='" + about + '\'' +
                '}';
    }
}
