/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import entidades.Beneficiario;
import conexion.ConexionBD;
import excepcion.ExcepcionDAO;
import java.util.ArrayList;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

/**
 * Implementación concreta de DAO para la entidad Beneficiario.
 * Proporciona métodos para realizar operaciones CRUD y consultas específicas sobre beneficiarios en la base de datos.
 * Utiliza JPA para interactuar con la base de datos.
 * 
 * Esta clase maneja la conexión a la base de datos a través de JPAUtil.
 * 
 * @author PC
 */
public class BeneficiarioDAO implements IBeneficiarioDAO{

    private EntityManager entityManager;

    /**
     * Constructor que inicializa el EntityManager utilizando JPAUtil.
     */
    public BeneficiarioDAO() {
        this.entityManager = ConexionBD.getEntityManager();
    }
    

    /**
     * Guarda un nuevo beneficiario en la base de datos.
     * 
     * @param beneficiario El objeto Beneficiario que se desea guardar.
     * @throws ExcepcionDAO Si ocurre un error durante la operación de persistencia.
     */
    @Override
    public void guardarBeneficiario(Beneficiario beneficiario) throws ExcepcionDAO {
        EntityTransaction transaction = null;
        
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            
            Beneficiario existingBeneficiario = buscarBeneficiarioPorClaveContrato(beneficiario.getClaveContrato());
            if (existingBeneficiario == null) {
                entityManager.persist(beneficiario);
            } else {
                System.out.println("Ya existe un beneficiario con la misma clave de contrato: " + beneficiario.getClaveContrato());
            }
            
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new ExcepcionDAO("Error al guardar el beneficiario", e);
        }
    }
    
    
    
    
    
    /**
     * Actualiza la información de un beneficiario existente en la base de datos.
     * 
     * @param beneficiario El objeto Beneficiario con los datos actualizados.
     * @throws ExcepcionDAO Si ocurre un error durante la operación de merge.
     */
    @Override
    public void actualizarBeneficiario(Beneficiario beneficiario) throws ExcepcionDAO {
        EntityTransaction transaction = null;
        
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(beneficiario);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new ExcepcionDAO("Error al actualizar el beneficiario", e);
        }
    }

    /**
     * Elimina un beneficiario de la base de datos.
     * 
     * @param beneficiario El objeto Beneficiario que se desea eliminar.
     * @throws ExcepcionDAO Si ocurre un error durante la operación de eliminación.
     */
    @Override
    public void eliminarBeneficiario(Beneficiario beneficiario) throws ExcepcionDAO {
        EntityTransaction transaction = null;
        
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            
            if (!entityManager.contains(beneficiario)) {
                beneficiario = entityManager.merge(beneficiario);
            }
            
            entityManager.remove(beneficiario);
            
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new ExcepcionDAO("Error al eliminar el beneficiario", e);
        }
    }

    /**
     * Busca y retorna un beneficiario por su clave de contrato.
     * 
     * @param claveContrato La clave de contrato del beneficiario que se desea buscar.
     * @return El objeto Beneficiario encontrado, o null si no existe.
     * @throws ExcepcionDAO Si ocurre un error durante la consulta.
     */
    @Override
    public Beneficiario buscarBeneficiarioPorClaveContrato(String claveContrato) throws ExcepcionDAO {
        try {
            TypedQuery<Beneficiario> query = entityManager.createQuery(
                "SELECT b FROM Beneficiario b WHERE b.claveContrato = :clave", Beneficiario.class);
            query.setParameter("clave", claveContrato);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new ExcepcionDAO("Error al buscar el beneficiario por clave de contrato", e);
        }
    }
    
    /**
     * Busca y retorna un beneficiario por su ID.
     *
     * @param id El ID del beneficiario que se desea buscar.
     * @return El objeto Beneficiario encontrado, o null si no existe.
     * @throws ExcepcionDAO Si ocurre un error durante la consulta.
     */
    @Override
    public Beneficiario buscarBeneficiarioPorId(Long id) throws ExcepcionDAO {
        try {
            return entityManager.find(Beneficiario.class, id);
        } catch (Exception e) {
            throw new ExcepcionDAO("Error al buscar el beneficiario por ID", e);
        }
    }

    /**
     * Retorna una lista con todos los beneficiarios almacenados en la base de datos.
     * 
     * @return Lista de objetos Beneficiario.
     * @throws ExcepcionDAO Si ocurre un error durante la consulta.
     */
    @Override
    public List<Beneficiario> obtenerTodosLosBeneficiarios() throws ExcepcionDAO {
        EntityTransaction transaction = null;
        
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            
            TypedQuery<Beneficiario> query = entityManager.createQuery("SELECT b FROM Beneficiario b", Beneficiario.class);
            List<Beneficiario> beneficiarios = query.getResultList();
            
            transaction.commit();
            return beneficiarios;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new ExcepcionDAO("Error al obtener todos los beneficiarios", e);
        }
    }
    
    /**
     * Cierra la conexión del EntityManager y el EntityManagerFactory.
     * Es importante llamar a este método al finalizar las operaciones con la instancia de BeneficiarioDAO.
     */
    public void cerrarConexion() {
        entityManager.close();
        ConexionBD.close();
    }
    /**
     * Valida que exista un registro con la clabe y contraseña ingresadas
     * @return Boolean
     */
    
    public Beneficiario login(Beneficiario beneficiario) throws ExcepcionDAO{
        EntityTransaction transaction = null;
        
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            
            TypedQuery<Beneficiario> query = entityManager.createQuery("SELECT b.claveContrato, b.contraseña FROM Beneficiario b WHERE b.claveContrato = :cContrato AND b.contraseña = :contraseña", Beneficiario.class);
            query.setParameter("cContrato", beneficiario.getClaveContrato());
            query.setParameter("contraseña", beneficiario.getContraseña());
            List<Beneficiario> beneficiarios = query.getResultList();
            
            return beneficiario;
        } catch(Exception e){
                if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new ExcepcionDAO("Error al iniciar sesión  8(", e);
        }
    

    }
}
