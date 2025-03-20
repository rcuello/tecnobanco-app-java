package edu.uni.comfenalco.tecnobanco;

import java.io.Console;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class Aplicacion {
    static double saldoUsuario = 0.0;
    static String usuarioAutenticadoNombre = "estudiante";
    static String usuarioAutenticadoClave = "123";
    static String usuarioAutenticadoIdentificacion = "USR007";

    public static void main(String[] args){

        System.out.println("+-------------------------------+");
        System.out.println("|       ** Bienvenido a         |");
        System.out.println("|        TecnoBanco **          |");
        System.out.println("+-------------------------------+");


    }

    public static void realizarTransferencia(Scanner scanner) {
        System.out.print("Ingrese el monto a transferir: ");
        double monto = scanner.nextDouble();

        if (monto > 0) {
            if (monto <= saldoUsuario) {
                saldoUsuario -= monto;
                String saldoFormateado = formatearMoneda(saldoUsuario);
                System.out.println("Transferencia realizada con éxito. Su nuevo saldo es: " + saldoFormateado);
            } else {
                System.out.println("Saldo insuficiente para realizar la transferencia.");
            }
        } else {
            System.out.println("El monto debe ser mayor que 0.");
        }
    }

    public static void realizarDeposito(Scanner scanner) {
        System.out.print("Ingrese el monto a depositar: ");
        double monto = scanner.nextDouble();

        if (monto > 0) {
            saldoUsuario += monto;
            String saldoFormateado = formatearMoneda(saldoUsuario);
            System.out.println("Depósito realizado con éxito. Su nuevo saldo es: " + saldoFormateado);
        } else {
            System.out.println("El monto debe ser mayor que 0.");
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
