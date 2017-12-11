/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessLayer;

import BusinessObjects.ConectarBaseDatos;
import BusinessObjects.Enfermedad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Wilmer Oñate
 */
public class EnfermedadesDAL {

    public String VerificarEnfermedad(String codigo) {
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        if (connection != null) {
            try {
                String sentencia = "SELECT  cod_cie from enfermedades where cod_cie =  ? and est_cie=?";                
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setString(1, codigo);
                comando.setString(2, "ACTIVO");
                ResultSet rs = comando.executeQuery();
                if (rs.next()) {
                    return null;
                }
                return "La enfermedad ingresada no existe";
            } catch (Exception e) {
                return e.getMessage();
            }
        }
        return connect.getError();
    }

    public String buscarEnfermedad(String codigo) {
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        if (connection != null) {
            try {
                String sentencia = "SELECT  cod_cie ,est_cie from enfermedades where cod_cie =  ? ";
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setString(1, codigo);
                ResultSet rs = comando.executeQuery();
                if (rs.next()) {
                    return "La enfermedad ya existe. \n Está enfermedad esta: " + rs.getString("est_cie");
                }
                return null;
            } catch (Exception e) {
                return e.getMessage();
            }
        }
        return connect.getError();
    }

    public String SelectPrimaryDescripcion(String codigo, String estado) {
        String desc = null;
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        if (connection != null) {
            try {
                String sentencia = "SELECT  desc_cie from enfermedades where cod_cie = ? and est_cie=?";
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setString(1, codigo);
                comando.setString(2, estado);
                ResultSet rs = comando.executeQuery();
                while (rs.next()) {
                    desc = (rs.getString("desc_cie"));
                }
            } catch (Exception e) {

            }
        }
        return desc;
    }

    public DefaultComboBoxModel SelectPrimaryKeyCombo(String codigo,String estado) {
        DefaultComboBoxModel com = new DefaultComboBoxModel();
        boolean res = false;
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        if (connection != null) {
            try {
                String sentencia = "SELECT  cod_cie from enfermedades where cod_cie like ? and est_cie=?";                
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setString(1, codigo + "%");
                comando.setString(2, estado);
                ResultSet rs = comando.executeQuery();
                while (rs.next()) {
                    com.addElement(rs.getString("cod_cie"));
                }

            } catch (Exception e) {
            }
        }
        return com;
    }

    public DefaultTableModel SelectPrimaryKeyTablaActivas(String codigo) {
        DefaultTableModel com = new DefaultTableModel();
        String[] fila = new String[2];
        com.addColumn("Código");
        com.addColumn("Descripción");
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        if (connection != null) {
            try {
                String sentencia = "SELECT  cod_cie, desc_cie from enfermedades where cod_cie like ? and est_cie=?";
                //String sentencia = "SELECT  cod_cie from enfermedades ";
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setString(1, codigo + "%");
                comando.setString(2, "ACTIVO");
                ResultSet rs = comando.executeQuery();
                while (rs.next()) {
                    fila[0] = rs.getString("cod_cie");
                    fila[1] = rs.getString("desc_cie");
                    com.addRow(fila);
                }

            } catch (Exception e) {
            }
        }
        return com;
    }

    public DefaultTableModel SelelctPrimaryKeyTablaInactivas(String codigo) {
        DefaultTableModel com = new DefaultTableModel();
        String[] fila = new String[2];
        com.addColumn("Código");
        com.addColumn("Descripción");
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        if (connection != null) {
            try {
                String sentencia = "SELECT  cod_cie, desc_cie from enfermedades where cod_cie like ? and est_cie=?";
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setString(1, codigo + "%");
                comando.setString(2, "INACTIVO");
                ResultSet rs = comando.executeQuery();
                while (rs.next()) {
                    fila[0] = rs.getString("cod_cie");
                    fila[1] = rs.getString("desc_cie");
                    com.addRow(fila);
                }

            } catch (Exception e) {
            }
        }
        return com;
    }

    public DefaultTableModel SelelctEnfermedadesActivas() {
        DefaultTableModel com = new DefaultTableModel();
        String[] fila = new String[2];
        com.addColumn("Código");
        com.addColumn("Descripción");

        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        if (connection != null) {
            try {
                String sentencia = "SELECT  cod_cie,desc_cie from enfermedades where est_cie =?";
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setString(1, "ACTIVO");
                ResultSet rs = comando.executeQuery();
                while (rs.next()) {
                    fila[0] = rs.getString("cod_cie");
                    fila[1] = rs.getString("desc_cie");
                    com.addRow(fila);
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        return com;
    }

    public DefaultTableModel SelelctEnfermedadesInactivas() {
        DefaultTableModel com = new DefaultTableModel();
        String[] fila = new String[2];
        com.addColumn("Código");
        com.addColumn("Descripción");

        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        if (connection != null) {
            try {
                String sentencia = "SELECT  cod_cie,desc_cie from enfermedades where est_cie = ? ";                
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setString(1, "INACTIVO");
                ResultSet rs = comando.executeQuery();
                while (rs.next()) {
                    fila[0] = rs.getString("cod_cie");
                    fila[1] = rs.getString("desc_cie");
                    com.addRow(fila);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        return com;
    }

    public String Insert(Enfermedad enfermedad) {
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        int registrosAfectados = 0;
        if (connection != null) {
            try {

                String sentencia = "INSERT INTO Enfermedades(cod_cie,desc_cie,est_cie) "
                        + "VALUES(?,?,?)";
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setString(1, enfermedad.getCodigoCie10());
                comando.setString(2, enfermedad.getDescripcion());
                comando.setString(3, enfermedad.getEstado());
                registrosAfectados = comando.executeUpdate();
                if (registrosAfectados > 0) {
                    return null;
                } else {
                    return "Enfermedad no ingresada.";
                }
            } catch (Exception e) {
                return e.getMessage();
            }
        }
        return connect.getError();
    }

    public String UpdateEstado(String codigo, String parametro) {
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        int registrosAfectados = 0;
        if (connection != null) {
            try {
                String sentencia = "UPDATE  Enfermedades SET est_cie= ? "
                        + "Where cod_cie=? ";
                PreparedStatement comando = connection.prepareStatement(sentencia);
                comando.setString(1, parametro);
                comando.setString(2, codigo);

                registrosAfectados = comando.executeUpdate();
                if (registrosAfectados > 0) {
                    return null;
                } else {
                    return "No se ha dado de alta correctamente la enfermedad " + codigo;
                }

            } catch (Exception e) {
                return e.getMessage();
            }
        }
        return connect.getError();
    }

}
