/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import entidades.Estatus;
import conexion.ConexionBD;

/**
 * Implementación concreta de DAO para la entidad Estatus.
 * Proporciona métodos para realizar operaciones CRUD y consultas específicas sobre estatus en la base de datos.
 * Utiliza JPA para interactuar con la base de datos.
 * 
 * Esta clase maneja la conexión a la base de datos a través de JPAUtil.
 * 
 * @author PC
 */
public class EstatusDAO implements IEstatusDAO{

    private EntityManager entityManager;

    /**
     * Constructor que inicializa el EntityManager utilizando JPAUtil.
     */
    public EstatusDAO() {
        this.entityManager = ConexionBD.getEntityManager();
    }

    /**
     * Guarda un nuevo estado en la base de datos.
     * 
     * @param estatus El objeto Estatus que se desea guardar.
     */
    @Override
    public void guardarEstatus(Estatus estatus) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(estatus);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    /**
     * Actualiza la información de un estado existente en la base de datos.
     * 
     * @param estatus El objeto Estatus con los datos actualizados.
     */
    @Override
    public void actualizarEstatus(Estatus estatus) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(estatus);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    /**
     * Elimina un estado de la base de datos.
     * 
     * @param estatus El objeto Estatus que se desea eliminar.
     */
    @Override
    public void eliminarEstatus(Estatus estatus) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.contains(estatus) ? estatus : entityManager.merge(estatus));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    /**
     * Busca y retorna un estado por su ID.
     * 
     * @param id El ID del estado que se desea buscar.
     * @return El objeto Estatus encontrado, o null si no existe.
     */
    @Override
    public Estatus buscarEstatusPorId(Long id) {
        return entityManager.find(Estatus.class, id);
    }

    /**
     * Retorna una lista con todos los estados almacenados en la base de datos.
     * 
     * @return Lista de objetos Estatus.
     */
    @Override
    public List<Estatus> obtenerTodosLosEstatus() {
        TypedQuery<Estatus> query = entityManager.createQuery("SELECT e FROM Estatus e", Estatus.class);
        return query.getResultList();
    }
    
    // Puedes agregar métodos adicionales de consulta aquí según tus necesidades
    
    /**
     * Cierra la conexión del EntityManager y el EntityManagerFactory.
     * Es importante llamar a este método al finalizar las operaciones con la instancia de EstatusDAO.
     */
    public void cerrarConexion() {
        entityManager.close();
        ConexionBD.close();
    }
}
