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
import entidades.Beneficiario;
import entidades.Pago;
import excepcionBO.ExcepcionBO;
import java.util.List;


/**
 * Interfaz que define las operaciones de negocio relacionadas con pagos, cuentas bancarias y beneficiarios.
 * Define métodos para guardar, actualizar, eliminar y buscar pagos, cuentas bancarias y beneficiarios en la base de datos.
 * Todos los métodos lanzan ExcepcionBO en caso de errores operativos.
 */
public interface IPagoNegocio {
    
    /**
     *
     * @param beneficiario
     * @return
     */
    public BeneficiarioDTO login(BeneficiarioDTO beneficiario);
    
    public long getId();
    
    public void setId(long id);
    
    /**
     * Guarda un nuevo pago en la base de datos.
     * 
     * @param pagoDTO El objeto Pago que se desea guardar.
     * @throws excepcionBO.ExcepcionBO
     */
    public void guardarPago(PagoDTO pagoDTO) throws ExcepcionBO;
    
    /**
     * Actualiza la información de un pago existente en la base de datos.
     * 
     * @param pagoDTO El objeto Pago con los datos actualizados.
     */
    void actualizarPago(PagoDTO pagoDTO) throws ExcepcionBO;
    
    /**
     * Elimina un pago de la base de datos.
     * 
     * @param id
     */
    void eliminarPago(Long id)throws ExcepcionBO;
    
    /**
     * Busca y retorna un pago por su ID.
     * 
     * @param id El ID del pago que se desea buscar.
     * @return El objeto Pago encontrado, o null si no existe.
     */
    PagoDTO buscarPagoPorId(Long id)throws ExcepcionBO;
    
    /**
     * Retorna una lista con todos los pagos almacenados en la base de datos.
     * 
     * @return Lista de objetos Pago.
     */
    List<PagoDTO> obtenerTodosLosPagos()throws ExcepcionBO;
    
    /**
     * Retorna una lista con todos los pagos asociados a un beneficiario específico.
     * 
     * @param idBeneficiario
     * @return Lista de pagos asociados al beneficiario.
     */
    List<PagoDTO> obtenerPagosPorBeneficiario(Long idBeneficiario)throws ExcepcionBO;
    
    /**
     * Guarda una cuenta bancaria en la base de datos.
     * 
     * @param cuentaBancariaDTO Objeto CuentaBancariaDTO que representa la cuenta bancaria a guardar.
     * @throws ExcepcionBO Si ocurre un error al guardar la cuenta bancaria.
     */
    void guardarCuentaBancaria(CuentaBancariaDTO cuentaBancariaDTO) throws ExcepcionBO;
    
    /**
     * Actualiza una cuenta bancaria existente en la base de datos.
     * 
     * @param cuentaBancariaDTO Objeto CuentaBancariaDTO que representa la cuenta bancaria a actualizar.
     * @throws ExcepcionBO Si ocurre un error al actualizar la cuenta bancaria.
     */
    void actualizarCuentaBancaria(CuentaBancariaDTO cuentaBancariaDTO) throws ExcepcionBO;
    
    /**
     * Elimina una cuenta bancaria de la base de datos por su ID.
     * 
     * @param id Identificador único de la cuenta bancaria a eliminar.
     * @throws ExcepcionBO Si ocurre un error al eliminar la cuenta bancaria.
     */
    void eliminarCuentaBancaria(Long id) throws ExcepcionBO;
    
    /**
     * Busca y retorna una cuenta bancaria por su ID.
     * 
     * @param id Identificador único de la cuenta bancaria a buscar.
     * @return Objeto CuentaBancariaDTO si se encuentra, o null si no existe ninguna cuenta bancaria con ese ID.
     * @throws ExcepcionBO Si ocurre un error al buscar la cuenta bancaria.
     */
    CuentaBancariaDTO buscarCuentaBancariaPorId(Long id) throws ExcepcionBO;
    
    /**
     * Retorna una lista con todas las cuentas bancarias almacenadas en la base de datos.
     * 
     * @return Lista de objetos CuentaBancariaDTO que representa todas las cuentas bancarias almacenadas.
     * @throws ExcepcionBO Si ocurre un error al obtener todas las cuentas bancarias.
     */
    List<CuentaBancariaDTO> obtenerTodasLasCuentasBancarias() throws ExcepcionBO;
    
    /**
     *
     * @param idBeneficiario
     * @return
     * @throws ExcepcionBO
     */
    public List<CuentaBancariaDTO> obtenerTodasLasCuentasBancariasPorBeneficiario(long idBeneficiario) throws ExcepcionBO;
    
    /**
     * Guarda un beneficiario en la base de datos.
     * 
     * @param beneficiarioDTO Objeto BeneficiarioDTO que representa el beneficiario a guardar.
     * @throws ExcepcionBO Si ocurre un error al guardar el beneficiario.
     */
    void guardarBeneficiario(BeneficiarioDTO beneficiarioDTO) throws ExcepcionBO;
    
    /**
     * Actualiza un beneficiario existente en la base de datos.
     * 
     * @param beneficiarioDTO Objeto BeneficiarioDTO que representa el beneficiario a actualizar.
     * @throws ExcepcionBO Si ocurre un error al actualizar el beneficiario.
     */
    void actualizarBeneficiario(BeneficiarioDTO beneficiarioDTO) throws ExcepcionBO;
    
    /**
     * Elimina un beneficiario de la base de datos por su ID.
     * 
     * @param id Identificador único del beneficiario a eliminar.
     * @throws ExcepcionBO Si ocurre un error al eliminar el beneficiario.
     */
    void eliminarBeneficiario(Long id) throws ExcepcionBO;
    
    /**
     * Busca y retorna un beneficiario por su ID.
     * 
     * @param id Identificador único del beneficiario a buscar.
     * @return Objeto BeneficiarioDTO si se encuentra, o null si no existe ningún beneficiario con ese ID.
     * @throws ExcepcionBO Si ocurre un error al buscar el beneficiario.
     */
    BeneficiarioDTO buscarBeneficiarioPorId(Long id) throws ExcepcionBO;
    
    /**
     * Retorna una lista con todos los beneficiarios almacenados en la base de datos.
     * 
     * @return Lista de objetos BeneficiarioDTO que representa todos los beneficiarios almacenados.
     * @throws ExcepcionBO Si ocurre un error al obtener todos los beneficiarios.
     */
    List<BeneficiarioDTO> obtenerTodosLosBeneficiarios() throws ExcepcionBO;
    
    public List<PagosEstatusDTO> obtenerPagosEstatusParaPagos(List<PagoDTO> pagos);
    
    public void guardarPagoConEstatus(PagoDTO pagoDTO, EstatusDTO estatusDTO);
}