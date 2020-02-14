package DAO;

import modelo.Profesor;

public interface DaoProfesor {
    public boolean insert(Profesor profesor, DAOManager dao);
    public boolean update(Profesor profesor, DAOManager dao);
    public boolean delete(Profesor profesor, DAOManager dao);
    public Profesor read(String dni, DAOManager dao);
}
