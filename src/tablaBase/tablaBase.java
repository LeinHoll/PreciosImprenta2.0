package tablaBase;

import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

public class tablaBase extends JTable {
    
    public tablaBase(DefaultTableModel modelo, int casoTextos){
        this.casoTextos = casoTextos;
        setModel    (modelo);
        misSettings ();
    }
    
    public tablaBase(DefaultTableModel modelo, int sizes[], int casoTextos, boolean sorted){
        this.casoTextos = casoTextos;
        setModel    (modelo);
        misSettings ();
        setSorted   (sorted);
        setSizes    (sizes);
    }
        
    private void misSettings(){
        getTableHeader().setReorderingAllowed(false);
                
        setDefaultEditor(Integer.class, new cellEditorNumeros   (campoNumeros.ENTEROS   , new picoEnter()));
        setDefaultEditor(Double .class, new cellEditorNumeros   (campoNumeros.DECIMALES , new picoEnter()));
        setObjectCellEditor();
        
        setDefaultRenderer(Integer  .class, new cellRenderBase(cellRenderBase.FOLIOS    ));
        setDefaultRenderer(Double   .class, new cellRenderBase(cellRenderBase.DECIMALES ));
        setDefaultRenderer(Object   .class, new cellRenderBase(cellRenderBase.TEXTOS    ));
        setDefaultRenderer(Date     .class, new cellRenderBase(cellRenderBase.FECHAS    ));
        
        addFocusListener(new FocusAdapter(){
           @Override public void focusGained(FocusEvent evt){ ganoEnfoque(evt); } 
        });
        addKeyListener(new KeyAdapter(){
            @Override public void keyTyped(KeyEvent evt){ picoTecla(evt); }
        });
        addMouseListener(new MouseAdapter(){
           @Override public void mouseClicked(MouseEvent evt) { clickMouse(evt); } 
        });
        
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER  , 0), "enter"   );
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB    , 0), "tab"     );
        
        getActionMap().put("enter"  , new picoEnter());
        getActionMap().put("tab"    , new picoTab());
    }
    
    private void setSorted(boolean sorted){
        if(sorted){ setRowSorter(new TableRowSorter(getModel())); }
    }
    
    private void setObjectCellEditor(){
        for(int i = 0 ; i < getModel().getColumnCount() ; i++){
            if(getModel().getColumnClass(i) == String.class) {
                TableColumn col = getColumnModel().getColumn(i);
                String nom = getModel().getColumnName(i);
                        if( nom.equals("Telefono"   ) || 
                            nom.equals("Celular"    ) || 
                            nom.equals("Tel"        ) || 
                            nom.equals("Cel"        ))
                    col.setCellEditor(new cellEditorFormatos(campoFormatos.TELEFONO, new picoEnter()));
                else
                    col.setCellEditor(new cellEditorTextos(casoTextos, new picoEnter()));
            }
        }
    }
        
    protected void picoTecla(KeyEvent evt){
        validaTecla(evt);
        try {
            if(!evt.isConsumed() && isCellEditable(getSelectedRow(), getSelectedColumn())){
                Component comp = getEditorComponent();
                if(comp instanceof campoNumeros){
                    campoNumeros num = (campoNumeros) comp;
                    num.setTeclazo(evt.getKeyChar());
                } else if(comp instanceof campoTextos){
                    campoTextos txt = (campoTextos) comp;
                    txt.setTeclazo(evt.getKeyChar());
                } else if(comp instanceof campoFormatos){
                    campoFormatos frm = (campoFormatos) comp;
                    frm.setTeclazo(evt.getKeyChar());
                }
                comp.requestFocusInWindow();
            }
        } catch(Exception exc){}
    }
    
    private void validaTecla(KeyEvent evt){
        Object col = getColumnClass(getSelectedColumn());
        int tecla = evt.getKeyChar();
        if(col == Integer.class || col == Double.class){
            if((!Character.isDigit(tecla)) 
                    && tecla != KeyEvent.VK_BACK_SPACE){
                if(col == Double.class && tecla == '.'){
                    return;
                }
                if(isEditing()){ getCellEditor().stopCellEditing(); }
                evt.consume();
            }
        }
    }
    
    protected void picoEnter(){
        if(isEditing()){ getCellEditor().stopCellEditing(); }
        
        int nCol = getSelectedColumn()  ;
        int nFil = getSelectedRow   ()  ;
        int fila = getRowCount      ()-1;
        int colu = getColumnCount   ()-1;

        if(nCol < 0) { nCol = 0; }
        if(nFil < 0) { nFil = 0; }
        if(nCol >= colu && nFil >= fila){
            if(picoTab()){
                nCol = 0;
                nFil = 0;
            } else { return; }
        } else if(nCol >= colu){
            nCol = 0;
            if(nFil == fila){ nFil = 0  ; } 
            else            { nFil++    ; }
        } else { nCol++; }

        changeSelection(nFil, nCol, false, false);
    }
    
    protected void clickMouse(MouseEvent evt){
        if(SwingUtilities.isLeftMouseButton(evt) && evt.getClickCount() == 2){
            if(!isCellEditable(getSelectedRow(), getSelectedColumn())){
                if(boton != null) { boton.doClick(); }
            }
            try {
                Component comp = getEditorComponent();
                comp.requestFocusInWindow();
            } catch(Exception exc){}
        } else if(SwingUtilities.isRightMouseButton(evt)) {
            Point p = evt.getPoint();
            changeSelection(rowAtPoint(p), columnAtPoint(p), false, false);
            if(pop != null){ pop.show(evt.getComponent(), evt.getX(), evt.getY()); }
        }
    }
    
    public void ganoEnfoque(FocusEvent evt){
        if(getSelectedRow() == -1 && getSelectedColumn() == -1)
            changeSelection(0, 0, false, false);
    }
    
    public void setPop(JPopupMenu pop) { this.pop = pop; }
    
    public void setBoton(JButton boton) { this.boton = boton; }
        
    private boolean picoTab(){
        Container               cont    = getParent();
        FocusTraversalPolicy    policy  = cont.getFocusTraversalPolicy();
        while(policy == null){
            cont    = cont.getParent();
            policy  = cont.getFocusTraversalPolicy();
        }
        Component comp = policy.getComponentAfter(cont, this);
        if(comp == this) return true;
        changeSelection(0, 0, false, false);
        clearSelection();
        transferFocus();
        return false;
    }
    
    private void setSizes(int sizes[]){
        TableColumn col;
        for(int i = 0 ; i < sizes.length ; i++){
            col = getColumnModel().getColumn(i);
            col.setPreferredWidth(sizes[i]);
        }
    }
        
    private class picoEnter extends AbstractAction{
        @Override public void actionPerformed(ActionEvent evt){ picoEnter(); }
    }
    
    private class picoTab extends AbstractAction{
        @Override public void actionPerformed(ActionEvent evt){ picoTab(); }
    }
    
    int                     casoTextos  ;
    public static final int INSENSITIVO = 0;
    public static final int MAYUSCULAS  = 1;
    public static final int MINUSCULAS  = 2;
    JButton                 boton       ;
    JPopupMenu              pop         ;
}