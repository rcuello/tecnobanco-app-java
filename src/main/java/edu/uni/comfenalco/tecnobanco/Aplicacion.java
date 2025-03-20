package edu.uni.comfenalco.tecnobanco;

import java.io.Console;
import java.util.Scanner;

import edu.uni.comfenalco.tecnobanco.modelo.Usuario;// Importamos la clase Usuario
import edu.uni.comfenalco.tecnobanco.repositorio.UsuarioRepositorio; // Importamos la clase UsuarioRepositorio
import edu.uni.comfenalco.tecnobanco.vistas.UsuarioVista;

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

            // Llamamos a la vista del menú para manejar la interacción con el usuario.
            UsuarioVista.mostrarMenu(usuarioAutenticado);

        } else {
            System.out.println("Credenciales invalidas!");
        }
        
        scanner.close();
    }
}
