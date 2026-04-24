package com.example.meditrackapp;

public class Patient {

    // Fields
    String name;
    int age;
    double temperatureC;
    String bloodType;
    boolean isAdmitted;

    // Constructor
    public Patient(String name, int age, double temperatureC, String bloodType, boolean isAdmitted) {
        this.name         = name;
        this.age          = age;
        this.temperatureC = temperatureC;
        this.bloodType    = bloodType;
        this.isAdmitted   = isAdmitted;
    }

    // Returns risk level based on temperature
    public String assessRisk() {
        if (temperatureC >= 39.5) return "HIGH";
        if (temperatureC >= 37.5) return "MODERATE";
        return "LOW";
    }

    // Print patient details
    public void printSummary() {
        System.out.println("--- Patient Summary ---");
        System.out.println("Name:             " + this.name);
        System.out.println("Age:              " + this.age);
        System.out.println("Temp:             " + this.temperatureC + "°C");
        System.out.println("Blood:            " + this.bloodType);
        System.out.println("Risk:             " + assessRisk());
        System.out.println("Admission Status: " + this.isAdmitted);
    }

    public static void main(String[] args) {
        Patient p1 = new Patient("Maria Santos", 45, 38.9, "O+", true);
        Patient p2 = new Patient("Ahmad Razali", 72, 37.1, "A-", false);
        Patient p3 = new Patient("Justin Chai", 20, 39.6, "B+", true);

        p1.printSummary();
        p2.printSummary();
        p3.printSummary();
    }
}