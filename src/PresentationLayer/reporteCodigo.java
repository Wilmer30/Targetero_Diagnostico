/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import BusinessLayer.EnfermedadesBL;
import BusinessLayer.ReportesBL;
import BusinessLayer.UsuariosBL;
import BusinessLayer.Validaciones;
import BusinessObjects.ConectarBaseDatos;
import BusinessObjects.Enumeraciones;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JRViewer;

/**
 *
 * @author Erick
 */
public class reporteCodigo extends javax.swing.JInternalFrame {

    // <editor-fold defaultstate="collapsed" desc="Datos">
    private EnfermedadesBL enfermedadesBL;
    private Validaciones validar;
    private ReportesBL reporteBL;
    private UsuariosBL usuarioBL;
    // </editor-fold>
    
    /**
     * Creates new form reporteCodigo
     */
    public reporteCodigo() {
        initComponents();
        enfermedadesBL = new EnfermedadesBL();
        validar = new Validaciones();
        reporteBL=new ReportesBL();
        usuarioBL=new UsuariosBL();
        BusquedaEnfermedad();
        
        cbCodigo.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() { //Obtenemos el editor del cbCodigo
            @Override
            public void keyReleased(KeyEvent e) {
                BusquedaEnfermedad(); //Ejeculamos la consulta y cargamos al cbCodigo
                busquedaEnfermedadDescripcion();
                if (cbCodigo.getItemCount() > 0) {
                    cbCodigo.showPopup();
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
                validar.convertirMayusculas(e);
                String textoBusqueda = (String) cbCodigo.getEditor().getItem();
                validar.longitudMaximoCuatro(e, textoBusqueda);
                validar.soloNumerosLetras(e);
            }
        });
    }

    private void BusquedaEnfermedad() {
        String textoBusqueda = (String) cbCodigo.getEditor().getItem();
        cbCodigo.setModel(enfermedadesBL.SelectCodigoCIE10(textoBusqueda, "ACTIVO"));
        cbCodigo.getEditor().setItem(textoBusqueda);
    }

    private void busquedaEnfermedadDescripcion() {
        String textoBusqueda = (String) cbCodigo.getEditor().getItem();
         txtaDescripcion.setLineWrap(true);
        txtaDescripcion.setText(enfermedadesBL.SelectDescripcionCIE10(textoBusqueda, "ACTIVO"));
        
    }
    
    private void confirmarCierre() {
        if (!txtaDescripcion.getText().isEmpty()) {
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

    private void limpiarControles() {
        cbCodigo.getEditor().setItem("");
        txtaDescripcion.setText("");
        cbCodigo.requestFocus();
    }

    private boolean controlCodigo() {
        if (txtaDescripcion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe escoger un código CIE-10", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
            cbCodigo.requestFocus();
            return false;
        }
        return true;
    }    
    
    private void reporte() {
        int numero=reporteBL.numeroReporte();
        ConectarBaseDatos connect = new ConectarBaseDatos();
        Connection connection = connect.conectar();
        if (connection != null) {
            Map parametro = new HashMap();
            parametro.put("codigo", cbCodigo.getSelectedItem().toString().toUpperCase());
            parametro.put("descripcion", txtaDescripcion.getText());
            parametro.put("numero",numero);
            parametro.put("foto",getClass().getResource("/Imagenes/logo.png"));
            try {
                JasperReport reporte = JasperCompileManager.compileReport("Reportes/reportePorCodigo.jrxml");
                JasperPrint imprimir = JasperFillManager.fillReport(reporte, parametro, connection);
                JRViewer ver = new JRViewer(imprimir);
                JInternalFrame visualizar = new JInternalFrame("Reporte por Código CIE-10");
                visualizar.getContentPane().add(ver);
                visualizar.setClosable(true);
                menu.agregarReporte(visualizar);
                try {
                    visualizar.setMaximum(true);
                    reporteBL.nuevoReporte("Reporte por Código CIE-10");
                    limpiarControles();
                    this.dispose();
                    menu.setEstadoVentana(Enumeraciones.EstadoVentanas.cerrado);
                    usuarioBL.ultimaActividad(menu.usuario());
                } catch (PropertyVetoException e) {
                    JOptionPane.showMessageDialog(null, "No se pudo cargar la ventana del reporte", "ERROR",
                            JOptionPane.WARNING_MESSAGE);
                }
                visualizar.toFront();
                visualizar.show();
            } catch (JRException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage() , "ERROR",
                        JOptionPane.WARNING_MESSAGE);                
            }
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
        jLabel1 = new javax.swing.JLabel();
        cbCodigo = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtaDescripcion = new javax.swing.JTextArea();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("REPORTE POR CÓDIGO CIE-10");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/reporte.png"))); // NOI18N
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

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("Código CIE 10");

        cbCodigo.setEditable(true);
        cbCodigo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbCodigo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbCodigoItemStateChanged(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Descripción de código");

        txtaDescripcion.setEditable(false);
        txtaDescripcion.setColumns(20);
        txtaDescripcion.setRows(5);
        jScrollPane2.setViewportView(txtaDescripcion);

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
                    .addComponent(jLabel5)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cbCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(100, Short.MAX_VALUE)
                .addComponent(btnAceptar)
                .addGap(41, 41, 41)
                .addComponent(btnCancelar)
                .addGap(95, 95, 95))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 53, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        confirmarCierre();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        // TODO add your handling code here:
        confirmarCierre();
    }//GEN-LAST:event_formInternalFrameClosing

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        if (controlCodigo()) {            
            reporte();
        }        
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void cbCodigoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbCodigoItemStateChanged
        // TODO add your handling code here:
        busquedaEnfermedadDescripcion();
    }//GEN-LAST:event_cbCodigoItemStateChanged

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
            java.util.logging.Logger.getLogger(reporteCodigo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(reporteCodigo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(reporteCodigo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(reporteCodigo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new reporteCodigo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JComboBox<String> cbCodigo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea txtaDescripcion;
    // End of variables declaration//GEN-END:variables
}
