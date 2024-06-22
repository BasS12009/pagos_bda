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
import excepcion.ExcepcionDAO;
import javax.persistence.EntityTransaction;

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
     * @throws ExcepcionDAO Si ocurre un error durante la operación de persistencia.
     */
    @Override
    public void guardarPago(Pago pago) throws ExcepcionDAO {
        EntityTransaction transaction = null;
        
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(pago);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new ExcepcionDAO("Error al guardar el pago", e);
        }
    }

    /**
     * Actualiza la información de un pago existente en la base de datos.
     * 
     * @param pago El objeto Pago con los datos actualizados.
     * @throws ExcepcionDAO Si ocurre un error durante la operación de merge.
     */
    @Override
    public void actualizarPago(Pago pago) throws ExcepcionDAO {
        EntityTransaction transaction = null;
        
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(pago);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new ExcepcionDAO("Error al actualizar el pago", e);
        }
    }

    /**
     * Elimina un pago de la base de datos.
     * 
     * @param pago El objeto Pago que se desea eliminar.
     * @throws ExcepcionDAO Si ocurre un error durante la operación de eliminación.
     */
    @Override
    public void eliminarPago(Pago pago) throws ExcepcionDAO {
        EntityTransaction transaction = null;
        
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            
            if (!entityManager.contains(pago)) {
                pago = entityManager.merge(pago);
            }
            
            entityManager.remove(pago);
            
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new ExcepcionDAO("Error al eliminar el pago", e);
        }
    }

    /**
     * Busca y retorna un pago por su ID.
     * 
     * @param id El ID del pago que se desea buscar.
     * @return El objeto Pago encontrado, o null si no existe.
     * @throws ExcepcionDAO Si ocurre un error durante la consulta.
     */
    @Override
    public Pago buscarPagoPorId(Long id) throws ExcepcionDAO {
        try {
            return entityManager.find(Pago.class, id);
        } catch (Exception e) {
            throw new ExcepcionDAO("Error al buscar el pago por ID", e);
        }
    }

    /**
     * Retorna una lista con todos los pagos almacenados en la base de datos.
     * 
     * @return Lista de objetos Pago.
     * @throws ExcepcionDAO Si ocurre un error durante la consulta.
     */
    @Override
    public List<Pago> obtenerTodosLosPagos() throws ExcepcionDAO {
        EntityTransaction transaction = null;
        
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            
            TypedQuery<Pago> query = entityManager.createQuery("SELECT p FROM Pago p", Pago.class);
            List<Pago> pagos = query.getResultList();
            
            transaction.commit();
            return pagos;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new ExcepcionDAO("Error al obtener todos los pagos", e);
        }
    }

    /**
     * Retorna una lista con todos los pagos asociados a un beneficiario específico.
     * 
     * @param idBeneficiario El ID del beneficiario.
     * @return Lista de pagos asociados al beneficiario.
     * @throws ExcepcionDAO Si ocurre un error durante la consulta.
     */
    @Override
    public List<Pago> obtenerPagosPorBeneficiario(Long idBeneficiario) throws ExcepcionDAO {
        try {
            TypedQuery<Pago> query = entityManager.createQuery(
                    "SELECT p FROM Pago p WHERE p.beneficiario.id = :idBeneficiario", Pago.class);
            query.setParameter("idBeneficiario", idBeneficiario);
            return query.getResultList();
        } catch (Exception e) {
            throw new ExcepcionDAO("Error al obtener los pagos por beneficiario", e);
        }
    }

    /**
     * Retorna una lista con todos los pagos asociados a una clave de contrato específica.
     *
     * @param claveContrato La clave de contrato cuyos pagos se desean obtener.
     * @return Lista de objetos Pago asociados a la clave de contrato.
     * @throws ExcepcionDAO Si ocurre un error durante la consulta.
     */
    @Override
    public List<Pago> obtenerPagosPorClaveContrato(String claveContrato) throws ExcepcionDAO {
        try {
            TypedQuery<Pago> query = entityManager.createQuery(
                    "SELECT p FROM Pago p WHERE p.claveContrato = :claveContrato", Pago.class);
            query.setParameter("claveContrato", claveContrato);
            return query.getResultList();
        } catch (Exception e) {
            throw new ExcepcionDAO("Error al obtener los pagos por clave de contrato", e);
        }
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
