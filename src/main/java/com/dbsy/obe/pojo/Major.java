package com.dbsy.obe.pojo;

public class Major {
    private Integer id;

    private String name;

    private String introduce;

    private Integer departmentId;

    public Major(Integer id, String name, String introduce, Integer departmentId) {
        this.id = id;
        this.name = name;
        this.introduce = introduce;
        this.departmentId = departmentId;
    }

    public Major() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }
}