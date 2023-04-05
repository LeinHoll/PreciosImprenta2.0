package visual;

import tablaBase.campoNumeros;
import tablaBase.tablaBase;
import tablaBase.dataChangeManager;
import util.modeloPapel;
import util.bindEnter;

public class principal extends javax.swing.JFrame {

    public principal() {
        initComponents();
        misComponentes();
    }

    private void misComponentes(){
        modelo = new modeloPapel(this);
        tablaPapel = new tablaBase(modelo, tablaBase.INSENSITIVO);
        tablaPapel.addPropertyChangeListener(new dataChangeManager(tablaPapel));
        tablaPapel.getTableHeader().setResizingAllowed(false);
        scroll.setViewportView(tablaPapel);
        
        bindEnter be = new bindEnter(panel);        
    }

    public void setDiagTot(diagTot tot){
        this.tot = tot;
        calcula();
    }
    
    public void calcula(){
        double numeroDeJuegos           = Double.parseDouble("0" + spinJuegos .getValue());
        double juegosPorHoja            = Double.parseDouble("0" + spinDivisor.getValue());
        double copias                   = Double.parseDouble("0" + spinCopias .getValue());
        int    tamaño                   = spinPapel.getSelectedIndex() + 1;
        double desperdicio              = numeroDeJuegos * (Double.parseDouble( "0" + spinDesperdicio.getValue() ) / 100) ;
        double capacidadDeGuillotina    = Double.parseDouble("0" + ftfCapacidad .getText    ());
        double diseño                   = Double.parseDouble("0" + ftfDiseño    .getText    ());
        double impresionPorHoja         = Double.parseDouble("0" + ftfImpresion .getText    ());
        double placa                    = Double.parseDouble("0" + ftfPlaca     .getText    ());
        double porCientoUtil            = Double.parseDouble("0" + ftfUtilidad  .getText    ()) / 100;
        double porCientoVendedor        = Double.parseDouble("0" + ftfVendedor  .getText    ()) / 100;
        double precioPorCorte           = Double.parseDouble("0" + ftfCorte     .getText    ());
        double tintas                   = Double.parseDouble("0" + spinTintas   .getValue   ());
        double relacionComunDePapel     = Math.ceil((( numeroDeJuegos + desperdicio )) / juegosPorHoja);
        double corte                    = 0, 
                folio                   = 0,
                precioMaterial          = 0,
                suaje                   = 0,
                numeroDeCortes          = 0;
        double precioPapel              ,
                precioImpresion         ,
                preprensa               ,
                utilidad                ,
                comisionVendedor        ,
                numeroDeHojas           ,
                subTotal                ,
                IVA                     ,
                Total                   ;

        //Parte 1: Costos del papel

        if(copias == 0){

            precioPapel     = Double.parseDouble(tablaPapel.getValueAt(3, tamaño).toString());
            precioMaterial  = precioPapel * ( relacionComunDePapel );

        } else {

            for(int i = 0 ; i < (int)copias ; i++){

                int tipoDePapel = 2;
                if(i < 2)
                    tipoDePapel = i;

                precioPapel     = Double.parseDouble(tablaPapel.getValueAt(tipoDePapel, tamaño).toString());
                precioMaterial += precioPapel * relacionComunDePapel;

            }

        }

        //Parte 2: Costos de diseño y placas

        preprensa = diseño + placa * tintas;

        //Parte 3: Costo de impresion

        precioImpresion = relacionComunDePapel * tintas * (copias + 1) * impresionPorHoja;

        //Parte 4: Costo de foliado

        if(cbFolio.isSelected())
            folio = relacionComunDePapel * Double.parseDouble(ftfFolio.getText());

        //Parte 5: Costo de suaje

        if(cbSuaje.isSelected())
            suaje = relacionComunDePapel * Double.parseDouble(ftfSuaje.getText());

        //Parte 6: Costo del corte

        numeroDeHojas = relacionComunDePapel * (copias + 1);
        
        if(capacidadDeGuillotina != 0){
            numeroDeCortes = 0;

            for(int i = 1 ; i < juegosPorHoja ; i++)
                numeroDeCortes += Math.ceil( ( numeroDeHojas * i ) / capacidadDeGuillotina );

            corte = numeroDeCortes * precioPorCorte;
        }

        //Parte 7: Utilidad y Comision al vendedor;

        subTotal            = precioMaterial + preprensa + precioImpresion + folio + suaje + corte;
        utilidad            = subTotal * porCientoUtil;
        comisionVendedor    = subTotal * porCientoVendedor;

        //Parte 8: Resultados

        subTotal   += utilidad + comisionVendedor;
        IVA         = subTotal * 0.16;
        Total       = subTotal + IVA;
        
        tot.setData(subTotal, IVA, Total, comisionVendedor, numeroDeHojas, numeroDeCortes);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        spinJuegos = new javax.swing.JSpinner();
        spinDivisor = new javax.swing.JSpinner();
        spinCopias = new javax.swing.JSpinner();
        spinPapel = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        ftfDiseño = new campoNumeros(campoNumeros.DECIMALES);
        spinTintas = new javax.swing.JSpinner();
        ftfPlaca = new campoNumeros(campoNumeros.DECIMALES);
        spinDesperdicio = new javax.swing.JSpinner();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        cbFolio = new javax.swing.JCheckBox();
        cbSuaje = new javax.swing.JCheckBox();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        ftfImpresion = new campoNumeros(campoNumeros.DECIMALES);
        ftfFolio = new campoNumeros(campoNumeros.DECIMALES);
        ftfSuaje = new campoNumeros(campoNumeros.DECIMALES);
        ftfCorte = new campoNumeros(campoNumeros.DECIMALES);
        ftfCapacidad = new campoNumeros(campoNumeros.ENTEROS);
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        ftfVendedor = new campoNumeros(campoNumeros.ENTEROS);
        ftfUtilidad = new campoNumeros(campoNumeros.ENTEROS);
        jPanel5 = new javax.swing.JPanel();
        scroll = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Parametros A"));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("# de Juegos");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);
        jPanel1.add(jLabel1, gridBagConstraints);

        jLabel2.setText("Juegos p/hoja");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);
        jPanel1.add(jLabel2, gridBagConstraints);

        jLabel3.setText("Copias p/juego");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);
        jPanel1.add(jLabel3, gridBagConstraints);

        jLabel4.setText("Tamaño d/papel");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);
        jPanel1.add(jLabel4, gridBagConstraints);

        spinJuegos.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        spinJuegos.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinJuegosStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipadx = 26;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel1.add(spinJuegos, gridBagConstraints);

        spinDivisor.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        spinDivisor.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinDivisorStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 26;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel1.add(spinDivisor, gridBagConstraints);

        spinCopias.setModel(new javax.swing.SpinnerNumberModel(0, 0, 4, 1));
        spinCopias.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinCopiasStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 26;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel1.add(spinCopias, gridBagConstraints);

        spinPapel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Carta", "Oficio" }));
        spinPapel.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                spinPapelItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel1.add(spinPapel, gridBagConstraints);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Parametros B"));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel5.setText("Diseño");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);
        jPanel2.add(jLabel5, gridBagConstraints);

        jLabel6.setText("Tintas");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);
        jPanel2.add(jLabel6, gridBagConstraints);

        jLabel7.setText("Placas");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);
        jPanel2.add(jLabel7, gridBagConstraints);

        jLabel8.setText("Desperdicio");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);
        jPanel2.add(jLabel8, gridBagConstraints);

        ftfDiseño.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        ftfDiseño.setText("150.00");
        ftfDiseño.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                ftfDiseñoPropertyChange(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel2.add(ftfDiseño, gridBagConstraints);

        spinTintas.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        spinTintas.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinTintasStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel2.add(spinTintas, gridBagConstraints);

        ftfPlaca.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        ftfPlaca.setText("30.00");
        ftfPlaca.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                ftfPlacaPropertyChange(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel2.add(ftfPlaca, gridBagConstraints);

        spinDesperdicio.setModel(new javax.swing.SpinnerNumberModel(5, 0, null, 1));
        spinDesperdicio.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinDesperdicioStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel2.add(spinDesperdicio, gridBagConstraints);

        jLabel9.setText("%");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel2.add(jLabel9, gridBagConstraints);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Parametros C"));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel10.setText("<HTML>Costo de impresión<P>p/hoja</HTML>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);
        jPanel3.add(jLabel10, gridBagConstraints);

        cbFolio.setSelected(true);
        cbFolio.setText("<HTML>Costo de folio<P>p/juego</HTML>");
        cbFolio.setNextFocusableComponent(ftfFolio);
        cbFolio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFolioActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);
        jPanel3.add(cbFolio, gridBagConstraints);

        cbSuaje.setText("<HTML>Costo de suaje<P>p/hoja</HTML>");
        cbSuaje.setNextFocusableComponent(ftfSuaje);
        cbSuaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSuajeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);
        jPanel3.add(cbSuaje, gridBagConstraints);

        jLabel11.setText("Costo de corte");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);
        jPanel3.add(jLabel11, gridBagConstraints);

        jLabel12.setText("<HTML>Capacidad de<p>hojas p/corte</HTML>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);
        jPanel3.add(jLabel12, gridBagConstraints);

        ftfImpresion.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        ftfImpresion.setText("0.05");
        ftfImpresion.setNextFocusableComponent(cbFolio);
        ftfImpresion.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                ftfImpresionPropertyChange(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 26;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);
        jPanel3.add(ftfImpresion, gridBagConstraints);

        ftfFolio.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        ftfFolio.setText("0.05");
        ftfFolio.setNextFocusableComponent(cbSuaje);
        ftfFolio.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                ftfFolioPropertyChange(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 26;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);
        jPanel3.add(ftfFolio, gridBagConstraints);

        ftfSuaje.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        ftfSuaje.setText("0.02");
        ftfSuaje.setEnabled(false);
        ftfSuaje.setNextFocusableComponent(ftfCorte);
        ftfSuaje.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                ftfSuajePropertyChange(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 26;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);
        jPanel3.add(ftfSuaje, gridBagConstraints);

        ftfCorte.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        ftfCorte.setText("5.00");
        ftfCorte.setNextFocusableComponent(ftfCapacidad);
        ftfCorte.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                ftfCortePropertyChange(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 26;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);
        jPanel3.add(ftfCorte, gridBagConstraints);

        ftfCapacidad.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        ftfCapacidad.setText("500");
        ftfCapacidad.setNextFocusableComponent(ftfVendedor);
        ftfCapacidad.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                ftfCapacidadPropertyChange(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 31;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);
        jPanel3.add(ftfCapacidad, gridBagConstraints);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Parametros D"));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel13.setText("<HTML>Comisión al<P>Vendedor</HTML>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);
        jPanel4.add(jLabel13, gridBagConstraints);

        jLabel14.setText("Utilidad");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);
        jPanel4.add(jLabel14, gridBagConstraints);

        jLabel15.setText("%");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel4.add(jLabel15, gridBagConstraints);

        jLabel16.setText("%");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel4.add(jLabel16, gridBagConstraints);

        ftfVendedor.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        ftfVendedor.setText("10");
        ftfVendedor.setNextFocusableComponent(ftfUtilidad);
        ftfVendedor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                ftfVendedorPropertyChange(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel4.add(ftfVendedor, gridBagConstraints);

        ftfUtilidad.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        ftfUtilidad.setText("100");
        ftfUtilidad.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                ftfUtilidadPropertyChange(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel4.add(ftfUtilidad, gridBagConstraints);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Parametros E"));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbFolioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFolioActionPerformed
        ftfFolio.setEnabled(cbFolio.isSelected());
        calcula();
    }//GEN-LAST:event_cbFolioActionPerformed

    private void cbSuajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSuajeActionPerformed
        ftfSuaje.setEnabled(cbSuaje.isSelected());
        calcula();
    }//GEN-LAST:event_cbSuajeActionPerformed

    private void ftfPlacaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ftfPlacaPropertyChange
        if(evt.getPropertyName().equals("value") && !ftfPlaca.hasFocus()){
            calcula();
        }
    }//GEN-LAST:event_ftfPlacaPropertyChange

    private void ftfDiseñoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ftfDiseñoPropertyChange
        if(evt.getPropertyName().equals("value") && !ftfDiseño.hasFocus()){
            calcula();
        }
    }//GEN-LAST:event_ftfDiseñoPropertyChange

    private void ftfImpresionPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ftfImpresionPropertyChange
        if(evt.getPropertyName().equals("value") && !ftfImpresion.hasFocus()){
            calcula();
        }
    }//GEN-LAST:event_ftfImpresionPropertyChange

    private void ftfCortePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ftfCortePropertyChange
        if(evt.getPropertyName().equals("value") && !ftfCorte.hasFocus()){
            calcula();
        }
    }//GEN-LAST:event_ftfCortePropertyChange

    private void ftfFolioPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ftfFolioPropertyChange
        if(evt.getPropertyName().equals("value") && !ftfFolio.hasFocus()){
            calcula();
        }
    }//GEN-LAST:event_ftfFolioPropertyChange

    private void ftfCapacidadPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ftfCapacidadPropertyChange
        if(evt.getPropertyName().equals("value") && !ftfCapacidad.hasFocus()){
            calcula();
        }
    }//GEN-LAST:event_ftfCapacidadPropertyChange

    private void ftfSuajePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ftfSuajePropertyChange
        if(evt.getPropertyName().equals("value") && !ftfSuaje.hasFocus()){
            calcula();
        }        
    }//GEN-LAST:event_ftfSuajePropertyChange

    private void ftfVendedorPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ftfVendedorPropertyChange
        if(evt.getPropertyName().equals("value") && !ftfVendedor.hasFocus()){
            calcula();
        }
    }//GEN-LAST:event_ftfVendedorPropertyChange

    private void ftfUtilidadPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ftfUtilidadPropertyChange
        if(evt.getPropertyName().equals("value") && !ftfUtilidad.hasFocus()){
            calcula();
        }
    }//GEN-LAST:event_ftfUtilidadPropertyChange

    private void spinPapelItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_spinPapelItemStateChanged
        if(evt.getStateChange() == 1){
            calcula();
        }   
    }//GEN-LAST:event_spinPapelItemStateChanged

    private void spinJuegosStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinJuegosStateChanged
        calcula();
    }//GEN-LAST:event_spinJuegosStateChanged

    private void spinDivisorStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinDivisorStateChanged
        calcula();
    }//GEN-LAST:event_spinDivisorStateChanged

    private void spinCopiasStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinCopiasStateChanged
        calcula();
    }//GEN-LAST:event_spinCopiasStateChanged

    private void spinTintasStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinTintasStateChanged
        calcula();
    }//GEN-LAST:event_spinTintasStateChanged

    private void spinDesperdicioStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinDesperdicioStateChanged
        calcula();
    }//GEN-LAST:event_spinDesperdicioStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox cbFolio;
    private javax.swing.JCheckBox cbSuaje;
    private javax.swing.JFormattedTextField ftfCapacidad;
    private javax.swing.JFormattedTextField ftfCorte;
    private javax.swing.JFormattedTextField ftfDiseño;
    private javax.swing.JFormattedTextField ftfFolio;
    private javax.swing.JFormattedTextField ftfImpresion;
    private javax.swing.JFormattedTextField ftfPlaca;
    private javax.swing.JFormattedTextField ftfSuaje;
    private javax.swing.JFormattedTextField ftfUtilidad;
    private javax.swing.JFormattedTextField ftfVendedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel panel;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JSpinner spinCopias;
    private javax.swing.JSpinner spinDesperdicio;
    private javax.swing.JSpinner spinDivisor;
    private javax.swing.JSpinner spinJuegos;
    private javax.swing.JComboBox spinPapel;
    private javax.swing.JSpinner spinTintas;
    // End of variables declaration//GEN-END:variables
    diagTot     tot;
    tablaBase   tablaPapel;
    modeloPapel modelo;
}