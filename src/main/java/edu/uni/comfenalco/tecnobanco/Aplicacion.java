package edu.uni.comfenalco.tecnobanco;

import java.io.Console;
import java.util.Scanner;

import edu.uni.comfenalco.tecnobanco.modelo.Usuario;// Importamos la clase Usuario
import edu.uni.comfenalco.tecnobanco.repositorio.UsuarioRepositorio; // Importamos la clase UsuarioRepositorio
import edu.uni.comfenalco.tecnobanco.servicios.DepositoServicio;// Importamos la clase DepositoServicio
import edu.uni.comfenalco.tecnobanco.servicios.TransferenciaServicio;// Importamos la clase TransferenciaServicio
import edu.uni.comfenalco.tecnobanco.util.FormateadorMoneda; // Importamos la clase FormateadorMoneda

public class Aplicacion {

    private static Usuario usuarioAutenticado;

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

        usuarioAutenticado = UsuarioRepositorio.obtenerUsuarioAutenticado(usuarioNombre, usuarioClave);

        if (usuarioAutenticado != null) {
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
                        System.out.print("Ingrese el monto a depositar: ");
                        double montoDeposito = scanner.nextDouble();
                        // Llamamos al servicio de depósitos para manejar la lógica específica.
                        DepositoServicio.realizarDeposito(usuarioAutenticado, montoDeposito);
                        break;
                    case 2:
                        System.out.print("Ingrese el monto a transferir: ");
                        double montoTransferencia = scanner.nextDouble();
                        // Llamamos al servicio de transferencias para manejar la lógica específica.
                        TransferenciaServicio.realizarTransferencia(usuarioAutenticado, montoTransferencia);
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
        System.out.println("Su saldo actual es: " + FormateadorMoneda.formatear(usuarioAutenticado.getSaldo()));
    }

    public static void mostrarInformacionUsuario() {
        String saldoFormateado = FormateadorMoneda.formatear(usuarioAutenticado.getSaldo());

        System.out.println("+-------------------------------+");
        System.out.println("| ** Información del Usuario ** |");
        System.out.println("+-------------------------------+");
        System.out.println("* Nombre de usuario: " + usuarioAutenticado.getNombre());
        System.out.println("* ID de usuario: " + usuarioAutenticado.getIdentificacion());
        System.out.println("* Saldo actual: " + saldoFormateado);
        System.out.println("+-------------------------------+");
    }
}
