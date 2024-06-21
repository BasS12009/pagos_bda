/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepcionBO;

/**
 *
 * @author 
 */
public class ExcepcionBO extends Exception{

    /**
     * Construcotr por omisión
     */
    public ExcepcionBO() {
    }

    /**
     * 
     * @param message mensaje que muestra el error
     */
    public ExcepcionBO(String message) {
        super(message);
    }

    /**
     * 
     * @param message mensaje que muestra el erro
     * @param cause causa de la excepcion
     */
    public ExcepcionBO(String message, Throwable cause) {
        super(message, cause);
    }
    
    
    
}
