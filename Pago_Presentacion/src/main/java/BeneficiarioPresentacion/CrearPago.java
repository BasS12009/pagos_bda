/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package BeneficiarioPresentacion;

import DTOs.CuentaBancariaDTO;
import DTOs.EstatusDTO;
import DTOs.PagoDTO;
import DTOs.TiposDTO;
import Utilerias.TiposComboBoxModel;
import excepcionBO.ExcepcionBO;
import java.awt.Component;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;
import negocio.PagoBO;


/**
 *
 * @author diana
 */
public class CrearPago extends javax.swing.JFrame {
    PagoBO pagoBO;
    List<TiposDTO> tiposCrear;
    TiposDTO tipoSeleccionado;
    List<CuentaBancariaDTO> cuentasCrear;
    CuentaBancariaDTO cuentaSeleccionada;
    
    /**
     * Creates new form CrearPago
     */
    public CrearPago(PagoBO pagoBO) {
        initComponents();
        this.setLocationRelativeTo(this);
        this.setSize(925, 610);
        this.pagoBO=pagoBO;
        
        llenarComboBoxTipos();
        llenarComboBoxCuentas();
    }
    
    
    public void llenarComboBoxCuentas() {
        try {
            jComboCuenta.removeAllItems();

            cuentasCrear = pagoBO.obtenerTodasLasCuentasBancariasPorBeneficiario(pagoBO.getId());

            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            for (CuentaBancariaDTO cuenta : cuentasCrear) {
                model.addElement(cuenta.getNumeroCuenta()); 
            }

            jComboCuenta.setModel(model);

            jComboCuenta.setRenderer(new ListCellRenderer<String>() {
                @Override
                public Component getListCellRendererComponent(JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) {
                    JLabel label = new JLabel(value);
                    if (isSelected) {
                        label.setBackground(list.getSelectionBackground());
                        label.setForeground(list.getSelectionForeground());
                    } else {
                        label.setBackground(list.getBackground());
                        label.setForeground(list.getForeground());
                    }
                    label.setOpaque(true);
                    return label;
                }
            });
        } catch (ExcepcionBO ex) {
            Logger.getLogger(CrearPago.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void llenarComboBoxTipos() {
        try {
            jComboTipo.removeAllItems();

            tiposCrear = pagoBO.obtenerTodosLosTipos();

            TiposComboBoxModel model = new TiposComboBoxModel(tiposCrear);
            jComboTipo.setModel(model);

            jComboTipo.setRenderer(new ListCellRenderer<String>() {
                @Override
                public Component getListCellRendererComponent(JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) {
                    JLabel label = new JLabel(value);
                    if (isSelected) {
                        label.setBackground(list.getSelectionBackground());
                        label.setForeground(list.getSelectionForeground());
                    } else {
                        label.setBackground(list.getBackground());
                        label.setForeground(list.getForeground());
                    }
                    label.setOpaque(true);
                    return label;
                }
            });
        } catch (ExcepcionBO ex) {
            Logger.getLogger(CrearPago.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        btnRegresar3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboTipo = new javax.swing.JComboBox<>();
        Crear = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        textoMonto = new javax.swing.JTextField();
        jComboCuenta = new javax.swing.JComboBox<>();
        logo = new javax.swing.JLabel();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(175, 176, 212));

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

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Crear Pago");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI Symbol", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Cuenta");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 310, -1, -1));
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 380, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI Symbol", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Tipo");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, -1, -1));

        jPanel1.add(jComboTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 190, 380, 40));

        Crear.setBackground(new java.awt.Color(116, 114, 178));
        Crear.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        Crear.setForeground(new java.awt.Color(255, 255, 255));
        Crear.setText("Crear Pago");
        Crear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CrearActionPerformed(evt);
            }
        });
        jPanel1.add(Crear, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 480, 100, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI Symbol", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Ingresa el monto:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 230, -1, -1));
        jPanel1.add(textoMonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 260, 380, 40));

        jPanel1.add(jComboCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 350, 380, 40));

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/potroPagoChico.png"))); // NOI18N
        jPanel1.add(logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 470, 180, 80));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 902, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresar3ActionPerformed
        // TODO add your handling code here:
        MisPagos misPagos = new MisPagos(pagoBO);
        misPagos.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresar3ActionPerformed

    private void CrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CrearActionPerformed
            try {
            PagoDTO pago = new PagoDTO();
            
            String montoTexto = textoMonto.getText().trim();
            if (montoTexto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un monto válido", "Error", JOptionPane.ERROR_MESSAGE);
                return; 
            }

            BigDecimal monto;
            try {
                monto = new BigDecimal(montoTexto);
                if (monto.compareTo(BigDecimal.ZERO) <= 0) {
                    JOptionPane.showMessageDialog(this, "El monto debe ser mayor que cero", "Error", JOptionPane.ERROR_MESSAGE);
                    return; 
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El monto ingresado no es válido", "Error", JOptionPane.ERROR_MESSAGE);
                return; 
            }

            String nombreTipoSeleccionado = (String) jComboTipo.getSelectedItem();

            for (TiposDTO tipo : tiposCrear) {
                String nombreTipo = tipo.getNombre() + " " + tipo.getNumeroParcialidades() + " Pagos";
                if (nombreTipo.equals(nombreTipoSeleccionado)) {
                    tipoSeleccionado = tipo;
                    break;
                }
            }

            if (tipoSeleccionado == null) {
                JOptionPane.showMessageDialog(this, "No se encontró el tipo seleccionado en la lista", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            pago.setTipo(tipoSeleccionado);
            
            String nombreCuentaSeleccionado = (String) jComboCuenta.getSelectedItem();

            for (CuentaBancariaDTO cuenta : cuentasCrear) {
                String nombreCuenta = cuenta.getNumeroCuenta();
                if (nombreCuenta.equals(nombreCuentaSeleccionado)) {
                    cuentaSeleccionada = cuenta;
                    break;
                }
            }

            if (cuentaSeleccionada == null) {
                JOptionPane.showMessageDialog(this, "No se encontró la cuenta seleccionada en la lista", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            pago.setBeneficiario(pagoBO.buscarBeneficiarioPorId(pagoBO.getId()));

            pago.setCuentas(Arrays.asList(cuentaSeleccionada)); 

            pago.setFechaHora(LocalDateTime.now());

            pago.setMonto(monto);
            
            List<EstatusDTO> listaEstatus = new ArrayList<>();
            EstatusDTO estatusCreado = null;
            List<EstatusDTO> estatus = pagoBO.obtenerEstatus();
            for (EstatusDTO estatu : estatus) {
                if ("Creado".equals(estatu.getNombre())) {
                    estatusCreado = estatu;
                    break;
                }
            }

            if (estatusCreado == null) {
                JOptionPane.showMessageDialog(this, "No se encontró el estatus 'Creado' en la lista", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            listaEstatus.add(estatusCreado);
            pago.setEstatus(listaEstatus);
            
                pagoBO.guardarPago(pago, estatusCreado);
            JOptionPane.showMessageDialog(this, "Pago creado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            MisPagos pagos=new MisPagos(pagoBO);
            pagos.show();
            this.dispose();
        } catch (ExcepcionBO ex) {
            Logger.getLogger(CrearPago.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El monto ingresado no es válido", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            Logger.getLogger(CrearPago.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Ocurrió un error al crear el pago", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_CrearActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Crear;
    private javax.swing.JButton btnRegresar3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboCuenta;
    private javax.swing.JComboBox<String> jComboTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel logo;
    private javax.swing.JTextField textoMonto;
    // End of variables declaration//GEN-END:variables
}
