/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import entidades.Estatus;
import entidades.Pago;
import entidades.PagosEstatus;
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
    
    /**
     * Convierte un objeto Estatus en un EstatusDTO.
     * 
     * @param estatus El Estatus a convertir.
     * @return El EstatusDTO resultante.
     */
    public static EstatusDTO convertir(Estatus estatus) {
        List<PagoDTO> pagosDTO = new ArrayList<>();
        if (estatus.getPagosEstatus() != null) {
            for (PagosEstatus pagoEstatus : estatus.getPagosEstatus()) {
                pagosDTO.add(PagoDTO.convertir(pagoEstatus.getPago()));
            }
        }
        return new EstatusDTO(estatus.getId(), estatus.getNombre(), pagosDTO);
    }
    
    /**
     * Convierte un EstatusDTO en un objeto Estatus.
     * 
     * @param estatusDTO El EstatusDTO a convertir.
     * @return El Estatus resultante.
     */
    public static Estatus convertir(EstatusDTO estatusDTO) {
        if (estatusDTO == null) {
            return null;
        }

        Estatus estatus = new Estatus();
        estatus.setId(estatusDTO.getId());
        estatus.setNombre(estatusDTO.getNombre());

        List<PagosEstatus> pagosEstatus = new ArrayList<>();
        if (estatusDTO.getPagos() != null) {
            for (PagoDTO pagoDTO : estatusDTO.getPagos()) {
                PagosEstatus pagoEstatus = new PagosEstatus();
                pagoEstatus.setPago(PagoDTO.convertir(pagoDTO));
                pagoEstatus.setEstatus(estatus);
                pagosEstatus.add(pagoEstatus);
            }
        }
        estatus.setPagosEstatus(pagosEstatus);

        return estatus;
    }
    
    /**
     * Convierte un EstatusDTO en un objeto PagosEstatus.
     *
     * @param estatusDTO El EstatusDTO a convertir.
     * @return El PagosEstatus resultante.
     */
    public static PagosEstatus convertirToPagosEstatus(EstatusDTO estatusDTO) {
        if (estatusDTO == null) {
            return null;
        }

        PagosEstatus pagosEstatus = new PagosEstatus();
        Estatus estatus = new Estatus();
        estatus.setId(estatusDTO.getId());
        estatus.setNombre(estatusDTO.getNombre());
        pagosEstatus.setEstatus(estatus);

        return pagosEstatus;
    }
   
}
