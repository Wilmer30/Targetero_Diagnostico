/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessLayer;

import BusinessObjects.ConectarBaseDatos;
import BusinessObjects.Historicos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Wilmer Oñate
 */
public class HistoricosDAL {

    private Timestamp fechaHora(Date fecha) {
        SimpleDateFormat cambiarfecha = new SimpleDateFormat("dd-MM-yyyy");//revisar
        Timestamp timestamp = new Timestamp(fecha.getTime());
        return timestamp;
    }
    
     public String Insert(Historicos historicos[]) {  //revisar hacer con  transacciones
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        Historicos historico ;        
        if (connection != null) {
            try {
                connection.setAutoCommit(false);
                for (int i = 0; i < historicos.length; i++) {
                    historico = historicos[i];
                    String sentencia = "INSERT INTO historicos(COD_CIE ,NUM_HIST_CLI ,FEC_ING ,EST_PAC ) "
                            + "VALUES(?,?,?,?)";
                    PreparedStatement comando = connection.prepareStatement(sentencia);
                    comando.setString(1, historico.getCodigoCie10());
                    comando.setString(2, historico.getNumeroHistoriaClinica());
                    comando.setTimestamp(3, fechaHora(historico.getFechaIngreso()));
                    comando.setString(4, historico.getEstadoPaciente());
                    int registrosAfectados = comando.executeUpdate();
//                    if (registrosAfectados > 0) {
//                        //verificar
//                    }
                }
                connection.commit();
                return  null;
            } catch (Exception e) {
                try {
                    connection.rollback();
                    return e.getMessage();
                } catch (SQLException ex) {
                    return ex.getMessage();
                }
            }
        }
        return connect.getError();
    }
     

//    public boolean Insert2(Historicos historicos[]) {  //revisar hacer con  transacciones
//        ConectarBaseDatos connect = new ConectarBaseDatos();
//        Connection connection = connect.conectar();
//        Historicos historico = new Historicos();
//        boolean respuesta = false;
//        if (connection != null) {
//            try {
//                connection.setAutoCommit(false);
//                for (int i = 0; i < historicos.length; i++) {
//                    historico = historicos[i];
//                    String sentencia = "INSERT INTO historicos(COD_CIE ,NUM_HIST_CLI ,FEC_ING ,EST_PAC ) "
//                            + "VALUES(?,?,?,?)";
//                    PreparedStatement comando = connection.prepareStatement(sentencia);
//                    comando.setString(1, historico.getCodigoCie10());
//                    comando.setString(2, historico.getNumeroHistoriaClinica());
//                    comando.setTimestamp(3, fechaHora(historico.getFechaIngreso()));
//                    comando.setString(4, historico.getEstadoPaciente());
//
//                    int registrosAfectados = comando.executeUpdate();
//                    if (registrosAfectados > 0) {
//                        respuesta = true;
//                    }
//                }
//                connection.commit();
//            } catch (Exception e) {
//                try {
//                    connection.rollback();
//                    respuesta = false;
//                } catch (SQLException ex) {
//
////                    JOptionPane.showMessageDialog(null, ex);
//                }
////                JOptionPane.showMessageDialog(null, e);
//            }
//        }
//        return respuesta;
//    }

   

}
