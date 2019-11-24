package com.android.medicaladvisor.models;

public class Person {

    private String name;
    private long civilId;
    private int regionId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCivilId() {
        return civilId;
    }

    public void setCivilId(long civilId) {
        this.civilId = civilId;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }
}
