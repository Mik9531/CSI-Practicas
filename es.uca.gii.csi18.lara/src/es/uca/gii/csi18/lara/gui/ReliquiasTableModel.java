package es.uca.gii.csi18.lara.gui;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import es.uca.gii.csi18.lara.data.*;

@SuppressWarnings("serial")
public class ReliquiasTableModel extends AbstractTableModel {

    private ArrayList<Reliquia> _aData;

    ReliquiasTableModel(ArrayList<Reliquia> aData) {
        _aData = aData;
    }

    public ArrayList<Reliquia> getData() {
        return _aData;
    }

    public void setData(ArrayList<Reliquia> aData) {
        _aData = aData;
    }

    public int getColumnCount() {
        int iCont = 0;
        if (_aData.get(0).getId() != 0)
            iCont++;
        if (_aData.get(0).getAutor() != null)
            iCont++;
        if (_aData.get(0).getPeso() != 0)
            iCont++;
        if (_aData.get(0).getNombre() != "")
            iCont++;
        return iCont;
    }

    public int getRowCount() {
        return _aData.size();
    }

    public Object getValueAt(int iRow, int iCol) {

        switch (iCol) {
            case 0:
                return _aData.get(iRow).getId();
            case 1:
                return _aData.get(iRow).getAutor().toString();
            case 2:
                return _aData.get(iRow).getPeso();
            case 3:
                return _aData.get(iRow).getNombre();
            default:
                return null;

        }
    }

    public Reliquia getData(int iRow) throws Exception {
        return new Reliquia(_aData.get(iRow).getId());
    }

}
