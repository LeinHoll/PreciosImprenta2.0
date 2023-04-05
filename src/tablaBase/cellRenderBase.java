package tablaBase;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class cellRenderBase extends JPanel implements TableCellRenderer{
    
    public cellRenderBase(int caso){
        this.caso   = caso;
        label       = new Label();
        add             (label              );
        setLayout       (new GridLayout()   );
        setBackground   (Color.white        );
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        label.setTxt(value);
        fondo(isSelected, row);
        enfoque(hasFocus);
        return this;
    }
    
    private void fondo(boolean selected, int fila){
        if(selected){ setBackground(new Color(153,204,255)); } 
        else {
            if(fila % 2 == 1) { setBackground(Color.white); }
            else { setBackground(new Color(225, 255, 224)); }
        }
    }
    
    private void enfoque(boolean foco){
        if(foco){ 
            setBackground(new Color(102,204,255));
            setBorder(BorderFactory.createEtchedBorder()); 
        } 
        else { 
            setBorder(null); 
        }
    }
    
    private class Label extends JLabel{
        
        public Label(){
            int aling = JLabel.RIGHT;
            switch(caso){
                case ENTEROS    : setText("0"   ); break;
                case DECIMALES  : setText("0.0000"); break;
                case TEXTOS     :
                    setText(""); 
                    aling = JLabel.LEFT; 
                    break;
                case FECHAS     :
                    setText("00/00/0000");
                    break;
            }
            setHorizontalAlignment(aling);
        }
        
        public void setTxt(Object value){
            String txt = "";
            if(value != null)
                txt = value.toString();
            switch(caso){
                case ENTEROS:
                    try {
                        txt = new DecimalFormat("#,##0").format(Integer.parseInt(value.toString()));
                    } catch(Exception exc){}
                    break;
                case DECIMALES:
                    try {
                        txt = new DecimalFormat("#,##0.0000").format(Double.parseDouble(value.toString()));
                    } catch(Exception exc){}
                    break;
                case FOLIOS: 
                    try {
                        txt = new DecimalFormat("No: 0000").format(Integer.parseInt(value.toString()));
                    } catch(Exception exc){}
                    break;
                case TEXTOS: break;
                case FECHAS: 
                    try {
                        txt = new SimpleDateFormat("dd/MM/yyyy").format(value);
                    } catch(Exception exc){}
                    break;
            }
            setText(txt);
        }
    }
    
    final int               caso        ;
    public static final int ENTEROS     = 0;
    public static final int DECIMALES   = 1;
    public static final int TEXTOS      = 2;
    public static final int FECHAS      = 3;
    public static final int FOLIOS      = 4;
    Label                   label       ;
}