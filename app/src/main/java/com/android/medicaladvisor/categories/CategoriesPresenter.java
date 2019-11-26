package com.android.medicaladvisor.categories;

import com.android.medicaladvisor.DiagnosisSharedPreferences;
import com.android.medicaladvisor.backend.CategoriesRepository;
import com.android.medicaladvisor.backend.RetrievingRepository;
import com.android.medicaladvisor.models.Category;

public class CategoriesPresenter {

    private final CategoriesRepository categoriesRepository;
    private final DiagnosisSharedPreferences diagnosisSharedPreferences;

    public CategoriesPresenter(CategoriesRepository categoriesRepository, DiagnosisSharedPreferences diagnosisSharedPreferences) {
        this.categoriesRepository = categoriesRepository;
        this.diagnosisSharedPreferences = diagnosisSharedPreferences;
    }

    public void retrieveCategories(RetrievingRepository.RetrievingRepositoryCallback<Category> callback) {
        categoriesRepository.retrieveAllData(callback);
    }

    public void setDiagnosisCategory(Category category) {
        diagnosisSharedPreferences.saveSelectedCategory(category);
    }
}
