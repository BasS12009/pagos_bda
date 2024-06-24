/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import conexion.ConexionBD;
import entidades.Tipos;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author PC Gamer
 */
public class TiposDAO implements ITiposDAO {

    private EntityManager entityManager;

    /**
     * Constructor que inicializa el EntityManager utilizando JPAUtil.
     */
    public TiposDAO() {
        this.entityManager = ConexionBD.getEntityManager();
    }
    
    @Override
    public List<Tipos> obtenerTodosLosTipos() {
        return entityManager.createQuery("SELECT t FROM Tipos t", Tipos.class).getResultList();
    }

    @Override
    public Tipos obtenerTipoPorId(Long id) {
        return entityManager.find(Tipos.class, id);
    }
    
}
