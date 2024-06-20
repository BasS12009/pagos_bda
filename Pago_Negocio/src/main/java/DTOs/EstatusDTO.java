/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import entidades.Estatus;
import entidades.Pago;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diana
 */
public class EstatusDTO {
    private Long id;
    private String nombre;  
    private List<PagoDTO> pagos;

    public EstatusDTO() {
    }

    public EstatusDTO(Long id, String nombre, List<PagoDTO> pagos) {
        this.id = id;
        this.nombre = nombre;
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

    public List<PagoDTO> getPagos() {
        return pagos;
    }

    public void setPagos(List<PagoDTO> pagos) {
        this.pagos = pagos;
    }
    
    public static EstatusDTO convertir(Estatus estatus) {
        List<PagoDTO> pagosDTO = new ArrayList<>();
        for (Pago pago : estatus.getPagos()) {
            pagosDTO.add(PagoDTO.convertir(pago));
        }
        return new EstatusDTO(estatus.getId(), estatus.getNombre(), pagosDTO);
    }
    
    public static Estatus convertir(EstatusDTO estatusDTO) {
        if (estatusDTO == null) {
            return null;
        }

        Estatus estatus = new Estatus();
        estatus.setId(estatusDTO.getId());
        estatus.setNombre(estatusDTO.getNombre());

        // Convertir la lista de PagoDTO a lista de Pago
        List<Pago> pagos = new ArrayList<>();
        for (PagoDTO pagoDTO : estatusDTO.getPagos()) {
            pagos.add(PagoDTO.convertir(pagoDTO));
        }
        estatus.setPagos(pagos);

        return estatus;
    }
    
}
