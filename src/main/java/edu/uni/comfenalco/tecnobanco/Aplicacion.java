package edu.uni.comfenalco.tecnobanco;

import java.io.Console;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class Aplicacion {
    static double saldoUsuario = 20000;
    static String usuarioAutenticadoNombre = "usuario";
    static String usuarioAutenticadoClave = "1234";
    static String usuarioAutenticadoIdentificacion = "USR007";

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

        if (usuarioNombre.equals(usuarioAutenticadoNombre) && usuarioClave.equals(usuarioAutenticadoClave)) {
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
                        mostrarInformacionUsuario();
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
        System.out.println("Su saldo actual es: " + formatearMoneda(saldoUsuario));
    }

    public static void mostrarInformacionUsuario() {
        String saldoFormateado = formatearMoneda(saldoUsuario);

        System.out.println("+-------------------------------+");
        System.out.println("| ** Información del Usuario ** |");
        System.out.println("+-------------------------------+");
        System.out.println("* Nombre de usuario: " + usuarioAutenticadoNombre);
        System.out.println("* ID de usuario: " + usuarioAutenticadoIdentificacion);
        System.out.println("* Saldo actual: " + saldoFormateado);
        System.out.println("+-------------------------------+");
    }

    // Método para formatear números como moneda
    public static String formatearMoneda(double cantidad) {
        NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(Locale.getDefault());
        return formatoMoneda.format(cantidad);
    }
}
