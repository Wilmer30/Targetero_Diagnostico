/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessLayer;

import BusinessObjects.ConectarBaseDatos;
import BusinessObjects.Paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 * Contiene los métodos de persistencia para la tabla: Pacientes.
 * @author Erick
 */
public class PacientesDAL {

    /**
     * Recupera un registro de la tabla: Pacientes, según el
     * documento de identidad especificado.
     * @param documentoIdentidad parámetro de la consulta.
     * @return objeto Paciente con los datos de la consulta.
     */
    public Paciente SelectById(String documentoIdentidad) {
        Paciente paciente=null;
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        if (connection != null) {
            try {
                String sentencia = "SELECT PRI_NOM_PAC,SEG_NOM_PAC,PRI_APE_PAC,"+
                        "SEG_APE_PAC,HIS_CLI_PAC,NAC_PAC,PAIS_NAC_PAC,GEN_PAC,"+
                        "FEC_NAC_PAC,ETNIA_PAC,COD_PARR,DIR_PAC FROM Pacientes "+
                        "WHERE DOC_ID_PAC=?";
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setString(1,documentoIdentidad);
                ResultSet lector = comando.executeQuery();
                if (lector.next()) {
                    paciente=new Paciente();
                    paciente.setDocumentoIdentidad(documentoIdentidad);
                    paciente.setPrimerNombre(lector.getString("PRI_NOM_PAC"));
                    paciente.setSegundoNombre(lector.getString("SEG_NOM_PAC"));
                    paciente.setPrimerApellido(lector.getString("PRI_APE_PAC"));
                    paciente.setSegundoApellido(lector.getString("SEG_APE_PAC"));
                    paciente.setNumeroHistoriaClinica(lector.getString("HIS_CLI_PAC"));
                    paciente.setNacionalidad(lector.getString("NAC_PAC"));
                    paciente.setPaisNacionalidad(lector.getString("PAIS_NAC_PAC"));
                    paciente.setGenero(lector.getString("GEN_PAC"));
                    paciente.setFechaNacimiento(lector.getDate("FEC_NAC_PAC"));
                    paciente.setEtnia(lector.getString("ETNIA_PAC"));
                    paciente.setCodigoParroquia(lector.getString("COD_PARR"));
                    paciente.setDireccion(lector.getString("DIR_PAC"));
                }                
                connection.close();
                return paciente;
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
    
    /**
     * Inserta un registro en la tabla: Pacientes.
     * @param paciente parámetro para la inserción.
     * @return cadena con el resultado de la inserción.
     */
    public String Insert(Paciente paciente){
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        if (connection != null) {
            try {
                String sentencia = "INSERT INTO Pacientes(DOC_ID_PAC,PRI_NOM_PAC,SEG_NOM_PAC,PRI_APE_PAC,"+
                        "SEG_APE_PAC,HIS_CLI_PAC,NAC_PAC,PAIS_NAC_PAC,GEN_PAC,"+
                        "FEC_NAC_PAC,ETNIA_PAC,COD_PARR,DIR_PAC) "
                        + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setString(1, paciente.getDocumentoIdentidad());
                comando.setString(2, paciente.getPrimerNombre());
                comando.setString(3, paciente.getSegundoNombre());
                comando.setString(4, paciente.getPrimerApellido());
                comando.setString(5, paciente.getSegundoApellido());
                comando.setString(6, paciente.getNumeroHistoriaClinica());
                comando.setString(7, String.valueOf(paciente.getNacionalidad()));
                comando.setString(8, paciente.getPaisNacionalidad());
                comando.setString(9, String.valueOf(paciente.getGenero()));
                comando.setDate(10, paciente.getFechaNacimiento());
                comando.setString(11, String.valueOf(paciente.getEtnia()));
                comando.setString(12, paciente.getCodigoParroquia());
                comando.setString(13, paciente.getDireccion());
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
     * Actualiza el número de historia clínica de un Paciente.
     * @param paciente parámetro para la actualización.
     * @return cadena con el resultado de la actualización.
     */
    public String updateHistoriaClinica(Paciente paciente){
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        if (connection != null) {
            try {
                String sentencia = "UPDATE Pacientes SET HIS_CLI_PAC=? WHERE DOC_ID_PAC=?";
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setString(1, paciente.getNumeroHistoriaClinica());
                comando.setString(2, paciente.getDocumentoIdentidad());
                int registrosAfectados = comando.executeUpdate();
                connection.close();
                if (registrosAfectados > 0) {
                    return null;
                }
                return "No se ha podido actualizar el número de historia clínica";
            } catch (Exception e) {
                return e.getMessage();
            }
        }
        return connect.getError();
    }
}
