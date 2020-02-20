/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author carlos
 */
public class Usuario {
    private String usuario;
    private String clave;
    private String nombre;
    private int movil;

    public Usuario(String usuario, String clave, String nombre, int movil) {
        this.usuario = usuario;
        this.clave = clave;
        this.nombre = nombre;
        this.movil = movil;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getMovil() {
        return movil;
    }

    public void setMovil(int movil) {
        this.movil = movil;
    }
    
    public boolean valida(String usuario, String clave){
        return this.usuario.equals(usuario) && this.clave.equals(clave);
    }

    @Override
    public String toString() {
        return nombre + "(" + usuario + ")" + ". " + movil;
    }
    
    
    
    
}
