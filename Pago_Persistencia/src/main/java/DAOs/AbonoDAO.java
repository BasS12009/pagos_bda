/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import conexion.ConexionBD;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import entidades.Abono;
import javax.persistence.EntityTransaction;

/**
 * Implementación concreta de DAO para la entidad Abono.
 * Proporciona métodos para realizar operaciones CRUD y consultas específicas sobre abonos en la base de datos.
 * Utiliza JPA para interactuar con la base de datos.
 * 
 * Esta clase maneja la conexión a la base de datos a través de JPAUtil.
 * 
 * @author PC
 */
public class AbonoDAO implements IAbonoDAO{

    private EntityManager entityManager;

    /**
     * Constructor que inicializa el EntityManager utilizando JPAUtil.
     */
    public AbonoDAO() {
        this.entityManager = ConexionBD.getEntityManager();
    }

    /**
     * Guarda un nuevo abono en la base de datos.
     * 
     * @param abono El objeto Abono que se desea guardar.
     */
    @Override
    public void guardarAbono(Abono abono) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = ConexionBD.getEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();

            entityManager.persist(abono);

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
     * Actualiza la información de un abono existente en la base de datos.
     * 
     * @param abono El objeto Abono con los datos actualizados.
     */
    @Override
    public void actualizarAbono(Abono abono) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = ConexionBD.getEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();

            entityManager.merge(abono);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace(); // Manejo básico de excepciones, ajusta según tu caso
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    /**
     * Elimina un abono de la base de datos.
     * 
     * @param abono El objeto Abono que se desea eliminar.
     */
    @Override
    public void eliminarAbono(Abono abono) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.contains(abono) ? abono : entityManager.merge(abono));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    /**
     * Busca y retorna un abono por su identificador único.
     * 
     * @param id El identificador único del abono que se desea buscar.
     * @return El objeto Abono encontrado, o null si no existe.
     */
    @Override
    public Abono buscarAbonoPorId(Long id) {
        return entityManager.find(Abono.class, id);
    }

    /**
     * Retorna una lista con todos los abonos almacenados en la base de datos.
     * 
     * @return Lista de objetos Abono.
     */
    @Override
    public List<Abono> obtenerTodosLosAbonos() {
        TypedQuery<Abono> query = entityManager.createQuery("SELECT a FROM Abono a", Abono.class);
        return query.getResultList();
    }
    
    /**
     *  Retorna una lista con todos los abonos asociados a un beneficiario específico.
     * 
     * @param claveContrato
     * @return
     */
    @Override
    public List<Abono> obtenerAbonosPorBeneficiario(Long beneficiarioId) {
        TypedQuery<Abono> query = entityManager.createQuery(
                "SELECT a FROM Abono a WHERE a.beneficiario.id = :beneficiarioId", Abono.class);
        query.setParameter("beneficiarioId", beneficiarioId);
        return query.getResultList();
    }
    
    /**
     * Cierra la conexión del EntityManager y el EntityManagerFactory.
     * Es importante llamar a este método al finalizar las operaciones con la instancia de AbonoDAO.
     */
    public void cerrarConexion() {
        entityManager.close();
        ConexionBD.close();
    }

}
