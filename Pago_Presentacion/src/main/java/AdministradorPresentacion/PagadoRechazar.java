/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package AdministradorPresentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.TableColumnModel;
import utilerias.JButtonCellEditor;
import utilerias.JButtonRenderer;

/**
 *
 * @author diana
 */
public class PagadoRechazar extends javax.swing.JFrame {

    /**
     * Creates new form PagadoRechazar
     */
    public PagadoRechazar() {
        initComponents();
        this.setLocationRelativeTo(this);
        this.setSize(965, 610);
        cargarConfiguracionInicialTabla();
    }

    private void cargarConfiguracionInicialTabla() { 
        
        ActionListener onPagadoClickListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
               
                
            }               
        };
            
        TableColumnModel modeloColumnas = this.tblPagadoRechazado.getColumnModel();
        modeloColumnas.getColumn(3).setCellRenderer(new JButtonRenderer("Pagado"));
        modeloColumnas.getColumn(3).setCellEditor(new JButtonCellEditor("Pagado",onPagadoClickListener));
        
        ActionListener onRechazadoClickListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
               
                
            }               
        };

        modeloColumnas.getColumn(4).setCellRenderer(new JButtonRenderer("Rechazado"));
        modeloColumnas.getColumn(4).setCellEditor(new JButtonCellEditor("Rechazado",onRechazadoClickListener));        
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
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPagadoRechazado = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        btnInicio = new javax.swing.JRadioButtonMenuItem();
        jMenu2 = new javax.swing.JMenu();
        btnAprobacionRechazar = new javax.swing.JRadioButtonMenuItem();
        jMenu3 = new javax.swing.JMenu();
        btnReportePago = new javax.swing.JRadioButtonMenuItem();
        jMenu4 = new javax.swing.JMenu();
        btnAdministracionBeneficiarios = new javax.swing.JRadioButtonMenuItem();
        jMenu5 = new javax.swing.JMenu();
        btnPagadoRechazar = new javax.swing.JRadioButtonMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(175, 176, 212));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Serif", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Pagado/Rechazado");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 40, -1, -1));

        tblPagadoRechazado.setBackground(new java.awt.Color(228, 222, 235));
        tblPagadoRechazado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Tipo", "Monto", "Beneficiario", "Pagado", "Rechazar"
            }
        ));
        jScrollPane1.setViewportView(tblPagadoRechazado);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 120, -1, 280));

        jMenuBar1.setBackground(new java.awt.Color(228, 222, 235));
        jMenuBar1.setForeground(new java.awt.Color(116, 114, 178));

        jMenu1.setForeground(new java.awt.Color(116, 114, 178));
        jMenu1.setText("Inicio");

        btnInicio.setForeground(new java.awt.Color(116, 114, 178));
        btnInicio.setSelected(true);
        btnInicio.setText("Inicio");
        btnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInicioActionPerformed(evt);
            }
        });
        jMenu1.add(btnInicio);

        jMenuBar1.add(jMenu1);

        jMenu2.setForeground(new java.awt.Color(116, 114, 178));
        jMenu2.setText("Aprobacion/Rechazar");

        btnAprobacionRechazar.setForeground(new java.awt.Color(116, 114, 178));
        btnAprobacionRechazar.setSelected(true);
        btnAprobacionRechazar.setText("Ver Aprobacion/Rechazar");
        btnAprobacionRechazar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAprobacionRechazarActionPerformed(evt);
            }
        });
        jMenu2.add(btnAprobacionRechazar);

        jMenuBar1.add(jMenu2);

        jMenu3.setForeground(new java.awt.Color(116, 114, 178));
        jMenu3.setText("Reporte Pago");

        btnReportePago.setForeground(new java.awt.Color(116, 114, 178));
        btnReportePago.setSelected(true);
        btnReportePago.setText("Ver Reporte de Pago");
        btnReportePago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportePagoActionPerformed(evt);
            }
        });
        jMenu3.add(btnReportePago);

        jMenuBar1.add(jMenu3);

        jMenu4.setForeground(new java.awt.Color(116, 114, 178));
        jMenu4.setText("Administracion de Beneficiarios");

        btnAdministracionBeneficiarios.setForeground(new java.awt.Color(116, 114, 178));
        btnAdministracionBeneficiarios.setSelected(true);
        btnAdministracionBeneficiarios.setText("Ver Administracion de Beneficiarios");
        btnAdministracionBeneficiarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdministracionBeneficiariosActionPerformed(evt);
            }
        });
        jMenu4.add(btnAdministracionBeneficiarios);

        jMenuBar1.add(jMenu4);

        jMenu5.setForeground(new java.awt.Color(116, 114, 178));
        jMenu5.setText("Pagado/Rechazar");

        btnPagadoRechazar.setForeground(new java.awt.Color(116, 114, 178));
        btnPagadoRechazar.setSelected(true);
        btnPagadoRechazar.setText("Ver Pagado/Rechazar");
        btnPagadoRechazar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagadoRechazarActionPerformed(evt);
            }
        });
        jMenu5.add(btnPagadoRechazar);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 960, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed
        // TODO add your handling code here:
         MenuAdministrador menuAdministrador = new MenuAdministrador();
        menuAdministrador.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnInicioActionPerformed

    private void btnAprobacionRechazarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAprobacionRechazarActionPerformed
        // TODO add your handling code here:
        AprobacionRechazar aprobacionRechazar = new AprobacionRechazar();
        aprobacionRechazar.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAprobacionRechazarActionPerformed

    private void btnReportePagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportePagoActionPerformed
        // TODO add your handling code here:
        ReportePago reportePago = new ReportePago();
        reportePago.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnReportePagoActionPerformed

    private void btnAdministracionBeneficiariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdministracionBeneficiariosActionPerformed
        // TODO add your handling code here:
         AdministracionBeneficiarios administracionBeneficiarios = new AdministracionBeneficiarios();
        administracionBeneficiarios.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAdministracionBeneficiariosActionPerformed

    private void btnPagadoRechazarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagadoRechazarActionPerformed
        // TODO add your handling code here:
         PagadoRechazar pagadoRechazar = new PagadoRechazar();
        pagadoRechazar.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnPagadoRechazarActionPerformed

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
            java.util.logging.Logger.getLogger(PagadoRechazar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PagadoRechazar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PagadoRechazar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PagadoRechazar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PagadoRechazar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButtonMenuItem btnAdministracionBeneficiarios;
    private javax.swing.JRadioButtonMenuItem btnAprobacionRechazar;
    private javax.swing.JRadioButtonMenuItem btnInicio;
    private javax.swing.JRadioButtonMenuItem btnPagadoRechazar;
    private javax.swing.JRadioButtonMenuItem btnReportePago;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPagadoRechazado;
    // End of variables declaration//GEN-END:variables
}
