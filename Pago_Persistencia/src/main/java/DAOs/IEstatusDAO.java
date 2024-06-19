/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import java.util.List;
import entidades.Estatus;

/**
 * Interfaz que define operaciones estándar para acceder y manipular entidades relacionadas con el estado (Estatus) en la base de datos.
 * Las implementaciones de esta interfaz pueden utilizar JPA u otro método de persistencia.
 * 
 * @author PC
 */
public interface IEstatusDAO {
    
    /**
     * Guarda un nuevo estado en la base de datos.
     * 
     * @param estatus El objeto Estatus que se desea guardar.
     */
    void guardarEstatus(Estatus estatus);
    
    /**
     * Actualiza la información de un estado existente en la base de datos.
     * 
     * @param estatus El objeto Estatus con los datos actualizados.
     */
    void actualizarEstatus(Estatus estatus);
    
    /**
     * Elimina un estado de la base de datos.
     * 
     * @param estatus El objeto Estatus que se desea eliminar.
     */
    void eliminarEstatus(Estatus estatus);
    
    /**
     * Busca y retorna un estado por su ID.
     * 
     * @param id El ID del estado que se desea buscar.
     * @return El objeto Estatus encontrado, o null si no existe.
     */
    Estatus buscarEstatusPorId(Long id);
    
    /**
     * Retorna una lista con todos los estados almacenados en la base de datos.
     * 
     * @return Lista de objetos Estatus.
     */
    List<Estatus> obtenerTodosLosEstatus();

}
