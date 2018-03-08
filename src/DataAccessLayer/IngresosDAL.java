/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessLayer;

import BusinessObjects.Ingreso;
import BusinessObjects.ConectarBaseDatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/**
 * Contiene los métodos de persistencia para la tabla: Ingresos.
 * @author Erick
 */
public class IngresosDAL {

    /**
     * Recupera los registros de la tabla: Ingresos, según el documento de
     * identidad del paciente especificado.
     *
     * @param documentoIdentidad parámetro de la consulta.
     * @return DefaultTableModel con los datos de la consulta.
     */
    public DefaultTableModel SelectIntelligentSearch(String documentoIdentidad) {
        DefaultTableModel model = new DefaultTableModel();
        String[] fila = new String[5];
        model.addColumn("Documento Identidad");
        model.addColumn("Fecha");
        model.addColumn("Servicio o Sala de Internación");
        model.addColumn("Hora");
        model.addColumn("Cama");
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        if (connection != null) {
            try {
                String sentencia = "SELECT DOC_ID_PAC,FEC_ING,SER_SAL_INTER,HORA_ING,COD_CAM "
                        + "FROM INGRESOS WHERE DOC_ID_PAC LIKE ?";
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setString(1, documentoIdentidad + "%");
                ResultSet lector = comando.executeQuery();
                connection.close();
                while (lector.next()) {
                    fila[0] = lector.getString("DOC_ID_PAC");
                    fila[1] = lector.getString("FEC_ING");
                    fila[2] = lector.getString("SER_SAL_INTER");
                    fila[3] = lector.getString("HORA_ING");
                    fila[4] = lector.getString("COD_CAM");
                    model.addRow(fila);
                }
                return model;
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    /**
     * Recupera un registro de la tabla: Ingresos, según el
     * documento de identidad del paciente especificado.
     * @param documentoIdentidad parámetro de la consulta.
     * @return objeto Ingreso con los datos de la consulta.
     */
    public Ingreso SelectById(String documentoIdentidad) {
        Ingreso ingreso = null;
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        if (connection != null) {
            try {
                String sentencia = "SELECT FEC_ING,COND_EDAD,EDAD_PAC_ING,"
                        + "TIPO_DISC_ING,SER_SAL_INTER,SALA_ING,HORA_ING,COD_CAM "
                        + "FROM INGRESOS WHERE DOC_ID_PAC=?";
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setString(1, documentoIdentidad);
                ResultSet lector = comando.executeQuery();
                if (lector.next()) {
                    ingreso = new Ingreso();
                    ingreso.setDocumentoIdentidad(documentoIdentidad);
                    ingreso.setFecha(lector.getDate("FEC_ING"));
                    ingreso.setCondicionEdad(lector.getString("COND_EDAD"));
                    ingreso.setEdad(lector.getInt("EDAD_PAC_ING"));
                    ingreso.setTipoDiscapacidad(lector.getString("TIPO_DISC_ING"));
                    ingreso.setServicioSalaInternacion(lector.getString("SER_SAL_INTER"));
                    ingreso.setSala(lector.getString("SALA_ING"));
                    ingreso.setHora(lector.getString("HORA_ING"));
                    ingreso.setCodigoCama(lector.getString("COD_CAM"));                    
                }
                connection.close();
                return ingreso;
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
    
    /**
     * Inserta un registro en la tabla: INGRESOS.
     * @param ingreso parámetro para la inserción.
     * @return cadena con el resultado de la inserción.
     */
    public String Insert(Ingreso ingreso) {
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        if (connection != null) {
            try {
                String sentencia = "INSERT INTO INGRESOS(DOC_ID_PAC,FEC_ING,COND_EDAD,"+
                        "EDAD_PAC_ING,TIPO_DISC_ING,SER_SAL_INTER,SALA_ING,HORA_ING,COD_CAM) "
                        + "VALUES(?,?,?,?,?,?,?,?,?)";
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setString(1, ingreso.getDocumentoIdentidad());
                comando.setDate(2, ingreso.getFecha());
                comando.setString(3, ingreso.getCondicionEdad());
                comando.setInt(4, ingreso.getEdad());
                comando.setString(5, ingreso.getTipoDiscapacidad());
                comando.setString(6, ingreso.getServicioSalaInternacion());
                comando.setString(7, ingreso.getSala());
                comando.setString(8, ingreso.getHora());
                comando.setString(9, ingreso.getCodigoCama());
                int registrosAfectados = comando.executeUpdate();
                connection.close();
                if (registrosAfectados > 0) {
                    return null;
                }
                return "No se ha podido insertar";
            } catch (Exception e) {
                return e.getMessage();
            }
        }
        return connect.getError();
    }
    
    /**
     * Actualiza la cama en la que ingresó un paciente.
     * @param ingreso parámetro para la actualización.
     * @return cadena con el resultado de la actualización.
     */
    public String UpdateCama(Ingreso ingreso) {
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();        
        if (connection != null) {
            try {
                String sentencia = "UPDATE INGRESOS SET COD_CAM=? "
                        + "WHERE DOC_ID_PAC=?";
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setString(1, ingreso.getCodigoCama());
                comando.setString(2, ingreso.getDocumentoIdentidad());
                int registrosAfectados = comando.executeUpdate();
                connection.close();
                if (registrosAfectados > 0) {
                    return null;
                }
                return "No se ha podido actualizar el ingreso";
            } catch (Exception e) {
                return e.getMessage();
            }
        }
        return connect.getError();
    }
    
    /**
     * Elimina un registro de la tabla: INGRESOS.
     * @param documentoIdentidad parámetro para la eliminación.
     * @return cadena con el resultado de la eliminación.
     */
    public String Delete(String documentoIdentidad) {
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();        
        if (connection != null) {
            try {
                String sentencia = "DELETE FROM INGRESOS WHERE DOC_ID_PAC=?";
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setString(1, documentoIdentidad);                
                int registrosAfectados = comando.executeUpdate();
                connection.close();
                if (registrosAfectados > 0) {
                    return null;
                }
                return "No se ha podido eliminar el ingreso";
            } catch (Exception e) {
                return e.getMessage();
            }
        }
        return connect.getError();
    }
}
