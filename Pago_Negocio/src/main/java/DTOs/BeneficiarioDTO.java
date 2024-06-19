/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import java.util.List;

/**
 *
 * @author diana
 */
public class BeneficiarioDTO {
    private Long id;
    private String claveContrato;
    private Double saldo;
    private NombreDTO nombre;
    private String usuario;
    private String contraseña;
    private List<PagoDTO> pagos;

    public BeneficiarioDTO() {
    }

    public BeneficiarioDTO(Long id, String claveContrato, Double saldo, NombreDTO nombre, String usuario, String contraseña, List<PagoDTO> pagos) {
        this.id = id;
        this.claveContrato = claveContrato;
        this.saldo = saldo;
        this.nombre = nombre;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.pagos = pagos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClaveContrato() {
        return claveContrato;
    }

    public void setClaveContrato(String claveContrato) {
        this.claveContrato = claveContrato;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public NombreDTO getNombre() {
        return nombre;
    }

    public void setNombre(NombreDTO nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public List<PagoDTO> getPagos() {
        return pagos;
    }

    public void setPagos(List<PagoDTO> pagos) {
        this.pagos = pagos;
    }
    
    
    
}
