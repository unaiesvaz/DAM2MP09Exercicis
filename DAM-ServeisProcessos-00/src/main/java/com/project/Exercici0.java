package com.project;

import java.util.concurrent.*;


public class Exercici0 {

    public static void main(String[] args) {
        ConcurrentHashMap<String, Double> datos = new ConcurrentHashMap<>(); // Creamos un mapa vacio

        datos.put("saldo",1000.0); // Como dato, introducimos saldo, el cual tiene el valor de 1000
        System.out.println(datos.get("saldo"));

    }
    
}
