/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import BusinessLayer.EnfermedadesBL;
import BusinessLayer.UsuariosBL;
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
    private EnfermedadesBL enfermedadesBL;
    private Validaciones validar;
    private EnfermedadesDAL enfermedadesDAL;
    private Enfermedades enfermedades;
    private UsuariosBL usuarioBL;
    // </editor-fold>

    public ingreso_CIE10() {
        initComponents();
        usuarioBL=new UsuariosBL();
        validar = new Validaciones();
        enfermedadesBL = new EnfermedadesBL();
        enfermedadesDAL = new EnfermedadesDAL();
        enfermedades = new Enfermedades();
        txtDiagnostico.setLineWrap(true);
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
    
    private void limpiarControles() {
        txtCodigo.setText(null);
        txtDiagnostico.setText(null);
        
    }

    private void controlVentana() {
        if (!txtCodigo.getText().isEmpty() || !txtDiagnostico.getText().isEmpty()) {
            int res = JOptionPane.showConfirmDialog(null, "Esta ventana contienen datos que se perderan. \n" + 
                    "¿Desea cerrar esta ventana?", "Seleccionar una opción", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            //res=0 si//res=1 =no                  
            if (res == 0) {//cierra la ventana
                this.dispose();
                menu.setEstadoVentana(Enumeraciones.EstadoVentanas.cerrado);
            }
        } else {
            this.dispose();//cierra la ventana
            menu.setEstadoVentana(Enumeraciones.EstadoVentanas.cerrado);
        }
    }

    private boolean controlIngreso() {
        if (!txtCodigo.getText().isEmpty() && txtCodigo.getText().length() >= 4) {
            if (!txtDiagnostico.getText().isEmpty() && txtCodigo.getText().length() >= 4) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Ingresar una descripción para la enfermedad", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                txtDiagnostico.requestFocus();
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingresar un código de enfermedad válido", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
            txtCodigo.requestFocus();
            return false;
        }
    }

    private void ingresoCodigo() {
        if (controlIngreso()) {
            int res = JOptionPane.showConfirmDialog(null, "Está seguro de ingresar este Código CIE-10?", "Seleccionar una opción", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            //res=0 si//res=1 =no                  
            if (res == 0) {
                String mensaje = enfermedadesBL.buscarEnfermedad(txtCodigo.getText());
                if (mensaje == null) {
                    String mensajeIngreso = enfermedadesBL.InsertEnfermedad(txtCodigo.getText(), txtDiagnostico.getText());

                    if (mensajeIngreso == null) {
                        JOptionPane.showMessageDialog(null, "Enfermedad ingresada correctamente", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
                        limpiarControles();
                        usuarioBL.ultimaActividad(menu.usuario());
                    } else {
                        JOptionPane.showMessageDialog(null, mensajeIngreso, "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                        limpiarControles();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, mensaje, "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                    limpiarControles();
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
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDiagnostico = new javax.swing.JTextArea();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("INGRESO DE CÓDIGO CIE-10");
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
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoKeyTyped(evt);
            }
        });

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

        txtDiagnostico.setColumns(20);
        txtDiagnostico.setRows(5);
        jScrollPane2.setViewportView(txtDiagnostico);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(btnAceptar)
                        .addGap(58, 58, 58)
                        .addComponent(btnCancelar)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAceptar)
                            .addComponent(btnCancelar)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel2)))
                .addContainerGap())
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
        controlVentana();        
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        // TODO add your handling code here:
        controlVentana();
    }//GEN-LAST:event_formInternalFrameClosing

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        ingresoCodigo();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped
        validar.soloNumerosLetras(evt);
        validar.longitudMaximoCuatro(evt, txtCodigo.getText());
        validar.convertirMayusculas(evt);
    }//GEN-LAST:event_txtCodigoKeyTyped

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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextArea txtDiagnostico;
    // End of variables declaration//GEN-END:variables
}
