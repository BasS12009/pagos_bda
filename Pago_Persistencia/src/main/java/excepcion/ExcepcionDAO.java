/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepcion;

/**
 * Clase de excepción personalizada para operaciones relacionadas con el DAO.
 * Extiende de la clase base Exception.
 * 
 * Esta clase permite manejar excepciones específicas relacionadas con operaciones en el DAO,
 * proporcionando constructores para inicializar la excepción con un mensaje o un mensaje y una causa.
 * 
 * @author PC Gamer
 */
public class ExcepcionDAO extends Exception {

    /**
     * Constructor sin argumentos.
     * Crea una nueva instancia de ExcepcionDAO.
     */
    public ExcepcionDAO() {
    }

    /**
     * Constructor que acepta un mensaje descriptivo de la excepción.
     * Crea una nueva instancia de ExcepcionDAO con el mensaje especificado.
     * 
     * @param message Mensaje descriptivo de la excepción.
     */
    public ExcepcionDAO(String message) {
        super(message);
    }

    /**
     * Constructor que acepta un mensaje descriptivo y una causa subyacente de la excepción.
     * Crea una nueva instancia de ExcepcionDAO con el mensaje y causa especificados.
     * 
     * @param message Mensaje descriptivo de la excepción.
     * @param cause Causa subyacente de la excepción.
     */
    public ExcepcionDAO(String message, Throwable cause) {
        super(message, cause);
    }
    
}
