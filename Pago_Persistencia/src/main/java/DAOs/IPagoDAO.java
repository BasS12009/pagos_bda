/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import entidades.Pago;
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
     */
    void guardarPago(Pago pago);
    
    /**
     * Actualiza la información de un pago existente en la base de datos.
     * 
     * @param pago El objeto Pago con los datos actualizados.
     */
    void actualizarPago(Pago pago);
    
    /**
     * Elimina un pago de la base de datos.
     * 
     * @param pago El objeto Pago que se desea eliminar.
     */
    void eliminarPago(Pago pago);
    
    /**
     * Busca y retorna un pago por su ID.
     * 
     * @param id El ID del pago que se desea buscar.
     * @return El objeto Pago encontrado, o null si no existe.
     */
    Pago buscarPagoPorId(Long id);
    
    /**
     * Retorna una lista con todos los pagos almacenados en la base de datos.
     * 
     * @return Lista de objetos Pago.
     */
    List<Pago> obtenerTodosLosPagos();
}

