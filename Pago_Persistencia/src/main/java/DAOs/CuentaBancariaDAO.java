/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import conexion.ConexionBD;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import entidades.CuentaBancaria; 

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
     */
    @Override
    public void guardarCuentaBancaria(CuentaBancaria cuenta) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(cuenta);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    /**
     * Actualiza la información de una cuenta bancaria existente en la base de datos.
     * 
     * @param cuenta La cuenta bancaria con los datos actualizados.
     */
    @Override
    public void actualizarCuentaBancaria(CuentaBancaria cuenta) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(cuenta);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    /**
     * Elimina una cuenta bancaria de la base de datos.
     * 
     * @param cuenta La cuenta bancaria que se desea eliminar.
     */
    @Override
    public void eliminarCuentaBancaria(CuentaBancaria cuenta) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.contains(cuenta) ? cuenta : entityManager.merge(cuenta));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    /**
     * Busca y retorna una cuenta bancaria por su número de cuenta.
     * 
     * @param numeroCuenta El número de cuenta de la cuenta bancaria que se desea buscar.
     * @return La cuenta bancaria encontrada, o null si no existe.
     */
    @Override
    public CuentaBancaria buscarCuentaBancariaPorNumero(String numeroCuenta) {
        TypedQuery<CuentaBancaria> query = entityManager.createQuery("SELECT c FROM CuentaBancaria c WHERE c.numeroCuenta = :numero", CuentaBancaria.class);
        query.setParameter("numero", numeroCuenta);
        return query.getSingleResult();
    }

    /**
     * Retorna una lista con todas las cuentas bancarias almacenadas en la base de datos.
     * 
     * @return Lista de objetos CuentaBancaria.
     */
    @Override
    public List<CuentaBancaria> obtenerTodasLasCuentasBancarias() {
        TypedQuery<CuentaBancaria> query = entityManager.createQuery("SELECT c FROM CuentaBancaria c", CuentaBancaria.class);
        return query.getResultList();
    }
    
    /**
     * Retorna una lista con todas las cuentas bancarias asociadas a un beneficiario específico.
     * 
     * @param claveContrato
     * @return
     */
    @Override
    public List<CuentaBancaria> obtenerCuentasBancariasPorBeneficiario(String claveContrato) {
        TypedQuery<CuentaBancaria> query = entityManager.createQuery(
                "SELECT c FROM CuentaBancaria c WHERE c.beneficiario.claveContrato = :claveContrato", CuentaBancaria.class);
        query.setParameter("claveContrato", claveContrato);
        return query.getResultList();
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
