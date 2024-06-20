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
     */
    @Override
    public void guardarBeneficiario(Beneficiario beneficiario) {
        EntityManager entityManager = null;
    EntityTransaction transaction = null;

    try {
        entityManager = ConexionBD.getEntityManager();
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
        e.printStackTrace(); 
    } finally {
        if (entityManager != null) {
            entityManager.close();
        }
    }
    }

    /**
     * Actualiza la información de un beneficiario existente en la base de datos.
     * 
     * @param beneficiario El objeto Beneficiario con los datos actualizados.
     */
    @Override
    public void actualizarBeneficiario(Beneficiario beneficiario) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

    try {
        entityManager = ConexionBD.getEntityManager();
        transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(beneficiario);

        transaction.commit();
    } catch (Exception e) {
        if (transaction != null && transaction.isActive()) {
            transaction.rollback();
        }
        e.printStackTrace();
    } finally {
        if (entityManager != null) {
            entityManager.close();
        }
    }
    }

    /**
     * Elimina un beneficiario de la base de datos.
     * 
     * @param beneficiario El objeto Beneficiario que se desea eliminar.
     */
    @Override
    public void eliminarBeneficiario(Beneficiario beneficiario) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = ConexionBD.getEntityManager();
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
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    /**
     * Busca y retorna un beneficiario por su clave de contrato.
     * 
     * @param claveContrato La clave de contrato del beneficiario que se desea buscar.
     * @return El objeto Beneficiario encontrado, o null si no existe.
     * @throws excepcion.ExcepcionDAO
     */
    @Override
    public Beneficiario buscarBeneficiarioPorClaveContrato(String claveContrato){
        Beneficiario beneficiario = null;
    EntityManager entityManager = null;

    try {
        entityManager = ConexionBD.getEntityManager();
        TypedQuery<Beneficiario> query = entityManager.createQuery(
            "SELECT b FROM Beneficiario b WHERE b.claveContrato = :clave", Beneficiario.class);
        query.setParameter("clave", claveContrato);
        beneficiario = query.getSingleResult();
    } catch (NoResultException e) {
        System.out.println(e.getMessage());
    } catch (NonUniqueResultException e) {
        e.printStackTrace();
    } finally {
        if (entityManager != null) {
            entityManager.close();
        }
    }
    return beneficiario;
    }

    /**
     * Retorna una lista con todos los beneficiarios almacenados en la base de datos.
     * 
     * @return Lista de objetos Beneficiario.
     */
    @Override
    public List<Beneficiario> obtenerTodosLosBeneficiarios() {
        List<Beneficiario> beneficiarios = new ArrayList<>();
    EntityManager entityManager = null;
    EntityTransaction transaction = null;

    try {
        entityManager = ConexionBD.getEntityManager();
        transaction = entityManager.getTransaction();
        transaction.begin();

        TypedQuery<Beneficiario> query = entityManager.createQuery("SELECT b FROM Beneficiario b", Beneficiario.class);
        beneficiarios = query.getResultList();

        transaction.commit();
    } catch (Exception e) {
        if (transaction != null && transaction.isActive()) {
            transaction.rollback();
        }
        e.printStackTrace();
    } finally {
        if (entityManager != null) {
            entityManager.close();
        }
    }

    return beneficiarios;
    }
    
    
    
    /**
     * Cierra la conexión del EntityManager y el EntityManagerFactory.
     * Es importante llamar a este método al finalizar las operaciones con la instancia de BeneficiarioDAO.
     */
    public void cerrarConexion() {
        entityManager.close();
        ConexionBD.close();
    }
}
