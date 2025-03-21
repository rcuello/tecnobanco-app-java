package edu.uni.comfenalco.tecnobanco.repositorio;

import edu.uni.comfenalco.tecnobanco.modelo.RolUsuario;
import edu.uni.comfenalco.tecnobanco.modelo.Usuario;

public class UsuarioRepositorio {
    // Método para buscar el usuario a traves de su nombre y clave
    public static Usuario obtenerUsuarioAutenticado(String nombreUsuario, String claveUsuario) {
        // Obtenemos la lista de usuarios
        Usuario[] usuarios = obtenerListaUsuarios();

        // recorremos la lista de usuarios (usamos foreach)
        for (Usuario usuario : usuarios) {
            // Verificar si el usuario y la clave coinciden
            if (usuario.getNombre().equals(nombreUsuario) && usuario.getClave().equals(claveUsuario)) {
                // Si el usuario y la clave coinciden, retornamos el usuario
                return usuario;
            }
        }
        
        // Si no se encuentra el usuario, retornamos null
        return null;
    }

    private static Usuario[] obtenerListaUsuarios() {
        Usuario[] usuarios = new Usuario[5];
        usuarios[0] = crearUsuario("usuario", "1234", "USR001", 1000, RolUsuario.CLIENTE);
        usuarios[1] = crearUsuario("cliente", "1234", "USR002", 2000, RolUsuario.CLIENTE);
        usuarios[2] = crearUsuario("cliente2", "1234", "USR003", 3000, RolUsuario.CLIENTE);

        usuarios[3] = crearUsuario("asesor", "1234", "ASE001", 0, RolUsuario.ASESOR_FINANCIERO);
        usuarios[4] = crearUsuario("asesor2", "1234", "ASE002", 0, RolUsuario.ASESOR_FINANCIERO);
        return usuarios;
    }

    private static Usuario crearUsuario(String nombre, String clave, String identificacion, double saldo,
            RolUsuario rol) {
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setClave(clave);
        usuario.setIdentificacion(identificacion);
        usuario.setSaldo(saldo);
        usuario.setRol(rol);
        return usuario;
    }
}
