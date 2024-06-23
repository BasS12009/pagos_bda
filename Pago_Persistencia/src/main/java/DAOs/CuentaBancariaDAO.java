/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import conexion.ConexionBD;
import entidades.Beneficiario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import entidades.CuentaBancaria; 
import excepcion.ExcepcionDAO;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

/**
 * Implementación concreta de DAO para la entidad CuentaBancaria.
 * Proporciona métodos para realizar operaciones CRUD y consultas específicas sobre cuentas bancarias en la base de datos.
 * Utiliza JPA para interactuar con la base de datos.
 * 
 * Esta clase maneja la conexión a la base de datos a través de JPAUtil.
 * 
 * @author PC
 */
public class CuentaBancariaDAO implements ICuentaBancariaDAO{

    private EntityManager entityManager;

    /**
     * Constructor que inicializa el EntityManager utilizando JPAUtil.
     */
    public CuentaBancariaDAO() {
        this.entityManager = ConexionBD.getEntityManager();
    }

    /**
     * Guarda una nueva cuenta bancaria en la base de datos.
     * 
     * @param cuenta La cuenta bancaria que se desea guardar.
     * @throws ExcepcionDAO Si ocurre un error durante la operación de persistencia.
     */
    @Override
    public void guardarCuentaBancaria(CuentaBancaria cuenta) throws ExcepcionDAO {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(cuenta);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new ExcepcionDAO("Error al guardar la cuenta bancaria", e);
        }
    }

    /**
     * Actualiza la información de una cuenta bancaria existente en la base de datos.
     * 
     * @param cuenta La cuenta bancaria con los datos actualizados.
     * @throws ExcepcionDAO Si ocurre un error durante la operación de merge.
     */
    @Override
    public void actualizarCuentaBancaria(CuentaBancaria cuenta) throws ExcepcionDAO {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(cuenta);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new ExcepcionDAO("Error al actualizar la cuenta bancaria", e);
        }
    }

    /**
     * Elimina una cuenta bancaria de la base de datos.
     * 
     * @param cuenta La cuenta bancaria que se desea eliminar.
     * @throws ExcepcionDAO Si ocurre un error durante la operación de eliminación.
     */
    @Override
    public void eliminarCuentaBancaria(CuentaBancaria cuenta) throws ExcepcionDAO {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.contains(cuenta) ? cuenta : entityManager.merge(cuenta));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new ExcepcionDAO("Error al eliminar la cuenta bancaria", e);
        }
    }

    /**
     * Busca y retorna una cuenta bancaria por su número de cuenta.
     * 
     * @param numeroCuenta El número de cuenta de la cuenta bancaria que se desea buscar.
     * @return La cuenta bancaria encontrada, o null si no existe.
     * @throws ExcepcionDAO Si ocurre un error durante la consulta.
     */
    @Override
    public CuentaBancaria buscarCuentaBancariaPorNumero(String numeroCuenta) throws ExcepcionDAO {
        try {
            TypedQuery<CuentaBancaria> query = entityManager.createQuery("SELECT c FROM CuentaBancaria c WHERE c.numeroCuenta = :numero", CuentaBancaria.class);
            query.setParameter("numero", numeroCuenta);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new ExcepcionDAO("Error al buscar la cuenta bancaria por número de cuenta", e);
        }
    }

    /**
     * Retorna una lista con todas las cuentas bancarias almacenadas en la base de datos.
     * 
     * @return Lista de objetos CuentaBancaria.
     * @throws ExcepcionDAO Si ocurre un error durante la consulta.
     */
    @Override
    public List<CuentaBancaria> obtenerTodasLasCuentasBancarias() throws ExcepcionDAO {
        try {
            TypedQuery<CuentaBancaria> query = entityManager.createQuery("SELECT c FROM CuentaBancaria c", CuentaBancaria.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new ExcepcionDAO("Error al obtener todas las cuentas bancarias", e);
        }
    }
    
    /**
     * Retorna una lista con todas las cuentas bancarias asociadas a un beneficiario específico.
     * 
     * @param claveContrato Clave de contrato del beneficiario.
     * @return Lista de cuentas bancarias asociadas al beneficiario.
     * @throws ExcepcionDAO Si ocurre un error durante la consulta.
     */
    @Override
    public List<CuentaBancaria> obtenerCuentasBancariasPorBeneficiarioContrato(String claveContrato) throws ExcepcionDAO {
        try {
            TypedQuery<CuentaBancaria> query = entityManager.createQuery(
                    "SELECT c FROM CuentaBancaria c WHERE c.beneficiario.claveContrato = :claveContrato", CuentaBancaria.class);
            query.setParameter("claveContrato", claveContrato);
            return query.getResultList();
        } catch (Exception e) {
            throw new ExcepcionDAO("Error al obtener las cuentas bancarias por beneficiario", e);
        }
    }
    
    /**
        * Retorna una lista con todas las cuentas bancarias asociadas a un beneficiario por su ID.
        * 
        * @param idBeneficiario ID del beneficiario.
        * @return Lista de cuentas bancarias asociadas al beneficiario especificado.
        * @throws ExcepcionDAO Si ocurre un error durante la consulta.
        */
       @Override
       public List<CuentaBancaria> obtenerCuentasBancariasPorIdBeneficiario(long idBeneficiario) throws ExcepcionDAO {
           try {
               CriteriaBuilder cb = entityManager.getCriteriaBuilder();
               CriteriaQuery<CuentaBancaria> cq = cb.createQuery(CuentaBancaria.class);
               Root<CuentaBancaria> root = cq.from(CuentaBancaria.class);

               // Join con la entidad Beneficiario para filtrar por ID del beneficiario
               Join<CuentaBancaria, Beneficiario> beneficiarioJoin = root.join("beneficiario", JoinType.INNER);

               // Filtrar por ID del beneficiario
               cq.where(cb.equal(beneficiarioJoin.get("id"), idBeneficiario));

               TypedQuery<CuentaBancaria> query = entityManager.createQuery(cq);
               return query.getResultList();
           } catch (Exception e) {
               throw new ExcepcionDAO("Error al obtener las cuentas bancarias por ID de beneficiario", e);
           }
       }
    
    /**
     * Busca y retorna una cuenta bancaria por su ID.
     * 
     * @param id El ID de la cuenta bancaria que se desea buscar.
     * @return La cuenta bancaria encontrada, o null si no existe.
     * @throws ExcepcionDAO Si ocurre un error durante la consulta.
     */
    @Override
    public CuentaBancaria buscarCuentaBancariaPorId(Long id) throws ExcepcionDAO {
        try {
            return entityManager.find(CuentaBancaria.class, id);
        } catch (Exception e) {
            throw new ExcepcionDAO("Error al buscar la cuenta bancaria por ID", e);
        }
    }
    
    
    
    /**
     * Cierra la conexión del EntityManager y el EntityManagerFactory.
     * Es importante llamar a este método al finalizar las operaciones con la instancia de CuentaBancariaDAO.
     */
    public void cerrarConexion() {
        entityManager.close();
        ConexionBD.close();
    }
}
