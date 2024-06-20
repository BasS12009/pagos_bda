/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import DAOs.IPagoDAO;
import DTOs.AbonoDTO;
import DTOs.BeneficiarioDTO;
import DTOs.CuentaBancariaDTO;
import DTOs.EstatusDTO;
import DTOs.PagoDTO;
import DTOs.TiposDTO;
import entidades.Abono;
import entidades.CuentaBancaria;
import entidades.Estatus;
import entidades.Pago;
import entidades.PagosEstatus;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase de negocio para operaciones relacionadas con pagos.
 * 
 * @author PC Gamer
 */
public class PagoNegocio implements IPagoNegocio {

    private IPagoDAO pagoDAO;

    /**
     * Constructor de la clase PagoNegocio.
     * @param pagoDAO Objeto IPagoDAO que se utilizará para acceder a la capa de datos.
     */
    public PagoNegocio(IPagoDAO pagoDAO) {
        this.pagoDAO = pagoDAO;
    }

    /**
     * Guarda un pago en la base de datos.
     * @param pagoDTO Objeto PagoDTO que representa el pago a guardar.
     */
    @Override
    public void guardarPago(PagoDTO pagoDTO) {
        Pago pago = convertir(pagoDTO);
        pagoDAO.guardarPago(pago);
    }

    /**
     * Actualiza un pago existente en la base de datos.
     * @param pagoDTO Objeto PagoDTO que representa el pago a actualizar.
     */
    @Override
    public void actualizarPago(PagoDTO pagoDTO) {
        Pago pago = convertir(pagoDTO);
        pagoDAO.actualizarPago(pago);
    }

    /**
     * Elimina un pago de la base de datos por su ID.
     * @param id Identificador único del pago a eliminar.
     * @throws RuntimeException Si no se encuentra ningún pago con el ID especificado.
     */
    @Override
    public void eliminarPago(Long id) {
        Pago pago = pagoDAO.buscarPagoPorId(id);
        if (pago != null) {
            pagoDAO.eliminarPago(pago);
        } else {
            throw new RuntimeException("El pago con ID " + id + " no existe.");
        }
    }

    /**
     * Busca un pago en la base de datos por su ID y lo convierte a PagoDTO.
     * @param id Identificador único del pago a buscar.
     * @return Objeto PagoDTO si se encuentra, o null si no existe ningún pago con ese ID.
     */
    @Override
    public PagoDTO buscarPagoPorId(Long id) {
        PagoDTO pagoDTO = convertir(pagoDAO.buscarPagoPorId(id));
        if (pagoDTO != null) {
            return pagoDTO;
        }
        return null;
    }

    /**
     * Obtiene todos los pagos de la base de datos y los convierte a una lista de PagoDTO.
     * @return Lista de PagoDTO que representa todos los pagos almacenados.
     */
    @Override
    public List<PagoDTO> obtenerTodosLosPagos() {
        List<PagoDTO> pagosDTO = convertir(pagoDAO.obtenerTodosLosPagos());
        return pagosDTO;
    }

    /**
     * Obtiene todos los pagos asociados a un beneficiario específico y los convierte a una lista de PagoDTO.
     * @param idBeneficiario Identificador único del beneficiario para el cual se obtienen los pagos.
     * @return Lista de PagoDTO que representa los pagos asociados al beneficiario especificado.
     */
    @Override
    public List<PagoDTO> obtenerPagosPorBeneficiario(Long idBeneficiario) {
        List<PagoDTO> pagosDTO = convertir(pagoDAO.obtenerPagosPorBeneficiario(idBeneficiario));
        return pagosDTO;
    }

    /**
     * Convierte un objeto PagoDTO a un objeto Pago.
     * @param pagoDTO Objeto PagoDTO que se desea convertir.
     * @return Objeto Pago resultante de la conversión.
     */
    private static Pago convertir(PagoDTO pagoDTO) {
        Pago pago = new Pago();
        pago.setId(pagoDTO.getId());
        pago.setMonto(pagoDTO.getMonto());
        pago.setFechaHora(pagoDTO.getFechaHora());
        pago.setComprobante(pagoDTO.getComprobante());

        if (pagoDTO.getAbonos() != null) {
            List<Abono> abonos = pagoDTO.getAbonos().stream()
                                    .map(AbonoDTO::convertir)
                                    .collect(Collectors.toList());
            pago.setAbonos(abonos);
        }

        if (pagoDTO.getEstatus() != null) {
            List<PagosEstatus> estatus = (List<PagosEstatus>) pagoDTO.getEstatus().stream()
                                        .map(EstatusDTO::convertir);
            pago.setPagosEstatus(estatus);
        }

        pago.setTipo(TiposDTO.convertir(pagoDTO.getTipo()));

        pago.setBeneficiario(BeneficiarioDTO.convertir(pagoDTO.getBeneficiario()));

        if (pagoDTO.getCuentas() != null) {
            List<CuentaBancaria> cuentasBancarias = pagoDTO.getCuentas().stream()
                                                    .map(CuentaBancariaDTO::convertir)
                                                    .collect(Collectors.toList());
            pago.setCuentaBancaria((CuentaBancaria) cuentasBancarias);
        }

        return pago;
    }

    /**
     * Convierte un objeto Pago a un objeto PagoDTO.
     * @param pago Objeto Pago que se desea convertir.
     * @return Objeto PagoDTO resultante de la conversión.
     */
    public static PagoDTO convertir(Pago pago) {
        PagoDTO pagoDTO = new PagoDTO();
        pagoDTO.setId(pago.getId());
        pagoDTO.setMonto(pago.getMonto());
        pagoDTO.setFechaHora(pago.getFechaHora());
        pagoDTO.setComprobante(pago.getComprobante());

        // Convertir abonos si existen
        if (pago.getAbonos() != null) {
            List<AbonoDTO> abonosDTO = pago.getAbonos().stream()
                                        .map(AbonoDTO::convertir)
                                        .collect(Collectors.toList());
            pagoDTO.setAbonos(abonosDTO);
        }

        // Convertir estatus si existen
        if (pago.getPagosEstatus() != null) {
            List<EstatusDTO> estatusDTO = pago.getPagosEstatus().stream()
                                             .map(PagosEstatus::getEstatus)
                                             .map(EstatusDTO::convertir)
                                             .collect(Collectors.toList());
            pagoDTO.setEstatus(estatusDTO);
        }

        // Convertir tipo
        pagoDTO.setTipo(TiposDTO.convertir(pago.getTipo()));

        // Convertir beneficiario
        pagoDTO.setBeneficiario(BeneficiarioDTO.convertir(pago.getBeneficiario()));

        // Convertir cuentas bancarias si existe una
        if (pago.getCuentaBancaria() != null) {
            CuentaBancariaDTO cuentaDTO = CuentaBancariaDTO.convertir(pago.getCuentaBancaria());
            pagoDTO.setCuentas(Collections.singletonList(cuentaDTO));   
    }
        return pagoDTO;
    }
    

    /**
     * Convierte una lista de objetos Pago a una lista de objetos PagoDTO.
     * @param pagos Lista de objetos Pago que se desea convertir.
     * @return Lista de objetos PagoDTO resultante de la conversión.
     */
    public static List<PagoDTO> convertir(List<Pago> pagos) {
        List<PagoDTO> pagosDTO = new ArrayList<>();
        
        for (Pago pago : pagos) {
            PagoDTO pagoDTO = convertir(pago);
            pagosDTO.add(pagoDTO);
        }
        
        return pagosDTO;
    }
}
