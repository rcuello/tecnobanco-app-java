package edu.uni.comfenalco.tecnobanco;

public class Aplicacion {
    public static void main(String[] args) {
        System.out.println("Hola, Mundo desde TecnoBanco");
        if (args.length > 0) {
            System.out.println("Recibí el siguiente argumento: " + args[0]);
        }
    }
}
