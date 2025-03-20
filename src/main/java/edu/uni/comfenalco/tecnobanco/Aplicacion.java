package edu.uni.comfenalco.tecnobanco;

import java.io.Console;
import java.util.Scanner;

public class Aplicacion {
    static double saldoUsuario = 20000;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Console console = System.console();

        System.out.println("+-------------------------------+");
        System.out.println("|       ** Bienvenido a         |");
        System.out.println("|        TecnoBanco **          |");
        System.out.println("+-------------------------------+");

        System.out.println("Ingrese el nombre de usuario:");

        String usuarioNombre = scanner.nextLine();

        char[] passwordArray = console.readPassword("Ingrese su contraseña: ");
        String usuarioClave = new String(passwordArray);

        if (usuarioNombre.equals("usuario") && usuarioClave.equals("1234")) {
            System.out.println(" ===============================");
            System.out.println("|  OPERACION COMPLETADA         |");
            System.out.println("|  Ha iniciado sesión con éxito |");
            System.out.println(" ===============================");

            boolean salirDelPrograma = false;

            while (!salirDelPrograma) {

                System.out.println("+-------------------------------+");
                System.out.println("|        ** Menú Principal **   |");
                System.out.println("+-------------------------------+");
                System.out.println("| 1. Depositar                  |");
                System.out.println("| 2. Transferir                 |");
                System.out.println("| 3. Ver saldo                  |");
                System.out.println("| 4. Información del usuario    |");
                System.out.println("| 5. Salir                      |");
                System.out.println("+-------------------------------+");

                System.out.print("Seleccione una opción: ");

                int opcion = scanner.nextInt();

                // Hacer un salto de Linea en la pantalla
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        System.out.println("TODO: Realizar deposito");
                        break;
                    case 2:
                        System.out.println("TODO: Realizar transferencia");
                        break;
                    case 3:
                        mostrarSaldo();
                        break;
                    case 4:
                        System.out.println("TODO: Mostrar informacion del usuario");
                        break;
                    case 5:
                        System.out.println("Saliendo del sistema...");
                        salirDelPrograma = true;
                        break;
                    default:
                        System.out.println("Opción inválida. Por favor, seleccione una opción del 1 al 5.");
                }
            }

        } else {
            System.out.println("Credenciales invalidas!");
        }
    }

    public static void mostrarSaldo() {
        System.out.println("Su saldo actual es: " + saldoUsuario);
    }
}
