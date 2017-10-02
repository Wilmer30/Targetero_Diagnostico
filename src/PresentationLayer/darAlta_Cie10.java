/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import BusinessLayer.EnfermedadesBL;
import BusinessLayer.UsuariosBL;
import BusinessLayer.Validaciones;
import BusinessObjects.Enumeraciones;
import javax.swing.JOptionPane;

/**
 *
 * @author Wilmer Oñate
 */
public class darAlta_Cie10 extends javax.swing.JInternalFrame {

    // <editor-fold defaultstate="collapsed" desc="Datos">
    private EnfermedadesBL enfermedadesBL;
    private Validaciones validar;
    private UsuariosBL usuarioBL;
    //</editor-fold>       

    public darAlta_Cie10() {
        initComponents();
        usuarioBL=new UsuariosBL();
        enfermedadesBL = new EnfermedadesBL();
        validar = new Validaciones();
        tbCie10.getTableHeader().setReorderingAllowed(false); //Poner las columnas estaticas
        CargarCIE10(); //Carga todos los codigos CIE10 activos
        ActivarDesactivarBtnAceptar();
    }

    // <editor-fold defaultstate="collapsed" desc="Metodos">
    private void ActivarDesactivarBtnAceptar() {
        if (tbCie10.getSelectedRow() >= 0) {
            btnAceptar.setEnabled(true);
        } else {
            btnAceptar.setEnabled(false);
        }
    }

    private void limpiarControles() {
        txtBusqueda.setText(null);
    }

    private void CargarCIE10() {
        tbCie10.setModel(enfermedadesBL.SelectCIE10Inactivas());
    }

    private void CargarCIE10PrimaryKey() {
        //Se realiza la busqueda inteligente        
        tbCie10.setModel(enfermedadesBL.SelectCIE10PrimaryKeyInactivas(txtBusqueda.getText()));
        ActivarDesactivarBtnAceptar();
    }

    private void darAltaCIE10() {
        //Pedimos confirmación para dar de alta. devuelve 0= si 1=no
        if (tbCie10.getSelectedRow() >= 0) {
            int res = JOptionPane.showConfirmDialog(null,
                    "Desea dar de alta a la enfermedad: \n " + tbCie10.getValueAt(tbCie10.getSelectedRow(), 0).toString() + ". \n" + 
                            tbCie10.getValueAt(tbCie10.getSelectedRow(), 1),
                    "DAR DE ALTA",
                    JOptionPane.YES_NO_OPTION, JOptionPane.OK_CANCEL_OPTION);
            if (res == 0) {
                //Procedemos a actualizar el estado de la enfermedad de activa a inactiva              
                String codigo = String.valueOf(tbCie10.getValueAt(tbCie10.getSelectedRow(), 0));//Tomamos el codigo de la enfermedad
                String mensaje = enfermedadesBL.UpdateEstadoCIE10(codigo, "ACTIVO"); //Ejecutamos la actualización

                if (mensaje == null) {
                    JOptionPane.showMessageDialog(null, "Enfermedad dada de alta correctamente", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
                    usuarioBL.ultimaActividad(menu.usuario());
                } else {
                    JOptionPane.showMessageDialog(null, mensaje, "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                }
                txtBusqueda.setText(null);//Limpiamos el txtBusqueda
                txtBusqueda.requestFocus();//Pone,os el foto en el txtBusqueda
                CargarCIE10();//Actualizamos los datos d ela tabla
                ActivarDesactivarBtnAceptar();//Activamos o desactivamos el btnAceptar
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una enfermedad", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
            ActivarDesactivarBtnAceptar();
        }
    }
    // </editor-fold>

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbCie10 = tbCie10 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtBusqueda = new javax.swing.JTextField();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("DAR DE ALTA CÓDIGO CIE-10");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tbCie10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tbCie10.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Diagnostico"
            }
        ));
        tbCie10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbCie10MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbCie10);

        btnAceptar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Guardar.png"))); // NOI18N
        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Código CIE-10");

        txtBusqueda.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnAceptar)
                                .addGap(31, 31, 31)
                                .addComponent(btnCancelar)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        darAltaCIE10();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
        menu.setEstadoVentana(Enumeraciones.EstadoVentanas.cerrado);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyReleased
        // TODO add your handling code here:       
        CargarCIE10PrimaryKey(); //Busqueda por completación        
        ActivarDesactivarBtnAceptar();
    }//GEN-LAST:event_txtBusquedaKeyReleased

    private void tbCie10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCie10MouseClicked
        // TODO add your handling code here:
        ActivarDesactivarBtnAceptar();
    }//GEN-LAST:event_tbCie10MouseClicked

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        menu.setEstadoVentana(Enumeraciones.EstadoVentanas.cerrado);
    }//GEN-LAST:event_formInternalFrameClosing

    private void txtBusquedaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyTyped
        // TODO add your handling code here:
        validar.convertirMayusculas(evt); //Covertir a mayusculas 
    }//GEN-LAST:event_txtBusquedaKeyTyped

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
            java.util.logging.Logger.getLogger(darAlta_Cie10.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(darAlta_Cie10.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(darAlta_Cie10.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(darAlta_Cie10.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new darAlta_Cie10().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbCie10;
    private javax.swing.JTextField txtBusqueda;
    // End of variables declaration//GEN-END:variables
}
