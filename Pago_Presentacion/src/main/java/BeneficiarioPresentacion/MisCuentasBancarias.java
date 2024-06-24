/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package BeneficiarioPresentacion;

import DTOs.CuentaBancariaDTO;
import GUI.logIn;
import Utilerias.JButtonCellEditor;
import Utilerias.JButtonRenderer;
import excepcion.ExcepcionPresentacion;
import excepcionBO.ExcepcionBO;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
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
public class MisCuentasBancarias extends javax.swing.JFrame {
    private PagoBO pagoBO;
    private int pagina=1;
    private int LIMITE=3;
    boolean conFiltro;
    
    /**
     * Creates new form MisCuentasBancarias
     */
    public MisCuentasBancarias(PagoBO pagoBO) {
        try {
            initComponents();
            this.setLocationRelativeTo(this);
            this.setSize(965, 610);
            this.pagoBO=pagoBO;
            cargarMetodosIniciales();
            System.out.println(pagoBO.getId());
        } catch (ExcepcionPresentacion ex) {
            Logger.getLogger(MisCuentasBancarias.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void modificarCuenta() {
        try {
            long id = this.getIdSeleccionadoTabla();
            ModificarCuenta modificarCuenta = new ModificarCuenta(pagoBO,id);
            modificarCuenta.setVisible(true);
            this.setVisible(false);
            cargarEnTabla();
        } catch (ExcepcionPresentacion ex) {
            Logger.getLogger(MisCuentasBancarias.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void eliminar() throws ExcepcionPresentacion {
        try {
        long id = this.getIdSeleccionadoTabla();
        CuentaBancariaDTO cuentaDTO = pagoBO.buscarCuentaBancariaPorId(id);
        int confirmacion = JOptionPane.showConfirmDialog(this, 
                            "¿Está seguro que desea eliminar al cliente?\n" +
                            "ID: " + cuentaDTO.getId()+ "\n" +
                            "Numero: " + cuentaDTO.getNumeroCuenta()+ "\n" +
                            "Banco: " + cuentaDTO.getBanco(),
                            "Confirmar eliminación",
                            JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            pagoBO.eliminarCuentaBancaria(id);
            JOptionPane.showMessageDialog(this, "Cliente eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarEnTabla();
        }
        } catch (ExcepcionBO ex) {
        throw new ExcepcionPresentacion("Error al eliminar la cuenta bancaria.", ex);
        }
    }
    
    private long getIdSeleccionadoTabla() {
        int indiceFilaSeleccionada = this.jTable1.getSelectedRow();
        if (indiceFilaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) this.jTable1.getModel();
            int indiceColumnaId = 0; // Suponiendo que el índice de la columna que contiene el ID es 0
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
        int indiceColumnaEditar = 4;
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
                    Logger.getLogger(MisCuentasBancarias.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }               
        };

        int indiceColumnaEliminar = 5;
        color = new Color(255, 105, 97);
        modeloColumnas.getColumn(indiceColumnaEliminar).setCellRenderer(new JButtonRenderer("Eliminar",color));
        modeloColumnas.getColumn(indiceColumnaEliminar).setCellEditor(new JButtonCellEditor("Eliminar", onEliminarClickListener));
    }
    
    
    private void llenarTabla(List<CuentaBancariaDTO> cuentaLista) {
         DefaultTableModel modeloTabla = (DefaultTableModel) this.jTable1.getModel();

    // Clear existing rows
    modeloTabla.setRowCount(0);
    if (cuentaLista != null) {
        cuentaLista.forEach(row -> {
            String eliminada = Optional.ofNullable(row.getEliminada())
                                       .map(elim -> elim ? "Eliminada" : "No eliminada")
                                       .orElse("No disponible");
            Object[] fila = new Object[6];
            fila[0] = row.getNumeroCuenta();
            fila[1] = row.getClave();
            fila[2] = row.getBanco();
            fila[3] = eliminada;
            fila[4] = "Eliminar";
            fila[5] = "Editar"; 
            modeloTabla.addRow(fila); 
        });
    }
    }
    
    private void cargarEnTabla() throws ExcepcionPresentacion {
        try {
                int indiceInicio = (pagina - 1) * LIMITE;
                List<CuentaBancariaDTO> todas = pagoBO.obtenerTodasLasCuentasBancariasPorBeneficiario(pagoBO.getId());
                int indiceFin = Math.min(indiceInicio + LIMITE, todas.size());

                List<CuentaBancariaDTO> enPagina = obtenerPagina(indiceInicio, indiceFin);

                llenarTabla(enPagina);

                actualizarNumeroDePagina();
            } catch (ExcepcionBO ex) {
                throw new ExcepcionPresentacion("Error al cargar las cuentas bancarias del beneficiario.", ex);
            }
    }
    
    private List<CuentaBancariaDTO> obtenerPagina(int indiceInicio, int indiceFin) throws ExcepcionPresentacion {
        try {
            List<CuentaBancariaDTO> todas = pagoBO.obtenerTodasLasCuentasBancariasPorBeneficiario(pagoBO.getId());
            List<CuentaBancariaDTO> todasLasPaginas = new ArrayList<>();
            indiceFin = Math.min(indiceFin, todas.size());
            for (int i = indiceInicio; i < indiceFin; i++) {
                todasLasPaginas.add(todas.get(i));
            }
            return todasLasPaginas;
        } catch (ExcepcionBO ex) {
        throw new ExcepcionPresentacion("Error al eliminar la cuenta bancaria.", ex);
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
        btnNuevaCuenta = new javax.swing.JButton();
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

        btnNuevaCuenta.setBackground(new java.awt.Color(116, 114, 178));
        btnNuevaCuenta.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        btnNuevaCuenta.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevaCuenta.setText("+Nueva Cuenta");
        btnNuevaCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaCuentaActionPerformed(evt);
            }
        });
        jPanel1.add(btnNuevaCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, -1, -1));

        jLabel1.setFont(new java.awt.Font("Serif", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Mis Cuentas Bancarias");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, -1, -1));

        jTable1.setBackground(new java.awt.Color(228, 222, 235));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", "12345", "Banorte", "Esperando abono", null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "No. Cuenta", "Clabe", "Banco", "Estatus", "Modificar", "Eliminar"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 810, 230));

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
        jPanel1.add(btnAtras, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 490, -1, -1));

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
        jPanel1.add(btnSiguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 490, -1, -1));

        NumeroDePagina.setBackground(new java.awt.Color(204, 169, 221));
        NumeroDePagina.setForeground(new java.awt.Color(255, 255, 255));
        NumeroDePagina.setText("1");
        NumeroDePagina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumeroDePaginaActionPerformed(evt);
            }
        });
        jPanel1.add(NumeroDePagina, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 490, 20, -1));

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/potroPagoChico.png"))); // NOI18N
        jPanel1.add(logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 10, 150, 80));

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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 960, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
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

    private void btnNuevaCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaCuentaActionPerformed
        try {
            AgregarCuenta nuevaCuenta = new AgregarCuenta(pagoBO);
            nuevaCuenta.setVisible(true);
            cargarEnTabla();
            this.dispose();
        } catch (ExcepcionPresentacion ex) {
            Logger.getLogger(MisCuentasBancarias.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnNuevaCuentaActionPerformed

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        // TODO add your handling code here:
        logIn lIn = new logIn(pagoBO);
        lIn.setVisible(true);
        this.dispose(); 
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        // TODO add your handling code here:
        if (pagina > 1) {
            try {
                pagina--;
                cargarEnTabla();
                actualizarNumeroDePagina();
            } catch (ExcepcionPresentacion ex) {
                Logger.getLogger(MisCuentasBancarias.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        try {
            List<CuentaBancariaDTO> todas = pagoBO.obtenerTodasLasCuentasBancarias();

            int totalPaginas = (int) Math.ceil((double) todas.size() / LIMITE);

            if (pagina < totalPaginas) {
                pagina++;
                cargarEnTabla();
                actualizarNumeroDePagina();
            } else {

                JOptionPane.showMessageDialog(this, "No hay más páginas disponibles", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
           } catch (ExcepcionBO ex) {
            try {
                throw new ExcepcionPresentacion("Error al eliminar la cuenta bancaria.", ex);
            } catch (ExcepcionPresentacion ex1) {
                Logger.getLogger(MisCuentasBancarias.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (ExcepcionPresentacion ex) {
            Logger.getLogger(MisCuentasBancarias.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void NumeroDePaginaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NumeroDePaginaActionPerformed
        // TODO add your handling code here:
        try {
            List<CuentaBancariaDTO> todas= pagoBO.obtenerTodasLasCuentasBancarias();

            int totalPaginas = (int) Math.ceil((double) todas.size() / LIMITE);

            int nuevaPagina = Integer.parseInt(NumeroDePagina.getText());

            if (nuevaPagina >= 1 && nuevaPagina <= totalPaginas) {
                pagina = nuevaPagina;

                cargarEnTabla();

                actualizarNumeroDePagina();
            } else {
                JOptionPane.showMessageDialog(this, "Número de página inválido", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un número válido para la página", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ExcepcionBO ex) { 
            try {
                throw new ExcepcionPresentacion("Error al eliminar la cuenta bancaria.", ex);
            } catch (ExcepcionPresentacion ex1) {
                Logger.getLogger(MisCuentasBancarias.class.getName()).log(Level.SEVERE, null, ex1);
            }
            } catch (ExcepcionPresentacion ex) {
            Logger.getLogger(MisCuentasBancarias.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_NumeroDePaginaActionPerformed

    private void actualizarNumeroDePagina() {
    NumeroDePagina.setText(""+pagina);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField NumeroDePagina;
    private javax.swing.JRadioButtonMenuItem btnAbonos;
    private javax.swing.JButton btnAtras;
    private javax.swing.JRadioButtonMenuItem btnCerrarSesion;
    private javax.swing.JRadioButtonMenuItem btnCuentasBancarias;
    private javax.swing.JRadioButtonMenuItem btnInicio;
    private javax.swing.JButton btnNuevaCuenta;
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
