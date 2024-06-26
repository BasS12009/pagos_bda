/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import entidades.Estatus;
import entidades.PagosEstatus;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC Gamer
 */
public class PagosEstatusDTO {
    private Long id;
    private PagoDTO pago;
    private EstatusDTO estatus;
    private String mensaje;

    public PagosEstatusDTO() {
    }

    public PagosEstatusDTO(Long id, PagoDTO pago, EstatusDTO estatus) {
        this.id = id;
        this.pago = pago;
        this.estatus = estatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PagoDTO getPago() {
        return pago;
    }

    public void setPago(PagoDTO pago) {
        this.pago = pago;
    }

    public EstatusDTO getEstatus() {
        return estatus;
    }

    public void setEstatus(EstatusDTO estatus) {
        this.estatus = estatus;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    /**
     * Convierte un PagosEstatus en un objeto EstatusDTO.
     * 
     * @param pagosEstatus El PagosEstatus a convertir.
     * @return El EstatusDTO resultante.
     */
    public static EstatusDTO convertirToEstatusDTO(PagosEstatus pagosEstatus) {
        if (pagosEstatus == null) {
            return null;
        }

        EstatusDTO estatusDTO = new EstatusDTO();
        estatusDTO.setId(pagosEstatus.getEstatus().getId());
        estatusDTO.setNombre(pagosEstatus.getEstatus().getNombre());

        List<PagoDTO> pagosDTO = new ArrayList<>();
        pagosDTO.add(PagoDTO.convertir(pagosEstatus.getPago()));
        
        estatusDTO.setPagos(pagosDTO);
        

        return estatusDTO;
    }
}
