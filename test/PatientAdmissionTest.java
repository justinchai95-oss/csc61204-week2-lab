package com.example.meditrackapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class PatientAdmissionTest {

    private PatientAdmission admission;

    @BeforeEach
    void setUp() {
        admission = new PatientAdmission();
    }

    // --- calculateRiskScore tests ---
    @Test
    void calculateRiskScore_highTempElderlyPatient_returnsHighScore() {
        int score = admission.calculateRiskScore(40.0, 70);
        assertEquals(80, score);
    }

    @Test
    void calculateRiskScore_normalTempYoungPatient_returnsZero() {
        int score = admission.calculateRiskScore(36.5, 25);
        assertEquals(0, score);
    }

    @Test
    void calculateRiskScore_elevatedTempMiddleAge_returnsMediumScore() {
        int score = admission.calculateRiskScore(38.0, 55);
        assertEquals(40, score);
    }

    // --- getAdmissionPriority tests ---
    @Test
    void getAdmissionPriority_scoreAbove70_returnsUrgent() {
        assertEquals("URGENT", admission.getAdmissionPriority(80));
    }

    @Test
    void getAdmissionPriority_scoreBetween30And70_returnsModerate() {
        assertEquals("MODERATE", admission.getAdmissionPriority(40));
    }

    @Test
    void getAdmissionPriority_scoreBelow30_returnsRoutine() {
        assertEquals("ROUTINE", admission.getAdmissionPriority(10));
    }

    // --- isValidPatientName tests ---
    @Test
    void isValidPatientName_validName_returnsTrue() {
        assertTrue(admission.isValidPatientName("Maria Santos"));
    }

    @Test
    void isValidPatientName_nullName_returnsFalse() {
        assertFalse(admission.isValidPatientName(null));
    }

    @Test
    void isValidPatientName_emptyString_returnsFalse() {
        assertFalse(admission.isValidPatientName(""));
    }

    // --- Intermediate boundary tests ---
    @Test
    void calculateRiskScore_tempExactly39point5_returns50() {
        int score = admission.calculateRiskScore(39.5, 25);
        assertEquals(50, score);
    }

    @Test
    void calculateRiskScore_ageExactly65_adds30ToScore() {
        int score = admission.calculateRiskScore(36.5, 65);
        assertEquals(30, score);
    }

    @Test
    void isValidPatientName_exactly100Chars_returnsTrue() {
        String name = "A".repeat(100);
        assertTrue(admission.isValidPatientName(name));
    }

    @Test
    void isValidPatientName_101Chars_returnsFalse() {
        String name = "A".repeat(101);
        assertFalse(admission.isValidPatientName(name));
    }

    // --- Exception tests ---
    @Test
    void calculateRiskScore_temperatureBelow30_throwsIllegalArgument() {
        assertThrows(IllegalArgumentException.class, () -> {
            admission.calculateRiskScore(20.0, 40);
        });
    }

    @Test
    void calculateRiskScore_temperatureAbove45_throwsIllegalArgument() {
        assertThrows(IllegalArgumentException.class, () -> {
            admission.calculateRiskScore(46.0, 40);
        });
    }

    // --- Parameterized tests ---
    @ParameterizedTest
    @CsvSource({
        "40.0, 70, 80",
        "38.0, 55, 40",
        "36.5, 25,  0",
        "39.5, 30, 50"
    })
    void calculateRiskScore_variousInputs_returnsCorrectScore(
            double temp, int age, int expectedScore) {
        assertEquals(expectedScore, admission.calculateRiskScore(temp, age));
    }

    // --- Challenge: isEligibleForPriorityCare tests ---
    @Test
    void isEligibleForPriorityCare_age65_returnsTrue() {
        assertTrue(admission.isEligibleForPriorityCare(65, false));
    }

    @Test
    void isEligibleForPriorityCare_hasChronicCondition_returnsTrue() {
        assertTrue(admission.isEligibleForPriorityCare(30, true));
    }

    @Test
    void isEligibleForPriorityCare_youngNoCondition_returnsFalse() {
        assertFalse(admission.isEligibleForPriorityCare(30, false));
    }

    @Test
    void isEligibleForPriorityCare_elderlyWithCondition_returnsTrue() {
        assertTrue(admission.isEligibleForPriorityCare(70, true));
    }
}
