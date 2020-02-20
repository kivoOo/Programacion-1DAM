/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author carlos
 */
import java.sql.*;
import java.util.ArrayList;
import modelo.Usuario;

public class DAOUsuarioSQL implements DAOUsuario {

    @Override
    public boolean insert(Usuario usuario, DAOManager dao) {
        String sentencia;
        sentencia = "INSERT INTO usuarios VALUES ('"
                + usuario.getUsuario() + "','"
                + usuario.getClave() + "','"
                + usuario.getNombre() + "','"
                + usuario.getMovil() + "';";
        System.out.println("sentencia = " + sentencia);
        try (Statement stmt = dao.getConn().createStatement()) {
            // enviar el commando insert
            stmt.executeUpdate(sentencia);
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }



    @Override
    public Usuario read(String user, DAOManager dao) {
        Usuario usuario = null;
        String sentencia;
        sentencia = "select * from alumnos where usuario = ?";
        try {
            PreparedStatement ps = dao.getConn().prepareStatement(sentencia);
            ps.setString(1, user);
            System.out.println("sentencia = " + sentencia);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // obtener cada una de la columnas y mapearlas a la clase Usuario
                    usuario = new Usuario(user,
                            rs.getString("clave"),
                            rs.getString("nombre"),
                            rs.getInt("movil"));
                }
            }
        } catch (SQLException ex) {
        }
        return usuario;
    }
    
    @Override
    public ArrayList<Usuario> readAll(DAOManager dao) {
        ArrayList lista = new ArrayList();
        Usuario usuario;
        String sentencia;
        sentencia = "select * from usuarios";
        try {
            PreparedStatement ps = dao.getConn().prepareStatement(sentencia);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    
                    // obtener cada una de la columnas y mapearlas a la clase Alumno
                    usuario = new Usuario(rs.getString("usuario"),
                            rs.getString("clave"),
                            rs.getString("nombre"),
                            rs.getInt("movil"));
                    lista.add(usuario);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }
}

