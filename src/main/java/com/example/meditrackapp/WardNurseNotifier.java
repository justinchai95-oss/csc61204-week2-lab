package com.example.meditrackapp;

public class WardNurseNotifier implements AdmissionObserver {
    @Override
    public void onPatientAdmitted(String patientName, int riskScore) {
        System.out.println("[Ward Nurse] Assigning ward for: " + patientName);
    }
}