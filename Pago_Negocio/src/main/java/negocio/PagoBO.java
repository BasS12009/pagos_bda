/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import DTOs.BeneficiarioDTO;
import DTOs.CuentaBancariaDTO;
import DTOs.EstatusDTO;
import DTOs.PagoDTO;
import DTOs.PagosEstatusDTO;
import excepcionBO.ExcepcionBO;
import java.util.List;

/**
 * Implementación de la interfaz IPagoBO que actúa como fachada para las operaciones de pago.
 * Esta clase delega las operaciones a un objeto IPagoNegocio.
 * 
 * @author PC Gamer
 */
public class PagoBO implements IPagoBO{
    
    private final IPagoNegocio pagoNegocio;


    /**
     * Constructor que inicializa la clase con un objeto IPagoNegocio.
     * 
     * @param pagoNegocio Objeto IPagoNegocio que se utilizará para realizar operaciones de pago.
     */
    public PagoBO(IPagoNegocio pagoNegocio) {
        this.pagoNegocio = pagoNegocio;
    }

    /**
     * Guarda un pago en el sistema.
     * 
     * @param pagoDTO Objeto PagoDTO que representa el pago a guardar.
     */
    @Override
    public void guardarPago(PagoDTO pagoDTO) throws ExcepcionBO {
        pagoNegocio.guardarPago(pagoDTO);
    }

    /**
     * Actualiza un pago existente en el sistema.
     * 
     * @param pagoDTO Objeto PagoDTO que representa el pago a actualizar.
     */
    @Override
    public void actualizarPago(PagoDTO pagoDTO) throws ExcepcionBO {
        pagoNegocio.actualizarPago(pagoDTO);
    }

    /**
     * Elimina un pago del sistema por su ID.
     * 
     * @param id Identificador único del pago a eliminar.
     */
    @Override
    public void eliminarPago(Long id) throws ExcepcionBO {
        pagoNegocio.eliminarPago(id);
    }

    /**
     * Busca un pago en el sistema por su ID.
     * 
     * @param id Identificador único del pago a buscar.
     * @return Objeto PagoDTO si se encuentra, o null si no existe ningún pago con ese ID.
     */
    @Override
    public PagoDTO buscarPagoPorId(Long id) throws ExcepcionBO{
        return pagoNegocio.buscarPagoPorId(id);
    }

    /**
     * Obtiene todos los pagos almacenados en el sistema.
     * 
     * @return Lista de objetos PagoDTO que representan todos los pagos almacenados.
     */
    @Override
    public List<PagoDTO> obtenerTodosLosPagos() throws ExcepcionBO{
        return pagoNegocio.obtenerTodosLosPagos();
    }

    /**
     * Obtiene todos los pagos asociados a un beneficiario específico.
     * 
     * @param idBeneficiario Identificador único del beneficiario para el cual se obtienen los pagos.
     * @return Lista de objetos PagoDTO que representan los pagos asociados al beneficiario especificado.
     */
    @Override
    public List<PagoDTO> obtenerPagosPorBeneficiario(Long idBeneficiario) throws ExcepcionBO {
        return pagoNegocio.obtenerPagosPorBeneficiario(idBeneficiario);
    }
    
    /**
     * Guarda una cuenta bancaria en el sistema.
     * 
     * @param cuentaBancariaDTO Objeto CuentaBancariaDTO que representa la cuenta bancaria a guardar.
     */
    @Override
    public void guardarCuentaBancaria(CuentaBancariaDTO cuentaBancariaDTO) throws ExcepcionBO{
        pagoNegocio.guardarCuentaBancaria(cuentaBancariaDTO);
    }

    /**
     * Actualiza una cuenta bancaria existente en el sistema.
     * 
     * @param cuentaBancariaDTO Objeto CuentaBancariaDTO que representa la cuenta bancaria a actualizar.
     */
    @Override
    public void actualizarCuentaBancaria(CuentaBancariaDTO cuentaBancariaDTO) throws ExcepcionBO{
        pagoNegocio.actualizarCuentaBancaria(cuentaBancariaDTO);
    }

    /**
     * Elimina una cuenta bancaria del sistema por su ID.
     * 
     * @param id Identificador único de la cuenta bancaria a eliminar.
     */
    @Override
    public void eliminarCuentaBancaria(Long id) throws ExcepcionBO{
        pagoNegocio.eliminarCuentaBancaria(id);
    }

    /**
     * Busca una cuenta bancaria en el sistema por su ID.
     * 
     * @param id Identificador único de la cuenta bancaria a buscar.
     * @return Objeto CuentaBancariaDTO si se encuentra, o null si no existe ninguna cuenta bancaria con ese ID.
     */
    @Override
    public CuentaBancariaDTO buscarCuentaBancariaPorId(Long id) throws ExcepcionBO{
        return pagoNegocio.buscarCuentaBancariaPorId(id);
    }

    /**
     * Obtiene todas las cuentas bancarias almacenadas en el sistema.
     * 
     * @return Lista de objetos CuentaBancariaDTO que representan todas las cuentas bancarias almacenadas.
     */
    @Override
    public List<CuentaBancariaDTO> obtenerTodasLasCuentasBancarias() throws ExcepcionBO{
        return pagoNegocio.obtenerTodasLasCuentasBancarias();
    }
    
    /**
     * Guarda un beneficiario en el sistema.
     * 
     * @param beneficiarioDTO Objeto BeneficiarioDTO que representa el beneficiario a guardar.
     */
    @Override
    public void guardarBeneficiario(BeneficiarioDTO beneficiarioDTO) throws ExcepcionBO {
        pagoNegocio.guardarBeneficiario(beneficiarioDTO);
    }

    /**
     * Actualiza un beneficiario existente en el sistema.
     * 
     * @param beneficiarioDTO Objeto BeneficiarioDTO que representa el beneficiario a actualizar.
     */
    @Override
    public void actualizarBeneficiario(BeneficiarioDTO beneficiarioDTO) throws ExcepcionBO {
        pagoNegocio.actualizarBeneficiario(beneficiarioDTO);
    }

    /**
     * Elimina un beneficiario del sistema por su ID.
     * 
     * @param id Identificador único del beneficiario a eliminar.
     */
    @Override
    public void eliminarBeneficiario(Long id) throws ExcepcionBO {
        pagoNegocio.eliminarBeneficiario(id);
    }

    /**
     * Busca un beneficiario en el sistema por su ID.
     * 
     * @param id Identificador único del beneficiario a buscar.
     * @return Objeto BeneficiarioDTO si se encuentra, o null si no existe ningún beneficiario con ese ID.
     */
    @Override
    public BeneficiarioDTO buscarBeneficiarioPorId(Long id) throws ExcepcionBO {
        return pagoNegocio.buscarBeneficiarioPorId(id);
    }

    /**
     * Obtiene todos los beneficiarios almacenados en el sistema.
     * 
     * @return Lista de objetos BeneficiarioDTO que representan todos los beneficiarios almacenados.
     */
    @Override
    public List<BeneficiarioDTO> obtenerTodosLosBeneficiarios() throws ExcepcionBO {
        return pagoNegocio.obtenerTodosLosBeneficiarios();
    }
    
    /**
     *
     * @param beneficiario
     * @return
     * @throws ExcepcionBO
     */
    @Override
    public BeneficiarioDTO login(BeneficiarioDTO beneficiario) throws ExcepcionBO{
        return pagoNegocio.login(beneficiario);
    }
    
    @Override
    public long getId(){
        return pagoNegocio.getId();
    }
    
    @Override
    public void setId(long id){
        pagoNegocio.setId(id);
    }

    @Override
    public List<CuentaBancariaDTO> obtenerTodasLasCuentasBancariasPorBeneficiario(long idBeneficiario) throws ExcepcionBO {
        return pagoNegocio.obtenerTodasLasCuentasBancariasPorBeneficiario(idBeneficiario);
    }
    
    @Override
    public List<PagosEstatusDTO> obtenerPagosEstatusParaPagos(List<PagoDTO> pagos){
        return pagoNegocio.obtenerPagosEstatusParaPagos(pagos);
    }
    
    /**
     *
     * @param pagoDTO
     * @param estatusDTO
     */
    public void guardarPagoConEstatus(PagoDTO pagoDTO, EstatusDTO estatusDTO){
        pagoNegocio.guardarPagoConEstatus(pagoDTO, estatusDTO);
    }
}
