/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import java.util.List;
import entidades.Abono;

/**
 * Interfaz que define operaciones estándar para acceder y manipular entidades relacionadas con abonos en la base de datos.
 * Las implementaciones de esta interfaz pueden utilizar JPA u otro método de persistencia.
 * 
 * Esta interfaz puede extenderse agregando métodos adicionales de consulta según sea necesario.
 * 
 * @author PC
 */
public interface IAbonoDAO {
    
    /**
     * Guarda un nuevo abono en la base de datos.
     * 
     * @param abono El objeto Abono que se desea guardar.
     */
    void guardarAbono(Abono abono);
    
    /**
     * Actualiza la información de un abono existente en la base de datos.
     * 
     * @param abono El objeto Abono con los datos actualizados.
     */
    void actualizarAbono(Abono abono);
    
    /**
     * Elimina un abono de la base de datos.
     * 
     * @param abono El objeto Abono que se desea eliminar.
     */
    void eliminarAbono(Abono abono);
    
    /**
     * Busca y retorna un abono por su identificador único.
     * 
     * @param id El identificador único del abono que se desea buscar.
     * @return El objeto Abono encontrado, o null si no existe.
     */
    public List<Abono> obtenerAbonosPorBeneficiario(Long beneficiarioId);
    
    public Abono buscarAbonoPorId(Long id);
    
    /**
     * Retorna una lista con todos los abonos almacenados en la base de datos.
     * 
     * @return Lista de objetos Abono.
     */
    List<Abono> obtenerTodosLosAbonos();
    

}
