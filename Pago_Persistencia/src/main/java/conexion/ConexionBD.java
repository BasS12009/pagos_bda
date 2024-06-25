/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Clase que gestiona la conexión a la base de datos utilizando JPA (Java Persistence API).
 * Proporciona métodos estáticos para obtener y cerrar el EntityManagerFactory.
 * 
 * @author PC
 */
public class ConexionBD {
    
    private static final String PERSISTENCE_UNIT_NAME = "ConexionBD"; // Nombre de la unidad de persistencia en persistence.xml
    private static EntityManagerFactory entityManagerFactory;

    /**
     * Obtiene una instancia de EntityManager utilizando el EntityManagerFactory.
     * Si entityManagerFactory aún no está inicializado, crea una nueva instancia.
     * 
     * @return EntityManager configurado para la unidad de persistencia "ConexionBD".
     */
    public static EntityManager getEntityManager() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        return entityManagerFactory.createEntityManager();
    }

    /**
     * Cierra el EntityManagerFactory si está abierto.
     */
    public static void close() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}
