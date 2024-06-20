/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.pago_persistencia;

import entidades.Tipos;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


/**
 *
 * @author diana
 */
public class Pago_Persistencia {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConexionBD");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            // Inicia la transacción
            tx.begin();

            // Crea un nuevo objeto Tipos para Viáticos
            Tipos viaticos = new Tipos("Viáticos", 7);

            // Persiste el objeto en la base de datos
            em.persist(viaticos);

            // Confirma la transacción
            tx.commit();

            System.out.println("Tipo de pago 'Viáticos' persistido correctamente.");
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            // Cierra el EntityManager
            em.close();
            emf.close();
        }
    }
}
