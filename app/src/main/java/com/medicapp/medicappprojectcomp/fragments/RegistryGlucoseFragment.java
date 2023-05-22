package com.medicapp.medicappprojectcomp.fragments;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.medicapp.medicappprojectcomp.R;
import com.medicapp.medicappprojectcomp.databinding.FragmentRegistryGlucoseBinding;
import com.medicapp.medicappprojectcomp.models.Glucose;
import com.medicapp.medicappprojectcomp.models.Insulin;
import com.medicapp.medicappprojectcomp.servicies.AlertsHelper;

import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegistryGlucoseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistryGlucoseFragment extends BaseFragment {

    FragmentRegistryGlucoseBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegistryGlucoseBinding.inflate(inflater);
        alerts=new AlertsHelper(getContext());
        addCalendatInputText();
        binding.buttonAddG.setOnClickListener(v->addRegisterGlucose());
        return binding.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void addRegisterGlucose() {
        Glucose glucose=getDataGlucose();
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users");;
        String insulinKey = userRef.child("history_glucose").push().getKey();
        userRef.child(user.getUid()).child("history_glucose").child(insulinKey).setValue(glucose)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        alerts.indefiniteSnackbar(binding.getRoot(), getResources().getString(R.string.updateSuccesfull));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        alerts.indefiniteSnackbar(binding.getRoot(), getResources().getString(R.string.updateError));
                    }
                });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private Glucose getDataGlucose() {
        Glucose glucose=new Glucose();
        glucose.setQuantity(Double.parseDouble(binding.measureTextInput.getEditText().getText().toString()));
        glucose.setDate(binding.dateTextInput.getEditText().getText().toString());
        int hour = binding.timePickerGluc.getHour();
        int minute = binding.timePickerGluc.getMinute();
        String time = String.format(Locale.getDefault(), "%02d:%02d", hour, minute);
        glucose.setHour(time);
        return glucose;
    }

    private void addCalendatInputText() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialogBirthday = new DatePickerDialog(getContext(),
                R.style.datePickerDialog,(view, year1, monthOfYear, dayOfMonth) -> {
            String monthZero = String.format("%02d", (monthOfYear + 1));
            String dayZero = String.format("%02d", dayOfMonth);
            String selectedDate =  year1+"-"+ monthZero + "-" +dayZero;
            binding.dateEditText.setText(selectedDate);
        }, year, month, day);

        binding.dateEditText.setOnClickListener(v -> datePickerDialogBirthday.show());
        binding.dateEditText.setInputType(InputType.TYPE_NULL);
    }
}