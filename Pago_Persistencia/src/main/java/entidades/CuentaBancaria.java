/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Representa una cuenta bancaria asociada a pagos realizados.
 * Cada cuenta bancaria tiene un número de cuenta único, una clave de acceso,
 * un nombre de banco y un estado de eliminación.
 * Además, puede estar asociada a varios pagos realizados.
 * 
 * @author PC Gamer
 */
@Entity
@Table(name = "cuentas_bancarias")
public class CuentaBancaria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "numero_cuenta", nullable = false, length = 20)
    private String numeroCuenta;

    @Column(name="clabe",nullable = false, length = 20)
    private String clabe;

    @Column(name="banco",nullable = false, length = 25)
    private String banco;

    @Column(name="eliminada",nullable = false)
    private Boolean eliminada;

    @OneToMany(mappedBy = "cuentaBancaria")
    private List<Pago> pagos;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "beneficiario_id")
    private Beneficiario beneficiario;
    
    /**
     * Constructor vacío requerido por JPA.
     */
    public CuentaBancaria() {
    }

    /**
     * Constructor para inicializar una nueva cuenta bancaria con los datos básicos.
     * 
     * @param numeroCuenta El número de cuenta bancaria.
     * @param clave La clave asociada a la cuenta bancaria.
     * @param banco El nombre del banco de la cuenta bancaria.
     * @param eliminada Indica si la cuenta bancaria está eliminada o no.
     */
    public CuentaBancaria(String numeroCuenta, String clave, String banco, Boolean eliminada) {
        this.numeroCuenta = numeroCuenta;
        this.clabe = clave;
        this.banco = banco;
        this.eliminada = eliminada;
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
     * Dos objetos de tipo CuentaBancaria se consideran iguales si tienen el mismo identificador (id).
     * 
     * @param object Objeto a comparar.
     * @return true si los objetos son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CuentaBancaria)) {
            return false;
        }
        CuentaBancaria other = (CuentaBancaria) object;
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
        return "entidades.CuentaBancaria[ id=" + id + " ]";
    }

    /**
     * Obtiene el identificador único de la cuenta bancaria.
     * 
     * @return Identificador único de la cuenta bancaria.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único de la cuenta bancaria.
     * 
     * @param id Identificador único de la cuenta bancaria.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el número de cuenta de la cuenta bancaria.
     * 
     * @return Número de cuenta de la cuenta bancaria.
     */
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    /**
     * Establece el número de cuenta de la cuenta bancaria.
     * 
     * @param numeroCuenta Número de cuenta de la cuenta bancaria.
     */
    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    /**
     * Obtiene la clave de acceso de la cuenta bancaria.
     * 
     * @return Clave de acceso de la cuenta bancaria.
     */
    public String getClave() {
        return clabe;
    }

    /**
     * Establece la clave de acceso de la cuenta bancaria.
     * 
     * @param clabe Clabe de acceso de la cuenta bancaria.
     */
    public void setClave(String clabe) {
        this.clabe = clabe;
    }

    /**
     * Obtiene el nombre del banco asociado a la cuenta bancaria.
     * 
     * @return Nombre del banco de la cuenta bancaria.
     */
    public String getBanco() {
        return banco;
    }

    /**
     * Establece el nombre del banco asociado a la cuenta bancaria.
     * 
     * @param banco Nombre del banco de la cuenta bancaria.
     */
    public void setBanco(String banco) {
        this.banco = banco;
    }

    /**
     * Obtiene el estado de eliminación de la cuenta bancaria.
     * 
     * @return true si la cuenta bancaria está eliminada, false si no lo está.
     */
    public Boolean getEliminada() {
        return eliminada;
    }

    /**
     * Establece el estado de eliminación de la cuenta bancaria.
     * 
     * @param eliminada true para marcar la cuenta bancaria como eliminada, false para activarla.
     */
    public void setEliminada(Boolean eliminada) {
        this.eliminada = eliminada;
    }

    /**
     * Obtiene la lista de pagos asociados a esta cuenta bancaria.
     * 
     * @return Lista de pagos asociados.
     */
    public List<Pago> getPagos() {
        return pagos;
    }

    /**
     * Establece la lista de pagos asociados a esta cuenta bancaria.
     * 
     * @param pagos Lista de pagos asociados.
     */
    public void setPagos(List<Pago> pagos) {
        this.pagos = pagos;
    }

    public Beneficiario getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(Beneficiario beneficiario) {
        this.beneficiario = beneficiario;
    }
    
    
}
