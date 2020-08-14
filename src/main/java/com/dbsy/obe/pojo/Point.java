package com.dbsy.obe.pojo;

public class Point {
    private Integer id;

    private String synopsis;

    private String introduction;

    private Integer requirementId;

    public Point(Integer id, String introduction, Integer requirementId,String synopsis) {
        this.id = id;
        this.synopsis = synopsis;
        this.introduction = introduction;
        this.requirementId = requirementId;
    }

    @Override
    public String toString() {
        return "Point{" +
                "id=" + id +
                ", synopsis=" + synopsis +
                ", introduction='" + introduction + '\'' +
                ", requirementId=" + requirementId +
                '}';
    }

    public Point() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public Integer getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(Integer requirementId) {
        this.requirementId = requirementId;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
}