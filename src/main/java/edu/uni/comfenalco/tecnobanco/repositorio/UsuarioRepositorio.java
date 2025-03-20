package edu.uni.comfenalco.tecnobanco.repositorio;

import edu.uni.comfenalco.tecnobanco.modelo.Usuario;

public class UsuarioRepositorio {
    // Método para buscar el usuario a traves de su nombre y clave
    public static Usuario obtenerUsuarioAutenticado(String nombreUsuario, String claveUsuario) {
        // Obtenemos la lista de usuarios
        Usuario[] usuarios = obtenerListaUsuarios();

        // recorremos la lista de usuarios
        for (int i = 0; i < usuarios.length; i++) {
            Usuario usuario = usuarios[i];

            // verificar si el usuario y la clave coinciden
            if (usuario.getNombre().equals(nombreUsuario) && usuario.getClave().equals(claveUsuario)) {
                // Si el usuario y la clave coinciden, retornamos el usuario
                return usuario;
            }
        }// fin del ciclo

        return null;
    }

    private static Usuario[] obtenerListaUsuarios() {
        Usuario[] usuarios = new Usuario[3];
        usuarios[0] = crearUsuario("usuario", "1234", "USR001", 1000);
        usuarios[1] = crearUsuario("admin", "1234", "USR002", 2000);
        usuarios[2] = crearUsuario("cliente", "1234", "USR003", 3000);
        return usuarios;
    }

    private static Usuario crearUsuario(String nombre, String clave, String identificacion, double saldo) {
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setClave(clave);
        usuario.setIdentificacion(identificacion);
        usuario.setSaldo(saldo);
        return usuario;
    }
}
