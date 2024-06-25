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
public class BeneficiarioDTO {

    
    private Long id;
    private String claveContrato;
    private Double saldo;
    private NombreDTO nombre;
    private String usuario;
    private String contraseña;
    private List<PagoDTO> pagos;
    private List<CuentaBancariaDTO> cuentasBancarias;
    
    public BeneficiarioDTO() {
    }
    
    public BeneficiarioDTO(Long id, String claveContrato, Double saldo, NombreDTO nombre, String usuario, String contraseña, List<PagoDTO> pagos, List<CuentaBancariaDTO> cuentasBancarias) {
        this.id = id;
        this.claveContrato = claveContrato;
        this.saldo = saldo;
        this.nombre = nombre;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.pagos = pagos;
        this.cuentasBancarias = cuentasBancarias;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClaveContrato() {
        return claveContrato;
    }

    public void setClaveContrato(String claveContrato) {
        this.claveContrato = claveContrato;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public NombreDTO getNombre() {
        return nombre;
    }

    public void setNombre(NombreDTO nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public List<PagoDTO> getPagos() {
        return pagos;
    }

    public void setPagos(List<PagoDTO> pagos) {
        this.pagos = pagos;
    }

    public List<CuentaBancariaDTO> getCuentasBancarias() {
        return cuentasBancarias;
    }

    public void setCuentasBancarias(List<CuentaBancariaDTO> cuentasBancarias) {
        this.cuentasBancarias = cuentasBancarias;
    }

    public static BeneficiarioDTO convertir(Beneficiario beneficiario) {
        if (beneficiario == null) {
            return null;
        }

        BeneficiarioDTO beneficiarioDTO = new BeneficiarioDTO();
        beneficiarioDTO.setId(beneficiario.getId());
        beneficiarioDTO.setClaveContrato(beneficiario.getClaveContrato());
        beneficiarioDTO.setSaldo(beneficiario.getSaldo());
        beneficiarioDTO.setNombre(NombreDTO.convertir(beneficiario.getNombre()));
        beneficiarioDTO.setUsuario(beneficiario.getUsuario());
        beneficiarioDTO.setContraseña(beneficiario.getContraseña());
        if(beneficiario.getPagos()!=null){
        List<PagoDTO> pagosDTO = beneficiario.getPagos().stream()
                .map(pago -> {
                    PagoDTO pagoDTO = new PagoDTO();
                    pagoDTO.setId(pago.getId());
                    return pagoDTO;
                })
                .collect(Collectors.toList());
        beneficiarioDTO.setPagos(pagosDTO);
        }
        if(beneficiario.getCuentasBancarias()!=null){
        List<CuentaBancariaDTO> cuentasBancariasDTO = beneficiario.getCuentasBancarias().stream()
                .map(cuentaBancaria -> {
                    CuentaBancariaDTO cuentaBancariaDTO = new CuentaBancariaDTO();
                    cuentaBancariaDTO.setId(cuentaBancaria.getId());
                    cuentaBancariaDTO.setNumeroCuenta(cuentaBancaria.getNumeroCuenta());
                    cuentaBancariaDTO.setClave(cuentaBancaria.getClave());
                    cuentaBancariaDTO.setBanco(cuentaBancaria.getBanco());
                    cuentaBancariaDTO.setEliminada(cuentaBancaria.getEliminada());
                    cuentaBancariaDTO.setBeneficiario(beneficiarioDTO);
                    return cuentaBancariaDTO;
                })
                .collect(Collectors.toList());
        beneficiarioDTO.setCuentasBancarias(cuentasBancariasDTO);
        }
        return beneficiarioDTO;
    }
    
    public static Beneficiario convertir(BeneficiarioDTO beneficiarioDTO) {
            if (beneficiarioDTO == null) {
            return null;
        }

        Beneficiario beneficiario = new Beneficiario();
        beneficiario.setId(beneficiarioDTO.getId());
        beneficiario.setClaveContrato(beneficiarioDTO.getClaveContrato());
        beneficiario.setSaldo(beneficiarioDTO.getSaldo());
        beneficiario.setNombre(NombreDTO.convertir(beneficiarioDTO.getNombre()));
        beneficiario.setUsuario(beneficiarioDTO.getUsuario());
        beneficiario.setContraseña(beneficiarioDTO.getContraseña());
        if (beneficiarioDTO.getPagos() != null) {
            List<Pago> pagos = beneficiarioDTO.getPagos().stream()
                    .map(pagoDTO -> {
                        Pago pago = new Pago();
                        pago.setId(pagoDTO.getId());
                        return pago;
                    })
                    .collect(Collectors.toList());
            beneficiario.setPagos(pagos);
        }
        
        if(beneficiarioDTO.getCuentasBancarias()!=null){
        List<CuentaBancaria> cuentasBancarias = beneficiarioDTO.getCuentasBancarias().stream()
                .map(CuentaBancariaDTO::convertir)
                .collect(Collectors.toList());
        beneficiario.setCuentasBancarias(cuentasBancarias);
        }
        return beneficiario;
    }
    
}
