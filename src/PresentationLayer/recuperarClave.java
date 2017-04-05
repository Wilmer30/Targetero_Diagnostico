/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import BusinessLayer.UsuariosBL;
import BusinessLayer.Validaciones;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Erick
 */
public class recuperarClave extends javax.swing.JFrame {

    // <editor-fold defaultstate="collapsed" desc="Datos">
    private UsuariosBL usuarioBL;
    private Validaciones validar;
    // </editor-fold>

    /**
     * Creates new form recuperarClave
     */
    public recuperarClave() {
        validar = new Validaciones();
        usuarioBL = new UsuariosBL();
        initComponents();
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/logo.png")).getImage());
        txtUsuario.requestFocus();
        btnAceptar.setEnabled(false);        
    }

    private void limpiarControles() {
        txtUsuario.setText("");
        txtPregunta.setText("");
        txtRespuesta.setText("");
    }

    private boolean controlUsuario() {
        if (txtUsuario.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un usuario", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
            txtUsuario.requestFocus();
            return false;
        }
        return true;
    }

    private void setPregunta() {
        if (controlUsuario()) {
            String mensaje = usuarioBL.seguridad(txtUsuario.getText());
            if (mensaje == null) {
                String pregunta = usuarioBL.pregunta(txtUsuario.getText());
                if (pregunta != null) {
                    txtPregunta.setText(pregunta);
                    btnAceptar.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(null, "El usuario no puede recuperar la contraseña", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                    limpiarControles();
                    txtUsuario.requestFocus();
                }
            } else {                
                JOptionPane.showMessageDialog(null, mensaje, "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                limpiarControles();                
                txtUsuario.requestFocus();
            }
        }
    }

    private void reiniciarClave() {
        if (controlRespuesta()) {
            String mensaje = usuarioBL.validarRecuperacion(txtUsuario.getText(), String.valueOf(txtRespuesta.getPassword()));
            if (mensaje == null) {
                String cambio = usuarioBL.cambiarClave(txtUsuario.getText(), txtUsuario.getText());
                if (cambio == null) {
                    JOptionPane.showMessageDialog(null, "Su nueva contraseña es su usuario\n"
                            + "La aplicación se va a cerrar", "CONTRASEÑA RECUPERADA CON ÉXITO", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                } else {
                    JOptionPane.showMessageDialog(null, cambio, "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                    limpiarControles();
                    txtUsuario.requestFocus();
                    btnAceptar.setEnabled(false);
                }
            } else {
                JOptionPane.showMessageDialog(null, mensaje, "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                limpiarControles();
                txtUsuario.requestFocus();
                btnAceptar.setEnabled(false);
            }
        }
    }

    private boolean controlRespuesta() {
        if (String.valueOf(txtRespuesta.getPassword()).isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar una respuesta", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private void mostrar() {       
        txtRespuesta.setVisible(false);
        txtMostrarRespuesta.setVisible(true);        
        txtMostrarRespuesta.setText(String.valueOf(txtRespuesta.getPassword()));        
    }

    private void ocultar() {
        txtMostrarRespuesta.setText("");
        txtMostrarRespuesta.setVisible(false);
        txtRespuesta.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtPregunta = new javax.swing.JTextField();
        txtRespuesta = new javax.swing.JPasswordField();
        txtMostrarRespuesta = new javax.swing.JTextField();
        btnMostrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("RECUPERAR CONTRASEÑA");
        setResizable(false);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/recuperar_clave.png"))); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setFocusable(false);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("Usuario");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Pregunta de seguridad");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Respuesta");

        txtUsuario.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyTyped(evt);
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

        txtPregunta.setEditable(false);
        txtPregunta.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txtRespuesta.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtRespuesta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtRespuestaFocusGained(evt);
            }
        });

        txtMostrarRespuesta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtMostrarRespuestaFocusGained(evt);
            }
        });

        btnMostrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/mostrar.png"))); // NOI18N
        btnMostrar.setToolTipText("Mostrar respuesta");
        btnMostrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnMostrarMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnMostrarMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtUsuario)
                    .addComponent(txtPregunta)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtMostrarRespuesta, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtRespuesta))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnMostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(83, Short.MAX_VALUE)
                .addComponent(btnAceptar)
                .addGap(31, 31, 31)
                .addComponent(btnCancelar)
                .addGap(65, 65, 65))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtPregunta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtMostrarRespuesta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRespuesta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnMostrar))
                .addGap(18, 18, 18)
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
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        reiniciarClave();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyTyped
        // TODO add your handling code here:
        validar.soloNumeros(evt);
        validar.longitudCedula(evt, txtUsuario.getText());
    }//GEN-LAST:event_txtUsuarioKeyTyped

    private void btnMostrarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMostrarMouseReleased
        // TODO add your handling code here:
        ocultar();
    }//GEN-LAST:event_btnMostrarMouseReleased

    private void txtRespuestaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtRespuestaFocusGained
        // TODO add your handling code here:
        if (txtPregunta.getText().isEmpty()) {
            setPregunta();
        }        
    }//GEN-LAST:event_txtRespuestaFocusGained

    private void btnMostrarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMostrarMousePressed
        // TODO add your handling code here:        
        mostrar();
    }//GEN-LAST:event_btnMostrarMousePressed

    private void txtMostrarRespuestaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMostrarRespuestaFocusGained
        // TODO add your handling code here:        
        if (txtUsuario.getText().isEmpty()) {
            txtUsuario.requestFocus();
            JOptionPane.showMessageDialog(null, "Debe ingresar un usuario", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
        }else{
            ocultar();
            txtRespuesta.requestFocus();
        }        
    }//GEN-LAST:event_txtMostrarRespuestaFocusGained

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
            java.util.logging.Logger.getLogger(recuperarClave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(recuperarClave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(recuperarClave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(recuperarClave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new recuperarClave().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnMostrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtMostrarRespuesta;
    private javax.swing.JTextField txtPregunta;
    private javax.swing.JPasswordField txtRespuesta;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
