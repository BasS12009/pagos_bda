/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author PC Gamer
 *
 * Entidad que representa los tipos de pagos.
 * 
 * Esta clase contiene información sobre los tipos de pagos disponibles,
 * incluyendo el nombre del tipo, el número de parcialidades, y una lista
 * de pagos asociados a este tipo.
 */
@Entity
@Table(name = "tipos")
public class Tipos implements Serializable{
    private static final long serialVersionUID = 1L;
    
    /**
     * Identificador único del tipo de pago.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Nombre del tipo de pago.
     */
    @Column(nullable = false, length = 50)
    private String nombre;
    
    /**
     * Número de parcialidades permitidas para este tipo de pago.
     */
    @Column(name = "numero_parcialidades", nullable = false)
    private Integer numeroParcialidades;

    
    /**
     * Lista de pagos asociados a este tipo de pago.
     */
    @OneToMany(mappedBy = "tipos")
    private List<Pago> pagos;

    // Constructor vacío requerido por JPA
    public Tipos() {
    }

    // Constructor detallado para inicializar todos los atributos
    public Tipos(String nombre, Integer numeroParcialidades) {
        this.nombre = nombre;
        this.numeroParcialidades = numeroParcialidades;
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
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipos)) {
            return false;
        }
        Tipos other = (Tipos) object;
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
        return "Tipos[ id=" + id + " ]";
    }

    /**
     * Obtiene el identificador único del tipo de pago.
     *
     * @return Identificador único del tipo de pago.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del tipo de pago.
     *
     * @param id Nuevo identificador único del tipo de pago.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del tipo de pago.
     *
     * @return Nombre del tipo de pago.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del tipo de pago.
     *
     * @param nombre Nuevo nombre del tipo de pago.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el número de parcialidades permitidas para este tipo de pago.
     *
     * @return Número de parcialidades permitidas.
     */
    public Integer getNumeroParcialidades() {
        return numeroParcialidades;
    }

    /**
     * Establece el número de parcialidades permitidas para este tipo de pago.
     *
     * @param numeroParcialidades Nuevo número de parcialidades permitidas.
     */
    public void setNumeroParcialidades(Integer numeroParcialidades) {
        this.numeroParcialidades = numeroParcialidades;
    }

    /**
     * Obtiene la lista de pagos asociados a este tipo de pago.
     *
     * @return Lista de pagos asociados.
     */
    public List<Pago> getPagos() {
        return pagos;
    }

    /**
     * Establece la lista de pagos asociados a este tipo de pago.
     *
     * @param pagos Nueva lista de pagos asociados.
     */
    public void setPagos(List<Pago> pagos) {
        this.pagos = pagos;
    }
    
    
    
}
