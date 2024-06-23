/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.probar;

import BeneficiarioPresentacion.MisCuentasBancarias;
import BeneficiarioPresentacion.MisPagos;
import DAOs.BeneficiarioDAO;
import DAOs.CuentaBancariaDAO;
import DAOs.IPagoDAO;
import DAOs.PagoDAO;
import DAOs.PagosEstatusDAO;
import DAOs.TiposDAO;
import negocio.PagoBO;
import negocio.PagoNegocio;

/**
 *
 * @author PC Gamer
 */
public class Probar {

    public static void main(String[] args) {
        PagoDAO pagoD=new PagoDAO();
        BeneficiarioDAO beneficiario=new BeneficiarioDAO();
        CuentaBancariaDAO cuenta=new CuentaBancariaDAO();
        PagosEstatusDAO pagosEstatus=new PagosEstatusDAO();
        TiposDAO tipos=new TiposDAO();
        PagoNegocio pagoN=new PagoNegocio(pagoD,cuenta,beneficiario,pagosEstatus,tipos);
        PagoBO pago=new PagoBO(pagoN);
        pago.setId(1);
        MisPagos probarPagos=new MisPagos(pago);
        probarPagos.show();
        MisCuentasBancarias probar=new MisCuentasBancarias(pago);
        
        probar.show();
    }
}
