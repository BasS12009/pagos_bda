/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package BeneficiarioPresentacion;

import DTOs.AbonoDTO;
import DTOs.BeneficiarioDTO;
import DTOs.EstatusDTO;
import DTOs.PagoDTO;
import DTOs.PagosEstatusDTO;
import excepcionBO.ExcepcionBO;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import negocio.PagoBO;

/**
 *
 * @author diana
 */
public class AgregarAbono extends javax.swing.JFrame {

    PagoBO pagoBO;
    List<PagosEstatusDTO> pagos;
    List ids;
    PagoDTO pagoSeleccionado;
    
    /**
     * Creates new form AgregarAbono
     */
    public AgregarAbono(PagoBO negocio) {
            initComponents();
            this.setLocationRelativeTo(this);
            this.setSize(965, 610);
            this.pagoBO = negocio;
            ids = new ArrayList<>(); // Inicialización de la lista de IDs
            pagos=new ArrayList<>();
            llenarComboPagos();
        }

        public void llenarComboPagos() {
            pagos = pagoBO.obtenerPagosEstatusPorBeneficiario(pagoBO.getId()); // Manejo de excepciones
            DefaultComboBoxModel<String> modelPagos = new DefaultComboBoxModel<>();
            for (PagosEstatusDTO pago : pagos) {
                //50
                if (pago.getEstatus().getNombre().equals("Pagado")) {
                    ids.add(pago.getId());
                    String displayText = pago.getPago().getTipo().getNombre() + " " +
                            (pago.getPago().getTipo().getNumeroParcialidades() - pago.getPago().getAbonos().size());
                    modelPagos.addElement(displayText);
                }
            }
            jComboBoxPagos.setModel(modelPagos);
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
        jLabel5 = new javax.swing.JLabel();
        textoMonto = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jComboBoxPagos = new javax.swing.JComboBox<>();
        btnAbono = new javax.swing.JButton();
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
        jLabel1.setText("Agregar Abono");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI Symbol", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Monto");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 270, -1, -1));
        jPanel1.add(textoMonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 300, 270, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI Symbol", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Pago");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 180, -1, -1));

        jPanel1.add(jComboBoxPagos, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 220, 270, 30));

        btnAbono.setBackground(new java.awt.Color(116, 114, 178));
        btnAbono.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        btnAbono.setForeground(new java.awt.Color(255, 255, 255));
        btnAbono.setText("Agregar Abono");
        btnAbono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbonoActionPerformed(evt);
            }
        });
        jPanel1.add(btnAbono, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 490, 170, 40));

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/potroPagoChico.png"))); // NOI18N
        jPanel1.add(logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 480, 180, 80));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 891, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresar3ActionPerformed
        // TODO add your handling code here:
        MisAbonos misAbonos = new MisAbonos(pagoBO);
        misAbonos.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresar3ActionPerformed

    private void btnAbonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbonoActionPerformed
        try {                                         
            AbonoDTO abono = new AbonoDTO();
            String montoTexto = textoMonto.getText().trim();
            if (montoTexto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un monto válido", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            double monto;
            try {
                monto = Double.parseDouble(montoTexto);
                if (monto <= 0) {
                    JOptionPane.showMessageDialog(this, "El monto debe ser mayor que cero", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                abono.setMonto(monto);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El monto ingresado no es válido", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Obtener el pago seleccionado del combo box
            pagoSeleccionado = pagos.get(jComboBoxPagos.getSelectedIndex()).getPago();
            if (pagoSeleccionado == null) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un pago válido", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Validar que el monto del abono no exceda lo que falta pagar según las parcialidades
            double montoRestante = Double.valueOf(pagoSeleccionado.getMonto().doubleValue());
            if (monto > montoRestante) {
                JOptionPane.showMessageDialog(this, "El monto del abono no puede ser mayor al monto restante por pagar", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int parcialidadesRestantes = pagoSeleccionado.getTipo().getNumeroParcialidades() - pagoSeleccionado.getAbonos().size();
            if (monto > parcialidadesRestantes * pagoSeleccionado.getTipo().getNumeroParcialidades()) {
                JOptionPane.showMessageDialog(this, "El abono excede el número de parcialidades restantes", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            double saldoBeneficiario = pagoBO.buscarBeneficiarioPorId(pagoBO.getId()).getSaldo();
        if (monto > saldoBeneficiario) {
            JOptionPane.showMessageDialog(this, "El monto del abono no puede exceder el saldo del beneficiario", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        BigDecimal nuevoMonto = pagoSeleccionado.getMonto().subtract(BigDecimal.valueOf(monto));
        Double nuevoSaldoBeneficiario = ((pagoBO.buscarBeneficiarioPorId(pagoBO.getId()).getSaldo())-((monto)));

            if (nuevoMonto.compareTo(BigDecimal.ZERO) <= 0) {
                List<EstatusDTO> estatus= pagoBO.obtenerEstatus();
                EstatusDTO estatuSeleccionado;
                for(EstatusDTO estatu:estatus){
                    if(estatu.getNombre().equals("Completado")){
                        estatuSeleccionado=pagoBO.obtenerEstatuPorId(estatu.getId());
                        estatus.clear();
                        estatus.add(estatuSeleccionado);
                        break;
                    }
                }
                
                
                
                pagoSeleccionado.setEstatus(estatus);
                generarComprobante(pagoSeleccionado);
                pagoBO.actualizarPago(pagoSeleccionado, pagoSeleccionado.getEstatus().get(0));
                abono.setFechaHora(LocalDateTime.now());
                pagoBO.agregarAbono(abono, pagoSeleccionado);
                BeneficiarioDTO beneficiario=pagoBO.buscarBeneficiarioPorId(pagoBO.getId());
                beneficiario.setSaldo(nuevoSaldoBeneficiario);
                pagoBO.actualizarBeneficiario(beneficiario);
                
                MisAbonos misAbonos=new MisAbonos(pagoBO);
                misAbonos.show();
                this.dispose();
            }
            
            pagoSeleccionado.setMonto(nuevoMonto);
            
            abono.setPagoDTO(pagoSeleccionado);
            pagoBO.actualizarPago(pagoSeleccionado, pagoSeleccionado.getEstatus().get(0));
            abono.setFechaHora(LocalDateTime.now());
            pagoBO.agregarAbono(abono, pagoSeleccionado);
            BeneficiarioDTO beneficiario=pagoBO.buscarBeneficiarioPorId(pagoBO.getId());
            beneficiario.setSaldo(nuevoSaldoBeneficiario);
            pagoBO.actualizarBeneficiario(beneficiario);
            MisAbonos misAbonos=new MisAbonos(pagoBO);
            misAbonos.show();
            this.dispose();

        } catch (ExcepcionBO ex) {
            Logger.getLogger(AgregarAbono.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnAbonoActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbono;
    private javax.swing.JButton btnRegresar3;
    private javax.swing.JComboBox<String> jComboBoxPagos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel logo;
    private javax.swing.JTextField textoMonto;
    // End of variables declaration//GEN-END:variables

    private void generarComprobante(PagoDTO pagoSeleccionado) {
        
    }
}
