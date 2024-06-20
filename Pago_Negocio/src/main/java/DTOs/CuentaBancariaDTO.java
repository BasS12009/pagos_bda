/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import entidades.CuentaBancaria;
import entidades.Pago;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author diana
 */
public class CuentaBancariaDTO{
    private Long id;
    private String numeroCuenta;
    private String clave;
    private String banco;
    private Boolean eliminada;
    private List<PagoDTO> pagos;
    
    public CuentaBancariaDTO() {
    }

    public CuentaBancariaDTO(Long id, String numeroCuenta, String clave, String banco, Boolean eliminada, List<PagoDTO> pagos) {
        this.id = id;
        this.numeroCuenta = numeroCuenta;
        this.clave = clave;
        this.banco = banco;
        this.eliminada = eliminada;
        this.pagos = pagos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public Boolean getEliminada() {
        return eliminada;
    }

    public void setEliminada(Boolean eliminada) {
        this.eliminada = eliminada;
    }

    public List<PagoDTO> getPagos() {
        return pagos;
    }

    public void setPagos(List<PagoDTO> pagos) {
        this.pagos = pagos;
    }
    
    public static CuentaBancariaDTO convertir(CuentaBancaria cuentaBancaria) {
        CuentaBancariaDTO cuentaBancariaDTO = new CuentaBancariaDTO();
        cuentaBancariaDTO.setId(cuentaBancaria.getId());
        cuentaBancariaDTO.setNumeroCuenta(cuentaBancaria.getNumeroCuenta());
        cuentaBancariaDTO.setClave(cuentaBancaria.getClave());
        cuentaBancariaDTO.setBanco(cuentaBancaria.getBanco());
        cuentaBancariaDTO.setEliminada(cuentaBancaria.getEliminada());

        // Convertir la lista de pagos
        List<PagoDTO> pagosDTO = cuentaBancaria.getPagos().stream()
                .map(PagoDTO::convertir)
                .collect(Collectors.toList());
        cuentaBancariaDTO.setPagos(pagosDTO);

        return cuentaBancariaDTO;
    }
    
    public static CuentaBancaria convertir(CuentaBancariaDTO cuentaBancariaDTO) {
        if (cuentaBancariaDTO == null) {
            return null;
        }

        CuentaBancaria cuentaBancaria = new CuentaBancaria();
        cuentaBancaria.setId(cuentaBancariaDTO.getId());
        cuentaBancaria.setNumeroCuenta(cuentaBancariaDTO.getNumeroCuenta());
        cuentaBancaria.setClave(cuentaBancariaDTO.getClave());
        cuentaBancaria.setBanco(cuentaBancariaDTO.getBanco());
        cuentaBancaria.setEliminada(cuentaBancariaDTO.getEliminada());

        // Convertir la lista de PagoDTO a lista de Pago
        List<Pago> pagos = new ArrayList<>();
        for (PagoDTO pagoDTO : cuentaBancariaDTO.getPagos()) {
            pagos.add(PagoDTO.convertir(pagoDTO));
        }
        cuentaBancaria.setPagos(pagos);

        return cuentaBancaria;
    }
}
