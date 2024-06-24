/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import entidades.Pago;
import excepcion.ExcepcionDAO;
import java.util.List;

/**
 *
 * @author PC Gamer
 * Interfaz que define las operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * para la entidad Pago.
 */
public interface IPagoDAO {
    
    /**
     * Guarda un nuevo pago en la base de datos.
     * 
     * @param pago El objeto Pago que se desea guardar.
     * @throws excepcion.ExcepcionDAO
     */
    public Pago guardarPago(Pago pago) throws ExcepcionDAO;
    
    /**
     * Actualiza la información de un pago existente en la base de datos.
     * 
     * @param pago El objeto Pago con los datos actualizados.
     * @throws excepcion.ExcepcionDAO
     */
    void actualizarPago(Pago pago)throws ExcepcionDAO;
    
    /**
     * Elimina un pago de la base de datos.
     * 
     * @param pago El objeto Pago que se desea eliminar.
     * @throws excepcion.ExcepcionDAO
     */
    void eliminarPago(Pago pago)throws ExcepcionDAO;
    
    /**
     * Busca y retorna un pago por su ID.
     * 
     * @param id El ID del pago que se desea buscar.
     * @return El objeto Pago encontrado, o null si no existe.
     * @throws excepcion.ExcepcionDAO
     */
    Pago buscarPagoPorId(Long id)throws ExcepcionDAO;
    
    /**
     * Retorna una lista con todos los pagos almacenados en la base de datos.
     * 
     * @return Lista de objetos Pago.
     * @throws excepcion.ExcepcionDAO
     */
    List<Pago> obtenerTodosLosPagos()throws ExcepcionDAO;
    
    /**
     * Retorna una lista con todos los pagos asociados a un beneficiario específico.
     * 
     * @param idBeneficiario el id del beneficiario.
     * @return Lista de pagos asociados al beneficiario.
     * @throws excepcion.ExcepcionDAO
     */
    public List<Pago> obtenerPagosPorBeneficiario(Long idBeneficiario)throws ExcepcionDAO;
    
    /**
    * Retorna una lista con todos los pagos asociados a una clave de contrato específica.
    *
    * @param claveContrato La clave de contrato cuyos pagos se desean obtener.
    * @return Lista de objetos Pago asociados a la clave de contrato.
     * @throws excepcion.ExcepcionDAO
    */
    public List<Pago> obtenerPagosPorClaveContrato(String claveContrato)throws ExcepcionDAO;
    
    public List<Pago> obtenerPagosEstatusPorEstatus(String nombre);
}

