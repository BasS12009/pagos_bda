package negocio;

import DAOs.AbonoDAO;
import DAOs.BeneficiarioDAO;
import DAOs.CuentaBancariaDAO;
import DAOs.EstatusDAO;
import DAOs.IBeneficiarioDAO;
import DAOs.ICuentaBancariaDAO;
import DAOs.IPagoDAO;
import DAOs.PagoDAO;
import DAOs.PagosEstatusDAO;
import DAOs.TiposDAO;
import DTOs.AbonoDTO;
import DTOs.CuentaBancariaDTO;
import DTOs.EstatusDTO;
import DTOs.NombreDTO;
import DTOs.PagoDTO;
import DTOs.TiposDTO;
import entidades.Abono;
import entidades.Beneficiario;
import entidades.CuentaBancaria;
import entidades.Pago;
import entidades.PagosEstatus;
import excepcionBO.ExcepcionBO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import DTOs.BeneficiarioDTO;
import DTOs.PagosEstatusDTO;
import entidades.Estatus;
import entidades.Tipos;
import excepcion.ExcepcionDAO;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Clase de negocio para operaciones relacionadas con pagos.
 * 
 * @author PC Gamer
 */
public class PagoNegocio implements IPagoNegocio {

    private PagoDAO pagoDAO;
    private CuentaBancariaDAO cuentaBancariaDAO;
    private BeneficiarioDAO beneficiarioDAO;
    private PagosEstatusDAO pagoEstatusDAO;
    private TiposDAO tiposDAO;
    private EstatusDAO estatusDAO;
    private AbonoDAO abonoDAO;
    long id;
    

    /**
     * Constructor de la clase PagoNegocio.
     * 
     * @param pagoDAO             Objeto IPagoDAO que se utilizará para acceder a la capa de datos de pagos.
     * @param cuentaBancariaDAO   Objeto ICuentaBancariaDAO que se utilizará para acceder a la capa de datos de cuentas bancarias.
     * @param beneficiarioDAO     Objeto IBeneficiarioDAO que se utilizará para acceder a la capa de datos de beneficiarios.
     */
    public PagoNegocio(PagoDAO pagoDAO, CuentaBancariaDAO cuentaBancariaDAO, BeneficiarioDAO beneficiarioDAO, PagosEstatusDAO pagoEstatusDAO, TiposDAO tiposDAO, EstatusDAO estatus,AbonoDAO abonoDAO) {

        this.pagoDAO = pagoDAO;
        this.cuentaBancariaDAO = cuentaBancariaDAO;
        this.beneficiarioDAO = beneficiarioDAO;
        this.pagoEstatusDAO=pagoEstatusDAO;
        this.tiposDAO=tiposDAO;
        this.estatusDAO=estatus;

        this.abonoDAO=abonoDAO;
    }

    /**
     *
     * @return
     */
    @Override
    public long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    @Override
    public void setId(long id) {
        this.id = id;
    }
    

    /**
     * Método que logea al beneficiario
     * @param beneficiario Objeto beneficiario
     * @return Objeto conversion de BeneficiarioDTO
     */
    public BeneficiarioDTO login(BeneficiarioDTO beneficiario) throws ExcepcionBO{

        Beneficiario beneficiarioAuxiliar = null;
        beneficiarioAuxiliar = convertir(beneficiario);
        try {
            return convertir(beneficiarioDAO.login(beneficiarioAuxiliar));
        } catch (ExcepcionDAO ex) {
            Logger.getLogger(PagoNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            return convertir(beneficiarioDAO.login(beneficiarioAuxiliar));
        } catch (ExcepcionDAO ex) {
            throw new ExcepcionBO("Error al iniciar sesión :(", ex);
        }
        
    }
    
    /**
     * Guarda un pago en la base de datos.
     * @param pagoDTO Objeto PagoDTO que representa el pago a guardar.
     */
    @Override
    public void guardarPago(PagoDTO pagoDTO, EstatusDTO estatus) throws ExcepcionBO {
        try {
            Pago pago = convertir(pagoDTO);
            pago.setFechaHora(pagoDTO.getFechaHora());
            pago.setMonto(pagoDTO.getMonto());
            pago.setComprobante("esperando Pago");
            pago = pagoDAO.guardarPago(pago);
            PagoDTO pagod = new PagoDTO();
            pagod.setId(pago.getId());
            System.out.println("Pago ID: " + pagod.getId());
            this.guardarPagoConEstatus(pagod, estatus);
        } catch (Exception ex) {
            throw new ExcepcionBO("Error al guardar el pago", ex);
        }
    }

    /**
     * Actualiza un pago existente en la base de datos.
     * @param pagoDTO Objeto PagoDTO que representa el pago a actualizar.
     */
    @Override
    public void actualizarPago(PagoDTO pagoDTO, EstatusDTO estatus) throws ExcepcionBO{
        try {
            Pago pago = pagoDAO.buscarPagoPorId(pagoDTO.getId());
            PagosEstatus pe=(PagosEstatus) pagoEstatusDAO.obtenerEstatusPagosPorPago(pago).get(0);
            pagoDAO.actualizarPago(pago);
            pe.setPago(pago);
            pe.setEstatus(estatusDAO.buscarEstatusPorId(estatus.getId()));
            pe.setMensaje("El pago ha sido modificado");
            pagoEstatusDAO.actualizarPagosEstatus(pe);
        } catch (ExcepcionDAO ex) {
            throw new ExcepcionBO("Error al actualizar el pago", ex);
        }
    }

    /**
     * Elimina un pago de la base de datos por su ID.
     * @param id Identificador único del pago a eliminar.
     * @throws RuntimeException Si no se encuentra ningún pago con el ID especificado.
     */
    @Override
    public void eliminarPago(Long id) throws ExcepcionBO {
        try {
            Pago pago = pagoDAO.buscarPagoPorId(id);
            if (pago != null) {
                pagoDAO.eliminarPago(pago);
                
            } else {
                throw new RuntimeException("El pago con ID " + id + " no existe.");
            }
        } catch (ExcepcionDAO ex) {
            throw new ExcepcionBO("Error al eliminar el pago", ex);
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
    public List<PagoDTO> obtenerTodosLosPagos() throws ExcepcionBO {
        try {
            List<PagoDTO> pagosDTO = convertirDAO(pagoDAO.obtenerTodosLosPagos());
            return pagosDTO;
        } catch (ExcepcionDAO ex) {
            throw new ExcepcionBO("Error al buscar todos los pagos", ex);
        }
    }

    /**
     * Obtiene todos los pagos asociados a un beneficiario específico y los convierte a una lista de PagoDTO.
     * @param idBeneficiario Identificador único del beneficiario para el cual se obtienen los pagos.
     * @return Lista de PagoDTO que representa los pagos asociados al beneficiario especificado.
     * @throws excepcionBO.ExcepcionBO
     */
    @Override
    public List<PagoDTO> obtenerPagosPorBeneficiario(Long idBeneficiario) throws ExcepcionBO {
        try {
            List<PagoDTO> pagosDTO = convertirDAO(pagoDAO.obtenerPagosPorBeneficiario(idBeneficiario));
            return pagosDTO;
        } catch (ExcepcionDAO ex) {
            throw new ExcepcionBO("Error los pagos los pagos del beneficiario", ex);
        }
    }
    
    /**
     * Guarda una cuenta bancaria en la base de datos.
     * 
     * @param cuentaBancariaDTO Objeto CuentaBancariaDTO que representa la cuenta bancaria a guardar.
     * @throws ExcepcionBO Si ocurre un error al guardar la cuenta bancaria.
     */
    @Override
    public void guardarCuentaBancaria(CuentaBancariaDTO cuentaBancariaDTO) throws ExcepcionBO {
        try {
            CuentaBancaria cuentaBancaria = convertirDAO(cuentaBancariaDTO);
            cuentaBancariaDAO.guardarCuentaBancaria(cuentaBancaria);
        } catch (ExcepcionDAO ex) {
            throw new ExcepcionBO("Error al guardar la cuenta", ex);
        }
    }

    /**
     * Actualiza una cuenta bancaria existente en la base de datos.
     * 
     * @param cuentaBancariaDTO Objeto CuentaBancariaDTO que representa la cuenta bancaria a actualizar.
     * @throws ExcepcionBO Si ocurre un error al actualizar la cuenta bancaria.
     */
    @Override
    public void actualizarCuentaBancaria(CuentaBancariaDTO cuentaBancariaDTO) throws ExcepcionBO {
        try {
            CuentaBancaria cuentaBancaria = convertirDAO(cuentaBancariaDTO);
            cuentaBancariaDAO.actualizarCuentaBancaria(cuentaBancaria);
        } catch (ExcepcionDAO ex) {
            throw new ExcepcionBO("Error al actualizar la cuenta", ex);
        }
    }

    
    /**
     * Elimina una cuenta bancaria de la base de datos por su ID.
     * 
     * @param id Identificador único de la cuenta bancaria a eliminar.
     * @throws ExcepcionBO Si ocurre un error al eliminar la cuenta bancaria.
     */
    @Override
    public void eliminarCuentaBancaria(Long id) throws ExcepcionBO {
        try {
            CuentaBancaria cuentaBancaria = cuentaBancariaDAO.buscarCuentaBancariaPorId(id);
            if (cuentaBancaria != null) {
                cuentaBancariaDAO.eliminarCuentaBancaria(cuentaBancaria);
            } else {
                throw new RuntimeException("La cuenta bancaria con ID " + id + " no existe.");
            }
        } catch (ExcepcionDAO ex) {
            throw new ExcepcionBO("Error al eliminar la cuenta", ex);
        }
    }

    
    /**
     * Busca una cuenta bancaria en la base de datos por su ID y la convierte a CuentaBancariaDTO.
     * 
     * @param id Identificador único de la cuenta bancaria a buscar.
     * @return Objeto CuentaBancariaDTO si se encuentra, o null si no existe ninguna cuenta bancaria con ese ID.
     * @throws ExcepcionBO Si ocurre un error al buscar la cuenta bancaria.
     */
    @Override
    public CuentaBancariaDTO buscarCuentaBancariaPorId(Long id) throws ExcepcionBO {
        try {
            CuentaBancaria cuentaBancaria = cuentaBancariaDAO.buscarCuentaBancariaPorId(id);
            return convertir(cuentaBancaria);
        } catch (ExcepcionDAO ex) {
            throw new ExcepcionBO("Error al buscar la cuenta", ex);
        }
    }

    /**
     * Obtiene todas las cuentas bancarias de la base de datos y las convierte a una lista de CuentaBancariaDTO.
     * 
     * @return Lista de CuentaBancariaDTO que representa todas las cuentas bancarias almacenadas.
     * @throws ExcepcionBO Si ocurre un error al obtener todas las cuentas bancarias.
     */
    @Override
    public List<CuentaBancariaDTO> obtenerTodasLasCuentasBancarias() throws ExcepcionBO {
        try {
            List<CuentaBancaria> cuentasBancarias = cuentaBancariaDAO.obtenerTodasLasCuentasBancarias();
            return convertirCuentasBancarias(cuentasBancarias);
        } catch (ExcepcionDAO ex) {
            throw new ExcepcionBO("Error al buscar todas las cuentas", ex);
        }
    }
    
   /**
        * Obtiene todas las cuentas bancarias filtradas por el ID del beneficiario y las convierte a una lista de CuentaBancariaDTO.
        *
        * @param idBeneficiario ID del beneficiario por el cual filtrar las cuentas bancarias.
        * @return Lista de CuentaBancariaDTO que representa todas las cuentas bancarias del beneficiario especificado.
        * @throws ExcepcionBO Si ocurre un error al obtener las cuentas bancarias por el beneficiario.
        */
       @Override
       public List<CuentaBancariaDTO> obtenerTodasLasCuentasBancariasPorBeneficiario(long idBeneficiario) throws ExcepcionBO {
           try {
               List<CuentaBancaria> cuentasBancarias = cuentaBancariaDAO.obtenerCuentasBancariasPorIdBeneficiario(idBeneficiario);

               List<CuentaBancariaDTO> cuentasPorBeneficiario = new ArrayList<>();
               for (CuentaBancaria cuenta : cuentasBancarias) {
                   cuentasPorBeneficiario.add(convertir(cuenta)); 
               }

               return cuentasPorBeneficiario;
           } catch (ExcepcionDAO ex) {
               throw new ExcepcionBO("Error al buscar cuentas bancarias por beneficiario con ID: " + idBeneficiario, ex);
           }
       }

       /**
        * Convierte un objeto CuentaBancaria a CuentaBancariaDTO.
        * 
        * @param cuenta Objeto CuentaBancaria a convertir.
        * @return CuentaBancariaDTO convertido.
        */
       private CuentaBancariaDTO convertir(CuentaBancaria cuenta) {
           CuentaBancariaDTO cuentaDTO = new CuentaBancariaDTO();
           cuentaDTO.setId(cuenta.getId());
           cuentaDTO.setNumeroCuenta(cuenta.getNumeroCuenta());
           cuentaDTO.setClave(cuenta.getClave());
           cuentaDTO.setBanco(cuenta.getBanco());
           cuentaDTO.setBeneficiario(convertir(cuenta.getBeneficiario()));
           cuentaDTO.setEliminada(cuenta.getEliminada());

           return cuentaDTO;
       }

    /**
     * Guarda un beneficiario en la base de datos.
     * 
     * @param beneficiarioDTO Objeto BeneficiarioDTO que representa el beneficiario a guardar.
     * @throws ExcepcionBO Si ocurre un error al guardar el beneficiario.
     */
    @Override
    public void guardarBeneficiario(BeneficiarioDTO beneficiarioDTO) throws ExcepcionBO{
        try {
            Beneficiario beneficiario = convertir(beneficiarioDTO);
            beneficiarioDAO.guardarBeneficiario(beneficiario);
        } catch (ExcepcionDAO ex) {
            throw new ExcepcionBO("Error al guardar el beneficiario", ex);
        }
    }

    /**
     * Actualiza un beneficiario existente en la base de datos.
     * 
     * @param beneficiarioDTO Objeto BeneficiarioDTO que representa el beneficiario a actualizar.
     * @throws ExcepcionBO Si ocurre un error al actualizar el beneficiario.
     */
    @Override
    public void actualizarBeneficiario(BeneficiarioDTO beneficiarioDTO) throws ExcepcionBO {
        try {
            Beneficiario beneficiario = convertir(beneficiarioDTO);
            beneficiarioDAO.actualizarBeneficiario(beneficiario);
        } catch (ExcepcionDAO ex) {
            throw new ExcepcionBO("Error al actualizar el beneficiario", ex);
        }
    }

     /**
     * Elimina un beneficiario de la base de datos por su ID.
     * 
     * @param id Identificador único del beneficiario a eliminar.
     * @throws ExcepcionBO Si ocurre un error al eliminar el beneficiario.
     */
    @Override
    public void eliminarBeneficiario(Long id) throws ExcepcionBO {
        try {
            Beneficiario beneficiario = beneficiarioDAO.buscarBeneficiarioPorId(id);
            if (beneficiario != null) {
                beneficiarioDAO.eliminarBeneficiario(beneficiario);
            } else {
                throw new RuntimeException("El beneficiario con ID " + id + " no existe.");
            }
        } catch (ExcepcionDAO ex) {
            throw new ExcepcionBO("Error al eliminar el beneficiario", ex);
        }
    }

     /**
     * Busca un beneficiario en la base de datos por su ID y lo convierte a BeneficiarioDTO.
     * 
     * @param id Identificador único del beneficiario a buscar.
     * @return Objeto BeneficiarioDTO si se encuentra, o null si no existe ningún beneficiario con ese ID.
     * @throws ExcepcionBO Si ocurre un error al buscar el beneficiario.
     */
    @Override
    public BeneficiarioDTO buscarBeneficiarioPorId(Long id) throws ExcepcionBO {
        try {
            Beneficiario beneficiario = beneficiarioDAO.buscarBeneficiarioPorId(id);
            return convertir(beneficiario);
        } catch (ExcepcionDAO ex) {
            throw new ExcepcionBO("Error al buscar el beneficiario", ex);
        }
    }

    /**
     * Obtiene todos los beneficiarios de la base de datos y los convierte a una lista de BeneficiarioDTO.
     * 
     * @return Lista de BeneficiarioDTO que representa todos los beneficiarios almacenados.
     * @throws ExcepcionBO Si ocurre un error al obtener todos los beneficiarios.
     */
    @Override
    public List<BeneficiarioDTO> obtenerTodosLosBeneficiarios() throws ExcepcionBO {
        try {
            List<Beneficiario> beneficiarios = beneficiarioDAO.obtenerTodosLosBeneficiarios();
            return convertirBeneficiarios(beneficiarios);
        } catch (ExcepcionDAO ex) {
            throw new ExcepcionBO("Error al buscar todos los beneficiarios", ex);
        }
    }
    
    /**
     *
     * @return
     * @throws ExcepcionBO
     */
    @Override
    public List<TiposDTO> obtenerTodosLosTipos() throws ExcepcionBO{
        List<TiposDTO> tipos= convertir(tiposDAO.obtenerTodosLosTipos());
        return tipos;
    }

    /**
     * Convierte un objeto PagoDTO a un objeto Pago.
     * @param pagoDTO Objeto PagoDTO que se desea convertir.
     * @return Objeto Pago resultante de la conversión.
     */
    private Pago convertir(PagoDTO pagoDTO) {
        try {
            Pago pago = new Pago();
            if(pagoDTO.getId()!=null){
               pago.setId(pagoDTO.getId()); 
            }
            pago.setMonto(pagoDTO.getMonto());
            pago.setFechaHora(pagoDTO.getFechaHora());
            pago.setComprobante(pagoDTO.getComprobante());
            if (pagoDTO.getAbonos() != null) {
                List<Abono> abonos = pagoDTO.getAbonos().stream()
                        .map(AbonoDTO::convertir)
                        .collect(Collectors.toList());
                pago.setAbonos(abonos);
            }
            List<PagosEstatus> p=pagoEstatusDAO.obtenerEstatusPagosPorPago(pago);
            pago.setPagosEstatus(p);
            pago.setTipo(TiposDTO.convertir(pagoDTO.getTipo()));
            pago.setBeneficiario(beneficiarioDAO.buscarBeneficiarioPorId(id));
            if (pagoDTO.getCuentas() != null) {
                List<CuentaBancaria> cuentasBancarias = pagoDTO.getCuentas().stream()
                        .map(CuentaBancariaDTO::convertir)
                        .collect(Collectors.toList());
                pago.setCuentaBancaria(cuentasBancarias.getFirst());
            }
            return pago;
        } catch (ExcepcionDAO ex) {
            Logger.getLogger(PagoNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public PagosEstatus convertirEPDAO(PagosEstatusDTO pagos){
        PagosEstatus estatus=new PagosEstatus();
        estatus=pagoEstatusDAO.buscarPagosEstatusPorId(id);
        
        return estatus;
    }
    
    /**
     * Convierte un objeto Pago a un objeto PagoDTO.
     * @param pago Objeto Pago que se desea convertir.
     * @return Objeto PagoDTO resultante de la conversión.
     */
    public PagoDTO convertir(Pago pago) {
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
        pagoDTO.setBeneficiario(convertir(pago.getBeneficiario()));

        // Convertir cuentas bancarias si existe una
        if (pago.getCuentaBancaria() != null) {
            CuentaBancariaDTO cuentaDTO = convertir(pago.getCuentaBancaria());
            pagoDTO.setCuentas(Collections.singletonList(cuentaDTO));   
    }
        return pagoDTO;
    }
    

    /**
        * Convierte una lista de objetos Pago a una lista de objetos PagoDTO.
        * 
        * @param pagos Lista de objetos Pago que se desea convertir.
        * @return Lista de objetos PagoDTO resultante de la conversión.
        */
       public static List<PagoDTO> convertirDAO(List<Pago> pagos) {
           return pagos.stream()
                       .map(PagoDTO::convertir)
                       .collect(Collectors.toList());
       }
    
    /**
        * Convierte una lista de objetos PagoDTO a una lista de objetos Pago.
        * 
        * @param pagosDTO Lista de objetos PagoDTO que se desea convertir.
        * @return Lista de objetos Pago resultante de la conversión.
        */
       public static List<Pago> convertirDTO(List<PagoDTO> pagosDTO) {
           if (pagosDTO == null) {
               return Collections.emptyList(); // Return an empty list if pagosDTO is null
           }

           return pagosDTO.stream()
                          .map(PagoDTO::convertir)
                          .collect(Collectors.toList());
       }

    
    /**
    * Convierte un objeto CuentaBancariaDTO a un objeto CuentaBancaria.
    * @param cuentaBancariaDTO Objeto CuentaBancariaDTO que se desea convertir.
    * @return Objeto CuentaBancaria resultante de la conversión.
    */
   private CuentaBancaria convertirDAO(CuentaBancariaDTO cuentaBancariaDTO) {
       CuentaBancaria cuentaBancaria = new CuentaBancaria();
       cuentaBancaria.setId(cuentaBancariaDTO.getId());
       cuentaBancaria.setNumeroCuenta(cuentaBancariaDTO.getNumeroCuenta());
       cuentaBancaria.setBanco(cuentaBancariaDTO.getBanco());
       cuentaBancaria.setClave(cuentaBancariaDTO.getClave());
       cuentaBancaria.setEliminada(cuentaBancariaDTO.getEliminada());
       cuentaBancaria.setPagos(convertirDTO(cuentaBancariaDTO.getPagos()));
       BeneficiarioDTO beneficiario=cuentaBancariaDTO.getBeneficiario();
       Beneficiario b=convertir(beneficiario);
       cuentaBancaria.setBeneficiario(b);
       return cuentaBancaria;
   }

   /**
    * Convierte un objeto CuentaBancaria a un objeto CuentaBancariaDTO.
    * @param cuentaBancaria Objeto CuentaBancaria que se desea convertir.
    * @return Objeto CuentaBancariaDTO resultante de la conversión.
    */
   private CuentaBancariaDTO convertirDTO(CuentaBancaria cuentaBancaria) {
       CuentaBancariaDTO cuentaBancariaDTO = new CuentaBancariaDTO();
       cuentaBancariaDTO.setId(cuentaBancaria.getId());
       cuentaBancariaDTO.setNumeroCuenta(cuentaBancaria.getNumeroCuenta());
       cuentaBancariaDTO.setBanco(cuentaBancaria.getBanco());
       cuentaBancariaDTO.setClave(cuentaBancaria.getClave());
       cuentaBancariaDTO.setEliminada(cuentaBancaria.getEliminada());
       cuentaBancariaDTO.setPagos(convertirDAO(cuentaBancaria.getPagos()));
       cuentaBancariaDTO.setBeneficiario(convertir(cuentaBancaria.getBeneficiario()));
      
       return cuentaBancariaDTO;
   }
   
   /**
     * Convierte un objeto BeneficiarioDTO a un objeto Beneficiario.
     * 
     * @param beneficiarioDTO Objeto BeneficiarioDTO que se desea convertir.
     * @return Objeto Beneficiario resultante de la conversión.
     */
    private Beneficiario convertir(BeneficiarioDTO beneficiarioDTO) {
            
        Beneficiario beneficiario = new Beneficiario();
        beneficiario.setId(beneficiarioDTO.getId());
        beneficiario.setNombre(NombreDTO.convertir(beneficiarioDTO.getNombre()));
        beneficiario.setClaveContrato(beneficiarioDTO.getClaveContrato());
        beneficiario.setContraseña(beneficiarioDTO.getContraseña());
        beneficiario.setSaldo(beneficiarioDTO.getSaldo());
        beneficiario.setUsuario(beneficiarioDTO.getUsuario());
        beneficiario.setPagos(convertirDTO(beneficiarioDTO.getPagos()));
        beneficiario.setCuentasBancarias(convertirCuentasBancariasDTO(beneficiarioDTO.getCuentasBancarias()));
        return beneficiario;
    }
   
   
   
   /**
     * Convierte un objeto Beneficiario a un objeto BeneficiarioDTO.
     * 
     * @param beneficiario Objeto Beneficiario que se desea convertir.
     * @return Objeto BeneficiarioDTO resultante de la conversión.
     */
    private BeneficiarioDTO convertir(Beneficiario beneficiario) {
        BeneficiarioDTO beneficiarioDTO = new BeneficiarioDTO();
        beneficiarioDTO.setId(beneficiario.getId());
        beneficiarioDTO.setNombre(NombreDTO.convertir(beneficiario.getNombre()));
        beneficiarioDTO.setClaveContrato(beneficiario.getClaveContrato());
        beneficiarioDTO.setContraseña(beneficiario.getContraseña());
        beneficiarioDTO.setSaldo(beneficiario.getSaldo());
        beneficiarioDTO.setUsuario(beneficiario.getUsuario());
        beneficiarioDTO.setPagos(convertirDAO(beneficiario.getPagos()));
        beneficiarioDTO.setCuentasBancarias(convertirCuentasBancarias(beneficiario.getCuentasBancarias()));
        return beneficiarioDTO;
    }
   
   /**
     * Convierte una lista de objetos Beneficiario a una lista de objetos BeneficiarioDTO.
     * 
     * @param beneficiarios Lista de objetos Beneficiario que se desea convertir.
     * @return Lista de objetos BeneficiarioDTO resultante de la conversión.
     */
    private List<BeneficiarioDTO> convertirBeneficiarios(List<Beneficiario> beneficiarios) {
        return beneficiarios.stream().map(this::convertir).collect(Collectors.toList());
    }

   /**
    * Convierte una lista de objetos CuentaBancaria a una lista de objetos CuentaBancariaDTO.
    * 
    * @param cuentasBancarias Lista de objetos CuentaBancaria que se desea convertir.
    * @return Lista de objetos CuentaBancariaDTO resultante de la conversión.
    */
   private static List<CuentaBancariaDTO> convertirCuentasBancarias(List<CuentaBancaria> cuentasBancarias) {
       if (cuentasBancarias != null)
       return cuentasBancarias.stream()
               .map(CuentaBancariaDTO::convertir)
               .collect(Collectors.toList());
       else return null;
   }
   
   /**
    * Convierte una lista de objetos CuentaBancariaDTO a una lista de objetos CuentaBancaria.
    * 
    * @param cuentasBancariasDTO Lista de objetos CuentaBancariaDTO que se desea convertir.
    * @return Lista de objetos CuentaBancaria resultante de la conversión.
    */
   private static List<CuentaBancaria> convertirCuentasBancariasDTO(List<CuentaBancariaDTO> cuentasBancariasDTO) {
              if (cuentasBancariasDTO != null)
       return cuentasBancariasDTO.stream()
               .map(CuentaBancariaDTO::convertir)
               .collect(Collectors.toList());
                     else return null;
   }
   
   private List<TiposDTO> convertir(List<Tipos> tipos) {
        List<TiposDTO> tiposDTOList = new ArrayList<>();
        for (Tipos tipo : tipos) {
            TiposDTO dto = new TiposDTO();
            dto.setId(tipo.getId());
            dto.setNombre(tipo.getNombre());
            dto.setNumeroParcialidades(tipo.getNumeroParcialidades());
            if(tipo.getPagos().isEmpty()){

            }else{
                dto.setPagos(convertirDAO(tipo.getPagos()));
            }
            tiposDTOList.add(dto);
        }
        return tiposDTOList;
    }

    public List<PagosEstatusDTO> obtenerPagosEstatusParaPagos(List<PagoDTO> pagos) {
        List<PagosEstatusDTO> pagosEstatus = new ArrayList<>();

//        for (PagoDTO pago : pagos) {
//            PagosEstatusDTO pagoEstatus = new PagosEstatusDTO();
//            pagoEstatus.setId(obtenerEstatusParaPago(pago).);
//            pagoEstatus.setEstatus(obtenerEstatusParaPago(pago));
//            pagoEstatus.setPago(pago);
//            pagosEstatus.add(pagoEstatus);
//            
//        }

        return pagosEstatus;
    }

    @Override
    public List<PagosEstatusDTO> obtenerPagosEstatusPorBeneficiario(long id){
        List<PagosEstatusDTO> pagosEstatus=convertirPE(pagoEstatusDAO.obtenerPagosEstatusPorBeneficiario(id));
        return pagosEstatus;
    }
    
    
    
   private EstatusDTO obtenerEstatusParaPago(PagoDTO pago) throws ExcepcionDAO {
        PagosEstatus pagosEstatus = (PagosEstatus) pagoEstatusDAO.obtenerEstatusPagosPorPago(pagoDAO.buscarPagoPorId(pago.getId()));

        if (pagosEstatus != null) {
            return EstatusDTO.convertir(pagosEstatus.getEstatus());
        } else {
            return null;
        }
    }
   
    /**
     *
     * @param pagoDTO
     * @param estatusDTO
     * @throws ExcepcionDAO
     */
    @Override
    public void guardarPagoConEstatus(PagoDTO pagoDTO, EstatusDTO estatusDTO) throws ExcepcionDAO {
        PagosEstatus pagosEstatus = new PagosEstatus();
        pagosEstatus.setPago(pagoDAO.buscarPagoPorId(pagoDTO.getId()));
        pagosEstatus.setEstatus(estatusDAO.buscarEstatusPorId(estatusDTO.getId()));
        pagosEstatus.setFechaHora(LocalDateTime.now());
        pagosEstatus.setMensaje("El pago ha sido creado");

        pagoEstatusDAO.guardarPagosEstatus(pagosEstatus);
    }

    
    private List<EstatusDTO> convertirEstatus(List<Estatus> estatus){
        List<EstatusDTO> estatua = new ArrayList<>();

        for (Estatus estatu : estatus) {
            EstatusDTO status = new EstatusDTO();
            status.setId(estatu.getId());
            status.setNombre(estatu.getNombre());
            if(estatus!=null){
                status.setPagos(obtenerPagosEstatusPorEstatus(estatu.getNombre()));
            }
            estatua.add(status);
        }

        return estatua;
    }
    
    private List<PagosEstatusDTO> convertirPE(List<PagosEstatus> pagosEstatus) {
        if (pagosEstatus == null) {
            System.out.println("Es null");
            return Collections.emptyList();
        }

        System.out.println("Número de PagosEstatus recibidos para conversión: " + pagosEstatus.size());

        List<PagosEstatusDTO> estatusDTO = pagosEstatus.stream()
                .map(pe -> {
                    PagosEstatusDTO peDTO = new PagosEstatusDTO();
                    peDTO.setId(pe.getId());
                    peDTO.setEstatus(EstatusDTO.convertir(pe.getEstatus()));
                    peDTO.setPago(convertir(pe.getPago()));
                    peDTO.setMensaje(pe.getMensaje());
                    System.out.println("Convertido PagosEstatus a PagosEstatusDTO: " + peDTO);
                    return peDTO;
                })
                .collect(Collectors.toList());

        System.out.println("Número de PagosEstatusDTO convertidos: " + estatusDTO.size());
        return estatusDTO;
    }
    
    @Override
    public List<EstatusDTO> obtenerEstatus(){
        
        return convertirEstatus(estatusDAO.obtenerTodosLosEstatus());
    }
    
    
    public List<PagoDTO> obtenerPagosEstatusPorEstatus(String nombre){
        return convertirDAO(pagoDAO.obtenerPagosEstatusPorEstatus(nombre));
    }
    
    public List<Pago> obtenerPagosEstatusPorEstatusDAO(String nombre){
        return pagoDAO.obtenerPagosEstatusPorEstatus(nombre);
    }
    
    @Override
    public void agregarAbono(AbonoDTO abonoDTO, PagoDTO pago){
        Abono abono=new Abono();
        abono.setMonto(abonoDTO.getMonto());
        abono.setPago(pagoDAO.buscarPagoPorId(pago.getId()));
        abonoDAO.guardarAbono(abono);
    }
    
    public List<AbonoDTO> obtenerAbonosPorBeneficiario(long id){
        List<Abono> abonos=abonoDAO.obtenerAbonosPorBeneficiario(id);
        List<AbonoDTO> abonosDTO=convertirAbonosADTO(abonos);
        return abonosDTO;
    }
    
    public List<AbonoDTO> convertirAbonosADTO(List<Abono> abonos){
         List<AbonoDTO> abonosDTO= new ArrayList<>();
        for(Abono abono:abonos){
            AbonoDTO abonoDTO=new AbonoDTO();
            abonoDTO.setFechaHora(abono.getFechaHora());
            abonoDTO.setId(abono.getId());
            abonoDTO.setMonto(abono.getMonto());
            abonoDTO.setPagoDTO(convertir(abono.getPago()));
            abonosDTO.add(abonoDTO);
        }
        return abonosDTO;
    }
    
    @Override
    public void editarAbono(AbonoDTO abonoDTO,PagoDTO pago){
        Abono abono= abonoDAO.buscarAbonoPorId(abonoDTO.getId());
        abono.setMonto(abonoDTO.getMonto());
        abono.setPago(pagoDAO.buscarPagoPorId(pago.getId()));
        abono.setFechaHora(LocalDateTime.now());
        abonoDAO.actualizarAbono(abono);
        
    }
    
    @Override
    public void eliminarAbono(AbonoDTO abonoDTO){
        Abono abono= abonoDAO.buscarAbonoPorId(abonoDTO.getId());
        abonoDAO.eliminarAbono(abono);
    }
    
    @Override
    public AbonoDTO buscarAbonoPorID(long id){
        Abono abono=abonoDAO.buscarAbonoPorId(id);
        AbonoDTO abonoDTO = new AbonoDTO();
        abonoDTO.setFechaHora(abono.getFechaHora());
        abonoDTO.setId(abono.getId());
        abonoDTO.setMonto(abono.getMonto());
        abonoDTO.setPagoDTO(convertir(abono.getPago()));
        return abonoDTO;
    }
    
    @Override
    public List<EstatusDTO> obtenerTodosLosEstatus(){
        List<Estatus> estatus=estatusDAO.obtenerTodosLosEstatus();
        List<EstatusDTO> estatusDTO=new ArrayList<>();
        for(Estatus estatu:estatus){
            EstatusDTO estatuDTO=new EstatusDTO();
            estatuDTO.setId(estatu.getId());
            estatuDTO.setNombre(estatu.getNombre());
            estatusDTO.add(estatuDTO);
        }
        return estatusDTO;
    }
    
    @Override
    public EstatusDTO obtenerEstatuPorId(long id){
        Estatus estatu=estatusDAO.buscarEstatusPorId(id);
        EstatusDTO estatusDTO=new EstatusDTO();
        estatusDTO.setId(estatu.getId());
        estatusDTO.setNombre(estatusDTO.getNombre());
        if(estatu.getPagosEstatus()!=null){
        List<PagoDTO> pagos=new ArrayList<>();
        pagos.add(convertir(estatu.getPagosEstatus().get(0).getPago()));
        estatusDTO.setPagos(pagos);  
        }
        return estatusDTO;
    }
}
