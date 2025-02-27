package com.example.calculatrice.service;

import org.springframework.stereotype.Service;

@Service
public class CalculatriceService {
    
    public double addition(double a, double b) {
        return a + b;
    }
    
    public double soustraction(double a, double b) {
        return a - b;
    }
    
    public double multiplication(double a, double b) {
        return a * b;
    }
    
    public double division(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Division par zéro n'est pas autorisée");
        }
        return a / b;
    }
    
    public double puissance(double a, double b) {
        return Math.pow(a, b);
    }
    
    public double racineCarree(double a) {
        if (a < 0) {
            throw new ArithmeticException("La racine carrée d'un nombre négatif n'est pas définie pour les nombres réels");
        }
        return Math.sqrt(a);
    }
}