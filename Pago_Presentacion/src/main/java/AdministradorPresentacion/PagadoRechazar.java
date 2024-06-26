/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package AdministradorPresentacion;

import DTOs.EstatusDTO;
import DTOs.PagoDTO;
import DTOs.PagosEstatusDTO;
import GUI.logIn;
import Utilerias.JButtonCellEditor;
import Utilerias.JButtonRenderer;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import excepcion.ExcepcionPresentacion;
import excepcionBO.ExcepcionBO;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.time.format.DateTimeFormatter;
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
public class PagadoRechazar extends javax.swing.JFrame {

    PagoBO pagoBO;
    private int pagina=1;
    private int LIMITE=3;    
    
    /**
     * Creates new form PagadoRechazar
     */
    public PagadoRechazar(PagoBO pagoBO) {
        try {
            initComponents();
            this.setLocationRelativeTo(this);
            this.setSize(965, 610);
            cargarConfiguracionInicialTabla();
            this.pagoBO = pagoBO;
            cargarMetodosIniciales();
        } catch (ExcepcionPresentacion ex) {
            Logger.getLogger(PagadoRechazar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private void cargarMetodosIniciales() throws ExcepcionPresentacion {
        this.cargarConfiguracionInicialTabla();
        this.cargarEnTabla();
    }
    
   private void llenarTabla(List<PagosEstatusDTO> lista) {
         DefaultTableModel modeloTabla = (DefaultTableModel) this.jTable1.getModel();

        modeloTabla.setRowCount(0);
        if (lista != null) {
            lista.forEach(row -> {
                Object[] fila = new Object[5];
                fila[0] =row.getPago().getId();
                fila[1] = row.getPago().getTipo().getNombre();
                fila[2] = row.getPago().getMonto();
                fila[3] = "Modificar";
                fila[4] = "Eliminar"; 
                modeloTabla.addRow(fila); 
            });
        }
    }
    
    private void cargarEnTabla() throws ExcepcionPresentacion {
        int indiceInicio = (pagina - 1) * LIMITE;
        List<PagosEstatusDTO> todas= obtenerPagosEstatusCorrecto();
        List<PagosEstatusDTO> pagoE=new ArrayList<>();
        
        int indiceFin = Math.min(indiceInicio + LIMITE, todas.size());
        List<PagosEstatusDTO> enPagina = obtenerPagina(indiceInicio, indiceFin);
        llenarTabla(enPagina);
        actualizarNumeroDePagina();
    }
    
    public List<PagosEstatusDTO> obtenerPagosEstatusCorrecto(){
        List<PagosEstatusDTO> todas= pagoBO.obtenerTodosLosPagosEstatus();
        List<PagosEstatusDTO> pagoE=new ArrayList<>();
        EstatusDTO estatusCreado = null;
            
            List<EstatusDTO> estatus = pagoBO.obtenerTodosLosEstatus();
            for (EstatusDTO estatu : estatus) {
                if ("Aprobado".equals(estatu.getNombre())) {
                    estatusCreado = estatu;
                    break;
                }
            }
        for(PagosEstatusDTO pagoEstatu:todas){
            if(estatusCreado.getId()==pagoEstatu.getEstatus().getId()){
                pagoE.add(pagoEstatu);
            }
        }
        return pagoE;
    }
    
    private List<PagosEstatusDTO> obtenerPagina(int indiceInicio, int indiceFin) throws ExcepcionPresentacion {
        List<PagosEstatusDTO> todas= obtenerPagosEstatusCorrecto();
        List<PagosEstatusDTO> todasLasPaginas = new ArrayList<>();
        indiceFin = Math.min(indiceFin, todas.size());
        for (int i = indiceInicio; i < indiceFin; i++) {
            todasLasPaginas.add(todas.get(i));
        }
        return todasLasPaginas;
    }

    private void actualizarNumeroDePagina() {
    NumeroDePagina.setText(""+pagina);
    }
        
    public void pagar() {
    
        try {
            long id=getIdSeleccionadoTabla();
             System.out.println(id);
            PagoDTO pago = pagoBO.buscarPagoPorId(id);
            System.out.println(pago.getId());
            List<EstatusDTO> listaEstatus = new ArrayList<>();
            EstatusDTO estatusCreado = null;
            
            List<EstatusDTO> estatus = pagoBO.obtenerTodosLosEstatus();
            for (EstatusDTO estatu : estatus) {
                if ("Pagado".equals(estatu.getNombre())) {
                    estatusCreado = estatu;
                    break;
                }
            }

            if (estatusCreado == null) {
                JOptionPane.showMessageDialog(this, "No se encontró el estatus 'Aprobado' en la lista", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            listaEstatus.add(estatusCreado);
            pago.setEstatus(listaEstatus);
            
            pagoBO.actualizarPago(pago,estatusCreado);
            generarRecibo();
            JOptionPane.showMessageDialog(this, "Pago aprobado!");
            cargarEnTabla();
         } catch (ExcepcionBO ex) {
            try {
                throw new ExcepcionPresentacion(ex.getMessage());
            } catch (ExcepcionPresentacion ex1) {
                Logger.getLogger(AprobacionRechazar.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (ExcepcionPresentacion ex) {
            Logger.getLogger(PagadoRechazar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            System.out.println("error al generar recibo");
            Logger.getLogger(PagadoRechazar.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
    }
    
    public void rechazar() {
    
        try {
            long idaux = getIdSeleccionadoTabla();
            EstatusDTO aprobado = new EstatusDTO();
            
            long id = 5;
            aprobado.setId(id);
            aprobado.setNombre("Rechazado");
            
            pagoBO.actualizarPago(pagoBO.buscarPagoPorId(idaux), aprobado);
                        JOptionPane.showMessageDialog(this, "Pago rechazado!");
        } catch (ExcepcionBO ex) {
            Logger.getLogger(AprobacionRechazar.class.getName()).log(Level.SEVERE, null, ex);
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
            

    private void generarRecibo() throws ExcepcionPresentacion, MalformedURLException{

        String dest = "reciboPago.pdf";

        try {

            PdfWriter writer = new PdfWriter(dest);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            
            long idaux = getIdSeleccionadoTabla();          
            
            PagoDTO pago = pagoBO.buscarPagoPorId(idaux);
            
            ImageData data = ImageDataFactory.create("src/main/resources/images/potroPagoChico"+idaux+".png");
            Image image = new Image(data);

            document.add(image);
            
            document.add(new Paragraph("Pago =" + pago.getEstatus().get(0).getNombre()+ "!!!"));
            document.add(new Paragraph(pago.getFechaHora().format(formatter)));
            document.add(new Paragraph("$" + pago.getMonto()));
            document.add(new Paragraph(pago.getBeneficiario().getNombre().getNombres() + 
                    pago.getBeneficiario().getNombre().getApellidoPaterno() + 
                    pago.getBeneficiario().getNombre().getApellidoMaterno()));
            document.add(new Paragraph("Recibo Terminado"));

            document.close();

            System.out.println("¡Recibo generado con éxito!");
            pago.setComprobante("src/main/resources/images/potroPagoChico"+idaux+".png");
            pagoBO.actualizarPago(pago, pago.getEstatus().get(0));
            JOptionPane.showMessageDialog(this, "Recibo generado con éxito!");

        } catch (FileNotFoundException e) {
            e.printStackTrace();    
    
    }   catch (ExcepcionBO ex) {    
            Logger.getLogger(PagadoRechazar.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    private void cargarConfiguracionInicialTabla() { 
        
        ActionListener onPagadoClickListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                pagar();
                
            }               
        };
            
        int indiceColumnaEditar = 3;
        TableColumnModel modeloColumnas = this.jTable1.getColumnModel();
        Color color = new Color(178, 218, 250);
        modeloColumnas.getColumn(indiceColumnaEditar).setCellRenderer(new JButtonRenderer("Pagado",color));
        modeloColumnas.getColumn(indiceColumnaEditar).setCellEditor(new JButtonCellEditor("Pagado", onPagadoClickListener));
        
        ActionListener onRechazadoClickListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
               rechazar();
               
                
            }               
        };

        color = new Color(255, 105, 97);
        modeloColumnas.getColumn(4).setCellRenderer(new JButtonRenderer("Rechazado",color));
        modeloColumnas.getColumn(4).setCellEditor(new JButtonCellEditor("Rechazado", onRechazadoClickListener));
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
        jTable1 = new javax.swing.JTable();
        logo = new javax.swing.JLabel();
        NumeroDePagina = new javax.swing.JTextField();
        btnSiguiente = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();
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
        jMenu6 = new javax.swing.JMenu();
        btnCerrarSesion = new javax.swing.JRadioButtonMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(175, 176, 212));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Serif", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Pagado/Rechazado");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 40, -1, -1));

        jTable1.setBackground(new java.awt.Color(228, 222, 235));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Tipo", "Monto", "Pagado", "Rechazar"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 760, 280));

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/potroPagoChico.png"))); // NOI18N
        jPanel1.add(logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 0, 150, 80));

        NumeroDePagina.setBackground(new java.awt.Color(204, 169, 221));
        NumeroDePagina.setForeground(new java.awt.Color(255, 255, 255));
        NumeroDePagina.setText("1");
        NumeroDePagina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumeroDePaginaActionPerformed(evt);
            }
        });
        jPanel1.add(NumeroDePagina, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 480, 20, -1));

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
        jPanel1.add(btnSiguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 460, -1, -1));

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
        jPanel1.add(btnAtras, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 460, -1, -1));

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

        jMenu6.setBackground(new java.awt.Color(116, 114, 178));
        jMenu6.setForeground(new java.awt.Color(116, 114, 178));
        jMenu6.setText("Cerrar Sesion");

        btnCerrarSesion.setForeground(new java.awt.Color(116, 114, 178));
        btnCerrarSesion.setSelected(true);
        btnCerrarSesion.setText("Cerrar Sesion");
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });
        jMenu6.add(btnCerrarSesion);

        jMenuBar1.add(jMenu6);

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

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        // TODO add your handling code here:
        logIn lIn = new logIn(pagoBO);
        lIn.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void NumeroDePaginaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NumeroDePaginaActionPerformed
        // TODO add your handling code here:
        try {
            List<PagosEstatusDTO> todas= obtenerPagosEstatusCorrecto();

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
        } catch (ExcepcionPresentacion ex) {
            Logger.getLogger(PagadoRechazar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_NumeroDePaginaActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        try {
            List<PagosEstatusDTO> todas= obtenerPagosEstatusCorrecto();

            int totalPaginas = (int) Math.ceil((double) todas.size() / LIMITE);

            if (pagina < totalPaginas) {
                pagina++;
                cargarEnTabla();
                actualizarNumeroDePagina();
            } else {

                JOptionPane.showMessageDialog(this, "No hay más páginas disponibles", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (ExcepcionPresentacion ex) {
            Logger.getLogger(PagadoRechazar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        // TODO add your handling code here:
        if (pagina > 1) {
            try {
                pagina--;
                cargarEnTabla();
                actualizarNumeroDePagina();
            } catch (ExcepcionPresentacion ex) {
                Logger.getLogger(PagadoRechazar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnAtrasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField NumeroDePagina;
    private javax.swing.JRadioButtonMenuItem btnAdministracionBeneficiarios;
    private javax.swing.JRadioButtonMenuItem btnAprobacionRechazar;
    private javax.swing.JButton btnAtras;
    private javax.swing.JRadioButtonMenuItem btnCerrarSesion;
    private javax.swing.JRadioButtonMenuItem btnInicio;
    private javax.swing.JRadioButtonMenuItem btnPagadoRechazar;
    private javax.swing.JRadioButtonMenuItem btnReportePago;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel logo;
    // End of variables declaration//GEN-END:variables
}
