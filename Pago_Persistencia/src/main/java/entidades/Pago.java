/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author PC Gamer
 * Entidad que representa un pago realizado.
 * 
 * Esta clase contiene información sobre los pagos, incluyendo el monto,
 * la fecha y hora del pago, el comprobante adjunto, el tipo de pago,
 * el beneficiario del pago, los abonos asociados y los estatus del pago.
 */
@Entity
@Table(name = "pagos")
public class Pago implements Serializable {

   private static final long serialVersionUID = 1L;

    /**
     * Identificador único del pago.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    /**
     * Monto del pago.
     */
    @Column(name = "monto", nullable = false)
    private BigDecimal monto;
    
    /**
     * Fecha y hora en que se realizó el pago.
     */
    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;
    
    /**
     * Comprobante adjunto al pago.
     */
    @Column(name="comprobante", nullable = true, length = 100)
    private String comprobante;
    
    /**
     * Tipo de pago asociado a este pago.
     */
    @ManyToOne
    @JoinColumn(name = "tipo_id", nullable = false)
    private Tipos tipo;
    
    /**
     * Beneficiario del pago.
     */
    @ManyToOne
    @JoinColumn(name = "beneficiario_id", nullable = false)
    private Beneficiario beneficiario;

    /**
     * Abonos asociados a este pago.
     */
    @OneToMany(mappedBy = "pago")
    private List<Abono> abonos;
    
    @OneToMany(mappedBy = "pago", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PagosEstatus> pagosEstatus;

    /**
     *
     * @return
     */
    public List<PagosEstatus> getPagosEstatus() {
        return pagosEstatus;
    }

    /**
     *
     * @param pagosEstatus
     */
    public void setPagosEstatus(List<PagosEstatus> pagosEstatus) {
        this.pagosEstatus = pagosEstatus;
    }
    
    @ManyToOne
    @JoinColumn(name = "cuenta_bancaria_id")
    private CuentaBancaria cuentaBancaria;
    
   
    /**
     * Constructor vacío requerido por JPA.
     */
    public Pago() {
    }

    /**
     * Constructor detallado para inicializar todos los atributos.
     *
     * @param monto         Monto del pago.
     * @param fechaHora     Fecha y hora del pago.
     * @param comprobante   Comprobante adjunto al pago.
     * @param tipo          Tipo de pago.
     * @param beneficiario  Beneficiario del pago.
     */
    public Pago(BigDecimal monto, LocalDateTime fechaHora, String comprobante, Tipos tipo, Beneficiario beneficiario) {
        this.monto = monto;
        this.fechaHora = fechaHora;
        this.comprobante = comprobante;
        this.tipo = tipo;
        this.beneficiario = beneficiario;
    }
    
    /**
     * Calcula el hash del objeto basado en su identificador.
     *
     * @return Valor hash del objeto.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Compara este objeto con otro objeto para determinar si son iguales.
     *
     * @param object Objeto a comparar.
     * @return true si los objetos son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object object) {
        // WARNING - Este método no funcionará si los campos id no están seteados
        if (!(object instanceof Pago)) {
            return false;
        }
        Pago other = (Pago) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * Devuelve una representación en forma de cadena de este objeto.
     *
     * @return Cadena que representa el objeto.
     */
    @Override
    public String toString() {
        return "Pago[ id=" + id + " ]";
    }

   /**
    * Devuelve el identificador único del pago.
    * 
    * @return Identificador único del pago.
    */
   public Long getId() {
       return id;
   }

   /**
    * Establece el identificador único del pago.
    * 
    * @param id Identificador único del pago.
    */
   public void setId(Long id) {
       this.id = id;
   }

   /**
    * Devuelve el monto del pago.
    * 
    * @return Monto del pago.
    */
   public BigDecimal getMonto() {
       return monto;
   }

   /**
    * Establece el monto del pago.
    * 
    * @param monto Monto del pago.
    */
   public void setMonto(BigDecimal monto) {
       this.monto = monto;
   }

   /**
    * Devuelve la fecha y hora en que se realizó el pago.
    * 
    * @return Fecha y hora del pago.
    */
   public LocalDateTime getFechaHora() {
       return fechaHora;
   }

   /**
    * Establece la fecha y hora en que se realizó el pago.
    * 
    * @param fechaHora Fecha y hora del pago.
    */
   public void setFechaHora(LocalDateTime fechaHora) {
       this.fechaHora = fechaHora;
   }

   /**
    * Devuelve el comprobante adjunto al pago.
    * 
    * @return Comprobante adjunto al pago.
    */
   public String getComprobante() {
       return comprobante;
   }

   /**
    * Establece el comprobante adjunto al pago.
    * 
    * @param comprobante Comprobante adjunto al pago.
    */
   public void setComprobante(String comprobante) {
       this.comprobante = comprobante;
   }

   /**
    * Devuelve el tipo de pago asociado.
    * 
    * @return Tipo de pago asociado.
    */
   public Tipos getTipo() {
       return tipo;
   }

   /**
    * Establece el tipo de pago asociado.
    * 
    * @param tipo Tipo de pago asociado.
    */
   public void setTipo(Tipos tipo) {
       this.tipo = tipo;
   }

   /**
    * Devuelve el beneficiario del pago.
    * 
    * @return Beneficiario del pago.
    */
   public Beneficiario getBeneficiario() {
       return beneficiario;
   }

   /**
    * Establece el beneficiario del pago.
    * 
    * @param beneficiario Beneficiario del pago.
    */
   public void setBeneficiario(Beneficiario beneficiario) {
       this.beneficiario = beneficiario;
   }

   /**
    * Devuelve la lista de abonos asociados al pago.
    * 
    * @return Lista de abonos asociados al pago.
    */
   public List<Abono> getAbonos() {
       return abonos;
   }

   /**
    * Establece la lista de abonos asociados al pago.
    * 
    * @param abonos Lista de abonos asociados al pago.
    */
   public void setAbonos(List<Abono> abonos) {
       this.abonos = abonos;
   }



    public CuentaBancaria getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }
   
   
}
