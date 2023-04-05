package tablaBase;

import javax.swing.AbstractAction;
import javax.swing.DefaultCellEditor;

public class cellEditorFormatos extends DefaultCellEditor {
    
    public cellEditorFormatos(int caso, AbstractAction picoEnter){
        super(new campoFormatos(caso));
        setDatos(picoEnter);
    }
    
    private void setDatos(AbstractAction picoEnter){
        campo = (campoFormatos) getComponent();
        campo.modoEditor(picoEnter);
    }
    
    public void setTeclazo(Character teclazo){ campo.setTeclazo(teclazo); }
        
    @Override public boolean stopCellEditing(){
        if(campo.validaEdicion())
            return super.stopCellEditing();
        return false;
    }
    
    campoFormatos campo;
}
