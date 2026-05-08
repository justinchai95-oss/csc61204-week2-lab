package com.example.meditrackapp;

public interface RiskCalculationStrategy {
    int calculateRisk(double temperatureC, int age);
}
