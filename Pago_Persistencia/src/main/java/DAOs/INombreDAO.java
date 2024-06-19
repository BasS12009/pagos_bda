/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import entidades.Nombre;
import java.util.List;

/**
 * Interfaz que define operaciones estándar para acceder y manipular entidades relacionadas con nombres en la base de datos.
 * Las implementaciones de esta interfaz pueden utilizar JPA u otro método de persistencia.
 * 
 * @author PC
 */
public interface INombreDAO {
    
    /**
     * Guarda un nuevo nombre en la base de datos.
     * 
     * @param nombre El objeto Nombre que se desea guardar.
     */
    void guardarNombre(Nombre nombre);
    
    /**
     * Actualiza la información de un nombre existente en la base de datos.
     * 
     * @param nombre El objeto Nombre con los datos actualizados.
     */
    void actualizarNombre(Nombre nombre);
    
    /**
     * Elimina un nombre de la base de datos.
     * 
     * @param nombre El objeto Nombre que se desea eliminar.
     */
    void eliminarNombre(Nombre nombre);
    
    /**
     * Busca y retorna un nombre por su ID.
     * 
     * @param id El ID del nombre que se desea buscar.
     * @return El objeto Nombre encontrado, o null si no existe.
     */
    Nombre buscarNombrePorId(Long id);
    
    /**
     * Retorna una lista con todos los nombres almacenados en la base de datos.
     * 
     * @return Lista de objetos Nombre.
     */
    List<Nombre> obtenerTodosLosNombres();
    
}
