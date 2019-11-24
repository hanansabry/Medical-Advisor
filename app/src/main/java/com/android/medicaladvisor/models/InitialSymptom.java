package com.android.medicaladvisor.models;

import java.util.ArrayList;

public class InitialSymptom {
    private String id;
    private String name;
    private String categoryId;
    private ArrayList<String> symptomDetails;

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

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public ArrayList<String> getSymptomDetails() {
        return symptomDetails;
    }

    public void setSymptomDetails(ArrayList<String> symptomDetails) {
        this.symptomDetails = symptomDetails;
    }
}
