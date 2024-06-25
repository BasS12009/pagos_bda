package entidades;

import entidades.Abono;
import entidades.Beneficiario;
import entidades.CuentaBancaria;
import entidades.PagosEstatus;
import entidades.Tipos;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-06-25T03:42:19", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Pago.class)
public class Pago_ { 

    public static volatile SingularAttribute<Pago, Tipos> tipo;
    public static volatile SingularAttribute<Pago, CuentaBancaria> cuentaBancaria;
    public static volatile SingularAttribute<Pago, BigDecimal> monto;
    public static volatile SingularAttribute<Pago, LocalDateTime> fechaHora;
    public static volatile ListAttribute<Pago, PagosEstatus> pagosEstatus;
    public static volatile SingularAttribute<Pago, String> comprobante;
    public static volatile SingularAttribute<Pago, Long> id;
    public static volatile ListAttribute<Pago, Abono> abonos;
    public static volatile SingularAttribute<Pago, Beneficiario> beneficiario;

}