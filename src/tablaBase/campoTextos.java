package tablaBase;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.JFormattedTextField;
import javax.swing.KeyStroke;

public class campoTextos extends JFormattedTextField {
    
    public campoTextos(int caso){
        this.caso = caso;
        setEventos();
    }
    
    private void setEventos(){
        addFocusListener(new FocusAdapter(){
            @Override public void focusGained   (FocusEvent evt){ gainedFocus   (evt); }
            @Override public void focusLost     (FocusEvent evt){ lostFocus     (evt); }
        });
        addKeyListener(new KeyAdapter(){
            @Override public void keyTyped(KeyEvent evt){ typedKey(evt); }
        });
    }
    
    private void gainedFocus(FocusEvent evt){
        if(editor){
            if(teclazo != null && Character.isLetterOrDigit(teclazo)){
                setText(teclazo.toString());
            } else { selectAll(); }
        } else {
            firstKey = true;
            selectAll();
        }
    }
    
    private void lostFocus(FocusEvent evt){
        switch(caso){
            case INSENSITIVO: break;
            case MAYUSCULAS : setText(getText().toUpperCase()); break;
            case MINUSCULAS : setText(getText().toLowerCase()); break;
        }
    }
    
    private void typedKey(KeyEvent evt){
        if(firstKey && !editor){ setText(""); }
        firstKey = false;
    }
    
    public Object getValor(){
        switch(caso){
            case INSENSITIVO: return getText();
            case MAYUSCULAS : return getText().toUpperCase();
            case MINUSCULAS : return getText().toLowerCase();
        }
        return getText();
    }
    
    public void modoEditor(AbstractAction picoEnter){
        editor = true;
        getInputMap ().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "enter");
        getActionMap().put("enter", picoEnter);
    }
    
    public void setTeclazo(Character teclazo){
        this.teclazo = teclazo;
    }
    
    int                     caso        ;
    public static final int INSENSITIVO = 0;
    public static final int MAYUSCULAS  = 1;
    public static final int MINUSCULAS  = 2;
    boolean                 editor      = false;
    boolean                 firstKey    = true;
    Character               teclazo     ;
}