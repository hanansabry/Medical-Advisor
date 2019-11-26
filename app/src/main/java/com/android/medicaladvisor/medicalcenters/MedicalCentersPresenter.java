package com.android.medicaladvisor.medicalcenters;

import com.android.medicaladvisor.DiagnosisSharedPreferences;
import com.android.medicaladvisor.backend.MedicalCentersRepository;
import com.android.medicaladvisor.backend.PersonsRepository;
import com.android.medicaladvisor.backend.RegionsRepository;
import com.android.medicaladvisor.backend.RetrievingRepository;
import com.android.medicaladvisor.models.MedicalCenter;
import com.android.medicaladvisor.models.Person;
import com.android.medicaladvisor.models.Region;

import java.util.ArrayList;

public class MedicalCentersPresenter {

    private ArrayList<MedicalCenter> medicalCenters = new ArrayList<>();
    private final PersonsRepository personsRepository;
    private final MedicalCentersRepository medicalCentersRepository;
    private final RegionsRepository regionsRepository;
    private final DiagnosisSharedPreferences diagnosisSharedPreferences;
    private ShowAvailableMedicalCenters mView;

    public MedicalCentersPresenter(ShowAvailableMedicalCenters view,
                                   PersonsRepository personsRepository,
                                   MedicalCentersRepository medicalCentersRepository,
                                   RegionsRepository regionsRepository,
                                   DiagnosisSharedPreferences diagnosisSharedPreferences) {
        mView = view;
        this.personsRepository = personsRepository;
        this.medicalCentersRepository = medicalCentersRepository;
        this.regionsRepository = regionsRepository;
        this.diagnosisSharedPreferences = diagnosisSharedPreferences;
        mView.setPresenter(this);
    }

    public void getPersonByCivilId(String civilId, RetrievingRepository.RetrievingRepositoryCallback<Person> callback) {
        personsRepository.retrieveDataById(civilId, callback);
    }

    public void getAvailableMedicalCentersInRegion(int regionId,
                                                   MedicalCentersRepository.MedicalCentersCallback callback) {
        medicalCentersRepository.retrieveMedicalCentersInRegionWithSpeciality(regionId,
                diagnosisSharedPreferences.getSpecialityId(), callback);
    }

    public void getRegionById(String regionId, RetrievingRepository.RetrievingRepositoryCallback<Region> callback) {
        regionsRepository.retrieveDataById(regionId, callback);
    }

    public int getAvailableMedicalCentersSize() {
        return medicalCenters.size();
    }

    public void onBindMedicalCenterAtPosition(MedicalCentersAdapter.MedicalCenterViewHolder holder, int position) {
        MedicalCenter medicalCenter = medicalCenters.get(position);
        holder.setMedicalCenterNameType(medicalCenter);
    }

    public void bindAvailableMedicalCenters(ArrayList<MedicalCenter> medicalCenters) {
        this.medicalCenters = medicalCenters;
    }

    public void onMedicalCenterClicked(int position) {
        MedicalCenter medicalCenter = medicalCenters.get(position);
        mView.onMedicalCenterClicked(medicalCenter);
    }

    public void setRegionName(String regionName) {
        diagnosisSharedPreferences.setRegionName(regionName);
    }

    public void setSelectedMedicalCenterName(String name) {
        diagnosisSharedPreferences.setSelectedMedicalCenter(name);
    }

}
