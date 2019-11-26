package com.android.medicaladvisor.medicalcenters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.medicaladvisor.R;
import com.android.medicaladvisor.models.MedicalCenter;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MedicalCentersAdapter extends RecyclerView.Adapter<MedicalCentersAdapter.MedicalCenterViewHolder> {

    private final MedicalCentersPresenter presenter;

    public MedicalCentersAdapter(MedicalCentersPresenter presenter) {
        this.presenter = presenter;
    }

    public void bindMedicalCenters(ArrayList<MedicalCenter> medicalCenters) {
        presenter.bindAvailableMedicalCenters(medicalCenters);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MedicalCenterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.medical_center_item_layout, parent, false);
        return new MedicalCenterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicalCenterViewHolder holder, int position) {
        presenter.onBindMedicalCenterAtPosition(holder, position);
    }

    @Override
    public int getItemCount() {
        return presenter.getAvailableMedicalCentersSize();
    }

    class MedicalCenterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView centerNameTypeTextView;
        private Context context;

        public MedicalCenterViewHolder(@NonNull View itemView) {
            super(itemView);
            this.context = itemView.getContext();
            centerNameTypeTextView = itemView.findViewById(R.id.center_name_type_textview);
            centerNameTypeTextView.setOnClickListener(this);
        }

        public void setMedicalCenterNameType(MedicalCenter medicalCenter) {
            centerNameTypeTextView.setText(String.format(context.getString(R.string.center_name_type),
                    medicalCenter.getName(), medicalCenter.getType()));
        }

        @Override
        public void onClick(View v) {
            presenter.onMedicalCenterClicked(getAdapterPosition());
        }
    }
}
