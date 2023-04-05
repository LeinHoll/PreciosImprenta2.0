package tablaBase;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.JFormattedTextField;
import javax.swing.KeyStroke;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

public class campoFormatos extends JFormattedTextField {
    
    public campoFormatos(int caso){
        this.caso = caso;
        setDatos();
        setEventos();
    }
    
    private void setDatos(){
        String mascara = "";
        switch(caso){
            case TELEFONO : mascara = "(###) ###-####"; break;
        }
        if(!mascara.isEmpty())
            try {
                setFormatterFactory(
                        new DefaultFormatterFactory(
                                new MaskFormatter(mascara)));
            } catch(Exception exc){}
    }
    
    private void setEventos(){
        addFocusListener(new FocusAdapter(){
            @Override public void focusGained(FocusEvent evt){ gainedFocus(evt); }
        });
    }
    
    public void modoEditor(AbstractAction picoEnter){
        editor = true;
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "enter");
        getActionMap().put("enter", picoEnter);
    }
    
    private void gainedFocus(FocusEvent evt){
        if(editor){
            switch(caso){
                case TELEFONO: 
                    if(Character.isDigit(teclazo)){
                        setText(teclazo.toString());
                        setCaretPosition(2);
                    } else {
                        setText("");
                        setCaretPosition(1);
                    }
                    break;
            }
            
        }
    }
        
    public boolean validaEdicion(){
        try {
            getFormatter().stringToValue(getText());
            return true;
        } catch(Exception exc){
            return false;
        }
    }
    
    public void setTeclazo(Character teclazo){
        this.teclazo = teclazo;
    }
    
    int                     caso        ;
    public static final int TELEFONO    = 0;
    public static final int CURP        = 1;
    public static final int RFC         = 2;
    boolean                 editor      = false;
    Character               teclazo     ;
}