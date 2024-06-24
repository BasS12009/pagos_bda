/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import conexion.ConexionBD;
import entidades.Estatus;
import entidades.Pago;
import entidades.PagosEstatus;
import excepcion.ExcepcionDAO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

/**
 *
 * @author PC Gamer
 */
public class PagosEstatusDAO implements IPagosEstatusDAO {
    private EntityManager entityManager;

    /**
     * Constructor que inicializa el EntityManager utilizando JPAUtil.
     */
    public PagosEstatusDAO() {
        this.entityManager = ConexionBD.getEntityManager();
    }

    /**
     * Obtiene una lista de PagosEstatus para una lista de pagos.
     * 
     * @param pagos La lista de pagos para la cual se desean obtener los estatus.
     * @return Lista de PagosEstatus.
     */
    @Override
    public List<PagosEstatus> obtenerPagosEstatusParaPagos(List<PagosEstatus> pagos) {
        List<PagosEstatus> pagosEstatus = new ArrayList<>();

        for (PagosEstatus pago : pagos) {
            PagosEstatus pagoEstatus = new PagosEstatus();
            pagoEstatus.setId(pago.getId());
            pagoEstatus.setPago(pago.getPago());
            pagoEstatus.setEstatus(obtenerEstatusParaPago(pago));
            pagoEstatus.setMensaje(obtenerMensajeParaPago(pago));
            pagoEstatus.setFechaHora(pago.getFechaHora());

            pagosEstatus.add(pagoEstatus);
        }

        return pagosEstatus;
    }

    /**
     * Obtiene el estatus para un pago dado.
     * 
     * @param pago El pago para el cual se desea obtener el estatus.
     * @return El estatus del pago.
     */
    @Override
    public Estatus obtenerEstatusParaPago(PagosEstatus pago) {
        // Aquí deberías implementar la lógica para obtener el estatus de un pago
        return pago.getEstatus();
    }

    /**
     * Obtiene el mensaje para un pago dado.
     * 
     * @param pago El pago para el cual se desea obtener el mensaje.
     * @return El mensaje del pago.
     */
    @Override
    public String obtenerMensajeParaPago(PagosEstatus pago) {
        // Aquí deberías implementar la lógica para obtener el mensaje de un pago
        return pago.getMensaje();
    }

    /**
     * Guarda un nuevo PagosEstatus en la base de datos.
     * 
     * @param pagosEstatus El objeto PagosEstatus que se desea guardar.
     * @throws excepcion.ExcepcionDAO
     */
    public void guardarPagosEstatus(PagosEstatus pagosEstatus) throws ExcepcionDAO {
        EntityTransaction transaction = null;
            try {
                transaction = entityManager.getTransaction();
                transaction.begin();

                entityManager.persist(pagosEstatus);
                entityManager.flush(); 

                transaction.commit();
            } catch (Exception e) {
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
                throw new ExcepcionDAO("Error al guardar el pago", e);
            }
    }


    /**
     * Actualiza la información de un PagosEstatus existente en la base de datos.
     * 
     * @param pagosEstatus El objeto PagosEstatus con los datos actualizados.
     */
    public void actualizarPagosEstatus(PagosEstatus pagosEstatus) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(pagosEstatus);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    /**
     * Elimina un PagosEstatus de la base de datos.
     * 
     * @param pagosEstatus El objeto PagosEstatus que se desea eliminar.
     */
    public void eliminarPagosEstatus(PagosEstatus pagosEstatus) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.contains(pagosEstatus) ? pagosEstatus : entityManager.merge(pagosEstatus));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    /**
     * Busca y retorna un PagosEstatus por su ID.
     * 
     * @param id El ID del PagosEstatus que se desea buscar.
     * @return El objeto PagosEstatus encontrado, o null si no existe.
     */
    public PagosEstatus buscarPagosEstatusPorId(Long id) {
        return entityManager.find(PagosEstatus.class, id);
    }

    /**
     * Retorna una lista con todos los PagosEstatus almacenados en la base de datos.
     * 
     * @return Lista de objetos PagosEstatus.
     */
    public List<PagosEstatus> obtenerTodosLosPagosEstatus() {
        TypedQuery<PagosEstatus> query = entityManager.createQuery("SELECT p FROM PagosEstatus p", PagosEstatus.class);
        return query.getResultList();
    }

    /**
     * Retorna una lista de PagosEstatus asociados a un Pago específico.
     *
     * @param pago El Pago del cual se desean obtener los PagosEstatus.
     * @return Lista de PagosEstatus asociados al Pago especificado.
     */
    @Override
    public List<PagosEstatus> obtenerEstatusPagosPorPago(Pago pago) {
        TypedQuery<PagosEstatus> query = entityManager.createQuery(
                "SELECT pe FROM PagosEstatus pe WHERE pe.pago = :pago", PagosEstatus.class);
        query.setParameter("pago", pago);
        return query.getResultList();
    }
    
    /**
    * Retorna una lista de PagosEstatus asociados a un Beneficiario específico.
    *
    * @param idBeneficiario El ID del Beneficiario del cual se desean obtener los PagosEstatus.
    * @return Lista de PagosEstatus asociados al Beneficiario especificado.
    */
    @Override
   public List<PagosEstatus> obtenerPagosEstatusPorBeneficiario(long idBeneficiario) {
       TypedQuery<PagosEstatus> query = entityManager.createQuery(
               "SELECT pe FROM PagosEstatus pe JOIN pe.pago p WHERE p.beneficiario.id = :idBeneficiario", PagosEstatus.class);
       query.setParameter("idBeneficiario", idBeneficiario);
       return query.getResultList();
   }
   
   /**
    * Retorna una lista de PagosEstatus asociados a un Pago específico.
    *
    * @param pago El Pago del cual se desean obtener los PagosEstatus.
    * @return Lista de PagosEstatus asociados al Pago especificado.
    */
   @Override
   public List<PagosEstatus> obtenerPagosEstatusPorPago(Pago pago) {
       TypedQuery<PagosEstatus> query = entityManager.createQuery(
               "SELECT pe FROM PagosEstatus pe WHERE pe.pago = :pago", PagosEstatus.class);
       query.setParameter("pago", pago);
       return query.getResultList();
   }
    
    /**
     * Cierra la conexión del EntityManager y el EntityManagerFactory.
     * Es importante llamar a este método al finalizar las operaciones con la instancia de PagosEstatusDAO.
     */
    public void cerrarConexion() {
        entityManager.close();
        ConexionBD.close();
    }
    
    
}
