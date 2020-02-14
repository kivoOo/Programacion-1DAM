package DAO;

import DAO.DAOManager;
import DAO.DaoProfesor;
import modelo.Alumno;
import modelo.Profesor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class DaoProfesorSQL implements DaoProfesor {

    @Override
    public boolean insert(Profesor profesor, DAOManager dao) {
        String sentencia;
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        String Stringfecha= formatoFecha.format(profesor.getFechaNacim().getTime());
        sentencia = "INSERT INTO profesores VALUES ('"
                + profesor.getDni() + "','"
                + profesor.getNombre() + "','"
                + profesor.getApellidos()  + "','"
                + Stringfecha + "','"
                + profesor.getMateria() + "',"
                + profesor.getSalario()+ ");";
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
    public boolean update(Profesor profesor, DAOManager dao) {
        //TODO
        return false;
    }

    @Override
    public boolean delete(Profesor profesor, DAOManager dao) {
        String sentencia;
        sentencia = "delete from profesores where dni = '" + profesor.getDni() + "';";
        System.out.println("sentencia = " + sentencia);
        try (Statement stmt = dao.getConn().createStatement()) {
            // enviar el commando delete
            stmt.executeUpdate(sentencia);
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    @Override
    public Profesor read(String dni, DAOManager dao) {
        Profesor profesor = null;
        String sentencia;
        sentencia = "select * from profesores where dni = ?";
        try {
            PreparedStatement ps = dao.getConn().prepareStatement(sentencia);
            ps.setString(1, dni);
            System.out.println("sentencia = " + sentencia);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Calendar cal = Calendar.getInstance();

                    //Obtengo la fecha y la preparo para meterla en el objeto
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    cal.setTime(sdf.parse(rs.getString("fecha_nac")));
                    GregorianCalendar cal2 = (GregorianCalendar) cal;

                    // obtener cada una de la columnas y mapearlas a la clase Alumno
                    profesor = new Profesor(dni,
                            rs.getString("nombre"),
                            rs.getString("apellidos"),
                            cal2,
                            rs.getString("materia"),
                            rs.getDouble("salario"));
                }
            }
        } catch (SQLException | ParseException ex) {
            ex.printStackTrace();
        }
        return profesor;
    }


}

