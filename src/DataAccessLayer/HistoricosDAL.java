/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessLayer;

import BusinessObjects.ConectarBaseDatos;
import BusinessObjects.Historicos;
import BusinessObjects.Usuarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Wilmer Oñate
 */
public class HistoricosDAL {

    private Timestamp fechaHora(Date fecha) {

        SimpleDateFormat cambiarfecha = new SimpleDateFormat("YYYY-MM-dd");//revisar
        Timestamp timestamp = new Timestamp(fecha.getTime());
        return timestamp;
    }

    public boolean Insert(Historicos historico) {
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        boolean respuesta = false;
        if (connection != null) {
            try {
                String sentencia = "INSERT INTO historicos(COD_CIE ,NUM_HIST_CLI ,FEC_ING ,EST_PAC ) "
                        + "VALUES(?,?,?,?)";
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setString(1, historico.getCodigoCie10());
                comando.setString(2, historico.getNumeroHistoriaClinica());
                comando.setTimestamp(3, fechaHora(historico.getFechaIngreso()));
                comando.setString(4, historico.getEstadoPaciente());

                int registrosAfectados = comando.executeUpdate();
                if (registrosAfectados > 0) {
                    respuesta = true;
                }

            } catch (Exception e) {
                //control errores}
                JOptionPane.showMessageDialog(null, e);
            }
        }
        return respuesta;
    }
}
