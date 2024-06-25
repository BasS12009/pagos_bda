/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package AdministradorPresentacion;

import DTOs.BeneficiarioDTO;
import DTOs.CuentaBancariaDTO;
import Utilerias.JButtonCellEditor;
import Utilerias.JButtonRenderer;
import static entidades.Abono_.id;
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
public class AdministracionBeneficiarios extends javax.swing.JFrame {

    private PagoBO pagoBO;
    private int pagina=1;
    private int LIMITE=3;
    boolean conFiltro;
    /**
     * Creates new form AdministracionBeneficiarios
     */
    public AdministracionBeneficiarios(PagoBO pagoBO) {
        try {
        initComponents();
        this.setLocationRelativeTo(this);
        this.setSize(1000, 610);
        cargarConfiguracionInicialTabla();
        this.pagoBO=pagoBO;
        cargarMetodosIniciales();
        } catch (ExcepcionPresentacion ex) {
            Logger.getLogger(AdministracionBeneficiarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void modificarCuenta() {
        try {
            long id = this.getIdSeleccionadoTabla();
            ModificarBeneficiario modificarCuenta = new ModificarBeneficiario(pagoBO,id);
            modificarCuenta.setVisible(true);
            this.setVisible(false);
            cargarEnTabla();
        } catch (ExcepcionPresentacion ex) {
            Logger.getLogger(AdministracionBeneficiarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void eliminar() throws ExcepcionPresentacion {
        try {
            if(pagoBO.obtenerAbonosPorBeneficiario( this.getIdSeleccionadoTabla())!=null){
                throw new ExcepcionPresentacion("No puedes eliminar un beneficiario con abonos.");
            }
        long id = this.getIdSeleccionadoTabla();
        BeneficiarioDTO beneficiario = pagoBO.buscarBeneficiarioPorId(id);
        int confirmacion = JOptionPane.showConfirmDialog(this, 
                            "¿Está seguro que desea eliminar al cliente?\n" +
                            "ID: " + beneficiario.getId()+ "\n" +
                            "Usuario: " + beneficiario.getUsuario()+ "\n" +
                            "Nombre: " + beneficiario.getNombre(),
                            "Confirmar eliminación",
                            JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            pagoBO.eliminarBeneficiario(id);
            JOptionPane.showMessageDialog(this, "Beneficiario eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarEnTabla();
        }
        } catch (ExcepcionBO ex) {
        throw new ExcepcionPresentacion("Error al eliminar beneficiario .", ex);
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
        
        ActionListener onModificarClickListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                modificarCuenta();
                
            }               
        };
        int indiceColumnaEditar = 6;
        TableColumnModel modeloColumnas = this.jTable1.getColumnModel();
        Color color = new Color(253, 253, 150);
        modeloColumnas.getColumn(indiceColumnaEditar).setCellRenderer(new JButtonRenderer("Modificar",color));
        modeloColumnas.getColumn(indiceColumnaEditar).setCellEditor(new JButtonCellEditor("Modificar", onModificarClickListener));
      
        
        ActionListener onEliminarClickListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                try {
                    eliminar();
                } catch (ExcepcionPresentacion ex) {
                    Logger.getLogger(AdministracionBeneficiarios.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }               
        };

        int indiceColumnaEliminar = 7;
        color = new Color(255, 105, 97);
        modeloColumnas.getColumn(indiceColumnaEliminar).setCellRenderer(new JButtonRenderer("Eliminar",color));
        modeloColumnas.getColumn(indiceColumnaEliminar).setCellEditor(new JButtonCellEditor("Eliminar", onEliminarClickListener));
        
        ActionListener onInformacionClickListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                informacion(); 
            }               
        };

        int indiceColumnaInformacion = 8;
        color = new Color(174, 198, 207);
        modeloColumnas.getColumn(indiceColumnaInformacion).setCellRenderer(new JButtonRenderer("Informacion",color));
        modeloColumnas.getColumn(indiceColumnaInformacion).setCellEditor(new JButtonCellEditor("Informacion", onInformacionClickListener));
    }
    
    
    public void informacion(){
        long id= this.getIdSeleccionadoTabla();
        infoBeneficiario info=new infoBeneficiario(pagoBO,id);
        info.show();
        this.dispose();
    }
    
    private void llenarTabla(List<BeneficiarioDTO> lista) {
         DefaultTableModel modeloTabla = (DefaultTableModel) this.jTable1.getModel();

    // Clear existing rows
    modeloTabla.setRowCount(0);
    if (lista != null) {
        
        lista.forEach(row -> {
            try {
                int numeroPagos=pagoBO.obtenerPagosPorBeneficiario(row.getId()).size();
                Object[] fila = new Object[9];
                fila[0] = row.getId();
                fila[1] = row.getNombre().getNombres();
                fila[2] = row.getUsuario();
                fila[3] = row.getContraseña();
                fila[4] = row.getSaldo();
                fila[5] = numeroPagos;
                fila[6] = "Eliminar";
                fila[7] = "Editar"; 
                fila[8] = "Informacion";
                modeloTabla.addRow(fila);
            } catch (ExcepcionBO ex) {
                Logger.getLogger(AdministracionBeneficiarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    }
    
    private void cargarEnTabla() throws ExcepcionPresentacion {
        try {
                int indiceInicio = (pagina - 1) * LIMITE;
                List<BeneficiarioDTO> todas = pagoBO.obtenerTodosLosBeneficiarios();
                int indiceFin = Math.min(indiceInicio + LIMITE, todas.size());

                List<BeneficiarioDTO> enPagina = obtenerPagina(indiceInicio, indiceFin);

                llenarTabla(enPagina);

                actualizarNumeroDePagina();
            } catch (ExcepcionBO ex) {
                throw new ExcepcionPresentacion("Error al cargar las cuentas bancarias del beneficiario.", ex);
            }
    }
    
    private List<BeneficiarioDTO> obtenerPagina(int indiceInicio, int indiceFin) throws ExcepcionPresentacion {
        try {
            List<BeneficiarioDTO> todas = pagoBO.obtenerTodosLosBeneficiarios();
            List<BeneficiarioDTO> todasLasPaginas = new ArrayList<>();
            indiceFin = Math.min(indiceFin, todas.size());
            for (int i = indiceInicio; i < indiceFin; i++) {
                todasLasPaginas.add(todas.get(i));
            }
            return todasLasPaginas;
        } catch (ExcepcionBO ex) {
        throw new ExcepcionPresentacion("Error al eliminar el beneficiario.", ex);
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

        checkbox1 = new java.awt.Checkbox();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnCrearBeneficiario = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        logo = new javax.swing.JLabel();
        btnAtras = new javax.swing.JButton();
        btnSiguiente = new javax.swing.JButton();
        NumeroDePagina = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        Inicio = new javax.swing.JMenu();
        btnInicio = new javax.swing.JRadioButtonMenuItem();
        jMenu2 = new javax.swing.JMenu();
        btnAprobacionRechazar = new javax.swing.JRadioButtonMenuItem();
        jMenu3 = new javax.swing.JMenu();
        btnReportePago = new javax.swing.JRadioButtonMenuItem();
        jMenu4 = new javax.swing.JMenu();
        btnAdministracionBeneficiarios = new javax.swing.JRadioButtonMenuItem();
        jMenu5 = new javax.swing.JMenu();
        btnPagadoRechazar = new javax.swing.JRadioButtonMenuItem();

        checkbox1.setLabel("checkbox1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(175, 176, 212));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Administracion de Beneficiarios");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, -1, -1));

        btnCrearBeneficiario.setBackground(new java.awt.Color(116, 114, 178));
        btnCrearBeneficiario.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        btnCrearBeneficiario.setForeground(new java.awt.Color(255, 255, 255));
        btnCrearBeneficiario.setText("+Crear Beneficiario");
        btnCrearBeneficiario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearBeneficiarioActionPerformed(evt);
            }
        });
        jPanel1.add(btnCrearBeneficiario, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        jTable1.setBackground(new java.awt.Color(228, 222, 235));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Folio", "Nombre Beneficiario", "Usuario", "Contraseña", "Saldo", "Numero de Pagos", "Modificar", "Eliminar", "Informacion"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 930, 270));

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/potroPagoChico.png"))); // NOI18N
        jPanel1.add(logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 10, 150, 80));

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
        jPanel1.add(btnAtras, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, -1, -1));

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
        jPanel1.add(btnSiguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 480, -1, -1));

        NumeroDePagina.setBackground(new java.awt.Color(204, 169, 221));
        NumeroDePagina.setForeground(new java.awt.Color(255, 255, 255));
        NumeroDePagina.setText("1");
        NumeroDePagina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumeroDePaginaActionPerformed(evt);
            }
        });
        jPanel1.add(NumeroDePagina, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 500, 20, -1));

        jMenuBar1.setBackground(new java.awt.Color(228, 222, 235));
        jMenuBar1.setForeground(new java.awt.Color(116, 114, 178));

        Inicio.setBackground(new java.awt.Color(116, 114, 178));
        Inicio.setForeground(new java.awt.Color(116, 114, 178));
        Inicio.setText("Inicio");

        btnInicio.setForeground(new java.awt.Color(116, 114, 178));
        btnInicio.setSelected(true);
        btnInicio.setText("Inicio");
        btnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInicioActionPerformed(evt);
            }
        });
        Inicio.add(btnInicio);

        jMenuBar1.add(Inicio);

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
        jMenu4.setText("Administracion Beneficiarios");

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
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed
        // TODO add your handling code here:
        MenuAdministrador menuAdministrador = new MenuAdministrador(pagoBO);
        menuAdministrador.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnInicioActionPerformed

    private void btnAprobacionRechazarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAprobacionRechazarActionPerformed
        // TODO add your handling code here:
        AprobacionRechazar aprobacionRechazar = new AprobacionRechazar(pagoBO);
        aprobacionRechazar.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAprobacionRechazarActionPerformed

    private void btnReportePagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportePagoActionPerformed
        // TODO add your handling code here:
         ReportePago reportePago = new ReportePago(pagoBO);
        reportePago.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnReportePagoActionPerformed

    private void btnAdministracionBeneficiariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdministracionBeneficiariosActionPerformed
        // TODO add your handling code here:
        AdministracionBeneficiarios administracionBeneficiarios = new AdministracionBeneficiarios(pagoBO);
        administracionBeneficiarios.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAdministracionBeneficiariosActionPerformed

    private void btnPagadoRechazarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagadoRechazarActionPerformed
        // TODO add your handling code here:
         PagadoRechazar pagadoRechazar = new PagadoRechazar(pagoBO);
        pagadoRechazar.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnPagadoRechazarActionPerformed

    private void btnCrearBeneficiarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearBeneficiarioActionPerformed
        // TODO add your handling code here:
        CrearBeneficiario crearBeneficiario = new CrearBeneficiario(pagoBO);
        crearBeneficiario.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCrearBeneficiarioActionPerformed

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
    private javax.swing.JMenu Inicio;
    private javax.swing.JTextField NumeroDePagina;
    private javax.swing.JRadioButtonMenuItem btnAdministracionBeneficiarios;
    private javax.swing.JRadioButtonMenuItem btnAprobacionRechazar;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnCrearBeneficiario;
    private javax.swing.JRadioButtonMenuItem btnInicio;
    private javax.swing.JRadioButtonMenuItem btnPagadoRechazar;
    private javax.swing.JRadioButtonMenuItem btnReportePago;
    private javax.swing.JButton btnSiguiente;
    private java.awt.Checkbox checkbox1;
    private javax.swing.JLabel jLabel1;
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
