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
import DTOs.TiposDTO;
import excepcionBO.ExcepcionBO;
import java.util.List;

/**
 * Interfaz que define las operaciones relacionadas con pagos y cuentas bancarias.
 * Las implementaciones de esta interfaz deben manejar excepciones del tipo ExcepcionBO.
 * 
 * @author PC Gamer
 */
public interface IPagoBO {
    
    /**
     *
     * @param beneficiario
     * @return
     */
    public BeneficiarioDTO login(BeneficiarioDTO beneficiario);
    
    public long getId();
    
    public void setId(long id);
    
    /**
     *
     * @param pagoDTO
     */
    public void guardarPago(PagoDTO pagoDTO, EstatusDTO estatus) throws ExcepcionBO;

    /**
     *
     * @param pagoDTO
     */
    void actualizarPago(PagoDTO pagoDTO) throws ExcepcionBO;

    /**
     *
     * @param id
     */
    void eliminarPago(Long id)throws ExcepcionBO;

    /**
     *
     * @param id
     * @return
     */
    PagoDTO buscarPagoPorId(Long id)throws ExcepcionBO;

    /**
     *
     * @return
     */
    List<PagoDTO> obtenerTodosLosPagos()throws ExcepcionBO;

    /**
     *
     * @param idBeneficiario
     * @return
     */
    List<PagoDTO> obtenerPagosPorBeneficiario(Long idBeneficiario)throws ExcepcionBO;
    
    /**
     * Guarda una cuenta bancaria en el sistema.
     * 
     * @param cuentaBancariaDTO Objeto CuentaBancariaDTO que representa la cuenta bancaria a guardar.
     */
    public void guardarCuentaBancaria(CuentaBancariaDTO cuentaBancariaDTO)throws ExcepcionBO;

    /**
     * Actualiza una cuenta bancaria existente en el sistema.
     * 
     * @param cuentaBancariaDTO Objeto CuentaBancariaDTO que representa la cuenta bancaria a actualizar.
     */
    public void actualizarCuentaBancaria(CuentaBancariaDTO cuentaBancariaDTO)throws ExcepcionBO;

    /**
     * Elimina una cuenta bancaria del sistema por su ID.
     * 
     * @param id Identificador único de la cuenta bancaria a eliminar.
     */
    public void eliminarCuentaBancaria(Long id)throws ExcepcionBO;

    /**
     * Busca una cuenta bancaria en el sistema por su ID.
     * 
     * @param id Identificador único de la cuenta bancaria a buscar.
     * @return Objeto CuentaBancariaDTO si se encuentra, o null si no existe ninguna cuenta bancaria con ese ID.
     */
    public CuentaBancariaDTO buscarCuentaBancariaPorId(Long id)throws ExcepcionBO;

    /**
     * Obtiene todas las cuentas bancarias almacenadas en el sistema.
     * 
     * @return Lista de objetos CuentaBancariaDTO que representan todas las cuentas bancarias almacenadas.
     */
    public List<CuentaBancariaDTO> obtenerTodasLasCuentasBancarias()throws ExcepcionBO;
    
    public List<CuentaBancariaDTO> obtenerTodasLasCuentasBancariasPorBeneficiario(long idBeneficiario) throws ExcepcionBO;
    
     /**
     * Guarda un beneficiario en el sistema.
     * 
     * @param beneficiarioDTO Objeto BeneficiarioDTO que representa el beneficiario a guardar.
     * @throws ExcepcionBO Si ocurre un error al intentar guardar el beneficiario.
     */
    void guardarBeneficiario(BeneficiarioDTO beneficiarioDTO) throws ExcepcionBO;

    /**
     * Actualiza un beneficiario existente en el sistema.
     * 
     * @param beneficiarioDTO Objeto BeneficiarioDTO que representa el beneficiario a actualizar.
     * @throws ExcepcionBO Si ocurre un error al intentar actualizar el beneficiario.
     */
    void actualizarBeneficiario(BeneficiarioDTO beneficiarioDTO) throws ExcepcionBO;

    /**
     * Elimina un beneficiario del sistema por su ID.
     * 
     * @param id Identificador único del beneficiario a eliminar.
     * @throws ExcepcionBO Si ocurre un error al intentar eliminar el beneficiario.
     */
    void eliminarBeneficiario(Long id) throws ExcepcionBO;

    /**
     * Busca un beneficiario en el sistema por su ID.
     * 
     * @param id Identificador único del beneficiario a buscar.
     * @return Objeto BeneficiarioDTO si se encuentra, o null si no existe ningún beneficiario con ese ID.
     * @throws ExcepcionBO Si ocurre un error al intentar buscar el beneficiario.
     */
    BeneficiarioDTO buscarBeneficiarioPorId(Long id) throws ExcepcionBO;

    /**
     * Obtiene todos los beneficiarios almacenados en el sistema.
     * 
     * @return Lista de objetos BeneficiarioDTO que representan todos los beneficiarios almacenados.
     * @throws ExcepcionBO Si ocurre un error al intentar obtener todos los beneficiarios.
     */
    List<BeneficiarioDTO> obtenerTodosLosBeneficiarios() throws ExcepcionBO;
    
    public List<PagosEstatusDTO> obtenerPagosEstatusParaPagos(List<PagoDTO> pagos);
    
    public void guardarPagoConEstatus(PagoDTO pagoDTO, EstatusDTO estatusDTO);
    
    public List<TiposDTO> obtenerTodosLosTipos() throws ExcepcionBO;
    public List<EstatusDTO> obtenerEstatus();
    
    public List<PagosEstatusDTO> obtenerPagosEstatusPorBeneficiario(long id);
}
