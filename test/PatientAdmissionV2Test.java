
import com.example.meditrackapp.ConservativeRiskStrategy;
import com.example.meditrackapp.PatientAdmissionV2;
import com.example.meditrackapp.PediatricRiskStrategy;
import com.example.meditrackapp.StandardRiskStrategy;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PatientAdmissionV2Test {

    // Test 1: Same input produces DIFFERENT scores under Standard vs Conservative
    @Test
    void testStandardVsConservativeProduceDifferentScores() {
        double temp = 38.7;
        int age = 62;

        PatientAdmissionV2 standard     = new PatientAdmissionV2(new StandardRiskStrategy());
        PatientAdmissionV2 conservative = new PatientAdmissionV2(new ConservativeRiskStrategy());

        int standardScore     = standard.calculateRiskScore(temp, age);
        int conservativeScore = conservative.calculateRiskScore(temp, age);

        assertEquals(25, standardScore,     "Standard: only temp band hit, age 62 < 65");
        assertEquals(80, conservativeScore, "Conservative: temp + age both triggered");
        assertNotEquals(standardScore, conservativeScore,
                "Same patient data should score differently under different strategies");
    }

    // Test 2: setStrategy() actually switches the algorithm at runtime
    @Test
    void testSetStrategySwapsAlgorithmAtRuntime() {
        double temp = 38.7;
        int age = 62;

        PatientAdmissionV2 admission = new PatientAdmissionV2(new StandardRiskStrategy());
        int before = admission.calculateRiskScore(temp, age); // 25

        admission.setStrategy(new ConservativeRiskStrategy());
        int after = admission.calculateRiskScore(temp, age);  // 80

        assertEquals(25, before, "Before swap: StandardRiskStrategy score");
        assertEquals(80, after,  "After swap:  ConservativeRiskStrategy score");
        assertNotEquals(before, after, "Score must change after strategy swap");
    }

    // Test 3: OCP demo — PediatricRiskStrategy added WITHOUT touching PatientAdmissionV2
    @Test
    void testPediatricStrategyWorksWithoutModifyingPatientAdmission() {
        // Child: age 8, temp 38.5 → age < 12 (+20) + temp >= 38.0 (+40) = 60
        PatientAdmissionV2 pediatric = new PatientAdmissionV2(new PediatricRiskStrategy());
        assertEquals(60, pediatric.calculateRiskScore(38.5, 8),
                "Pediatric: age bonus + fever bonus = 60");

        // Child with normal temp: age 5, temp 37.0 → only age bonus = 20
        assertEquals(20, pediatric.calculateRiskScore(37.0, 5),
                "Pediatric: age bonus only = 20");

        // Adult with fever: age 30, temp 39.0 → only fever bonus = 40
        assertEquals(40, pediatric.calculateRiskScore(39.0, 30),
                "Pediatric: fever bonus only = 40");
    }
}