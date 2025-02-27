package com.example.calculatrice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class CalculatriceServiceTests {
    
    private CalculatriceService calculatriceService;
    
    @BeforeEach
    void setUp() {
        calculatriceService = new CalculatriceService();
    }
    
    @ParameterizedTest
    @CsvSource({
        "5, 3, 8",
        "0, 0, 0",
        "-5, 5, 0",
        "10.5, 2.5, 13"
    })
    @DisplayName("Test d'addition de deux nombres")
    void testAddition(double a, double b, double resultatAttendu) {
        double resultat = calculatriceService.addition(a, b);
        assertEquals(resultatAttendu, resultat, 0.0001, "L'addition devrait donner le bon résultat");
    }
    
    @ParameterizedTest
    @CsvSource({
        "5, 3, 2",
        "0, 0, 0",
        "-5, 5, -10",
        "10.5, 2.5, 8"
    })
    @DisplayName("Test de soustraction de deux nombres")
    void testSoustraction(double a, double b, double resultatAttendu) {
        double resultat = calculatriceService.soustraction(a, b);
        assertEquals(resultatAttendu, resultat, 0.0001, "La soustraction devrait donner le bon résultat");
    }
    
    @ParameterizedTest
    @CsvSource({
        "5, 3, 15",
        "0, 0, 0",
        "-5, 5, -25",
        "10.5, 2, 21"
    })
    @DisplayName("Test de multiplication de deux nombres")
    void testMultiplication(double a, double b, double resultatAttendu) {
        double resultat = calculatriceService.multiplication(a, b);
        assertEquals(resultatAttendu, resultat, 0.0001, "La multiplication devrait donner le bon résultat");
    }
    
    @ParameterizedTest
    @CsvSource({
        "6, 3, 2",
        "10, 2, 5",
        "-10, 2, -5",
        "10.5, 2, 5.25"
    })
    @DisplayName("Test de division de deux nombres")
    void testDivision(double a, double b, double resultatAttendu) {
        double resultat = calculatriceService.division(a, b);
        assertEquals(resultatAttendu, resultat, 0.0001, "La division devrait donner le bon résultat");
    }
    
    @Test
    @DisplayName("Test de division par zéro")
    void testDivisionParZero() {
        assertThrows(ArithmeticException.class, () -> calculatriceService.division(5, 0),
                "La division par zéro devrait lancer une exception");
    }
    
    @ParameterizedTest
    @CsvSource({
        "2, 3, 8",
        "10, 0, 1",
        "3, 2, 9",
        "5, -1, 0.2"
    })
    @DisplayName("Test de puissance")
    void testPuissance(double a, double b, double resultatAttendu) {
        double resultat = calculatriceService.puissance(a, b);
        assertEquals(resultatAttendu, resultat, 0.0001, "La puissance devrait donner le bon résultat");
    }
    
    @ParameterizedTest
    @CsvSource({
        "4, 2",
        "9, 3",
        "25, 5",
        "0, 0"
    })
    @DisplayName("Test de racine carrée")
    void testRacineCarree(double a, double resultatAttendu) {
        double resultat = calculatriceService.racineCarree(a);
        assertEquals(resultatAttendu, resultat, 0.0001, "La racine carrée devrait donner le bon résultat");
    }
    
    @Test
    @DisplayName("Test de racine carrée d'un nombre négatif")
    void testRacineCarreeNombreNegatif() {
        assertThrows(ArithmeticException.class, () -> calculatriceService.racineCarree(-1),
                "La racine carrée d'un nombre négatif devrait lancer une exception");
    }
}