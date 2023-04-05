package util;

import tablaBase.modeloBase;
import visual.principal;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class modeloPapel extends modeloBase{
    
    public modeloPapel(principal frame){
        this.frame = frame;
        setCols();
        loader();
    }
    
    private void setCols(){
        String cols[] = {"Tipo" , "Carta", "Oficio"};
        for(String col : cols) {addColumn(col); }
    }
    
    private void setFilas(){
        limpiaTabla();
        for(setgetPapel papel : papeles){
            Object fila[] = {
                papel.getTipo(),
                papel.getCarta(),
                papel.getOficio()
            };
            addRow(fila);
        }
    }
        
    public void limpiaTabla(){
        for(int i = getRowCount() -1 ; i >= 0 ; i--)
            removeRow(i);
    }
        
    private void loader(){
        if(papers.exists()){
            try(BufferedReader reader = new BufferedReader(new FileReader(papers))){
                String line;
                while((line = reader.readLine()) != null){
                    String linea[] = line.split(":");
                    setgetPapel papel = new setgetPapel();
                    
                    papel.setTipo   (linea[0]                    );
                    papel.setCarta  (Double.parseDouble(linea[1]));
                    papel.setOficio (Double.parseDouble(linea[2]));
                    
                    papeles.add(papel);
                }
                setFilas();
            }catch (Exception exc){
                System.out.println(exc);
            }
        } else { defaults(); }
    }
    
    private void defaults(){
        Object filas[][] = {
            {"CB"           ,0.2974, 0.3527},
            {"CF"           ,0.2464, 0.2917},
            {"CBF"          ,0.3200, 0.4134},
            {"Bond Blanco"  ,0.1200, 0.1400}
        };
        for(Object[] fila : filas) {
            setgetPapel papel = new setgetPapel();
            papel.setTipo(fila[0].toString());
            papel.setCarta(Double.parseDouble(fila[1].toString()));
            papel.setOficio(Double.parseDouble(fila[2].toString()));
            papeles.add(papel);
        }
        setFilas();
        modifPapel();
    }
    
    private void modifPapel(){
        try(PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(papers)))){
            for(setgetPapel papel : papeles){
                String linea = papel.getTipo() + ":" + papel.getCarta() + ":" + papel.getOficio();
                writer.println(linea);
            }
            writer.flush();
            writer.close();
        }catch(Exception exc){}
    }

    private int intColumna(String nombre){
        for(int i = 0 ; i < getColumnCount() ; i++){
            if(getColumnName(i).equals(nombre))
                return i;
        }
        return -1;
    }
    
    @Override 
    public void modif(int fil, int col, Object valor){
        setgetPapel papel = papeles.get(fil);
        
                if (col == intColumna("Tipo"))  { papel.setTipo     ((String) valor); }
        else    if (col == intColumna("Carta")) { papel.setCarta    ((Double) valor); }
        else    if (col == intColumna("Oficio")){ papel.setOficio   ((Double) valor); }
        
        papeles.set(fil, papel);
        modifPapel();
        frame.calcula();
    }
    
    @Override
    public Class<?> getColumnClass(int col) {
        if(col == intColumna("Carta") || col == intColumna("Oficio")){
            return Double.class;
        } return Object.class;
    }
    
    @Override public boolean isCellEditable(int row, int col){
        if(col == intColumna("Tipo")) return false;
        return true;
    }
    
    principal               frame   ;
    File                    papers  = new File      ("Papeles.txt"  );
    ArrayList<setgetPapel>  papeles = new ArrayList (               );
}