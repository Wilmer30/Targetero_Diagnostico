/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import BusinessLayer.UsuariosBL;
import BusinessLayer.UsuariosRolesBL;
import BusinessLayer.Validaciones;
import BusinessObjects.Enumeraciones;
import javax.swing.JOptionPane;

/**
 *
 * @author Wilmer Oñate
 */
public class ingresoUsuarios extends javax.swing.JInternalFrame {

    // <editor-fold defaultstate="collapsed" desc="Datos">
    private UsuariosBL usuarioBL;
    private Validaciones validar;
    private UsuariosRolesBL userrolBL;
    // </editor-fold>

    /**
     * Creates new form ingresoUsuarios
     */
    public ingresoUsuarios() {
        usuarioBL = new UsuariosBL();
        validar = new Validaciones();
        userrolBL=new UsuariosRolesBL();
        initComponents();
    }

    private void limpiarCedula() {
        txtCedula.setText("");
        txtCedula.requestFocus();
    }

    private void limpiarMail() {
        txtEmail.setText("");
        txtEmail.requestFocus();
    }

    private void limpiarControles() {
        txtCedula.setText("");
        txtEmail.setText("");
        txtCedula.requestFocus();
    }

    private void nuevoUsuario() {
        if (controlCedula()) {
            if (controlMail()) {
                if (controlRol()) {
                    if (!usuarioBL.buscarUsuario(txtCedula.getText())) {
                        String mensaje = usuarioBL.nuevoUsuario(txtCedula.getText(), txtEmail.getText());
                        if (mensaje == null) {                            
                            //Asignar rol
                            int idUser = usuarioBL.idUsuario(txtCedula.getText());
                            int idRol=cbRol.getSelectedIndex();
                            if (idUser!=-1) {
                                String message=userrolBL.asignarRol(idUser, idRol);
                                if (message==null) {
                                    JOptionPane.showMessageDialog(null, "Usuario ingresado correctamente\n"+
                                            "Rol asignado correctamente", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
                                    limpiarControles();
                                }else{
                                    JOptionPane.showMessageDialog(null, "Usuario ingresado correctamente\n"+
                                            "No se pudo asignar el rol al usuario", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                                    limpiarControles();
                                }
                            }else{
                                JOptionPane.showMessageDialog(null, "Usuario ingresado correctamente\n"+
                                            "No se pudo asignar el rol al usuario", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                                    limpiarControles();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, mensaje, "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                            limpiarControles();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "El usuario ya existe en el sistema", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                        limpiarControles();
                    }
                }
            }
        }
    }

    private boolean controlCedula() {
        if (txtCedula.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar una cédula", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
            txtCedula.requestFocus();
            return false;
        } else if (!validar.validarCedula(txtCedula.getText())) {
            JOptionPane.showMessageDialog(null, "La cédula ingresada es incorrecta", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
            limpiarCedula();
            return false;
        }
        return true;
    }

    private boolean controlMail() {
        if (!txtEmail.getText().isEmpty()) {
            if (!validar.validarMail(txtEmail.getText())) {
                JOptionPane.showMessageDialog(null, "El email ingresado es incorrecto", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                limpiarMail();
                return false;
            }
        }
        return true;
    }

    private boolean controlRol() {
        if (cbRol.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Debe escoger un rol de la lista", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private void confirmarCierre() {
        if (!txtCedula.getText().isEmpty() || !txtEmail.getText().isEmpty()) {
            int resultado = JOptionPane.showConfirmDialog(null, "Esta ventana contienen datos que se perderan. \n"
                    + "¿Desea cerrar esta ventana.?", "Seleccionar una opción", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            //res=0 si//res=1 =no
            if (resultado == 0) {//cierra la ventana
                this.dispose();
                menu.setEstadoVentana(Enumeraciones.EstadoVentanas.cerrado);
            }
        } else {
            this.dispose();//cierra la ventana
            menu.setEstadoVentana(Enumeraciones.EstadoVentanas.cerrado);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCedula = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        btnCrear = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cbRol = new javax.swing.JComboBox<>();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("NUEVO USUARIO");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/nuevo_usuario.png"))); // NOI18N
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

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Email");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Número de cédula");

        txtCedula.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCedulaKeyTyped(evt);
            }
        });

        txtEmail.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        btnCrear.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Guardar.png"))); // NOI18N
        btnCrear.setText("Crear");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
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

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("Rol");

        cbRol.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Selecciona un rol --", "Coordinador", "Digitador" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCrear)
                .addGap(27, 27, 27)
                .addComponent(btnCancelar)
                .addGap(30, 30, 30))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCedula)
                    .addComponent(txtEmail)
                    .addComponent(cbRol, 0, 153, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCrear)
                    .addComponent(btnCancelar))
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        confirmarCierre();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        // TODO add your handling code here:
        confirmarCierre();
    }//GEN-LAST:event_formInternalFrameClosing

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        // TODO add your handling code here:
        nuevoUsuario();
    }//GEN-LAST:event_btnCrearActionPerformed

    private void txtCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaKeyTyped
        // TODO add your handling code here:
        validar.soloNumeros(evt);
        validar.longitudCedula(evt, txtCedula.getText());
    }//GEN-LAST:event_txtCedulaKeyTyped

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
            java.util.logging.Logger.getLogger(ingresoUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ingresoUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ingresoUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ingresoUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ingresoUsuarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCrear;
    private javax.swing.JComboBox<String> cbRol;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtEmail;
    // End of variables declaration//GEN-END:variables
}
