/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import entidades.Pago;
import entidades.Tipos;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    
    public static TiposDTO convertir(Tipos tipos) {
        TiposDTO tiposDTO = new TiposDTO();
        tiposDTO.setId(tipos.getId());
        tiposDTO.setNombre(tipos.getNombre());
        tiposDTO.setNumeroParcialidades(tipos.getNumeroParcialidades());

        List<PagoDTO> pagosDTO = tipos.getPagos().stream()
                .map(PagoDTO::convertir) 
                .collect(Collectors.toList());
        tiposDTO.setPagos(pagosDTO);

        return tiposDTO;
    }
    
    public static Tipos convertir(TiposDTO tiposDTO) {
        if (tiposDTO == null) {
            return null;
        }

        Tipos tipos = new Tipos();
        tipos.setId(tiposDTO.getId());
        tipos.setNombre(tiposDTO.getNombre());
        tipos.setNumeroParcialidades(tiposDTO.getNumeroParcialidades());

        // Convertir la lista de PagoDTO a lista de Pago
        List<Pago> pagos = new ArrayList<>();
        for (PagoDTO pagoDTO : tiposDTO.getPagos()) {
            pagos.add(PagoDTO.convertir(pagoDTO));
        }
        tipos.setPagos(pagos);

        return tipos;
    }
}
