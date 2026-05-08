package com.example.meditrackapp;

public class PatientAdmissionV2 {
    private RiskCalculationStrategy strategy;

    public PatientAdmissionV2(RiskCalculationStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(RiskCalculationStrategy strategy) {
        this.strategy = strategy;
    }

    public int calculateRiskScore(double temperatureC, int age) {
        return strategy.calculateRisk(temperatureC, age);
    }
}
