package com.project;

import java.util.concurrent.*;


public class Exercici0 { // Para ejecutar este ejercicio: .\run.ps1 com.project.Exercici0

    public static void main(String[] args) {
        ConcurrentHashMap<String, Double> datos = new ConcurrentHashMap<>(); // Creamos un mapa vacio, donde almacenaremos nuestros datos 

        ExecutorService executor = Executors.newFixedThreadPool(3); // Se encarga de gestionar varias tareas al mismo tiempo, en este caso 3

        CountDownLatch finTarea1 = new CountDownLatch(1); // Creamos una senal, mientras este en 1, la tarea no empezara 
        CountDownLatch finTarea2 = new CountDownLatch(1);

        Runnable tarea1 = () -> { // Primera tarea 
            datos.put("saldo", 1000.0);// Como dato, introducimos saldo, el cual tiene el valor de 1000, es como un diccionario
            finTarea1.countDown(); // Saldo inicial ya esta agregado
        };
        executor.execute(tarea1);

        Runnable tarea2 = () -> { // Segunda tarea 
        try {
            finTarea1.await(); // Esto hace que esta tarea no se ejecute hasta que no termine la tarea 1

            double saldo = datos.get("saldo"); 
            datos.put("saldo", saldo - 50.0); // Modificamos el saldo restandole 50

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        finTarea2.countDown(); //Fin de la tarea 2
        };
        executor.execute(tarea2); // Ejecutamos la tarea

        Callable<Double> tarea3 = () -> {
            finTarea2.await(); // Hasta que la tarea 2 no termine, no hagas nada
            return datos.get("saldo"); // Devolvemos el valor de saldo (es como un diccionario)
        };
        Future<Double> resultado = executor.submit(tarea3);// Para las tareas tipo callable se usa .submit
        // El future representa que aqui, habra un dato del tipo Double, en este caso 

        try {
            System.out.println("Saldo final: " + resultado.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdown(); // Cerramos el ejecutor





        

    }
    
}
