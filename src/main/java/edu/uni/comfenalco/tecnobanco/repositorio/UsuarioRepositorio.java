package edu.uni.comfenalco.tecnobanco.repositorio;

import edu.uni.comfenalco.tecnobanco.modelo.Usuario;

public class UsuarioRepositorio {
    // Método para obtener el usuario autenticado (simulación)
    public static Usuario obtenerUsuarioAutenticado(){
        Usuario usuario = new Usuario();
        usuario.setNombre("usuario");
        usuario.setClave("1234");
        usuario.setIdentificacion("USR007");
        usuario.setSaldo(0);
        return usuario;
    }
}
