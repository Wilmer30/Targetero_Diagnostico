/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Erick
 */
public class Principal extends javax.swing.JFrame {

    //<editor-fold defaultstate="collapsed" desc="Datos">
    Object sumando1[];
    Object sumando2[];
    Object resultado[];
    ArrayList<Object> letrasSinRepeticion;
    ArrayList<Object> cromosomasEvaluados;
    ArrayList<Object> cromosomasMejores;
    ArrayList<ArrayList<Object>> poblacion;
    //</editor-fold>

    /**
     * Creates new form Principal
     */
    public Principal() {
        initComponents();
        setLocationRelativeTo(null);
        setExtendedState(MAXIMIZED_BOTH);
        inicio();
    }

    private void inicio() {
        pnlDatosPrevios.setVisible(false);
        pnlResultados.setVisible(false);
        btnCalcular.setEnabled(false);
        txtSumando1.requestFocus();
    }

    private void mostrarPaneles() {
        pnlDatosPrevios.setVisible(true);
        pnlResultados.setVisible(true);
        lblMensaje.setVisible(false);
        tblResultado.setVisible(false);
        lstResultado.setVisible(false);
    }

    private void mostrarControles() {
        lblMensaje.setVisible(true);
        tblResultado.setVisible(true);
        lstResultado.setVisible(true);
    }

    private void mostrarMensaje() {
        lblMensaje.setVisible(true);
    }

    private void mostrarCalcular() {
        if (!txtSumando1.getText().isEmpty() && !txtSumando2.getText().isEmpty() && !txtResultado.getText().isEmpty()) {
            btnCalcular.setEnabled(true);
        } else {
            btnCalcular.setEnabled(false);
        }
    }

    private void soloLetras(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();
        if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z')) {
            evt.consume();
        }
    }

    private void armarVectoresLetras() {
        String operador1, operador2, suma;
        operador1 = txtSumando1.getText();
        operador2 = txtSumando2.getText();
        suma = txtResultado.getText();

        sumando1 = new Object[operador1.length()];
        sumando2 = new Object[operador2.length()];
        resultado = new Object[suma.length()];

        sumando1 = separarLetras(operador1, sumando1);
        sumando2 = separarLetras(operador2, sumando2);
        resultado = separarLetras(suma, resultado);
    }

    private Object[] separarLetras(String cadena, Object vector[]) {
        for (int i = 0; i < cadena.length(); i++) {
            vector[i] = cadena.charAt(i);
        }
        return vector;
    }

    private void mostrarDatosPrevios() {
        armarVectoresLetras();
        DefaultTableModel modelo = new DefaultTableModel(null, armarTitulos());
        tblDatos.setModel(modelo);
        crearFila(modelo);
        setearFila();
    }

    private String[] armarTitulos() {
        String titulos[];
        String operador1, operador2, suma;
        operador1 = txtSumando1.getText();
        operador2 = txtSumando2.getText();
        suma = txtResultado.getText();

        if (operador1.length() > operador2.length()) {
            if (operador1.length() > suma.length()) {
                titulos = new String[operador1.length()];
            } else {
                titulos = new String[suma.length()];
            }
        } else if (operador2.length() > suma.length()) {
            titulos = new String[operador2.length()];
        } else {
            titulos = new String[suma.length()];
        }
        return titulos;
    }

    private void crearFila(DefaultTableModel modeloSetear) {
        String fila[] = new String[tblDatos.getColumnCount()];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < fila.length; j++) {
                fila[j] = "";
            }
            modeloSetear.addRow(fila);
        }
    }

    private void setearFila() {
        int i = sumando1.length - 1;
        int j = tblDatos.getColumnCount() - 1;
        while (i >= 0) {
            tblDatos.setValueAt(sumando1[i], 0, j);
            i--;
            j--;
        }
        i = sumando2.length - 1;
        j = tblDatos.getColumnCount() - 1;
        while (i >= 0) {
            tblDatos.setValueAt(sumando2[i], 1, j);
            i--;
            j--;
        }
        i = resultado.length - 1;
        j = tblDatos.getColumnCount() - 1;
        while (i >= 0) {
            tblDatos.setValueAt(resultado[i], 2, j);
            i--;
            j--;
        }
    }

    private void obtenerLetras() {
        letrasSinRepeticion = new ArrayList<>();
        for (Object sumando11 : sumando1) {
            if (!letrasSinRepeticion.contains(sumando11)) {
                letrasSinRepeticion.add(sumando11);
            }
        }
        for (Object sumando21 : sumando2) {
            if (!letrasSinRepeticion.contains(sumando21)) {
                letrasSinRepeticion.add(sumando21);
            }
        }
        for (Object resultado1 : resultado) {
            if (!letrasSinRepeticion.contains(resultado1)) {
                letrasSinRepeticion.add(resultado1);
            }
        }
    }

    private ArrayList crearCromosoma() {
        int limite = letrasSinRepeticion.size();
        int aleatorio;
        ArrayList<Object> cromosoma = new ArrayList<>();
        int i = 0;
        while (i < limite) {
            aleatorio = (int) (Math.random() * 10);
            if (!cromosoma.contains(aleatorio)) {
                cromosoma.add(aleatorio);
                i++;
            }
        }
        return cromosoma;
    }

    private int numeroCombinaciones() {
        int f = 1,n=1,numero;
        
        for (int i = 1; i <= 10; i++) {
            f = f * i;
        }
        
        numero=10-letrasSinRepeticion.size();
        for (int i = 1; i <= numero; i++) {
            n = n * i;
        }
        return f/n;
    }

    private int calcularAptitud(ArrayList cromosomaEvaluar) {        
        String operador1="",operador2="",suma="",s;
        int s1,s2,sumaOriginal,m,n,ap1=0,ap2=0,c;
        
        for (Object sumando11 : sumando1) {
            int indice = letrasSinRepeticion.indexOf(sumando11);
            operador1+=cromosomaEvaluar.get(indice);
        }
        
        for (Object sumando21 : sumando2) {
            int indice = letrasSinRepeticion.indexOf(sumando21);
            operador2+=cromosomaEvaluar.get(indice);
        }
        
        for (Object resultado1 : resultado) {
            int indice = letrasSinRepeticion.indexOf(resultado1);
            suma+=cromosomaEvaluar.get(indice);
        }
        
        s1=Integer.valueOf(operador1);
        s2=Integer.valueOf(operador2);        
        sumaOriginal=s1+s2;
        s=String.valueOf(sumaOriginal);
        
        if (suma.length()>s.length()) {
            n=suma.length();
            m=s.length();
        }else{
            m=suma.length();
            n=s.length();
        }
        
        String sa="",soa="";
        for (int i = (suma.length()-1); i >= 0; i--) {
            sa+=suma.charAt(i);
        }
        
        for (int i = (s.length()-1); i >= 0; i--) {
            soa+=s.charAt(i);
        }
        
        for (int i = 0; i < m; i++) {
            int x,y;
            x=Integer.valueOf(String.valueOf(sa.charAt(i)));
            y=Integer.valueOf(String.valueOf(soa.charAt(i)));
            ap1+=Math.abs(x-y)*(m-i);
        }
        
        String mayor;
        if (sa.length()>soa.length()) {
            mayor=sa;
        }else{
            mayor=soa;
        }
        
        for (int i = m; i < n; i++) {
            int y=Integer.valueOf(String.valueOf(mayor.charAt(i)));
            ap2+=y*Math.pow(10, (i-m)+2);
        }
        
        if (suma.equals(s)) {
            c=0;
        }else{
            c=1;
        }
        return ap1+ap2+(c*5790);
    }
    
    private String cadenaCromosoma(ArrayList cromosomaConvertir){
        String cadena="";
        for (int i = 0; i < cromosomaConvertir.size(); i++) {
            cadena+=cromosomaConvertir.get(i);
        }
        return cadena;
    }
    
    private void generarPoblacionInicial(){
        int i=0;
        ArrayList<Object> cromosoma;
        ArrayList<Object> cromosomaEvaluado;
        String cromosomaPoblacion;
        int aptitud;
        cromosomasEvaluados=new ArrayList<>();
        poblacion=new ArrayList<>();
        do {
            cromosoma=crearCromosoma();
            cromosomaPoblacion=cadenaCromosoma(cromosoma);
            if (!cromosomasEvaluados.contains(cromosomaPoblacion)) {
                cromosomasEvaluados.add(cromosomaPoblacion);                
                aptitud=calcularAptitud(cromosoma);                
                cromosomaEvaluado=new ArrayList<>();
                cromosomaEvaluado.add(cromosomaPoblacion);
                cromosomaEvaluado.add(aptitud);
                poblacion.add(cromosomaEvaluado);
                i++;
            }
        } while (i<12500);
    }
    
    private void ordenarPoblacion(){
        for (int i = 0; i < poblacion.size(); i++) {
            for (int j = i; j < poblacion.size(); j++) {                
                if ((int)poblacion.get(j).get(1)<(int)poblacion.get(i).get(1))
                {
                    Object tempCr,tempAp;
                    tempAp = poblacion.get(j).get(1);
                    tempCr= poblacion.get(j).get(0);
                    poblacion.get(j).set(1,poblacion.get(i).get(1));
                    poblacion.get(j).set(0,poblacion.get(i).get(0));
                    poblacion.get(i).set(0,tempCr);
                    poblacion.get(i).set(1,tempAp);
                }
            }
        }
    }
        
    private void seleccionElitista(){        
        ordenarPoblacion();
        cromosomasMejores=new ArrayList<>();
        for (int i = 0; i < 1250; i++) {
            cromosomasMejores.add(poblacion.get(i).get(0));
        }
    }
    
    private void cruce(String cromosoma1,String Cromosoma2){
        int longitud=cromosoma1.length();
    }
    
    private void AlgoritmoGenético(){        
        mostrarPaneles();
        mostrarDatosPrevios();
        mostrarControles();
        obtenerLetras();
        long tiempoinicial=System.currentTimeMillis();
        generarPoblacionInicial();
        seleccionElitista();
//        for (int i = 0; i < poblacion.size(); i++) {
//            System.out.println("Cromosoma: "+poblacion.get(i).get(0)+" Aptitud: "+poblacion.get(i).get(1));
//        }
//        for (int i = 0; i < cromosomasMejores.size(); i++) {
//            System.out.println("Cromosoma: "+cromosomasMejores.get(i));
//        }
        long tiempototal=System.currentTimeMillis()-tiempoinicial;
        System.out.println("El tiempo de demora es: "+tiempototal+"miliseg");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlDatos = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtSumando1 = new javax.swing.JTextField();
        txtSumando2 = new javax.swing.JTextField();
        txtResultado = new javax.swing.JTextField();
        btnCalcular = new javax.swing.JButton();
        pnlDatosPrevios = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDatos = new javax.swing.JTable();
        pnlResultados = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblResultado = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstResultado = new javax.swing.JList<>();
        lblMensaje = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Criptoaritmética con Algoritmos Genéticos");

        pnlDatos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Ingreso de Datos"));

        jLabel1.setText("Sumando:");

        jLabel2.setText("Sumando:");

        jLabel3.setText("Resultado:");

        txtSumando1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSumando1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSumando1KeyTyped(evt);
            }
        });

        txtSumando2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSumando2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSumando2KeyTyped(evt);
            }
        });

        txtResultado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtResultadoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtResultadoKeyTyped(evt);
            }
        });

        btnCalcular.setText("Calcular");
        btnCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDatosLayout = new javax.swing.GroupLayout(pnlDatos);
        pnlDatos.setLayout(pnlDatosLayout);
        pnlDatosLayout.setHorizontalGroup(
            pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDatosLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnCalcular))
                    .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtSumando1, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                        .addComponent(txtSumando2)
                        .addComponent(txtResultado)))
                .addGap(0, 62, Short.MAX_VALUE))
        );
        pnlDatosLayout.setVerticalGroup(
            pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtSumando1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtSumando2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtResultado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCalcular)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlDatosPrevios.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblDatos);

        javax.swing.GroupLayout pnlDatosPreviosLayout = new javax.swing.GroupLayout(pnlDatosPrevios);
        pnlDatosPrevios.setLayout(pnlDatosPreviosLayout);
        pnlDatosPreviosLayout.setHorizontalGroup(
            pnlDatosPreviosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosPreviosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlDatosPreviosLayout.setVerticalGroup(
            pnlDatosPreviosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosPreviosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlResultados.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Resultado"));

        tblResultado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tblResultado);

        jScrollPane3.setViewportView(lstResultado);

        lblMensaje.setText("Mensaje");

        javax.swing.GroupLayout pnlResultadosLayout = new javax.swing.GroupLayout(pnlResultados);
        pnlResultados.setLayout(pnlResultadosLayout);
        pnlResultadosLayout.setHorizontalGroup(
            pnlResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResultadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlResultadosLayout.setVerticalGroup(
            pnlResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResultadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMensaje)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlDatosPrevios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlResultados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlResultados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlDatosPrevios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSumando1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSumando1KeyTyped
        // TODO add your handling code here:
        soloLetras(evt);
        mostrarCalcular();
    }//GEN-LAST:event_txtSumando1KeyTyped

    private void txtSumando2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSumando2KeyTyped
        // TODO add your handling code here:
        soloLetras(evt);
        mostrarCalcular();
    }//GEN-LAST:event_txtSumando2KeyTyped

    private void txtResultadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtResultadoKeyTyped
        // TODO add your handling code here:
        soloLetras(evt);
        mostrarCalcular();
    }//GEN-LAST:event_txtResultadoKeyTyped

    private void btnCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularActionPerformed
        // TODO add your handling code here:
        AlgoritmoGenético();
    }//GEN-LAST:event_btnCalcularActionPerformed

    private void txtSumando1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSumando1KeyReleased
        // TODO add your handling code here:
        txtSumando1.setText(txtSumando1.getText().toUpperCase());
    }//GEN-LAST:event_txtSumando1KeyReleased

    private void txtSumando2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSumando2KeyReleased
        // TODO add your handling code here:
        txtSumando2.setText(txtSumando2.getText().toUpperCase());
    }//GEN-LAST:event_txtSumando2KeyReleased

    private void txtResultadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtResultadoKeyReleased
        // TODO add your handling code here:
        txtResultado.setText(txtResultado.getText().toUpperCase());
    }//GEN-LAST:event_txtResultadoKeyReleased

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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCalcular;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JList<String> lstResultado;
    private javax.swing.JPanel pnlDatos;
    private javax.swing.JPanel pnlDatosPrevios;
    private javax.swing.JPanel pnlResultados;
    private javax.swing.JTable tblDatos;
    private javax.swing.JTable tblResultado;
    private javax.swing.JTextField txtResultado;
    private javax.swing.JTextField txtSumando1;
    private javax.swing.JTextField txtSumando2;
    // End of variables declaration//GEN-END:variables
}
