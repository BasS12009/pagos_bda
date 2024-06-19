/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import entidades.Beneficiario;
import java.util.List;

/**
 * Interfaz que define operaciones estándar para acceder y manipular entidades relacionadas con beneficiarios en la base de datos.
 * Las implementaciones de esta interfaz pueden utilizar JPA u otro método de persistencia.
 * 
 * Esta interfaz puede extenderse agregando métodos adicionales de consulta según sea necesario.
 * 
 * @author PC
 */
public interface IBeneficiarioDAO {
    
    /**
     * Guarda un nuevo beneficiario en la base de datos.
     * 
     * @param beneficiario El objeto Beneficiario que se desea guardar.
     */
    void guardarBeneficiario(Beneficiario beneficiario);
    
    /**
     * Actualiza la información de un beneficiario existente en la base de datos.
     * 
     * @param beneficiario El objeto Beneficiario con los datos actualizados.
     */
    void actualizarBeneficiario(Beneficiario beneficiario);
    
    /**
     * Elimina un beneficiario de la base de datos.
     * 
     * @param beneficiario El objeto Beneficiario que se desea eliminar.
     */
    void eliminarBeneficiario(Beneficiario beneficiario);
    
    /**
     * Busca y retorna un beneficiario por su clave de contrato.
     * 
     * @param claveContrato La clave de contrato del beneficiario que se desea buscar.
     * @return El objeto Beneficiario encontrado, o null si no existe.
     */
    Beneficiario buscarBeneficiarioPorClaveContrato(String claveContrato);
    
    /**
     * Retorna una lista con todos los beneficiarios almacenados en la base de datos.
     * 
     * @return Lista de objetos Beneficiario.
     */
    List<Beneficiario> obtenerTodosLosBeneficiarios(); 
}
