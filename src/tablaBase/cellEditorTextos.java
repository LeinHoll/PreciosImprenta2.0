package tablaBase;

import javax.swing.AbstractAction;
import javax.swing.DefaultCellEditor;

public class cellEditorTextos extends DefaultCellEditor {
        
    public cellEditorTextos(int caso, AbstractAction picoEnter){
        super(new campoTextos(caso));
        setDatos(picoEnter);
    }
    
    private void setDatos(AbstractAction picoEnter){
        campo = (campoTextos) getComponent();
        campo.modoEditor(picoEnter);
    }
    
    public void setTeclazo(Character teclazo){ campo.setTeclazo(teclazo); }
    
    @Override public Object getCellEditorValue(){ return campo.getValor(); }
    
    campoTextos campo;
}
