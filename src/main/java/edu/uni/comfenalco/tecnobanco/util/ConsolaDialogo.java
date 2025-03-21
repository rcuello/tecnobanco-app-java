package edu.uni.comfenalco.tecnobanco.util;

import java.util.Scanner;

public class ConsolaDialogo {
    public static void mostrarBienvenida() {
        System.out.println("+-------------------------------+");
        System.out.println("|       ** Bienvenido a         |");
        System.out.println("|        TecnoBanco **          |");
        System.out.println("+-------------------------------+");
        System.out.println("| 1. Iniciar sesión             |");
        System.out.println("| 2. Ver autores                |");
        System.out.println("| 3. Salir                      |");
        System.out.println("+-------------------------------+");
    }

    public static void mostrarCredencialesInvalidas() {
        System.out.println("/////////////////////////////////");
        System.out.println("//   ERROR: ACCESO DENEGADO    //");
        System.out.println("//   Credenciales inválidas    //");
        System.out.println("/////////////////////////////////");
    }
    public static void mostrarErrorRolDesconocido() {
        System.out.println("///////////////////////////////////");
        System.out.println("//   ERROR: Rol no reconocido     //");
        System.out.println("//   Contacte al administrador    //");
        System.out.println("///////////////////////////////////");
    }

    public static void mostrarInicioSesionExitoso() {
        System.out.println(" ===============================");
        System.out.println("|  OPERACION COMPLETADA         |");
        System.out.println("|  Ha iniciado sesión con éxito |");
        System.out.println(" ===============================");
    }

    public static boolean solicitarConfirmacion(Scanner scanner, String mensaje) {
        System.out.println("=== Confirmación ===");
        System.out.print(mensaje + " (s/n): ");
        String respuesta = scanner.nextLine();
        return respuesta.equalsIgnoreCase("s");
    }

    public static void mostrarError(String mensaje) {
        System.out.println("=== Error ===");
        System.out.println(mensaje);
        System.out.println("=============");
    }
}
