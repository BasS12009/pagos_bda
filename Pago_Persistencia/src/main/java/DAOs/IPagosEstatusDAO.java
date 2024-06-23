/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import entidades.Estatus;
import entidades.Pago;
import entidades.PagosEstatus;
import java.util.List;

/**
 *
 * @author PC Gamer
 */
public interface IPagosEstatusDAO {
    List<PagosEstatus> obtenerPagosEstatusParaPagos(List<PagosEstatus> pagos);
    Estatus obtenerEstatusParaPago(PagosEstatus pago);
    String obtenerMensajeParaPago(PagosEstatus pago);
    public List<PagosEstatus> obtenerEstatusPagosPorPago(Pago pago);
}
