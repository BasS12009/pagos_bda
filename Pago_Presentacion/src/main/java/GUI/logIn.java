/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import AdministradorPresentacion.MenuAdministrador;

/**
 *
 * @author diana
 */
public class logIn extends javax.swing.JFrame {

    /**
     * Creates new form logIn
     */
    public logIn() {
        initComponents();
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
        clave = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        contraseña = new javax.swing.JTextField();
        iniciarSesionBeneficiario = new javax.swing.JButton();
        iniciarSesionAdmin = new javax.swing.JButton();
        clave1 = new javax.swing.JTextField();
        clave2 = new javax.swing.JTextField();
        clave3 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(175, 176, 212));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        clave.setBackground(new java.awt.Color(228, 222, 235));
        clave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                claveActionPerformed(evt);
            }
        });
        jPanel1.add(clave, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 310, 260, 30));

        jLabel1.setFont(new java.awt.Font("Serif", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Iniciar Sesion");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI Symbol", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Clave:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 280, -1, 20));

        jLabel3.setFont(new java.awt.Font("Segoe UI Symbol", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Contraseña:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 350, -1, -1));

        contraseña.setBackground(new java.awt.Color(228, 222, 235));
        jPanel1.add(contraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 390, 260, 30));

        iniciarSesionBeneficiario.setBackground(new java.awt.Color(116, 114, 178));
        iniciarSesionBeneficiario.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        iniciarSesionBeneficiario.setForeground(new java.awt.Color(255, 255, 255));
        iniciarSesionBeneficiario.setText("Iniciar Sesion Beneficiario");
        iniciarSesionBeneficiario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniciarSesionBeneficiarioActionPerformed(evt);
            }
        });
        jPanel1.add(iniciarSesionBeneficiario, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 470, 210, 40));

        iniciarSesionAdmin.setBackground(new java.awt.Color(116, 114, 178));
        iniciarSesionAdmin.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        iniciarSesionAdmin.setForeground(new java.awt.Color(255, 255, 255));
        iniciarSesionAdmin.setText("Iniciar Sesion Administrador");
        iniciarSesionAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniciarSesionAdminActionPerformed(evt);
            }
        });
        jPanel1.add(iniciarSesionAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 470, 210, 40));

        clave1.setBackground(new java.awt.Color(228, 222, 235));
        clave1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clave1ActionPerformed(evt);
            }
        });
        jPanel1.add(clave1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 310, 260, 30));

        clave2.setBackground(new java.awt.Color(228, 222, 235));
        clave2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clave2ActionPerformed(evt);
            }
        });
        jPanel1.add(clave2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 310, 260, 30));

        clave3.setBackground(new java.awt.Color(228, 222, 235));
        clave3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clave3ActionPerformed(evt);
            }
        });
        jPanel1.add(clave3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 310, 260, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void claveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_claveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_claveActionPerformed

    private void iniciarSesionBeneficiarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iniciarSesionBeneficiarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_iniciarSesionBeneficiarioActionPerformed

    private void iniciarSesionAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iniciarSesionAdminActionPerformed
        // TODO add your handling code here:
        MenuAdministrador menu = new MenuAdministrador();
        menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_iniciarSesionAdminActionPerformed

    private void clave1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clave1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clave1ActionPerformed

    private void clave2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clave2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clave2ActionPerformed

    private void clave3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clave3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clave3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(logIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(logIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(logIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(logIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new logIn().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField clave;
    private javax.swing.JTextField clave1;
    private javax.swing.JTextField clave2;
    private javax.swing.JTextField clave3;
    private javax.swing.JTextField contraseña;
    private javax.swing.JButton iniciarSesionAdmin;
    private javax.swing.JButton iniciarSesionBeneficiario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
