package entidades;

import entidades.Beneficiario;
import entidades.Pago;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-06-26T10:34:49", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(CuentaBancaria.class)
public class CuentaBancaria_ { 

    public static volatile SingularAttribute<CuentaBancaria, Boolean> eliminada;
    public static volatile SingularAttribute<CuentaBancaria, String> banco;
    public static volatile SingularAttribute<CuentaBancaria, String> numeroCuenta;
    public static volatile SingularAttribute<CuentaBancaria, Long> id;
    public static volatile ListAttribute<CuentaBancaria, Pago> pagos;
    public static volatile SingularAttribute<CuentaBancaria, String> clabe;
    public static volatile SingularAttribute<CuentaBancaria, Beneficiario> beneficiario;

}