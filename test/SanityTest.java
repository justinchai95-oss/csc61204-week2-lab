package com.example.meditrackapp;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class SanityTest {

    @BeforeEach
    void setUp() { System.out.println("[Before] Setting up test"); }

    @AfterEach
    void tearDown() { System.out.println("[After] Cleaning up"); }

    @Test
    void junitIsConfiguredCorrectly() {
        // This test always passes — it confirms JUnit is working
        assertEquals(4, 2 + 2);
    }

    @Test
    void firstTest()  { System.out.println("[Test] firstTest");  assertTrue(true); }

    @Test
    void secondTest() { System.out.println("[Test] secondTest"); assertEquals(10, 5 * 2); }
}
