/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import DTOs.PagoDTO;
import entidades.Pago;
import java.util.List;

public interface IPagoNegocio {
    
    /**
     * Guarda un nuevo pago en la base de datos.
     * 
     * @param pagoDTO El objeto Pago que se desea guardar.
     */
    void guardarPago(PagoDTO pagoDTO);
    
    /**
     * Actualiza la información de un pago existente en la base de datos.
     * 
     * @param pagoDTO El objeto Pago con los datos actualizados.
     */
    void actualizarPago(PagoDTO pagoDTO);
    
    /**
     * Elimina un pago de la base de datos.
     * 
     * @param id
     */
    void eliminarPago(Long id);
    
    /**
     * Busca y retorna un pago por su ID.
     * 
     * @param id El ID del pago que se desea buscar.
     * @return El objeto Pago encontrado, o null si no existe.
     */
    PagoDTO buscarPagoPorId(Long id);
    
    /**
     * Retorna una lista con todos los pagos almacenados en la base de datos.
     * 
     * @return Lista de objetos Pago.
     */
    List<PagoDTO> obtenerTodosLosPagos();
    
    /**
     * Retorna una lista con todos los pagos asociados a un beneficiario específico.
     * 
     * @param idBeneficiario
     * @return Lista de pagos asociados al beneficiario.
     */
    List<PagoDTO> obtenerPagosPorBeneficiario(Long idBeneficiario);
    
}
