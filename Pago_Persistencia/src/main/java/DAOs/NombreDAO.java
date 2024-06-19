/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import conexion.ConexionBD;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import entidades.Nombre;

/**
 * Implementación concreta de DAO para la entidad Nombre.
 * Proporciona métodos para realizar operaciones CRUD y consultas específicas sobre nombres en la base de datos.
 * Utiliza JPA para interactuar con la base de datos.
 * 
 * @author PC
 */
public class NombreDAO {

    private EntityManager entityManager;

    /**
     * Constructor que inicializa el EntityManager utilizando JPAUtil.
     */
    public NombreDAO() {
        this.entityManager = ConexionBD.getEntityManager();
    }

    /**
     * Guarda un nuevo nombre en la base de datos.
     * 
     * @param nombre El objeto Nombre que se desea guardar.
     */
    public void guardarNombre(Nombre nombre) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(nombre);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    /**
     * Actualiza la información de un nombre existente en la base de datos.
     * 
     * @param nombre El objeto Nombre con los datos actualizados.
     */
    public void actualizarNombre(Nombre nombre) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(nombre);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    /**
     * Elimina un nombre de la base de datos.
     * 
     * @param nombre El objeto Nombre que se desea eliminar.
     */
    public void eliminarNombre(Nombre nombre) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.contains(nombre) ? nombre : entityManager.merge(nombre));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    /**
     * Busca y retorna un nombre por su ID.
     * 
     * @param id El ID del nombre que se desea buscar.
     * @return El objeto Nombre encontrado, o null si no existe.
     */
    public Nombre buscarNombrePorId(Long id) {
        return entityManager.find(Nombre.class, id);
    }

    /**
     * Retorna una lista con todos los nombres almacenados en la base de datos.
     * 
     * @return Lista de objetos Nombre.
     */
    public List<Nombre> obtenerTodosLosNombres() {
        TypedQuery<Nombre> query = entityManager.createQuery("SELECT n FROM Nombre n", Nombre.class);
        return query.getResultList();
    }
    
    // Puedes agregar métodos adicionales de consulta aquí según tus necesidades
    
    /**
     * Cierra la conexión del EntityManager y el EntityManagerFactory.
     * Es importante llamar a este método al finalizar las operaciones con la instancia de NombreDAO.
     */
    public void cerrarConexion() {
        entityManager.close();
        ConexionBD.close();
    }
}
