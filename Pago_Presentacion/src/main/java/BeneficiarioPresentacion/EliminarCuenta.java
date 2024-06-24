/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package BeneficiarioPresentacion;

import negocio.PagoBO;

/**
 *
 * @author diana
 */
public class EliminarCuenta extends javax.swing.JFrame {
    PagoBO pagoBO;
    
    
    /**
     * Creates new form ModificarCuenta
     */
    public EliminarCuenta(PagoBO pagoBO) {
        initComponents();
        this.setLocationRelativeTo(this);
        this.setSize(965, 610);
        this.pagoBO= pagoBO;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAbono = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnRegresar3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        btnAbono1 = new javax.swing.JButton();
        logo = new javax.swing.JLabel();

        btnAbono.setText("Agregar Abono");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(175, 176, 212));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnRegresar3.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btnRegresar3.setForeground(new java.awt.Color(255, 255, 255));
        btnRegresar3.setText("←");
        btnRegresar3.setContentAreaFilled(false);
        btnRegresar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresar3ActionPerformed(evt);
            }
        });
        jPanel1.add(btnRegresar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 40));

        jLabel1.setFont(new java.awt.Font("Serif", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Eliminar Cuenta");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 900, -1));
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 160, 270, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI Symbol", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Clabe");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, -1, -1));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 340, 270, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI Symbol", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Banco");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 300, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI Symbol", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("No. Cuenta");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 120, -1, -1));
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 250, 270, 30));

        btnAbono1.setBackground(new java.awt.Color(116, 114, 178));
        btnAbono1.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        btnAbono1.setForeground(new java.awt.Color(255, 255, 255));
        btnAbono1.setText("Eliminar Cuenta");
        btnAbono1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbono1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnAbono1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 420, -1, 30));

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/potroPagoChico.png"))); // NOI18N
        jPanel1.add(logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 480, 180, 80));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 904, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresar3ActionPerformed
        // TODO add your handling code here:
        MisCuentasBancarias misCuentasBancarias = new MisCuentasBancarias(pagoBO);
        misCuentasBancarias.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresar3ActionPerformed

    private void btnAbono1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbono1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAbono1ActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbono;
    private javax.swing.JButton btnAbono1;
    private javax.swing.JButton btnRegresar3;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel logo;
    // End of variables declaration//GEN-END:variables
}
