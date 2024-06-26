/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package BeneficiarioPresentacion;

import DTOs.BeneficiarioDTO;
import DTOs.CuentaBancariaDTO;
import DTOs.EstatusDTO;
import DTOs.PagoDTO;
import DTOs.TiposDTO;
import Utilerias.TiposComboBoxModel;
import excepcion.ExcepcionPresentacion;
import excepcionBO.ExcepcionBO;
import java.awt.Component;
import java.math.BigDecimal;
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
public class ModificarPago extends javax.swing.JFrame {
    PagoBO pagoBO;
    List<TiposDTO> tiposCrear;
    TiposDTO tipoSeleccionado;
    List<CuentaBancariaDTO> cuentasCrear;
    CuentaBancariaDTO cuentaSeleccionada;
    long id;
    
    /**
     * Creates new form ModificarPago
     */
    public ModificarPago(PagoBO pagoBO,long id) { 
        initComponents();
        this.setLocationRelativeTo(this);
        this.setSize(965, 610);
        this.id=id;
        this.pagoBO=pagoBO;
        
        inicializarValoresInicialesC();
        inicializarValoresInicialesT();
        
    }

    public void inicializarValoresInicialesC() {
    try {
        
        // Llenar el JComboBox de cuentas
        llenarComboBoxCuentas();
        PagoDTO pago=pagoBO.buscarPagoPorId(id);
        textoMonto.setText(String.valueOf(pago.getMonto()));
        // Obtener el número de cuenta del pago y establecerlo como seleccionado en el JComboBox
        if (pago != null && pago.getCuentas() != null && !pago.getCuentas().isEmpty()) {
            String numeroCuentaPago = pago.getCuentas().get(0).getNumeroCuenta();
            jComboBoxCuenta.setSelectedItem(numeroCuentaPago);
        }
    } catch (ExcepcionBO ex) {
        Logger.getLogger(CrearPago.class.getName()).log(Level.SEVERE, null, ex);
    }
}
        
    public void inicializarValoresInicialesT() {
    try {
            llenarComboBoxTipos();
            PagoDTO pago=pagoBO.buscarPagoPorId(id);

            if (pago != null && pago.getTipo() != null) {
                String nombreTipoPago = pago.getTipo().getNombre() + " " + pago.getTipo().getNumeroParcialidades() + " Pagos";
                jComboBoxTipo.setSelectedItem(nombreTipoPago);
            }
        } catch (ExcepcionBO ex) {
            Logger.getLogger(CrearPago.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void llenarComboBoxCuentas() {
        try {
            jComboBoxCuenta.removeAllItems();

            cuentasCrear = pagoBO.obtenerTodasLasCuentasBancariasPorBeneficiario(pagoBO.getId());

            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            for (CuentaBancariaDTO cuenta : cuentasCrear) {
                model.addElement(cuenta.getNumeroCuenta()); 
            }

            jComboBoxCuenta.setModel(model);

            jComboBoxCuenta.setRenderer(new ListCellRenderer<String>() {
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
            jComboBoxTipo.removeAllItems();

            tiposCrear = pagoBO.obtenerTodosLosTipos();

            TiposComboBoxModel model = new TiposComboBoxModel(tiposCrear);
            jComboBoxTipo.setModel(model);

            jComboBoxTipo.setRenderer(new ListCellRenderer<String>() {
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

        jPanel1 = new javax.swing.JPanel();
        btnRegresar3 = new javax.swing.JButton();
        jComboBoxTipo = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        textoMonto = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jComboBoxCuenta = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
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

        jComboBoxTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTipoActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBoxTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 170, 270, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI Symbol", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Tipo ");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 130, -1, -1));
        jPanel1.add(textoMonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 260, 270, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI Symbol", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Monto");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 230, -1, -1));

        jPanel1.add(jComboBoxCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 350, 270, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI Symbol", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Cuenta");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 310, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Modificar Pago");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 30, -1, -1));

        jButton1.setBackground(new java.awt.Color(116, 114, 178));
        jButton1.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Modificar Pago");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 490, -1, -1));

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/potroPagoChico.png"))); // NOI18N
        jPanel1.add(logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 480, 180, 80));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 915, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresar3ActionPerformed
        // TODO add your handling code here:
        MisPagos misPagos = new MisPagos(pagoBO);
        misPagos.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresar3ActionPerformed

    private void jComboBoxTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxTipoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            PagoDTO pago = pagoBO.buscarPagoPorId(id);

            if (pago != null) {
                if (pago.getAbonos() != null && !pago.getAbonos().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No se puede modificar el tipo de pago porque ya se han realizado abonos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
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
            pago.setMonto(monto);

            String nombreTipoSeleccionado = (String) jComboBoxTipo.getSelectedItem();

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
            
            String nombreCuentaSeleccionado = (String) jComboBoxCuenta.getSelectedItem();

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
            pago.setCuentas(Arrays.asList(cuentaSeleccionada)); 
            } else {
                System.out.println("La cuenta no se encontró para el ID proporcionado: " + id);
            }
            
            
            List<EstatusDTO> listaEstatus = new ArrayList<>();
            EstatusDTO estatusCreado = null;
            List<EstatusDTO> estatus = pagoBO.obtenerEstatus();
            for (EstatusDTO estatu : estatus) {
                if ("Modificado".equals(estatu.getNombre())) {
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
            
            pagoBO.actualizarPago(pago,estatusCreado);
            MisPagos p=new MisPagos(pagoBO);
            p.show();
            this.dispose();
        } catch (ExcepcionBO ex) {
            try {
                throw new ExcepcionPresentacion(ex.getMessage());
            } catch (ExcepcionPresentacion ex1) {
                Logger.getLogger(ModificarPago.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegresar3;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBoxCuenta;
    private javax.swing.JComboBox<String> jComboBoxTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel logo;
    private javax.swing.JTextField textoMonto;
    // End of variables declaration//GEN-END:variables
}
