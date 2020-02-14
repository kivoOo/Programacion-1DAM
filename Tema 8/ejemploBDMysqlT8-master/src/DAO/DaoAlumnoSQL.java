package DAO;

import modelo.Alumno;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DaoAlumnoSQL implements DaoAlumno {

    @Override
    public boolean insert(Alumno alumno, DAOManager dao) {
        String sentencia;
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        String Stringfecha = formatoFecha.format(alumno.getFechaNacim().getTime());
        sentencia = "INSERT INTO alumnos VALUES ('"
                + alumno.getDni() + "','"
                + alumno.getNombre() + "','"
                + alumno.getApellidos() + "','"
                + Stringfecha + "','"
                + alumno.getGrupo() + "',"
                + alumno.getNotaMedia() + ");";
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
    public boolean update(Alumno alumno, DAOManager dao) {
        String sentencia;
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        String Stringfecha = formatoFecha.format(alumno.getFechaNacim().getTime());
        //UPDATE `alumnos` SET `nombre`="carlos2",`apellidos`="barroso2",`fecha_nac`="05/04/1983",
        // `grupo`="2dam",`nota_media`=5 WHERE `dni` = "77322948F"
        sentencia = "UPDATE alumnos SET nombre = '"
                + alumno.getNombre() + "', apellidos = '"
                + alumno.getApellidos() + "', fecha_nac = '"
                + Stringfecha + "', grupo = '"
                + alumno.getGrupo() + "', nota_media = "
                + alumno.getNotaMedia() + ";";
        System.out.println("sentencia = " + sentencia);
        try (Statement stmt = dao.getConn().createStatement()) {
            // enviar el commando insert
            stmt.executeUpdate(sentencia);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Alumno alumno, DAOManager dao) {
        String sentencia;
        sentencia = "delete from alumnos where dni = '" + alumno.getDni() + "';";
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
    public Alumno read(String dni, DAOManager dao) {
        Alumno alumno = null;
        String sentencia;
        sentencia = "select * from alumnos where dni = ?";
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
                    alumno = new Alumno(dni,
                            rs.getString("nombre"),
                            rs.getString("apellidos"),
                            cal2,
                            rs.getString("grupo"),
                            rs.getDouble("nota_media"));
                }
            }
        } catch (SQLException | ParseException ex) {
            ex.printStackTrace();
        }
        return alumno;
    }
}
