package es.iesmz.tests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompteTest {

    @Test
    void compruebaIBAN() {
        // Casos de prueba válidos
        assertTrue(Compte.compruebaIBAN("ES6621000418401234567891", "Juanito"));
        assertTrue(Compte.compruebaIBAN("ES6000491500051234567892", "Luisito"));
        assertTrue(Compte.compruebaIBAN("ES9420805801101234567891", "Ruben"));

        // Casos de prueba inválidos
        assertFalse(Compte.compruebaIBAN("ES7600246912501234567891", "Manuel"));
        assertFalse(Compte.compruebaIBAN("ES4721000418401234567891", "Jorge"));
        assertFalse(Compte.compruebaIBAN("ES8200491500051234567892", "Mario"));
    }

    @Test
    void generaIBAN() {
        assertEquals("ES7100302053091234567895", Compte.generaIBAN("0030", "2053", "09", "1234567895"));
        assertEquals("ES1000492352082414205416", Compte.generaIBAN("0049", "2352", "08", "2414205416"));
        assertEquals("ES1720852066623456789011", Compte.generaIBAN("2085", "2066", "62", "3456789011"));
        assertNull(Compte.generaIBAN("2085", "2066", "62", "3456AE9011"));
        assertNull(Compte.generaIBAN("208", "2066", "62", "3456789011"));
        assertNull(Compte.generaIBAN("2080", "20A6", "62", "3456789011"));
        assertNull(Compte.generaIBAN("2080", "2086", "6", "3456789011"));
        assertNull(Compte.generaIBAN("2080", "2086", "63", "345678911"));
    }
}