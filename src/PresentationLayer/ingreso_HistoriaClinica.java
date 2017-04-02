package PresentationLayer;

import BusinessLayer.EnfermedadesBL;
import BusinessLayer.HistoriasBL;
import BusinessLayer.Validaciones;
import BusinessObjects.Enumeraciones;
import BusinessObjects.Historicos;
import com.toedter.calendar.JDateChooser;
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
    HistoriasBL historicoBL;
    Historicos historico;
    EnfermedadesBL enfermedadesBl;
    DefaultTableModel modelo;

    // </editor-fold>
    public ingreso_HistoriaClinica() {
        initComponents();
        validar = new Validaciones();
        enfermedadesBL = new EnfermedadesBL();
        historico = new Historicos();
        enfermedadesBl = new EnfermedadesBL();
        historicoBL = new HistoriasBL();

        setModeloTabla();
        BusquedaEnfermedad();

        ActivarDesactivarBtnGuardar();
        ActivarDesactivarBTNQuitar();

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

    private String getFecha(JDateChooser jd) {
        SimpleDateFormat Formato = new SimpleDateFormat("dd-MM-yyyy");
        if (jd.getDate() != null) {
            return Formato.format(jd.getDate());
        } else {
            return null;
        }
    }

    private Date convertirFechaStringDate(String fecha) {
        Date date = null;
        SimpleDateFormat cambiarfecha = new SimpleDateFormat("dd-MM-yyyy");//revisar       
        try {
            date = cambiarfecha.parse(fecha);
        } catch (ParseException ex) {

        }
        return date;
    }

    private void controlVentana() {
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

    private void limpiarContorles() {
        dcFecha.setDate(null);
        txtHistoriaClinica.setText(null);
        cbEstado.setSelectedIndex(0);
        cbCodigo.getEditor().setItem("");
        BusquedaEnfermedad();
        setModeloTabla();
        txtHistoriaClinica.requestFocus();

    }

    private void ContorlesAgregar() {
        txtHistoriaClinica.setEnabled(false);
        dcFecha.setEnabled(false);
        cbEstado.setEnabled(false);

    }

    private void ActivarControles() {
        txtHistoriaClinica.setEnabled(true);
        dcFecha.setEnabled(true);
        cbEstado.setEnabled(true);
        txtHistoriaClinica.requestFocus();   

    }

    private void limpiarContorlesNuevo() {

        cbCodigo.getEditor().setItem("");
        txtaDescripcion.setText(null);
        BusquedaEnfermedad();

    }

    private void ActivarDesactivarBTNQuitar() {
        if (tbHistorias.getSelectedRow() >= 0) {
            btnQuitar.setEnabled(true);
        } else {
            btnQuitar.setEnabled(false);
        }
    }

    private void ActivarDesactivarBtnGuardar() {
        if (tbHistorias.getRowCount() > 0) {
            btnAceptar.setEnabled(true);
        } else {
            btnAceptar.setEnabled(false);
        }
    }

    private void EliminarFila() {
        DefaultTableModel modeloEliminar = (DefaultTableModel) tbHistorias.getModel();
        int fila = tbHistorias.getSelectedRow();
        if (fila >= 0) {
            modeloEliminar.removeRow(fila);
            tbHistorias.setModel(modeloEliminar);
            ActivarDesactivarBTNQuitar();
            ActivarDesactivarBtnGuardar();
        }
    }

    private void setModeloTabla() {
        modelo = new DefaultTableModel();
        modelo.addColumn("N° Historia Clinica");
        modelo.addColumn("Código CIE10");
        modelo.addColumn("Fecha Ingreso");
        modelo.addColumn("Estado");
        tbHistorias.setModel(modelo);

    }

    private boolean controlIngreso() {
        if (txtHistoriaClinica.getText().length() == 6) { //Verificamos que el numero HC sea igual a 6 digitos    
            String mensaje = enfermedadesBl.validarEnfermedad(cbCodigo.getSelectedItem().toString());
            if (mensaje == null) { // verificamos si la enfermedad existe en la base de datos
                if (dcFecha.getDate() != null) { //Verificar que la fecha este ingresada
                    if (cbEstado.getSelectedIndex() != 0) { //Verificar que la fecha este ingresada
                        return true;
                    } else {
                        JOptionPane.showMessageDialog(null, "No se ha seleccionado un estado valido", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                        return false;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No se ha ingresado una fecha valida", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, mensaje, "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingresar el número de historia clinica (6 numeros) ", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    private boolean controlTablaRepetidos(String codigo) {
        for (int i = 0; i < tbHistorias.getModel().getRowCount(); i++) {
            String codigoTabla = (String) tbHistorias.getValueAt(i, 1);
            if (codigoTabla.equals(codigo)) {
                return false;
            }
        }

        return true;
    }

    private void BusquedaEnfermedad() {
        String textoBusqueda = (String) cbCodigo.getEditor().getItem();
        cbCodigo.setModel(enfermedadesBL.SelectCodigoCIE10(textoBusqueda, "ACTIVO"));
        cbCodigo.getEditor().setItem(textoBusqueda);

    }

    private void busquedaEnfermedadDescripcion() {
        String textoBusqueda = (String) cbCodigo.getEditor().getItem();
        txtaDescripcion.setText(enfermedadesBL.SelectDescripcionCIE10(textoBusqueda, "ACTIVO"));
    }

    private void HistoriaClinicaTabla() {
        if (controlIngreso()) {
            if (controlTablaRepetidos(cbCodigo.getSelectedItem().toString())) {
                String[] fila = new String[4];
                fila[0] = txtHistoriaClinica.getText();
                fila[1] = (String) cbCodigo.getSelectedItem();
                fila[2] = getFecha(dcFecha);
                fila[3] = cbEstado.getSelectedItem().toString();
                modelo.addRow(fila);
                tbHistorias.setModel(modelo);
                ActivarDesactivarBtnGuardar();
                //Limpiar
                limpiarContorlesNuevo();
                ContorlesAgregar();
            } else {
                JOptionPane.showMessageDialog(null, "Está historia ya fue agregada anteriormente", "controlTablaRepetidos", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void InsertarHistoriaClinica() {

//        Historicos[] historicoVec = new Historicos[tbHistorias.getModel().getRowCount()];
//        for (int i = 0; i < tbHistorias.getModel().getRowCount(); i++) {
//            historico = new Historicos();
//            historico.setNumeroHistoriaClinica(tbHistorias.getValueAt(i, 0).toString());
//            historico.setCodigoCie10(tbHistorias.getValueAt(i, 1).toString());
//            historico.setFechaIngreso(convertirFechaStringDate((String) (tbHistorias.getValueAt(i, 2))));
//            historico.setEstadoPaciente(tbHistorias.getValueAt(i, 3).toString());
//            historicoVec[i] = historico;
//        }
//
//        boolean res = historicoDAL.Insert(historicoVec); //Ejecutamos la inserción
        String mensaje = historicoBL.nuevaHistoria((DefaultTableModel) tbHistorias.getModel());
        if (mensaje == null) {
            JOptionPane.showMessageDialog(null, "Historia ingresada correctamente", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
            limpiarContorles();
            ActivarControles();
            ActivarDesactivarBtnGuardar();

        } else {
            JOptionPane.showMessageDialog(null, mensaje, "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
            limpiarContorles();
            ActivarControles();
            ActivarDesactivarBtnGuardar();
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
        dcFecha = new com.toedter.calendar.JDateChooser();
        btnQuitar = new javax.swing.JButton();

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
        jLabel1.setText("Código CIE-10");

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
        cbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Seleccione una opción--", "VIVO", "MUERTO+48", "MUERTO-48" }));

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
        tbHistorias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHistoriasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbHistorias);

        txtaDescripcion.setColumns(20);
        txtaDescripcion.setRows(5);
        txtaDescripcion.setEnabled(false);
        jScrollPane2.setViewportView(txtaDescripcion);

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Descripción de código");

        dcFecha.setDateFormatString("dd-MM-yyyy");

        btnQuitar.setText("Quitar");
        btnQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarActionPerformed(evt);
            }
        });

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
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbCodigo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbEstado, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtHistoriaClinica)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                                    .addComponent(dcFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnAgregar)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnQuitar)
                .addContainerGap())
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(btnAgregar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnQuitar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
        //TODO add your handling code here:
        
        limpiarContorles();
        ActivarDesactivarBtnGuardar();
        ActivarControles();
   

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
        validar.soloNumeros(evt);
        validar.controlLongitudMaximoSeis(evt, txtHistoriaClinica.getText());

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

    private void tbHistoriasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHistoriasMouseClicked
        // TODO add your handling code here:
        ActivarDesactivarBTNQuitar();
    }//GEN-LAST:event_tbHistoriasMouseClicked

    private void btnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarActionPerformed
        // TODO add your handling code here:
        EliminarFila();
    }//GEN-LAST:event_btnQuitarActionPerformed

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
            java.util.logging.Logger.getLogger(ingreso_HistoriaClinica.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ingreso_HistoriaClinica.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ingreso_HistoriaClinica.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ingreso_HistoriaClinica.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JButton btnQuitar;
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
