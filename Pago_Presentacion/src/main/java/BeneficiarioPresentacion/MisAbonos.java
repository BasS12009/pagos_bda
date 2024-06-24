/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package BeneficiarioPresentacion;

import GUI.logIn;
import Utilerias.JButtonCellEditor;
import Utilerias.JButtonRenderer;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.TableColumnModel;
import negocio.PagoBO;

/**
 *
 * @author diana
 */
public class MisAbonos extends javax.swing.JFrame {
    PagoBO pagoBO;
     private int pagina=1;
    private int LIMITE=3;
    
    /**
     * Creates new form MisAbonos
     */
    public MisAbonos() {
        initComponents();
        this.setLocationRelativeTo(this);
        this.setSize(965, 610);
        cargarConfiguracionInicialTabla();
    }

    private void cargarConfiguracionInicialTabla() { 
        
        ActionListener onEliminarClickListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
               
                
            }               
        };
          
        
        TableColumnModel modeloColumnas = this.jTable1.getColumnModel();
        int indiceColumnaEliminar = 5;
        Color color = new Color(255, 105, 97);
        modeloColumnas.getColumn(indiceColumnaEliminar).setCellRenderer(new JButtonRenderer("Eliminar",color));
        modeloColumnas.getColumn(indiceColumnaEliminar).setCellEditor(new JButtonCellEditor("Eliminar", onEliminarClickListener));
              
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
        btnAgregarAbono = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnAtras = new javax.swing.JButton();
        btnSiguiente = new javax.swing.JButton();
        NumeroDePagina = new javax.swing.JTextField();
        logo = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        btnInicio = new javax.swing.JRadioButtonMenuItem();
        jMenu2 = new javax.swing.JMenu();
        btnPagos = new javax.swing.JRadioButtonMenuItem();
        jMenu3 = new javax.swing.JMenu();
        btnAbonos = new javax.swing.JRadioButtonMenuItem();
        jMenu4 = new javax.swing.JMenu();
        btnCuentasBancarias = new javax.swing.JRadioButtonMenuItem();
        jMenu5 = new javax.swing.JMenu();
        btnCerrarSesion = new javax.swing.JRadioButtonMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(175, 176, 212));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAgregarAbono.setBackground(new java.awt.Color(116, 114, 178));
        btnAgregarAbono.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        btnAgregarAbono.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarAbono.setText("+Agregar Abono");
        btnAgregarAbono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarAbonoActionPerformed(evt);
            }
        });
        jPanel1.add(btnAgregarAbono, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 160, 30));

        jLabel1.setFont(new java.awt.Font("Serif", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Mis Abonos");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 30, -1, -1));

        jScrollPane1.setBackground(new java.awt.Color(116, 114, 178));

        jTable1.setBackground(new java.awt.Color(228, 222, 235));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", "$2,500", "Enviado", "1er abono enviado", null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Cuenta", "Monto", "Estatus", "Comentarios", "Eliminar Abono"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 900, 230));

        btnAtras.setBackground(new java.awt.Color(12, 33, 63));
        btnAtras.setFont(new java.awt.Font("Segoe UI Symbol", 0, 24)); // NOI18N
        btnAtras.setForeground(new java.awt.Color(255, 255, 255));
        btnAtras.setText("←");
        btnAtras.setContentAreaFilled(false);
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });
        jPanel1.add(btnAtras, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 480, -1, -1));

        btnSiguiente.setBackground(new java.awt.Color(12, 33, 63));
        btnSiguiente.setFont(new java.awt.Font("Segoe UI Symbol", 0, 24)); // NOI18N
        btnSiguiente.setForeground(new java.awt.Color(255, 255, 255));
        btnSiguiente.setText("→");
        btnSiguiente.setContentAreaFilled(false);
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });
        jPanel1.add(btnSiguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 480, -1, -1));

        NumeroDePagina.setBackground(new java.awt.Color(204, 169, 221));
        NumeroDePagina.setForeground(new java.awt.Color(255, 255, 255));
        NumeroDePagina.setText("1");
        NumeroDePagina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumeroDePaginaActionPerformed(evt);
            }
        });
        jPanel1.add(NumeroDePagina, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 480, 20, -1));

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/potroPagoChico.png"))); // NOI18N
        jPanel1.add(logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 0, 150, 80));

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
        jMenu2.setText("Mis pagos");

        btnPagos.setForeground(new java.awt.Color(116, 114, 178));
        btnPagos.setSelected(true);
        btnPagos.setText("Ver mis pagos");
        btnPagos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagosActionPerformed(evt);
            }
        });
        jMenu2.add(btnPagos);

        jMenuBar1.add(jMenu2);

        jMenu3.setForeground(new java.awt.Color(116, 114, 178));
        jMenu3.setText("Mis abonos");

        btnAbonos.setForeground(new java.awt.Color(116, 114, 178));
        btnAbonos.setSelected(true);
        btnAbonos.setText("Ver mis abonos");
        btnAbonos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbonosActionPerformed(evt);
            }
        });
        jMenu3.add(btnAbonos);

        jMenuBar1.add(jMenu3);

        jMenu4.setForeground(new java.awt.Color(116, 114, 178));
        jMenu4.setText("Mis cuentas bancarias");

        btnCuentasBancarias.setForeground(new java.awt.Color(116, 114, 178));
        btnCuentasBancarias.setSelected(true);
        btnCuentasBancarias.setText("Ver mis cuentas bancarias");
        btnCuentasBancarias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCuentasBancariasActionPerformed(evt);
            }
        });
        jMenu4.add(btnCuentasBancarias);

        jMenuBar1.add(jMenu4);

        jMenu5.setForeground(new java.awt.Color(116, 114, 178));
        jMenu5.setText("Cerrar Sesion");

        btnCerrarSesion.setForeground(new java.awt.Color(116, 114, 178));
        btnCerrarSesion.setSelected(true);
        btnCerrarSesion.setText("Cerrar Sesion");
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });
        jMenu5.add(btnCerrarSesion);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed
        // TODO add your handling code here:
         MenuBeneficiario menuBeneficiario = new MenuBeneficiario();
        menuBeneficiario.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnInicioActionPerformed

    private void btnPagosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagosActionPerformed
        // TODO add your handling code here:
        MisPagos misPagos = new MisPagos(pagoBO);
        misPagos.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnPagosActionPerformed

    private void btnAbonosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbonosActionPerformed
        // TODO add your handling code here:
        MisAbonos misAbonos = new MisAbonos();
        misAbonos.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAbonosActionPerformed

    private void btnCuentasBancariasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCuentasBancariasActionPerformed
        // TODO add your handling code here:
        MisCuentasBancarias misCuentasBancarias = new MisCuentasBancarias(pagoBO);
        misCuentasBancarias.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCuentasBancariasActionPerformed

    private void btnAgregarAbonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarAbonoActionPerformed
        // TODO add your handling code here:
        AgregarAbono agregarAbono = new AgregarAbono();
        agregarAbono.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAgregarAbonoActionPerformed

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        // TODO add your handling code here:
        logIn lIn = new logIn(pagoBO);
        lIn.setVisible(true);
        this.dispose(); 
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
     
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void NumeroDePaginaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NumeroDePaginaActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_NumeroDePaginaActionPerformed

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
            java.util.logging.Logger.getLogger(MisAbonos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MisAbonos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MisAbonos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MisAbonos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MisAbonos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField NumeroDePagina;
    private javax.swing.JRadioButtonMenuItem btnAbonos;
    private javax.swing.JButton btnAgregarAbono;
    private javax.swing.JButton btnAtras;
    private javax.swing.JRadioButtonMenuItem btnCerrarSesion;
    private javax.swing.JRadioButtonMenuItem btnCuentasBancarias;
    private javax.swing.JRadioButtonMenuItem btnInicio;
    private javax.swing.JRadioButtonMenuItem btnPagos;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel logo;
    // End of variables declaration//GEN-END:variables
}
