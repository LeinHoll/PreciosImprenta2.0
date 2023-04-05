package visual;

import java.text.DecimalFormat;

public class diagTot extends javax.swing.JDialog {


    public diagTot(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    public void setData(double subTotal, double IVA, double Total, double comision, double Hojas, double Cortes){
        labSubTotal .setText("$ " + textoDecimal.format(subTotal));
        labIva      .setText("$ " + textoDecimal.format(IVA     ));
        labTotal    .setText("$ " + textoDecimal.format(Total   ));
        labComision .setText("$ " + textoDecimal.format(comision));
        labHojas    .setText(textoNumeroEntero  .format(Hojas   ));
        labCortes   .setText(textoNumeroEntero  .format(Cortes  ));
    }
            

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        labSubTotal = new javax.swing.JLabel();
        labIva = new javax.swing.JLabel();
        labTotal = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        labComision = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        labHojas = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        labCortes = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Resultados");
        setAlwaysOnTop(true);
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Subtotal:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        getContentPane().add(jLabel1, gridBagConstraints);

        jLabel2.setText("IVA (16%):");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        getContentPane().add(jLabel2, gridBagConstraints);

        jLabel3.setText("Total:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        getContentPane().add(jLabel3, gridBagConstraints);

        labSubTotal.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        labSubTotal.setText("$ 0.00");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        getContentPane().add(labSubTotal, gridBagConstraints);

        labIva.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        labIva.setText("$ 0.00");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        getContentPane().add(labIva, gridBagConstraints);

        labTotal.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        labTotal.setText("$ 0.00");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        getContentPane().add(labTotal, gridBagConstraints);

        jLabel7.setText("Comisión al Vendedor:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        getContentPane().add(jLabel7, gridBagConstraints);

        labComision.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        labComision.setText("$ 0.00");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        getContentPane().add(labComision, gridBagConstraints);

        jLabel9.setText("# de Hojas a usar:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        getContentPane().add(jLabel9, gridBagConstraints);

        labHojas.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        labHojas.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        getContentPane().add(labHojas, gridBagConstraints);

        jLabel11.setText("# de Cortes a realizar:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        getContentPane().add(jLabel11, gridBagConstraints);

        labCortes.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        labCortes.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        getContentPane().add(labCortes, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel labComision;
    private javax.swing.JLabel labCortes;
    private javax.swing.JLabel labHojas;
    private javax.swing.JLabel labIva;
    private javax.swing.JLabel labSubTotal;
    private javax.swing.JLabel labTotal;
    // End of variables declaration//GEN-END:variables
    DecimalFormat textoDecimal      = new DecimalFormat("#,##0.00"  );
    DecimalFormat textoNumeroEntero = new DecimalFormat("#,##0"     );
}
