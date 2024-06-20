/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Representa el estado o estatus asociado a un pago.
 * Cada estatus tiene un nombre descriptivo y puede estar asociado a varios pagos.
 * 
 * @author PC Gamer
 */
@Entity
@Table(name = "estatus")
public class Estatus implements Serializable {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    
    @Column(name="nombre",nullable = false, length = 50)
    private String nombre;

    @OneToMany(mappedBy = "estatus", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PagosEstatus> pagosEstatus;
    
    
    
    /**
     * Constructor vacío requerido por JPA.
     */
    public Estatus() {
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
     * Dos objetos de tipo Estatus se consideran iguales si tienen el mismo identificador (id).
     * 
     * @param object Objeto a comparar.
     * @return true si los objetos son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Estatus)) {
            return false;
        }
        Estatus other = (Estatus) object;
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
        return "entidades.Estatus[ id=" + id + " ]";
    }

    /**
     * Obtiene el identificador único del estatus.
     * 
     * @return Identificador único del estatus.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del estatus.
     * 
     * @param id Identificador único del estatus.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre descriptivo del estatus.
     * 
     * @return Nombre descriptivo del estatus.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre descriptivo del estatus.
     * 
     * @param nombre Nombre descriptivo del estatus.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    /**
     * Obtiene la lista de pagos asociados a este estatus.
     * 
     * @return Lista de pagos asociados a este estatus.
     */
    public List<PagosEstatus> getPagosEstatus() {
        return pagosEstatus;
    }

    /**
     * Establece la lista de pagos asociados a este estatus.
     * 
     * @param pagosEstatus Lista de pagos asociados a este estatus.
     */
    public void setPagosEstatus(List<PagosEstatus> pagosEstatus) {
        this.pagosEstatus = pagosEstatus;
    }


}
