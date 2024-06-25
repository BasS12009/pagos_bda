/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.pago_persistencia;

import java.math.BigDecimal;
import entidades.Beneficiario;
import entidades.Estatus;
import entidades.Nombre;
import entidades.Pago;
import entidades.Tipos;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


/**
 *  Clase Main de la capa Persistencia
 * @author diana
 */
public class Pago_Persistencia {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConexionBD");

    public static void main(String[] args) {
        agregarPagos();
        cerrarEntityManagerFactory();
    }

    private static void agregarBeneficiario() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Nombre nombre = new Nombre("Itson", "Beneficiario", "1");
            Beneficiario beneficiario = new Beneficiario("123", 1000000.0, nombre, "itson", "123");

            Nombre nombre2 = new Nombre("Juan", "Pérez", "Meza");
            Beneficiario beneficiario2 = new Beneficiario("456", 2000000.0, nombre2, "itson2", "456");
            
            em.persist(beneficiario);
            em.persist(beneficiario2);

            tx.commit();
            System.out.println("Beneficiarios agregado correctamente.");
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    
    private static void persistirTipos() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            // Inicia la transacción para tipos
            tx.begin();

            Tipos viaticos = new Tipos("Viáticos", 7);
            em.persist(viaticos);

            Tipos reembolso = new Tipos("Reembolso", 1);
            em.persist(reembolso);

            Tipos proveedor = new Tipos("Proveedor", 5);
            em.persist(proveedor);

            // Confirma la transacción para tipos
            tx.commit();
            System.out.println("Tipos de pago persistidos correctamente.");
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    private static void persistirEstatus() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            // Inicia la transacción para estatus
            tx.begin();

            // Insertar varios estados de ejemplo
            insertarEstatus(em, "Creado");
            insertarEstatus(em, "Pagado");
            insertarEstatus(em, "Reembolso");
            insertarEstatus(em, "Aprobado");
            insertarEstatus(em, "Rechazado");
            insertarEstatus(em, "Modificado");

            // Confirma la transacción para estatus
            tx.commit();
            System.out.println("Estados de pago persistidos correctamente.");
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    
    private static void insertarEstatus(EntityManager em, String nombre) {
        Estatus estatus = new Estatus(nombre);
        em.persist(estatus);
    }
    
    private static void agregarPagos() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            
            //Beneficiarios
            Nombre nombre = new Nombre("Itson", "Beneficiario", "1");
            Beneficiario beneficiario = new Beneficiario("123", 1000000.0, nombre, "itson", "123");

            Nombre nombre2 = new Nombre("Juan", "Pérez", "Meza");
            Beneficiario beneficiario2 = new Beneficiario("456", 2000000.0, nombre2, "itson2", "456");
            
            em.persist(beneficiario);
            em.persist(beneficiario2);

            //Tipos
            Tipos viaticos = new Tipos("Viáticos", 7);
            em.persist(viaticos);

            Tipos reembolso = new Tipos("Reembolso", 1);
            em.persist(reembolso);

            Tipos proveedor = new Tipos("Proveedor", 5);
            em.persist(proveedor);

            //Estatus
            insertarEstatus(em, "Creado");
            insertarEstatus(em, "Pagado");
            insertarEstatus(em, "Reembolso");
            insertarEstatus(em, "Aprobado");
            insertarEstatus(em, "Rechazado");
            insertarEstatus(em, "Modificado");

            //Pagos
            

            BigDecimal monto = new BigDecimal(1);
            LocalDateTime fechaHora = LocalDateTime.now();
            String comprobante = "Comprobante";
            Estatus creado = null;


            Pago pago = new Pago(monto, fechaHora, comprobante, reembolso, beneficiario);
            em.persist(pago);

            tx.commit();
            System.out.println("Beneficiarios agregado correctamente.");
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }    

    private static void cerrarEntityManagerFactory() {
        emf.close();
    }
}
   

