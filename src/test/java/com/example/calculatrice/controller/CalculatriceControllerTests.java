package com.example.calculatrice.controller;

import com.example.calculatrice.service.CalculatriceService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CalculatriceControllerTests {
    
    @Mock
    private CalculatriceService calculatriceService;
    
    @InjectMocks
    private CalculatriceController calculatriceController;
    
    @Test
    @DisplayName("Test de l'endpoint d'addition")
    void testAddition() {
        // Arrange
        double a = 5.0;
        double b = 3.0;
        double resultatAttendu = 8.0;
        when(calculatriceService.addition(a, b)).thenReturn(resultatAttendu);
        
        // Act
        ResponseEntity<Map<String, Object>> response = calculatriceController.addition(a, b);
        
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(resultatAttendu, response.getBody().get("resultat"));
        assertEquals(a + " + " + b, response.getBody().get("operation"));
        verify(calculatriceService, times(1)).addition(a, b);
    }
    
    @Test
    @DisplayName("Test de l'endpoint de soustraction")
    void testSoustraction() {
        // Arrange
        double a = 5.0;
        double b = 3.0;
        double resultatAttendu = 2.0;
        when(calculatriceService.soustraction(a, b)).thenReturn(resultatAttendu);
        
        // Act
        ResponseEntity<Map<String, Object>> response = calculatriceController.soustraction(a, b);
        
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(resultatAttendu, response.getBody().get("resultat"));
        assertEquals(a + " - " + b, response.getBody().get("operation"));
        verify(calculatriceService, times(1)).soustraction(a, b);
    }
    
    @Test
    @DisplayName("Test de l'endpoint de multiplication")
    void testMultiplication() {
        // Arrange
        double a = 5.0;
        double b = 3.0;
        double resultatAttendu = 15.0;
        when(calculatriceService.multiplication(a, b)).thenReturn(resultatAttendu);
        
        // Act
        ResponseEntity<Map<String, Object>> response = calculatriceController.multiplication(a, b);
        
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(resultatAttendu, response.getBody().get("resultat"));
        assertEquals(a + " * " + b, response.getBody().get("operation"));
        verify(calculatriceService, times(1)).multiplication(a, b);
    }
    
    @Test
    @DisplayName("Test de l'endpoint de division")
    void testDivision() {
        // Arrange
        double a = 6.0;
        double b = 3.0;
        double resultatAttendu = 2.0;
        when(calculatriceService.division(a, b)).thenReturn(resultatAttendu);
        
        // Act
        ResponseEntity<Map<String, Object>> response = calculatriceController.division(a, b);
        
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(resultatAttendu, response.getBody().get("resultat"));
        assertEquals(a + " / " + b, response.getBody().get("operation"));
        verify(calculatriceService, times(1)).division(a, b);
    }
    
    @Test
    @DisplayName("Test de l'endpoint de division par zéro")
    void testDivisionParZero() {
        // Arrange
        double a = 5.0;
        double b = 0.0;
        when(calculatriceService.division(a, b)).thenThrow(new ArithmeticException("Division par zéro n'est pas autorisée"));
        
        // Act
        ResponseEntity<Map<String, Object>> response = calculatriceController.division(a, b);
        
        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().get("erreur"));
        verify(calculatriceService, times(1)).division(a, b);
    }
    
    @Test
    @DisplayName("Test de l'endpoint de puissance")
    void testPuissance() {
        // Arrange
        double a = 2.0;
        double b = 3.0;
        double resultatAttendu = 8.0;
        when(calculatriceService.puissance(a, b)).thenReturn(resultatAttendu);
        
        // Act
        ResponseEntity<Map<String, Object>> response = calculatriceController.puissance(a, b);
        
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(resultatAttendu, response.getBody().get("resultat"));
        assertEquals(a + " ^ " + b, response.getBody().get("operation"));
        verify(calculatriceService, times(1)).puissance(a, b);
    }
    
    @Test
    @DisplayName("Test de l'endpoint de racine carrée")
    void testRacineCarree() {
        // Arrange
        double a = 4.0;
        double resultatAttendu = 2.0;
        when(calculatriceService.racineCarree(a)).thenReturn(resultatAttendu);
        
        // Act
        ResponseEntity<Map<String, Object>> response = calculatriceController.racineCarree(a);
        
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(resultatAttendu, response.getBody().get("resultat"));
        assertEquals("√" + a, response.getBody().get("operation"));
        verify(calculatriceService, times(1)).racineCarree(a);
    }
    
    @Test
    @DisplayName("Test de l'endpoint de racine carrée d'un nombre négatif")
    void testRacineCarreeNombreNegatif() {
        // Arrange
        double a = -1.0;
        when(calculatriceService.racineCarree(a)).thenThrow(
            new ArithmeticException("La racine carrée d'un nombre négatif n'est pas définie pour les nombres réels"));
        
        // Act
        ResponseEntity<Map<String, Object>> response = calculatriceController.racineCarree(a);
        
        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().get("erreur"));
        verify(calculatriceService, times(1)).racineCarree(a);
    }
}