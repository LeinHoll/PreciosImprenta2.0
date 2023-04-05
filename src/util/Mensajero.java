package util;

import javax.swing.JOptionPane;

public class Mensajero {
    
    public static void mensaje(String txt){
        JOptionPane.showMessageDialog(null, txt);
    }
    
    public static boolean confirme(String txt){
        int opt = JOptionPane.showConfirmDialog(null, txt, "Confirme", JOptionPane.YES_NO_OPTION);
        if(opt == 0) { return true; }
        return false;
    }
}