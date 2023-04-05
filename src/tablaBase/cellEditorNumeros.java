package tablaBase;

import javax.swing.AbstractAction;
import javax.swing.DefaultCellEditor;

public class cellEditorNumeros extends DefaultCellEditor {
    
    public cellEditorNumeros(int caso, AbstractAction picoEnter){
        super(new campoNumeros(caso));
        setDatos(picoEnter);
    }
    
    private void setDatos(AbstractAction picoEnter){ 
        txt = (campoNumeros) getComponent(); 
        txt.modoCellEditor(picoEnter);
    }
    
    public void setTeclazo(char teclazo){ txt.setTeclazo(teclazo); }
    
    @Override public Object getCellEditorValue() { return txt.getValor(); }
    
    campoNumeros txt;
}