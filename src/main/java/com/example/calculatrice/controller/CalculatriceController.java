package com.example.calculatrice.controller;

import com.example.calculatrice.service.CalculatriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/calculatrice")
public class CalculatriceController {
    
    private final CalculatriceService calculatriceService;
    
    @Autowired
    public CalculatriceController(CalculatriceService calculatriceService) {
        this.calculatriceService = calculatriceService;
    }
    
    @GetMapping("/addition")
    public ResponseEntity<Map<String, Object>> addition(@RequestParam double a, @RequestParam double b) {
        double resultat = calculatriceService.addition(a, b);
        return createResponse(a, b, "+", resultat);
    }
    
    @GetMapping("/soustraction")
    public ResponseEntity<Map<String, Object>> soustraction(@RequestParam double a, @RequestParam double b) {
        double resultat = calculatriceService.soustraction(a, b);
        return createResponse(a, b, "-", resultat);
    }
    
    @GetMapping("/multiplication")
    public ResponseEntity<Map<String, Object>> multiplication(@RequestParam double a, @RequestParam double b) {
        double resultat = calculatriceService.multiplication(a, b);
        return createResponse(a, b, "*", resultat);
    }
    
    @GetMapping("/division")
    public ResponseEntity<Map<String, Object>> division(@RequestParam double a, @RequestParam double b) {
        try {
            double resultat = calculatriceService.division(a, b);
            return createResponse(a, b, "/", resultat);
        } catch (ArithmeticException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("erreur", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    @GetMapping("/puissance")
    public ResponseEntity<Map<String, Object>> puissance(@RequestParam double a, @RequestParam double b) {
        double resultat = calculatriceService.puissance(a, b);
        return createResponse(a, b, "^", resultat);
    }
    
    @GetMapping("/racine-carree")
    public ResponseEntity<Map<String, Object>> racineCarree(@RequestParam double a) {
        try {
            double resultat = calculatriceService.racineCarree(a);
            Map<String, Object> response = new HashMap<>();
            response.put("operation", "√" + a);
            response.put("resultat", resultat);
            return ResponseEntity.ok(response);
        } catch (ArithmeticException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("erreur", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    private ResponseEntity<Map<String, Object>> createResponse(double a, double b, String operateur, double resultat) {
        Map<String, Object> response = new HashMap<>();
        response.put("operation", a + " " + operateur + " " + b);
        response.put("resultat", resultat);
        return ResponseEntity.ok(response);
    }
}