/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLayer;

import BusinessObjects.Historico;
import DataAccessLayer.HistoricosDAL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Wilmer Oñate
 */
public class HistoriasBL {

    Historico historico;
    HistoricosDAL historicoDAL;

    public HistoriasBL() {
        historico = new Historico();
        historicoDAL = new HistoricosDAL();
    }

    private Date convertirFechaStringDate(String fecha) {
        Date date = null;
        SimpleDateFormat cambiarfecha = new SimpleDateFormat("dd-MM-yyyy");       
        try {
            date = cambiarfecha.parse(fecha);
        } catch (ParseException ex) {

        }
        return date;
    }

    public String nuevaHistoria(javax.swing.table.DefaultTableModel tbHistorias) {        
        Historico[] historicoVec = new Historico[tbHistorias.getRowCount()];
        for (int i = 0; i < tbHistorias.getRowCount(); i++) {
            historico = new Historico();
            historico.setNumeroHistoriaClinica(tbHistorias.getValueAt(i, 0).toString());
            historico.setCodigoCie10(tbHistorias.getValueAt(i, 1).toString());
            historico.setFechaIngreso(convertirFechaStringDate((String) (tbHistorias.getValueAt(i, 2))));
            historico.setEstadoPaciente(tbHistorias.getValueAt(i, 3).toString());
            historicoVec[i] = historico;
        }
        String mensaje = historicoDAL.Insert(historicoVec); //Ejecutamos la inserción
        return mensaje;
    }
}
