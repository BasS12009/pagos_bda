/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Representa un beneficiario que puede recibir pagos.
 * Cada beneficiario tiene una clave de contrato única, un saldo actual,
 * un nombre (compuesto por nombres, apellido paterno y apellido materno),
 * un nombre de usuario y una contraseña para autenticación.
 * Además, puede estar asociado a varios pagos recibidos.
 * 
 * @author PC Gamer
 */
@Entity
@Table(name = "beneficiarios")
public class Beneficiario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    
    @Column(name = "clave_contrato", nullable = false, length=10)
    private String claveContrato;
    
    @Column(name="saldo",nullable = false)
    private Double saldo;
    
    @Embedded
    private Nombre nombre;
    
    @Column(name="usuario",nullable = false, length=10)
    private String usuario;
    
    @Column(name="contraseña",nullable = false, length=12)
    private String contraseña;
    
    @OneToMany(mappedBy = "beneficiario")
    private List<Pago> pagos;
    
    @OneToMany(mappedBy = "beneficiario", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<CuentaBancaria> cuentasBancarias;
    
    
    /**
     * Constructor vacío requerido.
     */
    public Beneficiario() {
    }

    /**
     * Constructor para inicializar un nuevo beneficiario con los datos básicos.
     * 
     * @param claveContrato La clave de contrato del beneficiario.
     * @param saldo El saldo inicial del beneficiario.
     * @param nombre El nombre completo del beneficiario.
     * @param usuario El nombre de usuario del beneficiario.
     * @param contraseña La contraseña del beneficiario.
     */
    public Beneficiario(String claveContrato, Double saldo, Nombre nombre, String usuario, String contraseña) {
        this.claveContrato = claveContrato;
        this.saldo = saldo;
        this.nombre = nombre;
        this.usuario = usuario;
        this.contraseña = contraseña;
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
     * Dos objetos de tipo Beneficiario se consideran iguales si tienen el mismo identificador (id).
     * 
     * @param object Objeto a comparar.
     * @return true si los objetos son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Beneficiario)) {
            return false;
        }
        Beneficiario other = (Beneficiario) object;
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
        return "entidades.Beneficiario[ id=" + id + " ]";
    }

    /**
     * Obtiene el identificador único del beneficiario.
     * 
     * @return Identificador único del beneficiario.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del beneficiario.
     * 
     * @param id Identificador único del beneficiario.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene la clave de contrato del beneficiario.
     * 
     * @return Clave de contrato del beneficiario.
     */
    public String getClaveContrato() {
        return claveContrato;
    }

    /**
     * Establece la clave de contrato del beneficiario.
     * 
     * @param claveContrato Clave de contrato del beneficiario.
     */
    public void setClaveContrato(String claveContrato) {
        this.claveContrato = claveContrato;
    }

    /**
     * Obtiene el saldo actual del beneficiario.
     * 
     * @return Saldo actual del beneficiario.
     */
    public Double getSaldo() {
        return saldo;
    }

    /**
     * Establece el saldo actual del beneficiario.
     * 
     * @param saldo Saldo actual del beneficiario.
     */
    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    /**
     * Obtiene el nombre completo del beneficiario.
     * 
     * @return Nombre completo del beneficiario.
     */
    public Nombre getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre completo del beneficiario.
     * 
     * @param nombre Nombre completo del beneficiario.
     */
    public void setNombre(Nombre nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el nombre de usuario del beneficiario.
     * 
     * @return Nombre de usuario del beneficiario.
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Establece el nombre de usuario del beneficiario.
     * 
     * @param usuario Nombre de usuario del beneficiario.
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene la contraseña del beneficiario.
     * 
     * @return Contraseña del beneficiario.
     */
    public String getContraseña() {
        return contraseña;
    }

    /**
     * Establece la contraseña del beneficiario.
     * 
     * @param contraseña Contraseña del beneficiario.
     */
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    /**
     * Obtiene la lista de pagos asociados a este beneficiario.
     * 
     * @return Lista de pagos asociados.
     */
    public List<Pago> getPagos() {
        return pagos;
    }

    /**
     * Establece la lista de pagos asociados a este beneficiario.
     * 
     * @param pagos Lista de pagos asociados.
     */
    public void setPagos(List<Pago> pagos) {
        this.pagos = pagos;
    }

    public List<CuentaBancaria> getCuentasBancarias() {
        return cuentasBancarias;
    }

    public void setCuentasBancarias(List<CuentaBancaria> cuentasBancarias) {
        this.cuentasBancarias = cuentasBancarias;
    }
    
}

