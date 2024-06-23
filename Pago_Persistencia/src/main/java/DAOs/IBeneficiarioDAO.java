/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import entidades.Beneficiario;
import excepcion.ExcepcionDAO;
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
     * @throws excepcion.ExcepcionDAO
     */
    void guardarBeneficiario(Beneficiario beneficiario)throws ExcepcionDAO;
    
    /**
     * Actualiza la información de un beneficiario existente en la base de datos.
     * 
     * @param beneficiario El objeto Beneficiario con los datos actualizados.
     * @throws excepcion.ExcepcionDAO
     */
    void actualizarBeneficiario(Beneficiario beneficiario)throws ExcepcionDAO;
    
    /**
     * Elimina un beneficiario de la base de datos.
     * 
     * @param beneficiario El objeto Beneficiario que se desea eliminar.
     * @throws excepcion.ExcepcionDAO
     */
    void eliminarBeneficiario(Beneficiario beneficiario)throws ExcepcionDAO;
    
    /**
     * Busca y retorna un beneficiario por su clave de contrato.
     * 
     * @param claveContrato La clave de contrato del beneficiario que se desea buscar.
     * @return El objeto Beneficiario encontrado, o null si no existe.
     * @throws excepcion.ExcepcionDAO
     */
    Beneficiario buscarBeneficiarioPorClaveContrato(String claveContrato)throws ExcepcionDAO;
    
    /**
     * Retorna una lista con todos los beneficiarios almacenados en la base de datos.
     * 
     * @return Lista de objetos Beneficiario.
     * @throws excepcion.ExcepcionDAO
     */
    List<Beneficiario> obtenerTodosLosBeneficiarios()throws ExcepcionDAO; 
    
    /**
     * Busca y retorna una cuenta bancaria por su ID.
     * 
     * @param id El ID de la cuenta bancaria que se desea buscar.
     * @return La cuenta bancaria encontrada, o null si no existe.
     * @throws excepcion.ExcepcionDAO
     */
    public Beneficiario buscarBeneficiarioPorId(Long id)throws ExcepcionDAO;
    
    /**
     * Logea al beneficiario
     * @param beneficiario Beneficiario a logear
     * @return 
     * @throws ExcepcionDAO 
     */
    public Beneficiario login(Beneficiario beneficiario)throws ExcepcionDAO;
}
