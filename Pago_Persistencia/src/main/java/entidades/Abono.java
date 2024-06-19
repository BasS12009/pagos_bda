/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Representa un abono realizado a un pago específico.
 * Cada abono tiene una fecha y hora de registro, un monto abonado y está asociado a un pago específico.
 * 
 * @author PC Gamer
 */
@Entity
@Table(name = "abonos")
public class Abono implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @Column(nullable = false)
    private Double monto;

    @ManyToOne
    @JoinColumn(name = "pago_id", nullable = false)
    private Pago pago;

    /**
     * Constructor vacío requerido por JPA.
     */
    public Abono() {
    }

    /**
     * Constructor para inicializar un nuevo abono con los datos básicos.
     * 
     * @param fechaHora La fecha y hora del abono.
     * @param monto El monto del abono.
     * @param pago El pago al que está asociado este abono.
     */
    public Abono(LocalDateTime fechaHora, Double monto, Pago pago) {
        this.fechaHora = fechaHora;
        this.monto = monto;
        this.pago = pago;
    }
    
    /**
     * Calcula el código hash del objeto basado en su identificador.
     * 
     * @return Código hash del objeto.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Compara este objeto con otro para determinar si son iguales.
     * Dos objetos de tipo Abono se consideran iguales si tienen el mismo identificador (id).
     * 
     * @param object Objeto a comparar.
     * @return true si los objetos son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Abono)) {
            return false;
        }
        Abono other = (Abono) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * Devuelve una representación en cadena de este objeto, que incluye su identificador.
     * 
     * @return Cadena que representa el objeto.
     */
    @Override
    public String toString() {
        return "entidades.Abono[ id=" + id + " ]";
    }

    /**
     * Obtiene el identificador único del abono.
     * 
     * @return Identificador único del abono.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del abono.
     * 
     * @param id Identificador único del abono.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene la fecha y hora en que se realizó el abono.
     * 
     * @return Fecha y hora del abono.
     */
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    /**
     * Establece la fecha y hora en que se realizó el abono.
     * 
     * @param fechaHora Fecha y hora del abono.
     */
    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    /**
     * Obtiene el monto abonado en este abono.
     * 
     * @return Monto abonado.
     */
    public Double getMonto() {
        return monto;
    }

    /**
     * Establece el monto abonado en este abono.
     * 
     * @param monto Monto abonado.
     */
    public void setMonto(Double monto) {
        this.monto = monto;
    }

    /**
     * Obtiene el pago asociado a este abono.
     * 
     * @return Pago asociado.
     */
    public Pago getPago() {
        return pago;
    }

    /**
     * Establece el pago asociado a este abono.
     * 
     * @param pago Pago asociado.
     */
    public void setPago(Pago pago) {
        this.pago = pago;
    }
}
