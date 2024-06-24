/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Representa la entidad PagosEstatus que mantiene el estado de un pago en un momento dado.
 * Esta clase relaciona un pago con su estatus y contiene información adicional como un mensaje y la fecha y hora del estado.
 * 
 * @author PC Gamer
 */
@Entity
@Table(name="pagosEstatus")
public class PagosEstatus implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Identificador único del estado del pago.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Pago asociado a este estado.
     */
    @ManyToOne
    @JoinColumn(name = "pago_id", nullable = false)
    private Pago pago;

    /**
     * Estatus del pago.
     */
    @ManyToOne
    @JoinColumn(name = "estatus_id", nullable = false)
    private Estatus estatus;

    /**
     * Mensaje adicional relacionado con el estado del pago.
     */
    @Column(name = "mensaje", length = 255)
    private String mensaje;

    /**
     * Fecha y hora en que se registró el estado del pago.
     */
    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    /**
     * Constructor vacío requerido por JPA.
     */
    public PagosEstatus() {
        this.fechaHora = LocalDateTime.now(); 
    }

    /**
     * Constructor detallado para inicializar todos los atributos.
     *
     * @param pago       Pago asociado.
     * @param estatus    Estatus del pago.
     * @param mensaje    Mensaje adicional.
     * @param fechaHora  Fecha y hora del estado.
     */
    public PagosEstatus(Pago pago, Estatus estatus, String mensaje, LocalDateTime fechaHora) {
        this.pago = pago;
        this.estatus = estatus;
        this.mensaje = mensaje;
        this.fechaHora = fechaHora;
    }

    /**
     * Devuelve el identificador único del estado del pago.
     *
     * @return Identificador único del estado del pago.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del estado del pago.
     *
     * @param id Identificador único del estado del pago.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Devuelve el pago asociado a este estado.
     *
     * @return Pago asociado a este estado.
     */
    public Pago getPago() {
        return pago;
    }

    /**
     * Establece el pago asociado a este estado.
     *
     * @param pago Pago asociado a este estado.
     */
    public void setPago(Pago pago) {
        this.pago = pago;
    }

    /**
     * Devuelve el estatus del pago.
     *
     * @return Estatus del pago.
     */
    public Estatus getEstatus() {
        return estatus;
    }

    /**
     * Establece el estatus del pago.
     *
     * @param estatus Estatus del pago.
     */
    public void setEstatus(Estatus estatus) {
        this.estatus = estatus;
    }

    /**
     * Devuelve el mensaje adicional relacionado con el estado del pago.
     *
     * @return Mensaje adicional relacionado con el estado del pago.
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Establece el mensaje adicional relacionado con el estado del pago.
     *
     * @param mensaje Mensaje adicional relacionado con el estado del pago.
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * Devuelve la fecha y hora en que se registró el estado del pago.
     *
     * @return Fecha y hora en que se registró el estado del pago.
     */
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    /**
     * Establece la fecha y hora en que se registró el estado del pago.
     *
     * @param fechaHora Fecha y hora en que se registró el estado del pago.
     */
    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
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
        if (!(object instanceof PagosEstatus)) {
            return false;
        }
        PagosEstatus other = (PagosEstatus) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    /**
     * Devuelve una representación en forma de cadena de este objeto.
     *
     * @return Cadena que representa el objeto.
     */
    @Override
    public String toString() {
       return "entidades.PagosEstatus[ id_estatus=" + id + " ]";
    }
}
