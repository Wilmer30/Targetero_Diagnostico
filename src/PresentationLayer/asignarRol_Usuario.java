/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import BusinessLayer.RolesBL;
import BusinessLayer.UsuariosBL;
import BusinessLayer.Validaciones;
import BusinessObjects.Enumeraciones;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import BusinessLayer.UsuariosRolesBL;

/**
 *
 * @author Wilmer Oñate
 */
public class asignarRol_Usuario extends javax.swing.JInternalFrame {

    // <editor-fold defaultstate="collapsed" desc="Datos">
    private UsuariosBL usuarioBL;
    private Validaciones validar;
    private RolesBL rolBL;
    private UsuariosRolesBL userrolBL;
    // </editor-fold>

    /**
     * Creates new form asignarRol_Usuario
     */
    public asignarRol_Usuario() {
        usuarioBL = new UsuariosBL();
        validar = new Validaciones();
        rolBL = new RolesBL();
        initComponents();
        cargarDatos();
        tblUsuarios.getTableHeader().setReorderingAllowed(false);
    }

    private void cargarTabla() {
        DefaultTableModel modelo = usuarioBL.usuarios();
        if (modelo != null) {
            tblUsuarios.setModel(modelo);
        } else {
            JOptionPane.showMessageDialog(null, "No se ha podido recuperar los usuarios", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void cargarTabla(String usuario) {
        DefaultTableModel modelo = usuarioBL.busquedaInteligenteUsuarios(usuario);
        if (modelo != null) {
            tblUsuarios.setModel(modelo);
        } else {
            JOptionPane.showMessageDialog(null, "No se ha podido recuperar los usuarios", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void cargarDatos() {
        tblUsuarios.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (tblUsuarios.getSelectedRow() != -1) {
                    dgBuscarUsuario.dispose();
                    int fila = tblUsuarios.getSelectedRow();
                    txtCedulaUsuario.setText(String.valueOf(tblUsuarios.getValueAt(fila, 0)));
                    txtNombreUsuario.setText(String.valueOf(tblUsuarios.getValueAt(fila, 0)));
                    String rol=rolBL.recuperarRol(String.valueOf(tblUsuarios.getValueAt(fila, 0)));
                    if (rol!=null) {
                        txtRol.setText(rol);
                    }else{
                        JOptionPane.showMessageDialog(null, "No se ha podido cargar el rol para el usuario\n"
                    + "La ventana se va a cerrar", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                        limpiarControles();
                        dispose();
                    }                    
                }
            }
        });
    }

    private void confirmarCierre() {
        if (!txtCedulaUsuario.getText().isEmpty()) {
            int resultado = JOptionPane.showConfirmDialog(null, "Esta ventana contiene datos que se perderan. \n"
                    + "¿Desea cerrar esta ventana?", "Seleccionar una opción", JOptionPane.YES_NO_OPTION,
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

    private void abrirBusqueda() {
        dgBuscarUsuario.setIconImage(new ImageIcon(getClass().getResource("/Imagenes/buscar_usuario.png")).getImage());
        dgBuscarUsuario.setBounds(0, 0, 285, 455);
        dgBuscarUsuario.setLocationRelativeTo(null);
        dgBuscarUsuario.setVisible(true);
        txtBusquedaCedula.requestFocus();
        cargarTabla();
    }

    private boolean controlRol() {
        if (cbRol.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Debe escoger un rol de la lista", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (cbRol.getSelectedItem().equals(txtRol.getText())) {
            JOptionPane.showMessageDialog(null, "Debe escoger un rol diferente del que actualmente se encuentra asignado", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private boolean controlCedula() {
        if (txtCedulaUsuario.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe buscar un usuario para asignar un rol", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private void cambiarRol() {
        if (controlCedula()) {
            if (controlRol()) {
                int idRol = cbRol.getSelectedIndex();
                int idUser = usuarioBL.idUsuario(txtCedulaUsuario.getText());
                if (idUser != -1) {
                    String message = userrolBL.cambiarRol(idUser, idRol);
                    if (message == null) {
                        JOptionPane.showMessageDialog(null, "Rol asignado correctamente", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
                        limpiarControles();
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo asignar el rol al usuario", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                        limpiarControles();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo encontrar al usuario\n"
                            + "No se pudo asignar el rol al usuario", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                    limpiarControles();
                }
            }
        }
    }

    private void limpiarControles() {
        txtCedulaUsuario.setText("");
        txtNombreUsuario.setText("");
        txtRol.setText("");
        cbRol.setSelectedIndex(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dgBuscarUsuario = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblUsuarios = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txtBusquedaCedula = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnBuscarUsuario = new javax.swing.JButton();
        txtCedulaUsuario = new javax.swing.JTextField();
        txtNombreUsuario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtRol = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        cbRol = new javax.swing.JComboBox<>();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        dgBuscarUsuario.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        dgBuscarUsuario.setTitle("BUSCAR USUARIO");
        dgBuscarUsuario.setModal(true);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblUsuarios.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tblUsuarios);

        jLabel8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel8.setText("Número de cédula");

        txtBusquedaCedula.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtBusquedaCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusquedaCedulaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBusquedaCedulaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(txtBusquedaCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtBusquedaCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout dgBuscarUsuarioLayout = new javax.swing.GroupLayout(dgBuscarUsuario.getContentPane());
        dgBuscarUsuario.getContentPane().setLayout(dgBuscarUsuarioLayout);
        dgBuscarUsuarioLayout.setHorizontalGroup(
            dgBuscarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dgBuscarUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dgBuscarUsuarioLayout.setVerticalGroup(
            dgBuscarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dgBuscarUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("ASIGNACIÓN DE ROLES");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/rol.png"))); // NOI18N
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

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setToolTipText("");

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("Número de cédula");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Nombre de usuario");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("BUSCAR USUARIO");

        btnBuscarUsuario.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnBuscarUsuario.setText("Buscar");
        btnBuscarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarUsuarioActionPerformed(evt);
            }
        });

        txtCedulaUsuario.setEditable(false);
        txtCedulaUsuario.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txtNombreUsuario.setEditable(false);
        txtNombreUsuario.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Rol Actual");

        txtRol.setEditable(false);
        txtRol.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addGap(18, 18, 18)
                            .addComponent(btnBuscarUsuario))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(18, 18, 18))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(22, 22, 22)))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtCedulaUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                                .addComponent(txtNombreUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                                .addComponent(txtRol))))
                    .addComponent(jLabel3))
                .addContainerGap(86, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(btnBuscarUsuario))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCedulaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Nombre del rol");

        cbRol.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Selecciona un rol --", "Coordinador", "Digitador" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(41, 41, 41)
                .addComponent(cbRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(btnAceptar)
                .addGap(26, 26, 26)
                .addComponent(btnCancelar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar))
                .addContainerGap())
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

    private void btnBuscarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarUsuarioActionPerformed
        // TODO add your handling code here:
        abrirBusqueda();
    }//GEN-LAST:event_btnBuscarUsuarioActionPerformed

    private void txtBusquedaCedulaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaCedulaKeyReleased
        // TODO add your handling code here:
        cargarTabla(txtBusquedaCedula.getText());
    }//GEN-LAST:event_txtBusquedaCedulaKeyReleased

    private void txtBusquedaCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaCedulaKeyTyped
        // TODO add your handling code here:
        validar.soloNumeros(evt);
        validar.longitudCedula(evt, txtBusquedaCedula.getText());
    }//GEN-LAST:event_txtBusquedaCedulaKeyTyped

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        cambiarRol();
    }//GEN-LAST:event_btnAceptarActionPerformed

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
            java.util.logging.Logger.getLogger(asignarRol_Usuario.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(asignarRol_Usuario.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(asignarRol_Usuario.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(asignarRol_Usuario.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new asignarRol_Usuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnBuscarUsuario;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JComboBox<String> cbRol;
    private javax.swing.JDialog dgBuscarUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblUsuarios;
    private javax.swing.JTextField txtBusquedaCedula;
    private javax.swing.JTextField txtCedulaUsuario;
    private javax.swing.JTextField txtNombreUsuario;
    private javax.swing.JTextField txtRol;
    // End of variables declaration//GEN-END:variables
}
