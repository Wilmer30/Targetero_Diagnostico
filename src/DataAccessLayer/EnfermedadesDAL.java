/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessLayer;

import BusinessObjects.ConectarBaseDatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Wilmer Oñate
 */
public class EnfermedadesDAL {

    public DefaultComboBoxModel SelelctPrimaryKey(String codigo) {
        DefaultComboBoxModel com = new DefaultComboBoxModel();
        boolean res = false;
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        if (connection != null) {
            try {
                String sentencia = "SELECT  cod_cie from enfermedades where cod_cie like  ? and est_cie=?";
                //String sentencia = "SELECT  cod_cie from enfermedades ";
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setString(1, codigo + "%");
                comando.setString(2, "ACTIVO");
                ResultSet rs = comando.executeQuery();
                while (rs.next()) {
                    com.addElement(rs.getString("cod_cie"));
                }

            } catch (Exception e) {
            }
        }
        return com;
    }
    
    public DefaultTableModel SelelctPrimaryKeyTabla(String codigo) {
        DefaultTableModel com = new DefaultTableModel();
        String []fila = new String[2];
        com.addColumn("Código");
        com.addColumn("Descripción");
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        if (connection != null) {
            try {
                String sentencia = "SELECT  cod_cie, desc_cie from enfermedades where cod_cie like  ? and est_cie=?";
                //String sentencia = "SELECT  cod_cie from enfermedades ";
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setString(1, codigo + "%");
                comando.setString(2, "ACTIVO");
                ResultSet rs = comando.executeQuery();
                while (rs.next()) {
                     fila[0]= rs.getString("cod_cie");
                    fila[1]= rs.getString("desc_cie");
                    com.addRow(fila);
                }

            } catch (Exception e) {
            }
        }
        return com;
    }
     public DefaultTableModel Selelct() {
        DefaultTableModel com = new DefaultTableModel();
        String []fila = new String[2];
        com.addColumn("Código");
        com.addColumn("Descripción");
        
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        if (connection != null) {
            try {
                String sentencia = "SELECT  cod_cie,desc_cie from enfermedades where est_cie =?";
                //String sentencia = "SELECT  cod_cie from enfermedades ";
                PreparedStatement comando = connection.prepareStatement(sentencia);                
                comando.setString(1, "ACTIVO");
                ResultSet rs = comando.executeQuery();
                while (rs.next()) {
                    fila[0]= rs.getString("cod_cie");
                    fila[1]= rs.getString("desc_cie");
                    com.addRow(fila);
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        return com;
    }
}
