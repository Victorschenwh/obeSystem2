package com.dbsy.obe.pojo;

public class Clazz {
    private Integer id;

    private String name;

    private String introduce;

    private Integer majorId;

    public Clazz(Integer id, String name, String introduce, Integer majorId) {
        this.id = id;
        this.name = name;
        this.introduce = introduce;
        this.majorId = majorId;
    }

    public Clazz() {
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

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }
}