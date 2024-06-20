/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author diana
 */
public class PagoDTO {
    private Long id;
    private Double monto;
    private LocalDateTime fechaHora;
    private String comprobante;
    private TiposDTO tipo;
    private BeneficiarioDTO beneficiario;
    private List<AbonoDTO> abonos;
    private List<EstatusDTO> estatus;

    public PagoDTO() {
    }

    public PagoDTO(Long id, Double monto, LocalDateTime fechaHora, String comprobante, TiposDTO tipo, BeneficiarioDTO beneficiario, List<AbonoDTO> abonos, List<EstatusDTO> estatus) {
        this.id = id;
        this.monto = monto;
        this.fechaHora = fechaHora;
        this.comprobante = comprobante;
        this.tipo = tipo;
        this.beneficiario = beneficiario;
        this.abonos = abonos;
        this.estatus = estatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getComprobante() {
        return comprobante;
    }

    public void setComprobante(String comprobante) {
        this.comprobante = comprobante;
    }

    public TiposDTO getTipo() {
        return tipo;
    }

    public void setTipo(TiposDTO tipo) {
        this.tipo = tipo;
    }

    public BeneficiarioDTO getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(BeneficiarioDTO beneficiario) {
        this.beneficiario = beneficiario;
    }

    public List<AbonoDTO> getAbonos() {
        return abonos;
    }

    public void setAbonos(List<AbonoDTO> abonos) {
        this.abonos = abonos;
    }

    public List<EstatusDTO> getEstatus() {
        return estatus;
    }

    public void setEstatus(List<EstatusDTO> estatus) {
        this.estatus = estatus;
    }
    
    
    
}
