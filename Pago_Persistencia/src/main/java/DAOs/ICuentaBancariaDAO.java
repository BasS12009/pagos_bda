/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import entidades.CuentaBancaria;
import java.util.List;

/**
 * Interfaz que define operaciones estándar para acceder y manipular entidades relacionadas con cuentas bancarias en la base de datos.
 * Las implementaciones de esta interfaz pueden utilizar JPA u otro método de persistencia.
 * 
 * @author PC
 */
public interface ICuentaBancariaDAO {
    
    /**
     * Guarda una nueva cuenta bancaria en la base de datos.
     * 
     * @param cuenta La cuenta bancaria que se desea guardar.
     */
    void guardarCuentaBancaria(CuentaBancaria cuenta);
    
    /**
     * Actualiza la información de una cuenta bancaria existente en la base de datos.
     * 
     * @param cuenta La cuenta bancaria con los datos actualizados.
     */
    void actualizarCuentaBancaria(CuentaBancaria cuenta);
    
    /**
     * Elimina una cuenta bancaria de la base de datos.
     * 
     * @param cuenta La cuenta bancaria que se desea eliminar.
     */
    void eliminarCuentaBancaria(CuentaBancaria cuenta);
    
    /**
     * Busca y retorna una cuenta bancaria por su número de cuenta.
     * 
     * @param numeroCuenta El número de cuenta de la cuenta bancaria que se desea buscar.
     * @return La cuenta bancaria encontrada, o null si no existe.
     */
    CuentaBancaria buscarCuentaBancariaPorNumero(String numeroCuenta);
    
    /**
     * Retorna una lista con todas las cuentas bancarias almacenadas en la base de datos.
     * 
     * @return Lista de objetos CuentaBancaria.
     */
    List<CuentaBancaria> obtenerTodasLasCuentasBancarias();
    
    /**
     * Retorna una lista con todas las cuentas bancarias asociadas a un beneficiario específico.
     * 
     * @param claveContrato La clave de contrato del beneficiario.
     * @return Lista de cuentas bancarias asociadas al beneficiario.
     */
    List<CuentaBancaria> obtenerCuentasBancariasPorBeneficiario(String claveContrato);
    
}
