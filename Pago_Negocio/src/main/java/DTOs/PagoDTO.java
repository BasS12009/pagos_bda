/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import entidades.Abono;
import entidades.CuentaBancaria;
import entidades.Estatus;
import entidades.Pago;
import entidades.PagosEstatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author diana
 */
public class PagoDTO {
    private Long id;
    private BigDecimal monto;
    private LocalDateTime fechaHora;
    private String comprobante;
    private TiposDTO tipo;
    private BeneficiarioDTO beneficiario;
    private List<AbonoDTO> abonos;
    private List<EstatusDTO> estatus;
    private List<CuentaBancariaDTO> cuentas;

    public PagoDTO() {
    }

    public PagoDTO(Long id, BigDecimal monto, LocalDateTime fechaHora, String comprobante, TiposDTO tipo, BeneficiarioDTO beneficiario, List<AbonoDTO> abonos, List<EstatusDTO> estatus, List<CuentaBancariaDTO> cuentas) {
        this.id = id;
        this.monto = monto;
        this.fechaHora = fechaHora;
        this.comprobante = comprobante;
        this.tipo = tipo;
        this.beneficiario = beneficiario;
        this.abonos = abonos;
        this.estatus = estatus;
        this.cuentas = cuentas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
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

    public List<CuentaBancariaDTO> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<CuentaBancariaDTO> cuentas) {
        this.cuentas = cuentas;
    }

    /**
     * Convierte un objeto Pago en un PagoDTO.
     * 
     * @param pago El Pago a convertir.
     * @return El PagoDTO resultante.
     */
    public static PagoDTO convertir(Pago pago) {
        PagoDTO pagoDTO = new PagoDTO();
        pagoDTO.setId(pago.getId());
        pagoDTO.setMonto(pago.getMonto());
        pagoDTO.setFechaHora(pago.getFechaHora());
        pagoDTO.setComprobante(pago.getComprobante());
        pagoDTO.setTipo(TiposDTO.convertir(pago.getTipo()));
        pagoDTO.setBeneficiario(BeneficiarioDTO.convertir(pago.getBeneficiario()));
        pagoDTO.setAbonos(pago.getAbonos().stream()
                .map(AbonoDTO::convertir)
                .collect(Collectors.toList()));
        pagoDTO.setEstatus(pago.getPagosEstatus().stream()
                .map(PagosEstatus::getEstatus)
                .map(EstatusDTO::convertir)
                .collect(Collectors.toList()));

        CuentaBancaria cuentaBancaria = pago.getCuentaBancaria();

        if (cuentaBancaria != null) {
            CuentaBancariaDTO cuentaBancariaDTO = CuentaBancariaDTO.convertir(cuentaBancaria);
            pagoDTO.setCuentas(Arrays.asList(cuentaBancariaDTO));
        }

        return pagoDTO;
    }

    /**
     * Convierte un PagoDTO en un objeto Pago.
     * 
     * @param pagoDTO El PagoDTO a convertir.
     * @return El Pago resultante.
     */
    public static Pago convertir(PagoDTO pagoDTO) {
        Pago pago = new Pago();
        pago.setId(pagoDTO.getId());
        pago.setMonto(pagoDTO.getMonto());
        pago.setFechaHora(pagoDTO.getFechaHora());
        pago.setComprobante(pagoDTO.getComprobante());
        pago.setTipo(TiposDTO.convertir(pagoDTO.getTipo()));
        pago.setBeneficiario(BeneficiarioDTO.convertir(pagoDTO.getBeneficiario()));

        if (pagoDTO.getAbonos() != null) {
            List<Abono> abonos = pagoDTO.getAbonos().stream()
                    .map(AbonoDTO::convertir)
                    .collect(Collectors.toList());
            pago.setAbonos(abonos);
        }

        if (pagoDTO.getEstatus() != null) {
            List<PagosEstatus> pagosEstatus = pagoDTO.getEstatus().stream()
                    .map(EstatusDTO::convertir)
                    .map(pe -> {
                        PagosEstatus pagoEstatus = new PagosEstatus();
                        pagoEstatus.setEstatus(pe);
                        pagoEstatus.setPago(pago);
                        return pagoEstatus;
                    })
                    .collect(Collectors.toList());
            pago.setPagosEstatus(pagosEstatus);
        }

        if (pagoDTO.getCuentas() != null && !pagoDTO.getCuentas().isEmpty()) {
            CuentaBancaria cuentaBancaria = CuentaBancariaDTO.convertir(pagoDTO.getCuentas().get(0)); // Suponiendo que solo hay una cuenta bancaria
            pago.setCuentaBancaria(cuentaBancaria);
        }

        return pago;
    }
}
