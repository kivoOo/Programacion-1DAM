package DAO;

import modelo.Alumno;


public interface DaoAlumno {
    public boolean insert(Alumno alumno, DAOManager dao);
    public boolean update(Alumno alumno, DAOManager dao);
    public boolean delete(Alumno alumno, DAOManager dao);
    public Alumno read(String dni, DAOManager dao);
}
