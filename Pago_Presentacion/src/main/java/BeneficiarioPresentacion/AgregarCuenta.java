/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package BeneficiarioPresentacion;

import DTOs.BeneficiarioDTO;
import DTOs.CuentaBancariaDTO;
import excepcion.ExcepcionPresentacion;
import excepcionBO.ExcepcionBO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import negocio.PagoBO;

/**
 *
 * @author diana
 */
public class AgregarCuenta extends javax.swing.JFrame {
    PagoBO pagoBO;
    
    /**
     * Creates new form NuevaCuenta
     */
    public AgregarCuenta(PagoBO pagoBO) {
        initComponents();
        this.setLocationRelativeTo(this);
        this.setSize(965, 610);
        this.pagoBO=pagoBO;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnRegresar3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        textoNumero = new javax.swing.JTextField();
        textoClabe = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        agregar = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        textoBanco = new javax.swing.JTextField();
        logo = new javax.swing.JLabel();

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
        jLabel1.setText("Agregar Cuenta");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 30, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI Symbol", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("No. Cuenta");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 100, -1, -1));

        textoNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoNumeroActionPerformed(evt);
            }
        });
        jPanel1.add(textoNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 130, 270, 30));
        jPanel1.add(textoClabe, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 230, 270, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI Symbol", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Clabe");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 200, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI Symbol", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Banco");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 280, -1, -1));

        agregar.setBackground(new java.awt.Color(116, 114, 178));
        agregar.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        agregar.setForeground(new java.awt.Color(255, 255, 255));
        agregar.setText("Agregar Cuenta");
        agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarActionPerformed(evt);
            }
        });
        jPanel1.add(agregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(399, 460, 140, 30));
        jPanel1.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 230, 270, 30));
        jPanel1.add(textoBanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 310, 270, 30));

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/potroPagoChico.png"))); // NOI18N
        jPanel1.add(logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 470, 180, 80));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 924, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresar3ActionPerformed
        // TODO add your handling code here:
        MisCuentasBancarias misCuentasBancarias = new MisCuentasBancarias(pagoBO);
        misCuentasBancarias.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresar3ActionPerformed

    private void textoNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoNumeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textoNumeroActionPerformed

    private void agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarActionPerformed
       try {
            // Obtener datos del formulario
            String numeroCuenta = textoNumero.getText();
            String clabe = textoClabe.getText();
            String banco = textoBanco.getText();

            // Validar que los campos no estén vacíos
            if (numeroCuenta.isEmpty() || clabe.isEmpty() || banco.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Crear objeto CuentaBancariaDTO
            CuentaBancariaDTO nuevaCuenta = new CuentaBancariaDTO();
            nuevaCuenta.setNumeroCuenta(numeroCuenta);
            nuevaCuenta.setClave(clabe);
            nuevaCuenta.setBanco(banco);
            nuevaCuenta.setEliminada(false);
            Long idBeneficiario = pagoBO.getId();
            if (idBeneficiario == null) {
                JOptionPane.showMessageDialog(this, "ID de beneficiario no válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            System.out.println(idBeneficiario);
            if (idBeneficiario != null) {
                BeneficiarioDTO beneficiario = pagoBO.buscarBeneficiarioPorId(idBeneficiario);
                if (beneficiario != null) {
                    nuevaCuenta.setBeneficiario(beneficiario);
                    beneficiario.getCuentasBancarias().add(nuevaCuenta);
                    
                    pagoBO.guardarCuentaBancaria(nuevaCuenta);
                    System.out.println(nuevaCuenta.getBeneficiario().getId());
                    JOptionPane.showMessageDialog(this, "Cuenta bancaria agregada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    textoNumero.setText("");
                    textoClabe.setText("");
                    textoBanco.setText("");
                    
                    MisCuentasBancarias cuentas=new MisCuentasBancarias(pagoBO);
                    cuentas.show();
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Beneficiario no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "ID de beneficiario no válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (ExcepcionBO ex) {
           try {
               throw new ExcepcionPresentacion(ex.getMessage());
           } catch (ExcepcionPresentacion ex1) {
               Logger.getLogger(AgregarCuenta.class.getName()).log(Level.SEVERE, null, ex1);
           }
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_agregarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregar;
    private javax.swing.JButton btnRegresar3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JLabel logo;
    private javax.swing.JTextField textoBanco;
    private javax.swing.JTextField textoClabe;
    private javax.swing.JTextField textoNumero;
    // End of variables declaration//GEN-END:variables
}
