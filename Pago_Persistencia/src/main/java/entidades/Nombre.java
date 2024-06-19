/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Representa el nombre completo de una persona, compuesto por nombres, apellido paterno y apellido materno.
 * Esta clase es utilizada como parte de la información personal de entidades como Beneficiario.
 * 
 * @author PC Gamer
 */
@Embeddable
public class Nombre implements Serializable {

        @Column(nullable = false, length = 50)
    private String nombres;

    @Column(name = "apellido_paterno", nullable = false, length = 50)
    private String apellidoPaterno;

    @Column(name = "apellido_materno", nullable = false, length = 50)
    private String apellidoMaterno;

    /**
     * Constructor vacío requerido por JPA.
     */
    public Nombre() {
    }

    /**
     * Constructor que inicializa el nombre completo de una persona.
     *
     * @param nombres         Nombres de la persona.
     * @param apellidoPaterno Apellido paterno de la persona.
     * @param apellidoMaterno Apellido materno de la persona.
     */
    public Nombre(String nombres, String apellidoPaterno, String apellidoMaterno) {
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
    }
    
    
    /**
     * Devuelve los nombres de la persona.
     * 
     * @return Nombres de la persona.
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * Establece los nombres de la persona.
     * 
     * @param nombres Nombres de la persona.
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * Devuelve el apellido paterno de la persona.
     * 
     * @return Apellido paterno de la persona.
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * Establece el apellido paterno de la persona.
     * 
     * @param apellidoPaterno Apellido paterno de la persona.
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    /**
     * Devuelve el apellido materno de la persona.
     * 
     * @return Apellido materno de la persona.
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * Establece el apellido materno de la persona.
     * 
     * @param apellidoMaterno Apellido materno de la persona.
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

}
