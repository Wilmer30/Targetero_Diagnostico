package PresentationLayer;

import BusinessLayer.EnfermedadesBL;
import BusinessLayer.Validaciones;
import BusinessObjects.Enumeraciones;
import BusinessObjects.Historicos;
import DataAccessLayer.EnfermedadesDAL;
import DataAccessLayer.HistoricosDAL;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Wilmer Oñate
 */
public class ingreso_HistoriaClinica extends javax.swing.JInternalFrame {

    // <editor-fold defaultstate="collapsed" desc="Datos">
    EnfermedadesBL enfermedadesBL;
    Validaciones validar;
    HistoricosDAL historicoDAL;
    Historicos historico;
    EnfermedadesDAL enfermedades;
    DefaultTableModel modelo;

    // </editor-fold>
    public ingreso_HistoriaClinica() {
        initComponents();
        validar = new Validaciones();
        enfermedadesBL = new EnfermedadesBL();
        historicoDAL = new HistoricosDAL();
        historico = new Historicos();
        enfermedades = new EnfermedadesDAL();
        setModeloTabla();

        btnAceptar.setEnabled(false);

        tbHistorias.getTableHeader().setReorderingAllowed(false);
        cbCodigo.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() { //Obtenemos el editor del cbCodigo
            public void keyReleased(KeyEvent e) {
                if ((e.getKeyCode() >= 48 && e.getKeyCode() <= 57) || (e.getKeyCode() >= 65 && e.getKeyCode() <= 90) || e.getKeyCode() == 8 || e.getKeyCode() == 127) { // Sejecunta cuando es una letra un numero delete, suprimir
                    //Comprobamos si es una letra
                    if (Character.isLetter(e.getKeyChar())) {
                        //Obtenemos lo ingresado y lo convertimos a mayuscula
                        String textoBusqueda = ((String) cbCodigo.getEditor().getItem()).toUpperCase();
                        cbCodigo.getEditor().setItem(textoBusqueda); //Agremamos convertido a mayuscula en el cbCodigo
                    }
                    BusquedaEnfermedad(); //Ejeculamos la consulta y cargamos al cbCodigo
                    busquedaEnfermedadDescripcion();
                } else {
                    //e.consume();
                }
            } //Revisar
        });

    }

    private String fechaHora(Date fecha) {

        SimpleDateFormat cambiarfecha = new SimpleDateFormat("YYYY-MM-dd");//revisar        
        return String.valueOf(cambiarfecha.format(fecha));

    }

    private Date convertirFechaStringDate(String fecha) {
        Date date = null;
        SimpleDateFormat cambiarfecha = new SimpleDateFormat("YYYY-MM-dd");//revisar       
        try {
            date = cambiarfecha.parse(fecha);
        } catch (ParseException ex) {

        }
        return date;
    }

    public void controlVentana() {
        if (!txtHistoriaClinica.getText().equals("") || dcFecha.getDate() != null) {
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

    public void limpiarContorles() {
        dcFecha.setDate(null);
        txtHistoriaClinica.setText(null);
        cbEstado.setSelectedItem(1);
        cbCodigo.getEditor().setItem("");
        BusquedaEnfermedad();
        setModeloTabla();

    }

    public void limpiarContorlesNuevo() {

        cbCodigo.getEditor().setItem("");
        txtaDescripcion.setText(null);
        BusquedaEnfermedad();

    }

    public void BusquedaEnfermedad() {
        String textoBusqueda = (String) cbCodigo.getEditor().getItem();
        cbCodigo.setModel(enfermedades.SelelctPrimaryKeyActivas(textoBusqueda));
        cbCodigo.getEditor().setItem(textoBusqueda);

    }

    public void busquedaEnfermedadDescripcion() {
        String textoBusqueda = (String) cbCodigo.getEditor().getItem();
        txtaDescripcion.setText(enfermedades.SelelctPrimaryDesciprcion(textoBusqueda));

    }

    public void controlLongitud(java.awt.event.KeyEvent evt) {
        if (!(txtHistoriaClinica.getText().length() < 6)) {
            evt.consume();
        }

    }

    public void setModeloTabla() {
        modelo = new DefaultTableModel();
        modelo.addColumn("N° Historia Clinica");
        modelo.addColumn("Código CIE10");
        modelo.addColumn("Fecha Ingreso");
        modelo.addColumn("Estado");
        tbHistorias.setModel(modelo);

    }

    public boolean controlIngreso() {
        if (txtHistoriaClinica.getText().length() == 6) { //Verificamos que el numero HC sea igual a 6 digitos
            //Revisar error al iniciar y guardar no controla
            if (enfermedadesBL.VerificarEnfermedad(cbCodigo.getSelectedItem().toString())) { // verificamos si la enfermedad existe en la base de datos
                if (dcFecha.getDate() != null) { //Verificar que la fecha este ingresada
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "No se ha ingresado una fecha valida", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingresar un codigo de enfermedad valida", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }

        } else {
            JOptionPane.showMessageDialog(null, "Ingresar el número de historia clinica (6 numeros) ", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
    }

    public boolean controlTablaRepetidos(String codigo) {
        for (int i = 0; i < tbHistorias.getModel().getRowCount(); i++) {
            String codigoTabla = (String) tbHistorias.getValueAt(i, 1);
            if (codigoTabla.equals(codigo)) {
                return false;
            }
        }

        return true;
    }

    public void HistoriaClinicaTabla() {
        if (controlIngreso()) {
            if (controlTablaRepetidos(cbCodigo.getSelectedItem().toString())) {
                String[] fila = new String[4];
                fila[0] = txtHistoriaClinica.getText();
                fila[1] = (String) cbCodigo.getSelectedItem();
                fila[2] = fechaHora(dcFecha.getDate());  //Revisar no toma fechha correcta
                fila[3] = cbEstado.getSelectedItem().toString();
                modelo.addRow(fila);
                tbHistorias.setModel(modelo);
                btnAceptar.setEnabled(true);
                //Limpiar
                limpiarContorlesNuevo();
            } else {
                JOptionPane.showMessageDialog(null, "Está historia ya fue agregada anteriormente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }

    public void InsertarHistoriaClinica() {
        for (int i = 0; i < tbHistorias.getModel().getRowCount(); i++) {
            historico.setNumeroHistoriaClinica(tbHistorias.getValueAt(i, 0).toString());
            historico.setCodigoCie10(tbHistorias.getValueAt(i, 1).toString());
            historico.setFechaIngreso(convertirFechaStringDate((String) (tbHistorias.getValueAt(i, 2))));
            historico.setEstadoPaciente(tbHistorias.getValueAt(i, 3).toString());

            boolean res = historicoDAL.Insert(historico); //Ejecutamos la inserción
            if (res) {
                JOptionPane.showMessageDialog(null, "Historia ingresada correctamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                limpiarContorles();
                btnAceptar.setEnabled(false);
                txtHistoriaClinica.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Historia no ingresada", "Mensaje", JOptionPane.ERROR);
            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButton1 = new javax.swing.JRadioButton();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtHistoriaClinica = new javax.swing.JTextField();
        cbCodigo = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cbEstado = new javax.swing.JComboBox<>();
        dcFecha = new com.toedter.calendar.JDateChooser();
        btnAgregar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbHistorias = tbHistorias = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        jScrollPane2 = new javax.swing.JScrollPane();
        txtaDescripcion = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        jRadioButton1.setText("jRadioButton1");

        jPasswordField1.setText("jPasswordField1");

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("INGRESO DE HISRIA CLINICA");
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

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("Código");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Número de historia clinica");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Fecha de ingreso");

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

        txtHistoriaClinica.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtHistoriaClinica.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtHistoriaClinicaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtHistoriaClinicaKeyTyped(evt);
            }
        });

        cbCodigo.setEditable(true);
        cbCodigo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbCodigo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbCodigoMouseClicked(evt);
            }
        });
        cbCodigo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbCodigoItemStateChanged(evt);
            }
        });
        cbCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbCodigoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cbCodigoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cbCodigoKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Estado del paciente");

        cbEstado.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "VIVO", "MUERTO+48", "MUERTO-48" }));

        dcFecha.setDateFormatString("YYYY-MM-dd");

        btnAgregar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        tbHistorias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1"
            }
        ));
        jScrollPane1.setViewportView(tbHistorias);

        txtaDescripcion.setColumns(20);
        txtaDescripcion.setRows(5);
        txtaDescripcion.setEnabled(false);
        jScrollPane2.setViewportView(txtaDescripcion);

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Descripción de código");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnAceptar)
                                .addGap(41, 41, 41)
                                .addComponent(btnCancelar))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbCodigo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbEstado, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dcFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtHistoriaClinica)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnAgregar)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHistoriaClinica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(dcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel5))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(btnAgregar)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        limpiarContorles();
        btnAceptar.setEnabled(false);
        txtHistoriaClinica.requestFocus();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        // TODO add your handling code here:
        controlVentana();
    }//GEN-LAST:event_formInternalFrameClosing

    private void cbCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbCodigoKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_cbCodigoKeyPressed

    private void cbCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbCodigoKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_cbCodigoKeyReleased

    private void cbCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbCodigoKeyTyped


    }//GEN-LAST:event_cbCodigoKeyTyped

    private void txtHistoriaClinicaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHistoriaClinicaKeyTyped
        controlLongitud(evt); // 6 digitos
        validar.soloNumeros(evt);

    }//GEN-LAST:event_txtHistoriaClinicaKeyTyped

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        InsertarHistoriaClinica();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void txtHistoriaClinicaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHistoriaClinicaKeyPressed

    }//GEN-LAST:event_txtHistoriaClinicaKeyPressed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:    
        HistoriaClinicaTabla();

    }//GEN-LAST:event_btnAgregarActionPerformed

    private void cbCodigoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbCodigoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCodigoMouseClicked

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
            java.util.logging.Logger.getLogger(ingreso_HistoriaClinica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ingreso_HistoriaClinica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ingreso_HistoriaClinica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ingreso_HistoriaClinica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ingreso_HistoriaClinica().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JComboBox<String> cbCodigo;
    private javax.swing.JComboBox<String> cbEstado;
    private com.toedter.calendar.JDateChooser dcFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tbHistorias;
    private javax.swing.JTextField txtHistoriaClinica;
    private javax.swing.JTextArea txtaDescripcion;
    // End of variables declaration//GEN-END:variables
}
