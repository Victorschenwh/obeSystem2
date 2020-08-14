package com.dbsy.obe.pojo;

public class CoursePlan {
    private Integer id;

    private Integer courseId;

    private Integer planId;

    private Integer totalHours;

    private Integer theoryHours;

    private Integer experimentHours;

    private Integer credit;

    private String studyTerm;

    public CoursePlan(Integer id, Integer courseId, Integer planId, Integer totalHours, Integer theoryHours, Integer experimentHours, Integer credit, String studyTerm) {
        this.id = id;
        this.courseId = courseId;
        this.planId = planId;
        this.totalHours = totalHours;
        this.theoryHours = theoryHours;
        this.experimentHours = experimentHours;
        this.credit = credit;
        this.studyTerm = studyTerm;
    }

    public CoursePlan() {
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

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public Integer getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(Integer totalHours) {
        this.totalHours = totalHours;
    }

    public Integer getTheoryHours() {
        return theoryHours;
    }

    public void setTheoryHours(Integer theoryHours) {
        this.theoryHours = theoryHours;
    }

    public Integer getExperimentHours() {
        return experimentHours;
    }

    public void setExperimentHours(Integer experimentHours) {
        this.experimentHours = experimentHours;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public String getStudyTerm() {
        return studyTerm;
    }

    public void setStudyTerm(String studyTerm) {
        this.studyTerm = studyTerm == null ? null : studyTerm.trim();
    }
}