package edu.uni.comfenalco.tecnobanco.vistas;

import java.util.Scanner;

import edu.uni.comfenalco.tecnobanco.modelo.Usuario;

public class UsuarioAsesorFinancieroVista {
    private static Scanner scanner = new Scanner(System.in);

    public static void mostrarMenu(Usuario usuario) {
        boolean salirDelPrograma = false;

        while (!salirDelPrograma) {
            System.out.println("+-------------------------------+");
            System.out.println("|    ** Menú Asesor Financiero **|");
            System.out.println("+-------------------------------+");
            System.out.println("| 1. Ver clientes               |");
            System.out.println("| 2. Generar reportes            |");
            System.out.println("| 3. Ver información del usuario|");
            System.out.println("| 4. Salir                      |");
            System.out.println("+-------------------------------+");

            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            // Hacer un salto de Linea en la pantalla
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Listado de clientes...");
                    // Lógica para ver clientes
                    break;
                case 2:
                    System.out.println("Generando reportes...");
                    // Lógica para generar reportes
                    break;
                case 3:                    
                    System.out.println("Mostrar informacion del usuario...");
                    break;
                case 4:
                    System.out.println("Saliendo del sistema...");
                    salirDelPrograma = true;
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción del 1 al 4.");
            }
        }
    }
}
