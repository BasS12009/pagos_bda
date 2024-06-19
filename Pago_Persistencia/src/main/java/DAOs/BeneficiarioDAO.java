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

/**
 * Implementación concreta de DAO para la entidad Beneficiario.
 * Proporciona métodos para realizar operaciones CRUD y consultas específicas sobre beneficiarios en la base de datos.
 * Utiliza JPA para interactuar con la base de datos.
 * 
 * Esta clase maneja la conexión a la base de datos a través de JPAUtil.
 * 
 * @author PC
 */
public class BeneficiarioDAO {

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
    public void guardarBeneficiario(Beneficiario beneficiario) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(beneficiario);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    /**
     * Actualiza la información de un beneficiario existente en la base de datos.
     * 
     * @param beneficiario El objeto Beneficiario con los datos actualizados.
     */
    public void actualizarBeneficiario(Beneficiario beneficiario) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(beneficiario);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    /**
     * Elimina un beneficiario de la base de datos.
     * 
     * @param beneficiario El objeto Beneficiario que se desea eliminar.
     */
    public void eliminarBeneficiario(Beneficiario beneficiario) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.contains(beneficiario) ? beneficiario : entityManager.merge(beneficiario));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    /**
     * Busca y retorna un beneficiario por su clave de contrato.
     * 
     * @param claveContrato La clave de contrato del beneficiario que se desea buscar.
     * @return El objeto Beneficiario encontrado, o null si no existe.
     */
    public Beneficiario buscarBeneficiarioPorClaveContrato(String claveContrato) {
        TypedQuery<Beneficiario> query = entityManager.createQuery("SELECT b FROM Beneficiario b WHERE b.claveContrato = :clave", Beneficiario.class);
        query.setParameter("clave", claveContrato);
        return query.getSingleResult();
    }

    /**
     * Retorna una lista con todos los beneficiarios almacenados en la base de datos.
     * 
     * @return Lista de objetos Beneficiario.
     */
    public List<Beneficiario> obtenerTodosLosBeneficiarios() {
        TypedQuery<Beneficiario> query = entityManager.createQuery("SELECT b FROM Beneficiario b", Beneficiario.class);
        return query.getResultList();
    }
    
    // Puedes agregar métodos adicionales de consulta aquí según tus necesidades
    
    /**
     * Cierra la conexión del EntityManager y el EntityManagerFactory.
     * Es importante llamar a este método al finalizar las operaciones con la instancia de BeneficiarioDAO.
     */
    public void cerrarConexion() {
        entityManager.close();
        ConexionBD.close();
    }
}
