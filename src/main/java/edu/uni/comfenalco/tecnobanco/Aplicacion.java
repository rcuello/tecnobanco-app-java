package edu.uni.comfenalco.tecnobanco;

import java.io.Console;
import java.util.Scanner;

public class Aplicacion {

    public static void main(String[] args){


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
        } else {
            System.out.println("Credenciales invalidas!");
        }
    }
}
