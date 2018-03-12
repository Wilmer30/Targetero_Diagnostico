/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessLayer;

import BusinessObjects.Registro;
import BusinessObjects.ConectarBaseDatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/**
 * Contiene los métodos de persistencia para la tabla: Registros.
 * @author Erick
 */
public class RegistrosDAL {
    
    /**
     * Recupera los registros de la tabla: Registros, según el
     * documento de identidad del paciente especificado.
     * @param documentoIdentidad parámetro de la consulta.
     * @return DefaultTableModel con los datos de la consulta.
     */
    public DefaultTableModel SelectIntelligentSearch(String documentoIdentidad) {
        DefaultTableModel model = new DefaultTableModel();
        String[] fila = new String[6];
        model.addColumn("Documento Identidad");
        model.addColumn("Fecha Ingreso");
        model.addColumn("Servicio o Sala de Internación");
        model.addColumn("Fecha Egreso");
        model.addColumn("Estado Paciente");
        model.addColumn("CIE 10 Egreso");
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        if (connection != null) {
            try {
                String sentencia = "SELECT DOC_ID_PAC,FEC_ING,SER_SAL_INTER,FEC_EGR,EST_PAC,CIE_DEF_EGR "
                        + "FROM REGISTROS WHERE DOC_ID_PAC LIKE ?";
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setString(1, documentoIdentidad + "%");
                ResultSet lector = comando.executeQuery();
                connection.close();
                while (lector.next()) {
                    fila[0] = lector.getString("DOC_ID_PAC");
                    fila[1] = lector.getString("FEC_ING");
                    fila[2] = lector.getString("SER_SAL_INTER");
                    fila[3] = lector.getString("FEC_EGR");
                    fila[4] = lector.getString("EST_PAC");
                    fila[5] = lector.getString("CIE_DEF_EGR");
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
     * Recupera un registro de la tabla: Registros, según el documento
     * de identidad del paciente especificado.
     * @param documentoIdentidad parámetro de la consulta.
     * @return objeto Registro con los datos de la consulta.
     */
    public Registro SelectById(String documentoIdentidad) {
        Registro registro = null;
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        if (connection != null) {
            try {
                String sentencia = "SELECT FEC_ING,COND_EDAD,EDAD_PAC_ING,"
                        + "TIPO_DISC_ING,SER_SAL_INTER,SALA_ING,HORA_ING,FEC_EGR,"
                        + "DIAS_EST,EST_PAC,CIE_DEF_EGR,CIE_SEC1_EGR,CIE_SEC2_EGR,"
                        + "CIE_SEC3_EGR,CIE_SEC4_EGR,CIE_SEC5_EGR,CIE_EXT_EGR,COD_ESP "
                        + "FROM REGISTROS WHERE DOC_ID_PAC=?";
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setString(1, documentoIdentidad);
                ResultSet lector = comando.executeQuery();
                if (lector.next()) {
                    registro = new Registro();
                    registro.setDocumentoIdentidad(documentoIdentidad);
                    registro.setFechaIngreso(lector.getDate("FEC_ING"));
                    registro.setCondicionEdad(lector.getString("COND_EDAD"));
                    registro.setEdad(lector.getInt("EDAD_PAC_ING"));
                    registro.setTipoDiscapacidad(lector.getString("TIPO_DISC_ING"));
                    registro.setServicioSalaInternacion(lector.getString("SER_SAL_INTER"));
                    registro.setSala(lector.getString("SALA_ING"));
                    registro.setHoraIngreso(lector.getString("HORA_ING"));
                    registro.setFechaEgreso(lector.getDate("FEC_EGR"));
                    registro.setDiasEstancia(lector.getInt("DIAS_EST"));
                    registro.setEstado(lector.getString("EST_PAC"));
                    registro.setCieDefinitivo(lector.getString("CIE_DEF_EGR"));
                    registro.setCieSecundario1(lector.getString("CIE_SEC1_EGR"));
                    registro.setCieSecundario2(lector.getString("CIE_SEC2_EGR"));
                    registro.setCieSecundario3(lector.getString("CIE_SEC3_EGR"));
                    registro.setCieSecundario4(lector.getString("CIE_SEC4_EGR"));
                    registro.setCieSecundario5(lector.getString("CIE_SEC5_EGR"));
                    registro.setCieExterno(lector.getString("CIE_EXT_EGR"));
                    registro.setCodigoEspecialidad(lector.getString("COD_ESP"));                    
                }
                connection.close();
                return registro;
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
    
    /**
     * Inserta un registro en la tabla: Registros.
     * @param registro parámetro para la inserción.
     * @return cadena con el resultado de la inserción.
     */
    public String Insert(Registro registro) {
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        if (connection != null) {
            try {
                String sentencia = "INSERT INTO INGRESOS(DOC_ID_PAC,FEC_ING,COND_EDAD,"+
                        "EDAD_PAC_ING,TIPO_DISC_ING,SER_SAL_INTER,SALA_ING,HORA_ING,FEC_EGR,"+
                        "DIAS_EST,EST_PAC,CIE_DEF_EGR,CIE_SEC1_EGR,CIE_SEC2_EGR,CIE_SEC3_EGR,"+
                        "CIE_SEC4_EGR,CIE_SEC5_EGR,CIE_EXT_EGR,COD_ESP) "
                        + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setString(1, registro.getDocumentoIdentidad());
                comando.setDate(2, registro.getFechaIngreso());
                comando.setString(3, registro.getCondicionEdad());
                comando.setInt(4, registro.getEdad());
                comando.setString(5, registro.getTipoDiscapacidad());
                comando.setString(6, registro.getServicioSalaInternacion());
                comando.setString(7, registro.getSala());
                comando.setString(8, registro.getHoraIngreso());
                comando.setDate(9, registro.getFechaEgreso());
                comando.setInt(10, registro.getDiasEstancia());
                comando.setString(11, registro.getEstado());
                comando.setString(12, registro.getCieDefinitivo());
                comando.setString(13, registro.getCieSecundario1());
                comando.setString(14, registro.getCieSecundario2());
                comando.setString(15, registro.getCieSecundario3());
                comando.setString(16, registro.getCieSecundario4());
                comando.setString(17, registro.getCieSecundario5());
                comando.setString(18, registro.getCieExterno());
                comando.setString(19, registro.getCodigoEspecialidad());
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
}