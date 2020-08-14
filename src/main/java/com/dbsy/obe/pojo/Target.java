package com.dbsy.obe.pojo;

public class Target {
    private Integer id;

    private String synopsis;

    private String introduction;

    private Integer planId;

    public Target(Integer id, String synopsis, String introduction, Integer planId) {
        this.id = id;
        this.synopsis = synopsis;
        this.introduction = introduction;
        this.planId = planId;
    }

    public Target() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis == null ? null : synopsis.trim();
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }
}