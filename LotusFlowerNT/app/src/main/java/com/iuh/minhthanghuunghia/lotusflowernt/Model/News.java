package com.iuh.minhthanghuunghia.lotusflowernt.Model;

import java.util.Date;

/**
 * Created by ThinkPad on 10/14/2017.
 */

public class News {
    private String id;
    private Date time;
    private String content;

    public News(String id, Date time, String content) {
        this.id = id;
        this.time = time;
        this.content = content;
    }

    public News(String id) {
        this.id = id;
    }

    public News() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
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
}
