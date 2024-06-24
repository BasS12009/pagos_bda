/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import entidades.Tipos;
import java.util.List;

/**
 * Interfaz para el acceso a datos de Tipos.
 * 
 * @author PC Gamer
 */
public interface ITiposDAO {
    /**
     * Método para obtener todos los tipos.
     * 
     * @return Una lista de todos los tipos.
     */
    List<Tipos> obtenerTodosLosTipos();
    
    Tipos obtenerTipoPorId(Long id);
}
