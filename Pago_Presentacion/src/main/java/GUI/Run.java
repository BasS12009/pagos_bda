/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package GUI;

import DAOs.AbonoDAO;
import DAOs.BeneficiarioDAO;
import DAOs.CuentaBancariaDAO;
import DAOs.EstatusDAO;
import DAOs.IBeneficiarioDAO;
import DAOs.ICuentaBancariaDAO;
import DAOs.IPagoDAO;
import DAOs.IPagosEstatusDAO;
import DAOs.PagoDAO;
import DAOs.PagosEstatusDAO;
import DAOs.TiposDAO;
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
        
        PagoDAO pagoD=new PagoDAO();
        BeneficiarioDAO beneficiario=new BeneficiarioDAO();
        CuentaBancariaDAO cuenta=new CuentaBancariaDAO();
        PagosEstatusDAO pagosEstatus=new PagosEstatusDAO();
        TiposDAO tipos=new TiposDAO();
        EstatusDAO estatus=new EstatusDAO();
        AbonoDAO abono=new AbonoDAO();
        PagoNegocio pagoN=new PagoNegocio(pagoD,cuenta,beneficiario,pagosEstatus,tipos,estatus,abono);
        PagoBO pago=new PagoBO(pagoN);
        
        logIn login = new logIn(pago);
        login.show();
        
        
        
    }
    
}
