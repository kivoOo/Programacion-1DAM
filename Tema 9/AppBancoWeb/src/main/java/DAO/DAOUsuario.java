/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ArrayList;
import modelo.Usuario;

/**
 *
 * @author carlos
 */
    public interface DAOUsuario {
    public boolean insert(Usuario usuario, DAOManager dao);
    public Usuario read(String user, DAOManager dao);
    public ArrayList<Usuario> readAll(DAOManager dao);
}
