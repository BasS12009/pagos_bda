/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package GUI;

import DAOs.BeneficiarioDAO;
import DAOs.CuentaBancariaDAO;
import DAOs.IBeneficiarioDAO;
import DAOs.ICuentaBancariaDAO;
import DAOs.IPagoDAO;
import DAOs.IPagosEstatusDAO;
import DAOs.PagoDAO;
import DAOs.PagosEstatusDAO;
import conexion.ConexionBD;
import negocio.IPagoBO;
import negocio.IPagoNegocio;
import negocio.PagoBO;
import negocio.PagoNegocio;

/**
 *
 * @author santi
 */
public class Run {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ConexionBD conexionBD = new ConexionBD();      
        
        BeneficiarioDAO beneficiario = new BeneficiarioDAO();
        PagoDAO pago = new PagoDAO();
        CuentaBancariaDAO cuenta = new CuentaBancariaDAO();
        PagosEstatusDAO pagoE = new PagosEstatusDAO();
        IPagoNegocio pagoNegocio = new PagoNegocio(pago, cuenta, beneficiario, pagoE);
        PagoBO pagoBO = new PagoBO(pagoNegocio);
        
//        logIn login = new logIn(pagoBO);
//        login.show();
//        
        
        
    }
    
}
