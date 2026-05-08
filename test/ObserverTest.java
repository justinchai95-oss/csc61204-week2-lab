import com.example.meditrackapp.AdmissionObserver;
import com.example.meditrackapp.Hospital;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class ObserverTest {
    @Test
    void removeObserver_afterRemoval_observerNotCalled() {
        Hospital hospital = new Hospital("Test Hospital");

        boolean[] wasCalled = {false};

        AdmissionObserver testObserver = new AdmissionObserver() {
            @Override
            public void onPatientAdmitted(String patientName, int riskScore) {
                wasCalled[0] = true;
            }
        };

        hospital.addObserver(testObserver);
        hospital.admitPatient("Maria Santos", 80);

        wasCalled[0] = false;
        hospital.removeObserver(testObserver);
        hospital.admitPatient("Ahmad Razali", 25);

        assertFalse(wasCalled[0]);
    }
}
