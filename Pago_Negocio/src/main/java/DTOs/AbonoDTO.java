/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import entidades.Abono;
import java.time.LocalDateTime;

/**
 *
 * @author diana
 * 
 */
public class AbonoDTO {
    private Long id;
    private LocalDateTime fechaHora;
    private Double monto;
    private PagoDTO pagoDTO;

    public AbonoDTO() {
    }

    public AbonoDTO(Long id, LocalDateTime fechaHora, Double monto, PagoDTO pagoDTO) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.monto = monto;
        this.pagoDTO = pagoDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public PagoDTO getPagoDTO() {
        return pagoDTO;
    }

    public void setPagoDTO(PagoDTO pagoDTO) {
        this.pagoDTO = pagoDTO;
    }
    
    public static AbonoDTO convertir(Abono abono) {
        AbonoDTO abonoDTO = new AbonoDTO();
        abonoDTO.setId(abono.getId());
        abonoDTO.setFechaHora(abono.getFechaHora());
        abonoDTO.setMonto(abono.getMonto());
        if (abono.getPago() != null) {
            abonoDTO.setPagoDTO(PagoDTO.convertir(abono.getPago()));
        }
        return abonoDTO;
    }
    
    public static Abono convertir(AbonoDTO abonoDTO) {
        Abono abono = new Abono();
        abono.setId(abonoDTO.getId());
        abono.setFechaHora(abonoDTO.getFechaHora());
        abono.setMonto(abonoDTO.getMonto());
        
        if (abonoDTO.getPagoDTO() != null) {
            abono.setPago(PagoDTO.convertir(abonoDTO.getPagoDTO()));
        }
        
        return abono;
    }
}
