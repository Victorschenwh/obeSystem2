package com.dbsy.obe.pojo;

public class Course {
    private Integer id;

    private String number;

    private String name;

    private Integer departmentId;

    public Course(Integer id, String number, String name, Integer departmentId) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.departmentId = departmentId;
    }

    public Course() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }
}