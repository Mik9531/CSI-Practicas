package es.uca.gii.csi18.lara.gui;

import java.util.*;
import es.uca.gii.csi18.lara.data.*;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

@SuppressWarnings("serial")
public class AutorListModel
extends AbstractListModel<Autor> 
implements ComboBoxModel<Autor> {

    private List<Autor> _aData;
    private Object _oSelectedItem = null;

    public List<Autor> getData() {
        return _aData;
    }

    public void setData(List<Autor> aData) {
        _aData = aData;
    }

    public AutorListModel(List<Autor> aData) {
        _aData = aData;
    }


    public Autor getElementAt(int iIndex) {
        return _aData.get(iIndex);
    }

    public int getSize() {
        return _aData.size();
    }

    @Override
    public Object getSelectedItem() {
        return _oSelectedItem;
    }

    @Override
    public void setSelectedItem(Object o) {
        Autor estatusSocial = (Autor) o;
        for (Autor e : _aData) {
            if (e.getNombre().equals(estatusSocial.getNombre()))
                _oSelectedItem = e;
        }
    }
}
