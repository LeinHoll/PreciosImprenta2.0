package arranque;

import visual.principal;
import visual.diagTot;
import java.awt.Point;
import javax.swing.UIManager;

public class inicio {
    public static void main(String [] args){
        
        try {
            UIManager.LookAndFeelInfo infor[] = UIManager.getInstalledLookAndFeels();
            for(int i = 0 ; i < infor.length ; i++){
                if("Windows".equals(infor[i].getName())){
                    UIManager.setLookAndFeel(infor[i].getClassName());
                    break;
                }
            }
        } catch(Exception exc){}     
        
        principal Principal = new principal();
        Principal.setLocationRelativeTo(null);
        Principal.setVisible(true);
        
        diagTot tot = new diagTot(Principal, false);
        Point point = Principal.getLocationOnScreen();
        point.setLocation(point.getX() + Principal.getSize().getWidth(), point.getY());
        tot.setLocation(point);
        tot.setVisible(true);
        Principal.setDiagTot(tot);
    }
}