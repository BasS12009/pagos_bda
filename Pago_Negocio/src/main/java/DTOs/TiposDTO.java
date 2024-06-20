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
public class TiposDTO {
    private Long id;
    private String nombre;
    private Integer numeroParcialidades;
    private List<PagoDTO> pagos;

    public TiposDTO() {
    }

    public TiposDTO(Long id, String nombre, Integer numeroParcialidades, List<PagoDTO> pagos) {
        this.id = id;
        this.nombre = nombre;
        this.numeroParcialidades = numeroParcialidades;
        this.pagos = pagos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNumeroParcialidades() {
        return numeroParcialidades;
    }

    public void setNumeroParcialidades(Integer numeroParcialidades) {
        this.numeroParcialidades = numeroParcialidades;
    }

    public List<PagoDTO> getPagos() {
        return pagos;
    }

    public void setPagos(List<PagoDTO> pagos) {
        this.pagos = pagos;
    }
    
    
}
