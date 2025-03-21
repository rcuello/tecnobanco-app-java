package edu.uni.comfenalco.tecnobanco.seguridad;

import edu.uni.comfenalco.tecnobanco.modelo.Usuario;

/**
 * Clase que maneja el usuario en sesión.
 */
public class SesionUsuario {
    // Usuario autenticado
    private static Usuario usuarioAutenticado;

    /**
     * Guarda el usuario autenticado en memoria.
     * 
     * @param usuario El usuario que ha iniciado sesión.
     * @throws IllegalArgumentException Si el usuario es nulo.
     */
    public static void guardarUsuarioAutenticado(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("El usuario no puede ser nulo.");
        }
        usuarioAutenticado = usuario;
    }

    /**
     * Cierra la sesión del usuario actual.
     */
    public static void cerrarSesion() {
        usuarioAutenticado = null;
        System.out.println("Sesión cerrada correctamente.");
    }

    /**
     * Obtiene el usuario autenticado.
     * 
     * @return El usuario autenticado, o null si no hay sesión activa.
     */
    public static Usuario getUsuarioAutenticado() {
        return usuarioAutenticado;
    }

    /**
     * Verifica si hay un usuario autenticado.
     * 
     * @return true si hay un usuario autenticado, false en caso contrario.
     */
    public static boolean haySesionActiva() {
        return usuarioAutenticado != null;
    }
}
