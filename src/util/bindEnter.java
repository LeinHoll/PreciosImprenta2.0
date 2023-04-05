package util;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JViewport;
import javax.swing.KeyStroke;

public class bindEnter {
    
    public bindEnter(Component comp){
        JComponent jc = (JComponent) comp;
        if(jc instanceof JPanel || jc instanceof JScrollPane || jc instanceof JViewport || jc instanceof JTabbedPane){
            for(Component c : jc.getComponents())
                be = new bindEnter(c);
        } else if (jc instanceof JButton || jc instanceof JComboBox || jc instanceof JTextField || jc instanceof JTextArea){
            if(sinBind(jc)){
                jc.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "enter");
                jc.getActionMap().put("enter", new accionPicoEnter(jc));
            }
        }
    }
    
    private boolean sinBind(JComponent jc){
        try{
            for(Object key : jc.getInputMap().keys())
                if(key == KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0))
                    return false;
        } catch(Exception exc){}
        return true;
    }
    
    private class accionPicoEnter extends AbstractAction {
    
        public accionPicoEnter(JComponent comp){
            this.comp = comp;
        }

        @Override
        public void actionPerformed(ActionEvent evt){
            if(comp instanceof JButton) {
                JButton boton = (JButton) comp;
                boton.doClick();
            } else if(comp instanceof JTextField || comp instanceof JTextArea || comp instanceof JComboBox) {
                comp.transferFocus();
            }
        }

        JComponent comp;
    }
    
    bindEnter be;
}