package com.android.medicaladvisor.models;

public class Diagnosis {

    private String id;
    private String name;
    private int categoryId;
    private int initialSymptomId;
    private int symptomDetailsId;
    private int specialityId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getInitialSymptomId() {
        return initialSymptomId;
    }

    public void setInitialSymptomId(int initialSymptomId) {
        this.initialSymptomId = initialSymptomId;
    }

    public int getSymptomDetailsId() {
        return symptomDetailsId;
    }

    public void setSymptomDetailsId(int symptomDetailsId) {
        this.symptomDetailsId = symptomDetailsId;
    }

    public int getSpecialityId() {
        return specialityId;
    }

    public void setSpecialityId(int specialityId) {
        this.specialityId = specialityId;
    }
}
