package com.dbsy.obe.pojo;

public class CoursePoint {
    private Integer id;

    private Integer courseId;

    private Integer pointId;

    private Double weight;

    public CoursePoint(Integer id, Integer courseId, Integer pointId, Double weight) {
        this.id = id;
        this.courseId = courseId;
        this.pointId = pointId;
        this.weight = weight;
    }

    public CoursePoint() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getPointId() {
        return pointId;
    }

    public void setPointId(Integer pointId) {
        this.pointId = pointId;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}