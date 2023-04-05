package tablaBase;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import javax.swing.AbstractAction;
import javax.swing.JFormattedTextField;
import javax.swing.KeyStroke;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

public class campoNumeros extends JFormattedTextField {
    
    public campoNumeros(int caso){
        this.caso = caso;
        setDatos        ();
        setEventos      ();
        setExcluciones  ();
    }
    
    private void setEventos(){
        addFocusListener(new FocusAdapter(){
            @Override public void focusGained(FocusEvent evt){ gainedFocus(evt); }
        });
        
        addKeyListener(new KeyAdapter(){
            @Override public void keyTyped      (KeyEvent evt){ picoTecla   (evt); }
            @Override public void keyReleased   (KeyEvent evt){ soltoTecla  (evt); }
        });
    }
    
    private void setDatos(){
        setHorizontalAlignment(JFormattedTextField.TRAILING);
        String mascara = "";
        switch(caso){
            case ENTEROS:
                setText("0");
                mascara = "#0";
                break;
            case DECIMALES:
                setText("0.00");
                mascara = "#0.00";
        }
        setFormatterFactory(
                new DefaultFormatterFactory(
                        new NumberFormatter(
                                new DecimalFormat(mascara))));
    }
    
    private void setExcluciones(){
        switch(caso){
            case ENTEROS:
                exclucion = new int[excEnteros.length];
                System.arraycopy(excEnteros, 0, exclucion, 0, excEnteros.length);
                break;
            case DECIMALES:
                exclucion = new int[excDecimal.length];
                System.arraycopy(excDecimal, 0, exclucion, 0, excDecimal.length);
                break;
        }
    }
    
    private boolean getExclucion(int tecla){
        for(int i = 0 ; i < exclucion.length ; i++)
            if(tecla == exclucion[i])
                return false;
        return true;
    }
        
    private void picoTecla(KeyEvent evt){
        int tecla = evt.getKeyChar();
        
        if((tecla < '0' ||  tecla > '9') 
                && getExclucion(tecla)) { evt.consume(); } 
        else if(getText().contains(".") && tecla == '.'){ evt.consume(); }
        
        if(!evt.isConsumed() && firstKey && !editor) {
            if(tecla != KeyEvent.VK_BACK_SPACE && getCaretPosition() == 0){
                setText("0");
                setCaretPosition(1);
            }
            firstKey = false;
        }
        
        if(!evt.isConsumed() && 
            getText().equals("0") &&
             getCaretPosition() == 1 && 
              tecla != KeyEvent.VK_BACK_SPACE)
        {
            if(tecla != '0' && tecla != '.')
                setText(getText().replace('0', (char) tecla));
            else if (tecla == '.') { setText(getText() + "."); }
            evt.consume();
        }
    }
    
    private void soltoTecla(KeyEvent evt){
        String txt = getText();
        if(txt.equals("")) { setText("0"); }
    }
    
    private void gainedFocus(FocusEvent evt){
        if(editor){
            if(getValue() != null)
                try {
                    switch(caso){
                        case ENTEROS    : valor = Integer   .parseInt   (getValue().toString()); break;
                        case DECIMALES  : valor = Double    .parseDouble(getValue().toString()); break;
                    }
                } catch(Exception exc){}
            if(teclazo != '#'){
                if(teclazo == KeyEvent.VK_BACK_SPACE) { setText("0"); } 
                else { setText(teclazo + ""); }
                teclazo = '#';
            } else { selectAll(); }
        } else {
            firstKey = true;
            selectAll();
        }
    }
    
    public Object getValor(){
        try {
            switch(caso) {
                case ENTEROS    : valor = Integer   .parseInt   (getText()); break;
                case DECIMALES  : valor = Double    .parseDouble(getText()); break;
            }
        } catch(Exception exc){}
        return valor;
    }
    
    public void modoCellEditor(AbstractAction picoEnter){
        editor = true;
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "enter");
        getActionMap().put("enter", picoEnter);
    }
    
    public void setTeclazo(char teclazo){
        this.teclazo = teclazo;
    }
    
    int                     caso        ;
    public static final int ENTEROS     = 0;
    public static final int DECIMALES   = 1;
    int[]                   exclucion   = {};
    int[]                   excEnteros  = {KeyEvent.VK_BACK_SPACE};
    int[]                   excDecimal  = {KeyEvent.VK_BACK_SPACE, '.'};
    boolean                 firstKey    = true;
    boolean                 editor      = false;
    Object                  valor       = 0;
    char                    teclazo     = '#';
}