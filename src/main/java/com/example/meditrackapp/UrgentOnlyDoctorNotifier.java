package com.example.meditrackapp;

public class UrgentOnlyDoctorNotifier implements AdmissionObserver {
    private String urgentDoctorName;
    public UrgentOnlyDoctorNotifier(String urgentDoctorName) {
        this.urgentDoctorName = urgentDoctorName;
    }

    @Override
    public void onPatientAdmitted(String patientName, int riskScore) {
        if(riskScore>=70)System.out.println("[Doctor " + urgentDoctorName+ "] Patient " + patientName
                + " admitted. Risk score: " + riskScore);
    }
}