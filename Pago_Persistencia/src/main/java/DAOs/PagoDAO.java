/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import conexion.ConexionBD;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import entidades.Pago;

/**
 * Implementación de la interfaz IPagoDAO que utiliza JPA para interactuar con la entidad Pago en la base de datos.
 * Proporciona métodos para realizar operaciones CRUD y consultas específicas sobre pagos.
 * 
 * La clase gestiona la conexión a la base de datos a través de JPAUtil.
 * 
 * @author PC
 */
public class PagoDAO implements IPagoDAO {

    private EntityManager entityManager;

    /**
     * Constructor que inicializa el EntityManager utilizando JPAUtil.
     */
    public PagoDAO() {
        this.entityManager = ConexionBD.getEntityManager();
    }

    /**
     * Guarda un nuevo pago en la base de datos.
     * 
     * @param pago El objeto Pago que se desea guardar.
     */
    @Override
    public void guardarPago(Pago pago) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(pago);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    /**
     * Actualiza la información de un pago existente en la base de datos.
     * 
     * @param pago El objeto Pago con los datos actualizados.
     */
    @Override
    public void actualizarPago(Pago pago) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(pago);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    /**
     * Elimina un pago de la base de datos.
     * 
     * @param pago El objeto Pago que se desea eliminar.
     */
    @Override
    public void eliminarPago(Pago pago) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.contains(pago) ? pago : entityManager.merge(pago));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    /**
     * Busca y retorna un pago por su ID.
     * 
     * @param id El ID del pago que se desea buscar.
     * @return El objeto Pago encontrado, o null si no existe.
     */
    @Override
    public Pago buscarPagoPorId(Long id) {
        return entityManager.find(Pago.class, id);
    }

    /**
     * Retorna una lista con todos los pagos almacenados en la base de datos.
     * 
     * @return Lista de objetos Pago.
     */
    @Override
    public List<Pago> obtenerTodosLosPagos() {
        TypedQuery<Pago> query = entityManager.createQuery("SELECT p FROM Pago p", Pago.class);
        return query.getResultList();
    }
    
    /**
     * Retorna una lista con todos los pagos asociados a un beneficiario específico.
     * 
     * @param claveContrato
     * @return
     */
    @Override
    public List<Pago> obtenerPagosPorBeneficiario(String claveContrato) {
        TypedQuery<Pago> query = entityManager.createQuery(
                "SELECT p FROM Pago p WHERE p.beneficiario.claveContrato = :claveContrato", Pago.class);
        query.setParameter("claveContrato", claveContrato);
        return query.getResultList();
    }
    
    /**
     * Cierra la conexión del EntityManager y el EntityManagerFactory.
     * Es importante llamar a este método al finalizar las operaciones con la instancia de PagoDAO.
     */
    public void cerrarConexion() {
        entityManager.close();
        ConexionBD.close();
    }
}
