package com.project;

import java.util.concurrent.CompletableFuture;

public class Exercici1 { // Para ejecutar este ejercicio: .\run.ps1 com.project.Exercici0
    public static void main(String[] args) { // Este ejercicio es como una cadena de tareas en la que se pasan la info de una a otra

        CompletableFuture<Integer> tarea1 = CompletableFuture.supplyAsync(() -> { //Esta tarea devolvera un Integer
            System.out.println("Creamos datos");
            return 100; //Hacemos que esta tarea devuelva 100 (Un integer)
        });

        CompletableFuture<Integer> tarea2 = tarea1.thenApply(valor -> { //Esta tarea aumenta el valor en 50 una vez acabada la tarea 1
            System.out.println("Sumamos un valor al dato");
            return valor + 50; // --> Aqui aumenta el valor 
        }); 

        CompletableFuture<Void> tarea3 = tarea2.thenAccept(resultado -> { // Una vez termine la tarea 2, se ejecutara esta tarea
            System.out.println("Resultado final: " + resultado); 
        }); 

        tarea3.join(); // El join espera a que termine tarea 3
    }   


}
