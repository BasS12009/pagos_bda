/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import entidades.Beneficiario;
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
    private BeneficiarioDTO beneficiario;
    
    public CuentaBancariaDTO() {
    }

    public CuentaBancariaDTO(Long id, String numeroCuenta, String clave, String banco, Boolean eliminada, List<PagoDTO> pagos,BeneficiarioDTO beneficiario) {
        this.id = id;
        this.numeroCuenta = numeroCuenta;
        this.clave = clave;
        this.banco = banco;
        this.eliminada = eliminada;
        this.pagos = pagos;
        this.beneficiario=beneficiario;
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

    public BeneficiarioDTO getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(BeneficiarioDTO beneficiario) {
        this.beneficiario = beneficiario;
    }

    
    public static CuentaBancariaDTO convertir(CuentaBancaria cuentaBancaria) {
        if (cuentaBancaria == null) {
            return null;
        }

        CuentaBancariaDTO cuentaBancariaDTO = new CuentaBancariaDTO();
        cuentaBancariaDTO.setId(cuentaBancaria.getId());
        cuentaBancariaDTO.setNumeroCuenta(cuentaBancaria.getNumeroCuenta());
        cuentaBancariaDTO.setClave(cuentaBancaria.getClave());
        cuentaBancariaDTO.setBanco(cuentaBancaria.getBanco());
        cuentaBancariaDTO.setEliminada(cuentaBancaria.getEliminada());

        Beneficiario beneficiario = cuentaBancaria.getBeneficiario();
        if (beneficiario != null) {
            BeneficiarioDTO beneficiarioDTO = new BeneficiarioDTO();
            beneficiarioDTO.setId(beneficiario.getId());
            beneficiarioDTO.setClaveContrato(beneficiario.getClaveContrato());
            beneficiarioDTO.setSaldo(beneficiario.getSaldo());
            beneficiarioDTO.setNombre(NombreDTO.convertir(beneficiario.getNombre()));
            beneficiarioDTO.setUsuario(beneficiario.getUsuario());
            beneficiarioDTO.setContraseña(beneficiario.getContraseña());
            cuentaBancariaDTO.setBeneficiario(beneficiarioDTO);
        }

        if(cuentaBancaria.getPagos()!=null){
        List<PagoDTO> pagosDTO = cuentaBancaria.getPagos().stream()
                .map(pago -> {
                    PagoDTO pagoDTO = new PagoDTO();
                    pagoDTO.setId(pago.getId());
                    return pagoDTO;
                })
                .collect(Collectors.toList());
        cuentaBancariaDTO.setPagos(pagosDTO);
        }
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
        BeneficiarioDTO beneficiarioDTO = cuentaBancariaDTO.getBeneficiario();
        if (beneficiarioDTO != null) {
            Beneficiario beneficiario = new Beneficiario();
            beneficiario.setId(beneficiarioDTO.getId());
            beneficiario.setClaveContrato(beneficiarioDTO.getClaveContrato());
            beneficiario.setSaldo(beneficiarioDTO.getSaldo());
            beneficiario.setNombre(NombreDTO.convertir(beneficiarioDTO.getNombre()));
            beneficiario.setUsuario(beneficiarioDTO.getUsuario());
            beneficiario.setContraseña(beneficiario.getContraseña());
            cuentaBancaria.setBeneficiario(beneficiario);
        }
        
        if(cuentaBancariaDTO.getPagos()!=null){
        List<Pago> pagos = cuentaBancariaDTO.getPagos().stream()
                .map(pagoDTO -> {
                    Pago pago = new Pago();
                    pago.setId(pagoDTO.getId());
                    return pago;
                })
                .collect(Collectors.toList());
        cuentaBancaria.setPagos(pagos);
        }

        return cuentaBancaria;
    }
    
}
