/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import BusinessLayer.EnfermedadesBL;
import BusinessLayer.Validaciones;
import BusinessObjects.Enfermedades;
import BusinessObjects.Enumeraciones;
import DataAccessLayer.EnfermedadesDAL;
import javax.swing.JOptionPane;

/**
 *
 * @author Wilmer Oñate
 */
public class ingreso_CIE10 extends javax.swing.JInternalFrame {

    // <editor-fold defaultstate="collapsed" desc="Datos">
    EnfermedadesBL enfermedadesBL;
    Validaciones validar;
    EnfermedadesDAL enfermedadesDAL;
    Enfermedades enfermedades;

    // </editor-fold>
    public ingreso_CIE10() {
        initComponents();
        validar = new Validaciones();
        enfermedadesBL = new EnfermedadesBL();
        enfermedadesDAL = new EnfermedadesDAL();
        enfermedades = new Enfermedades();
    }

// <editor-fold defaultstate="collapsed" desc="Metodos">   
    private void controlDescripcion(java.awt.event.KeyEvent evt) {

        char c = evt.getKeyChar();
        if (!((c >= 65 && c <= 90) || (c >= 97 && c <= 122) || c == 44 || c == 46 || Character.isDigit(c) || c == 8 || c == 32)) {
            evt.consume();
        }
    }

    private void controlDescripcionEnter(java.awt.event.KeyEvent evt) {

        char c = evt.getKeyChar();
        if ((c == java.awt.event.KeyEvent.VK_TAB || c == java.awt.event.KeyEvent.VK_ENTER)) {
            evt.consume();
        }
    }

//    private void controlCodigo(java.awt.event.KeyEvent evt) {
//        char c = evt.getKeyChar();
//        if (!((c >= 65 && c <= 90) || (c >= 97 && c <= 122) || Character.isDigit(c))) {
//            evt.consume();
//        }
//    }
//    private void controlLimiteCaracterCodigo(java.awt.event.KeyEvent evt) {
//        char c = evt.getKeyChar();
//
//        if (txtCodigo.getText().toString().length() >= 4) {
//            evt.consume();
//        }
//    }
    private void limpiarContorles() {
        txtCodigo.setText(null);
        txtDiagnostico.setText(null);

    }

    private void controlVentana() {
        if (!txtCodigo.getText().isEmpty() || !txtDiagnostico.getText().isEmpty()) {
            int res = JOptionPane.showConfirmDialog(null, "Esta ventana contienen datos que se perderan. \n" + "¿Desea cerrar esta ventana.?", "Seleccionar una opción", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            //res=0 si//res=1 =no                  
            if (res == 1) {
                this.setDefaultCloseOperation(0); // no cierra la ventana
            } else {
                this.setDefaultCloseOperation(1);//  cierra la ventana
                menu.setEstadoVentana(Enumeraciones.EstadoVentanas.cerrado);
            }
        } else {
            this.setDefaultCloseOperation(1);//cierra la ventana
            menu.setEstadoVentana(Enumeraciones.EstadoVentanas.cerrado);
        }
    }

    private boolean controlIngreso() {
        if (!txtCodigo.getText().isEmpty() && txtCodigo.getText().length() >= 4) {
            if (!txtDiagnostico.getText().isEmpty() && txtCodigo.getText().length() >= 4) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Ingresar una descripcion a la enfermedad", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                txtDiagnostico.requestFocus();
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingresar un código de la enfermedad validao", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
            txtCodigo.requestFocus();
            return false;
        }
    }

    private void ingresoUsuario() {
        if (controlIngreso()) {
            int res = JOptionPane.showConfirmDialog(null, "Esta seguro de ingresar esta Historia Clinica?", "Seleccionar una opción", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            //res=0 si//res=1 =no                  
            if (res == 0) {
                String mensaje = enfermedadesBL.buscarEnfermedad(txtCodigo.getText());
                if (mensaje == null) {
                    String mensajeIngreso = enfermedadesBL.InsertEnfermedad(txtCodigo.getText(), txtDiagnostico.getText());

                    if (mensajeIngreso == null) {
                        JOptionPane.showMessageDialog(null, "Enfermedad ingresada correctamente", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
                        limpiarContorles();
                    } else {
                        JOptionPane.showMessageDialog(null, mensajeIngreso, "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                        limpiarContorles();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, mensaje, "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                    limpiarContorles();
                    txtCodigo.requestFocus();
                }
            }
        }
    }
    // </editor-fold>

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDiagnostico = new javax.swing.JTextArea();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("INGRESO DE CIE10");
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
        jPanel1.setToolTipText("");

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("Código");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Diagnostico");

        txtCodigo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoKeyTyped(evt);
            }
        });

        txtDiagnostico.setColumns(20);
        txtDiagnostico.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtDiagnostico.setRows(5);
        txtDiagnostico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDiagnosticoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiagnosticoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDiagnosticoKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(txtDiagnostico);

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAceptar)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(42, 42, 42)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
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

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        limpiarContorles();
        txtCodigo.requestFocus();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        // TODO add your handling code here:
        controlVentana();
    }//GEN-LAST:event_formInternalFrameClosing

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        ingresoUsuario();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void txtDiagnosticoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiagnosticoKeyTyped
        // TODO add your handling code here:
        controlDescripcion(evt);
        validar.convertirMayusculas(evt);

    }//GEN-LAST:event_txtDiagnosticoKeyTyped

    private void txtDiagnosticoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiagnosticoKeyReleased


    }//GEN-LAST:event_txtDiagnosticoKeyReleased

    private void txtDiagnosticoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiagnosticoKeyPressed
        // TODO add your handling code here:
        controlDescripcionEnter(evt);
    }//GEN-LAST:event_txtDiagnosticoKeyPressed

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped
//        controlCodigo(evt);
        validar.soloNumerosLetras(evt);
        validar.longitudMaximoCuatro(evt, txtCodigo.getText());
        validar.convertirMayusculas(evt);
        //controlLimiteCaracterCodigo(evt);


    }//GEN-LAST:event_txtCodigoKeyTyped

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoKeyReleased

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
            java.util.logging.Logger.getLogger(ingreso_CIE10.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ingreso_CIE10.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ingreso_CIE10.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ingreso_CIE10.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ingreso_CIE10().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextArea txtDiagnostico;
    // End of variables declaration//GEN-END:variables
}
