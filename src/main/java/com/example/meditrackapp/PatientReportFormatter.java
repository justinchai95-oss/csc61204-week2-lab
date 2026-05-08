package com.example.meditrackapp;

public class PatientReportFormatter {
    public String format(String name, int riskScore) {
    return "Patient: " + name + " | Risk: " + riskScore;
}
}
