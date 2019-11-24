package com.android.medicaladvisor.models;

public class SymptomDetail {

    private String id;
    private String name;
    private String initialSymptomIds;

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

    public String getInitialSymptomIds() {
        return initialSymptomIds;
    }

    public void setInitialSymptomIds(String initialSymptomIds) {
        this.initialSymptomIds = initialSymptomIds;
    }
}
