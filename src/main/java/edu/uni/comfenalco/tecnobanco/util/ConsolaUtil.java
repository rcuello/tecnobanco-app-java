package edu.uni.comfenalco.tecnobanco.util;

import java.io.Console;
import java.util.Scanner;

/**
 * Clase de utilidad para operaciones relacionadas con la consola.
 */
public class ConsolaUtil {
    /**
     * Solicita y valida un nombre de usuario.
     * Asegura que el nombre de usuario no esté vacío.
     * 
     * @param scanner Scanner para leer la entrada del usuario
     * @return El nombre de usuario válido
     */
    public static String solicitarNombreUsuario(Scanner scanner) {
        System.out.println("Ingrese el nombre de usuario:");
        String usuarioNombre = scanner.nextLine();

        while (usuarioNombre == null || usuarioNombre.trim().isEmpty()) {
            System.out.println("El nombre de usuario es requerido. Inténtelo de nuevo:");
            usuarioNombre = scanner.nextLine();
        }

        return usuarioNombre;
    }

    /**
     * Solicita y valida la contraseña del usuario.
     * Intenta utilizar Console para ocultar la entrada, pero si no está disponible
     * utiliza Scanner como alternativa.
     * 
     * @param scanner Scanner alternativo si Console no está disponible
     * @return La contraseña ingresada por el usuario
     */
    public static String solicitarContrasenia(Scanner scanner) {
        // Intentamos obtener la consola del sistema
        Console console = System.console();
        
        if (console != null) {
            // Si la consola está disponible, usamos readPassword para ocultar la entrada
            char[] passwordArray = console.readPassword("Ingrese su contraseña: ");
            return new String(passwordArray);
        } else {
            // Si la consola no está disponible (por ejemplo, en IDEs), usamos Scanner
            System.out.println("Ingrese su contraseña: ");
            System.out.println("(Nota: la contraseña será visible debido a limitaciones del entorno)");
            return scanner.nextLine();
        }
    }

    /**
     * Pausa la ejecución hasta que el usuario presione Enter.
     * Útil para mostrar mensajes antes de continuar.
     * 
     * @param scanner Scanner para leer la entrada del usuario
     */
    public static void esperarTecla(Scanner scanner) {
        System.out.println("\nPresione Enter para continuar...");
        scanner.nextLine();
    }

    /**
     * Intenta limpiar la pantalla de la consola.
     * Este método funciona diferente dependiendo del sistema operativo.
     */
    public static void limpiarPantalla() {
        try {
            // Detectamos el sistema operativo
            String sistemaOperativo = System.getProperty("os.name");
            
            // Ejecutamos el comando apropiado según el sistema operativo
            if (sistemaOperativo.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Para sistemas Unix/Linux/Mac
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // Si hay algún error, simplemente imprimimos líneas en blanco
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }
}
