/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import BusinessObjects.Enumeraciones.*;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Wilmer Oñate
 */
public class menu extends javax.swing.JFrame {

    static EstadoVentanas estadoVentana;

    public static void setEstadoVentana(EstadoVentanas estadoVentana) {
        menu.estadoVentana = estadoVentana;       
    }

    public menu(String usuario) {
        initComponents();        
        setExtendedState(MAXIMIZED_BOTH);
        lblUsuario.setText(usuario);
        estadoVentana = EstadoVentanas.cerrado;
         setIconImage(new ImageIcon(getClass().getResource("/Imagenes/logo.png")).getImage());
        //lbCedulaUsuario.setText(ConectarBaseDatos.getUsuario());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        dpPrincipal = new javax.swing.JDesktopPane();
        lblUsuario = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnAdministrador = new javax.swing.JMenu();
        smRol = new javax.swing.JMenu();
        miIngresoUsuario = new javax.swing.JMenuItem();
        miDarBajaUsuario = new javax.swing.JMenuItem();
        jMenu10 = new javax.swing.JMenu();
        miAsignarRol = new javax.swing.JMenuItem();
        mnCIE10 = new javax.swing.JMenu();
        miIngresarCIE10 = new javax.swing.JMenuItem();
        miDarBajaCIE10 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        mnHistoriaClinica = new javax.swing.JMenu();
        miIngresarHC = new javax.swing.JMenuItem();
        mnReportes = new javax.swing.JMenu();
        mnConfiguraciones = new javax.swing.JMenu();
        miCambioClave = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        jMenu1.setText("jMenu1");

        jMenu5.setText("File");
        jMenuBar2.add(jMenu5);

        jMenu6.setText("Edit");
        jMenuBar2.add(jMenu6);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MENÚ PRINCIPAL");
        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("Usuario:");

        dpPrincipal.setBackground(new java.awt.Color(224, 224, 224));

        javax.swing.GroupLayout dpPrincipalLayout = new javax.swing.GroupLayout(dpPrincipal);
        dpPrincipal.setLayout(dpPrincipalLayout);
        dpPrincipalLayout.setHorizontalGroup(
            dpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        dpPrincipalLayout.setVerticalGroup(
            dpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 392, Short.MAX_VALUE)
        );

        lblUsuario.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jMenuBar1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        mnAdministrador.setText("Usuarios");

        smRol.setText("Usuarios");

        miIngresoUsuario.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        miIngresoUsuario.setText("Ingreso");
        miIngresoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miIngresoUsuarioActionPerformed(evt);
            }
        });
        smRol.add(miIngresoUsuario);

        miDarBajaUsuario.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        miDarBajaUsuario.setText("Dar de baja");
        miDarBajaUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miDarBajaUsuarioActionPerformed(evt);
            }
        });
        smRol.add(miDarBajaUsuario);

        mnAdministrador.add(smRol);

        jMenu10.setText("Roles");

        miAsignarRol.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        miAsignarRol.setText("Asignar");
        miAsignarRol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miAsignarRolActionPerformed(evt);
            }
        });
        jMenu10.add(miAsignarRol);

        mnAdministrador.add(jMenu10);

        jMenuBar1.add(mnAdministrador);

        mnCIE10.setText("CIE10");

        miIngresarCIE10.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        miIngresarCIE10.setText("Ingresar");
        miIngresarCIE10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miIngresarCIE10ActionPerformed(evt);
            }
        });
        mnCIE10.add(miIngresarCIE10);

        miDarBajaCIE10.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        miDarBajaCIE10.setText("Dar de baja");
        miDarBajaCIE10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miDarBajaCIE10ActionPerformed(evt);
            }
        });
        mnCIE10.add(miDarBajaCIE10);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Dar de alta");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        mnCIE10.add(jMenuItem2);

        jMenuBar1.add(mnCIE10);

        mnHistoriaClinica.setText("Historias clinicas");

        miIngresarHC.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        miIngresarHC.setText("Ingresar");
        miIngresarHC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miIngresarHCActionPerformed(evt);
            }
        });
        mnHistoriaClinica.add(miIngresarHC);

        jMenuBar1.add(mnHistoriaClinica);

        mnReportes.setText("Reportes");
        jMenuBar1.add(mnReportes);

        mnConfiguraciones.setText("Configuración");

        miCambioClave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        miCambioClave.setText("Cambio de clave");
        miCambioClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miCambioClaveActionPerformed(evt);
            }
        });
        mnConfiguraciones.add(miCambioClave);

        jMenuBar1.add(mnConfiguraciones);

        jMenu2.setText("Ayuda");

        jMenu3.setText("Manual");
        jMenu2.add(jMenu3);

        jMenuItem1.setText("Acerca de");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dpPrincipal)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(469, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(dpPrincipal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void miIngresarCIE10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miIngresarCIE10ActionPerformed
        // TODO add your handling code here:
        if (!estadoVentana.name().equals("abierto")) {
            ingreso_CIE10 ventana = new ingreso_CIE10();
            dpPrincipal.add(ventana);
            ventana.setVisible(true);
             estadoVentana = EstadoVentanas.abierto;
        }else{
            JOptionPane.showMessageDialog(null, "Cerrar las ventana abirta para poder continuar", "Informacion", JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_miIngresarCIE10ActionPerformed

    private void miIngresarHCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miIngresarHCActionPerformed
        // TODO add your handling code here:
        if (!estadoVentana.name().equals("abierto")) {
        ingreso_HistoriaClinica ventana = new ingreso_HistoriaClinica();
        dpPrincipal.add(ventana);
        ventana.setVisible(true);
        estadoVentana = EstadoVentanas.abierto;
        }else{
            JOptionPane.showMessageDialog(null, "Cerrar las ventana abirta para poder continuar", "Informacion", JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_miIngresarHCActionPerformed

    private void miCambioClaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miCambioClaveActionPerformed
        // TODO add your handling code here:
         if (!estadoVentana.name().equals("abierto")) {
        cambioClave ventana = new cambioClave();
        dpPrincipal.add(ventana);
        ventana.setVisible(true);
        estadoVentana = EstadoVentanas.abierto;
         }else{
            JOptionPane.showMessageDialog(null, "Cerrar las ventana abirta para poder continuar", "Informacion", JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_miCambioClaveActionPerformed

    private void miDarBajaCIE10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miDarBajaCIE10ActionPerformed
        // TODO add your handling code here:
         if (!estadoVentana.name().equals("abierto")) {
        darBaja_Cie10 ventana = new darBaja_Cie10();
        dpPrincipal.add(ventana);
        ventana.setVisible(true);
        estadoVentana = EstadoVentanas.abierto;
         }else{
            JOptionPane.showMessageDialog(null, "Cerrar las ventana abirta para poder continuar", "Informacion", JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_miDarBajaCIE10ActionPerformed

    private void miAsignarRolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miAsignarRolActionPerformed
        // TODO add your handling code here:
        if (!estadoVentana.name().equals("abierto")) {
            asignarRol_Usuario ventana = new asignarRol_Usuario();
            dpPrincipal.add(ventana);
            ventana.setVisible(true);
            estadoVentana = EstadoVentanas.abierto;
        }else{
            JOptionPane.showMessageDialog(null, "Cerrar las ventana abirta para poder continuar", "Informacion", JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_miAsignarRolActionPerformed

    private void miIngresoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miIngresoUsuarioActionPerformed
        // TODO add your handling code here:

        if (!estadoVentana.name().equals("abierto")) {
            ingresoUsuarios ventana = new ingresoUsuarios();
            dpPrincipal.add(ventana);
            ventana.setVisible(true);
            estadoVentana = EstadoVentanas.abierto;
        }else{
            JOptionPane.showMessageDialog(null, "Cerrar las ventana abirta para poder continuar", "Informacion", JOptionPane.OK_OPTION);
        }

    }//GEN-LAST:event_miIngresoUsuarioActionPerformed

    private void miDarBajaUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miDarBajaUsuarioActionPerformed
        // TODO add your handling code here:
        if (!estadoVentana.name().equals("abierto")) {
            darBaja_Usuario ventana = new darBaja_Usuario();
            dpPrincipal.add(ventana);
            ventana.setVisible(true);
            estadoVentana = EstadoVentanas.abierto;
        }else{
            JOptionPane.showMessageDialog(null, "Cerrar las ventana abirta para poder continuar", "Informacion", JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_miDarBajaUsuarioActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        if (!estadoVentana.name().equals("abierto")) {
            AcercaDe ventana = new AcercaDe();
            dpPrincipal.add(ventana);
            ventana.setVisible(true);
            estadoVentana = EstadoVentanas.abierto;
        }else{
            JOptionPane.showMessageDialog(null, "Cerrar las ventana abirta para poder continuar", "Informacion", JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        if (!estadoVentana.name().equals("abierto")) {
            darAlta_Cie10 ventana = new darAlta_Cie10();
            dpPrincipal.add(ventana);
            ventana.setVisible(true);
            estadoVentana = EstadoVentanas.abierto;
        }else{
            JOptionPane.showMessageDialog(null, "Cerrar las ventana abirta para poder continuar", "Informacion", JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menu("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane dpPrincipal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JMenuItem miAsignarRol;
    private javax.swing.JMenuItem miCambioClave;
    private javax.swing.JMenuItem miDarBajaCIE10;
    private javax.swing.JMenuItem miDarBajaUsuario;
    private javax.swing.JMenuItem miIngresarCIE10;
    private javax.swing.JMenuItem miIngresarHC;
    private javax.swing.JMenuItem miIngresoUsuario;
    private javax.swing.JMenu mnAdministrador;
    private javax.swing.JMenu mnCIE10;
    private javax.swing.JMenu mnConfiguraciones;
    private javax.swing.JMenu mnHistoriaClinica;
    private javax.swing.JMenu mnReportes;
    private javax.swing.JMenu smRol;
    // End of variables declaration//GEN-END:variables
}
