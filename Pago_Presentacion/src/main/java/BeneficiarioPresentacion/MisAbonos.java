/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package BeneficiarioPresentacion;

import DTOs.AbonoDTO;
import DTOs.BeneficiarioDTO;
import DTOs.PagoDTO;
import DTOs.PagosEstatusDTO;
import GUI.logIn;
import Utilerias.JButtonCellEditor;
import Utilerias.JButtonRenderer;
import excepcion.ExcepcionPresentacion;
import excepcionBO.ExcepcionBO;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
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
    public MisAbonos(PagoBO negocio) {
        initComponents();
        this.setLocationRelativeTo(this);
        this.setSize(965, 610);
        this.pagoBO = negocio;
        cargarConfiguracionInicialTabla();
    }

    
    private void eliminar() throws ExcepcionPresentacion {
        try {
            long id = this.getIdSeleccionadoTabla();
            AbonoDTO abonoDTO = pagoBO.buscarAbonoPorID(id);
            if(pagoBO.buscarPagoPorId(abonoDTO.getPagoDTO().getId()).getEstatus().get(0).getNombre().equals("Completados")){
                throw new ExcepcionPresentacion("No se pueden eliminar abonos de pagos completados");
            
            }else{
                int confirmacion = JOptionPane.showConfirmDialog(this,
                    "¿Está seguro que desea eliminar al cliente?\n" +
                            "ID: " + abonoDTO.getId()+ "\n" +
                                    "El monto: " + abonoDTO.getMonto()+ "\n" +
                                            "Beneficiario: " + abonoDTO.getPagoDTO().getBeneficiario(),
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                BeneficiarioDTO beneficiario=pagoBO.buscarBeneficiarioPorId(pagoBO.getId());
                double sumar=beneficiario.getSaldo()+abonoDTO.getMonto();
                pagoBO.actualizarBeneficiario(beneficiario);
                pagoBO.eliminarAbono(pagoBO.buscarAbonoPorID(id));
                
                JOptionPane.showMessageDialog(this, "Abono eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarEnTabla();
            }
            }
        } catch (ExcepcionBO ex) {
            Logger.getLogger(MisAbonos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private long getIdSeleccionadoTabla() {
        int indiceFilaSeleccionada = this.jTable1.getSelectedRow();
        if (indiceFilaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) this.jTable1.getModel();
            int indiceColumnaId = 0;
            Object valor = modelo.getValueAt(indiceFilaSeleccionada, indiceColumnaId);
            if (valor instanceof Long) {
                return (long) valor;
            } else if (valor instanceof Integer) {
                return (long) (int) valor;
            } else if (valor instanceof String) {
                try {
                    return Long.parseLong((String) valor);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    return 0; 
                }
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }
    
    private void cargarMetodosIniciales() throws ExcepcionPresentacion {
        this.cargarConfiguracionInicialTabla();
        this.cargarEnTabla();
    }
    
    private void cargarConfiguracionInicialTabla() { 
        
        TableColumnModel modeloColumnas = this.jTable1.getColumnModel();

        ActionListener onEliminarClickListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                try {
                    eliminar();
                } catch (ExcepcionPresentacion ex) {
                    Logger.getLogger(MisCuentasBancarias.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }               
        };

        int indiceColumnaEliminar = 4;
        Color color = new Color(255, 105, 97);
        modeloColumnas.getColumn(indiceColumnaEliminar).setCellRenderer(new JButtonRenderer("Eliminar",color));
        modeloColumnas.getColumn(indiceColumnaEliminar).setCellEditor(new JButtonCellEditor("Eliminar", onEliminarClickListener));
    }
    
    
    private void llenarTabla(List<AbonoDTO> lista) {
         DefaultTableModel modeloTabla = (DefaultTableModel) this.jTable1.getModel();

    // Clear existing rows
    modeloTabla.setRowCount(0);
    if (lista != null) {
       
        lista.forEach(row -> {
            Object[] fila = new Object[5];
             List<PagoDTO> pago = new ArrayList<>();
             pago.add(row.getPagoDTO());
            fila[0] = row.getPagoDTO().getCuentas().get(0).getNumeroCuenta();;
            fila[1] = row.getMonto();
            fila[2] =row.getPagoDTO().getEstatus();
            fila[3] = pagoBO.obtenerPagosEstatusParaPagos(pago).get(0).getMensaje();
            fila[4] = "Eliminar"; 
            modeloTabla.addRow(fila); 
        });
    }
    }
    
    private void cargarEnTabla() throws ExcepcionPresentacion {
        int indiceInicio = (pagina - 1) * LIMITE;
        List<AbonoDTO> todas = pagoBO.obtenerAbonosPorBeneficiario(pagoBO.getId());
        int indiceFin = Math.min(indiceInicio + LIMITE, todas.size());
        List<AbonoDTO> enPagina = obtenerPagina(indiceInicio, indiceFin);
        llenarTabla(enPagina);
        actualizarNumeroDePagina();
    }
    
    private List<AbonoDTO> obtenerPagina(int indiceInicio, int indiceFin) throws ExcepcionPresentacion {
        List<AbonoDTO> todas = pagoBO.obtenerAbonosPorBeneficiario(pagoBO.getId());
        List<AbonoDTO> todasLasPaginas = new ArrayList<>();
        indiceFin = Math.min(indiceFin, todas.size());
        for (int i = indiceInicio; i < indiceFin; i++) {
            todasLasPaginas.add(todas.get(i));
        }
        return todasLasPaginas;
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
         MenuBeneficiario menuBeneficiario = new MenuBeneficiario(pagoBO);
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
        MisAbonos misAbonos = new MisAbonos(pagoBO);
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
        // Obtener todos los pagos por beneficiario
        List<PagosEstatusDTO> pagosBeneficiario = pagoBO.obtenerPagosEstatusPorBeneficiario(pagoBO.getId());
        // Filtrar los pagos aprobados
        List<PagoDTO> pagosAprobados = new ArrayList<>();
        for (PagosEstatusDTO pago : pagosBeneficiario) {
            // Verificar si el estatus no es nulo y es "Aprobado"
            if (pago.getEstatus() != null && "Pagado".equals(pago.getEstatus().getNombre())) {
                pagosAprobados.add(pago.getPago());
            }
        }
        if (pagosAprobados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No tiene pagos aprobados para agregar abonos.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return; // Detener la ejecución si no hay pagos aprobados
        }
        AgregarAbono agregarAbono = new AgregarAbono(pagoBO);
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

    private void actualizarNumeroDePagina() {
        NumeroDePagina.setText(""+pagina);
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
