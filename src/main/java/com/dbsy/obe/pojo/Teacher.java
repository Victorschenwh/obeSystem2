package com.dbsy.obe.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Teacher {
    private Integer id;

    private String username;

    private String password;

    private String name;

    private Boolean gender;

    @DateTimeFormat(pattern = "yyyy-MM-dd")//前台字符串->后台Date()
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")//后台Date()->前台字符串
    private Date birthday;

    private Integer titleId;

    private Integer departmentId;

    private String post;

    public Teacher(Integer id, String username, String password, String name, Boolean gender, Date birthday, Integer titleId, Integer departmentId, String post) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.titleId = titleId;
        this.departmentId = departmentId;
        this.post = post;
    }

    public Teacher() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getTitleId() {
        return titleId;
    }

    public void setTitleId(Integer titleId) {
        this.titleId = titleId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post == null ? null : post.trim();
    }
}