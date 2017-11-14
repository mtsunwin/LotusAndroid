package com.iuh.minhthanghuunghia.lotusflowernt.Model;

/**
 * Created by ThinkPad on 10/14/2017.
 */

public class News {
    private String id;
    private String time;
    private String userName;
    private String nickName;
    private String content;
    private boolean like;

    public News(String id, String time, String content) {
        this.id = id;
        this.time = time;
        this.content = content;
    }

    public News(String id, String time, String accountName, String userName, String content, boolean like) {
        this.id = id;
        this.time = time;
        this.userName = accountName;
        this.nickName = userName;
        this.content = content;
        this.like = like;
    }

    public News(String id) {
        this.id = id;
    }

    public News() {

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public String getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        News aNew = (News) o;

        return id != null ? id.equals(aNew.id) : aNew.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "News{" +
                "id='" + id + '\'' +
                ", time='" + time + '\'' +
                ", userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", content='" + content + '\'' +
                ", like=" + like +
                '}';
    }
}
