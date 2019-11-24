package com.android.medicaladvisor.models;

import java.util.ArrayList;

public class Category {
    private String id;
    private String name;
    private ArrayList<String> initialSymptoms;

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

    public ArrayList<String> getInitialSymptoms() {
        return initialSymptoms;
    }

    public void setInitialSymptoms(ArrayList<String> initialSymptoms) {
        this.initialSymptoms = initialSymptoms;
    }
}
