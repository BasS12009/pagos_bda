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
        BeneficiarioDTO beneficiarioDTO = new BeneficiarioDTO();
        beneficiarioDTO.setId(beneficiario.getId());
        beneficiarioDTO.setClaveContrato(beneficiario.getClaveContrato());
        beneficiarioDTO.setSaldo(beneficiario.getSaldo());
        beneficiarioDTO.setNombre(NombreDTO.convertir(beneficiario.getNombre()));
        beneficiarioDTO.setUsuario(beneficiario.getUsuario());
        beneficiarioDTO.setContraseña(beneficiario.getContraseña());

        // Convertir la lista de Pago a lista de PagoDTO usando stream y map
        List<PagoDTO> pagosDTO = beneficiario.getPagos().stream()
                .map(PagoDTO::convertir)
                .collect(Collectors.toList());
        beneficiarioDTO.setPagos(pagosDTO);

        // Convertir la lista de cuentas bancarias
        List<CuentaBancariaDTO> cuentasBancariasDTO = beneficiario.getCuentasBancarias().stream()
                .map(CuentaBancariaDTO::convertir)
                .collect(Collectors.toList());
        beneficiarioDTO.setCuentasBancarias(cuentasBancariasDTO);

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

        // Convertir la lista de PagoDTO a lista de Pago
        List<Pago> pagos = new ArrayList<>();
        for (PagoDTO pagoDTO : beneficiarioDTO.getPagos()) {
            pagos.add(PagoDTO.convertir(pagoDTO));
        }
        beneficiario.setPagos(pagos);

        // Convertir la lista de CuentaBancariaDTO a lista de CuentaBancaria
        List<CuentaBancaria> cuentasBancarias = new ArrayList<>();
        for (CuentaBancariaDTO cuentaBancariaDTO : beneficiarioDTO.getCuentasBancarias()) {
            cuentasBancarias.add(CuentaBancariaDTO.convertir(cuentaBancariaDTO));
        }
        beneficiario.setCuentasBancarias(cuentasBancarias);

        return beneficiario;
    }
    
}
