package com.dbsy.obe.pojo;

public class Plan {
    private Integer id;

    private String name;

    private Integer year;

    private String introduce;

    private Integer majorId;

    public Plan(Integer id, String name, Integer year, String introduce, Integer majorId) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.introduce = introduce;
        this.majorId = majorId;
    }

    public Plan() {
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
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