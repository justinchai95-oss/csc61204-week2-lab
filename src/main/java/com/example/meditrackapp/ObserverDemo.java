package com.example.meditrackapp;

public class ObserverDemo {
    public static void main(String[] args) {
        Hospital hospital = new Hospital("MediTrack Central");

// Register observers
        hospital.addObserver(new DoctorNotifier("Dr. Lim"));
        hospital.addObserver(new BillingNotifier());
        hospital.addObserver(new AuditNotifier());

// Add nurse using long way (need variable to remove later)
        WardNurseNotifier nurse = new WardNurseNotifier();
        hospital.addObserver(nurse);

// First patient — nurse IS notified
        hospital.admitPatient("Maria Santos", 80);

        System.out.println();

// Remove nurse
        hospital.removeObserver(nurse);

// Second patient — nurse is NOT notified
        hospital.admitPatient("Ahmad Razali", 25);
    }
}


