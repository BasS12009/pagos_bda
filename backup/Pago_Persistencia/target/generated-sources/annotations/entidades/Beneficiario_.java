package entidades;

import entidades.CuentaBancaria;
import entidades.Nombre;
import entidades.Pago;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-06-25T03:27:06", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Beneficiario.class)
public class Beneficiario_ { 

    public static volatile ListAttribute<Beneficiario, CuentaBancaria> cuentasBancarias;
    public static volatile SingularAttribute<Beneficiario, String> usuario;
    public static volatile SingularAttribute<Beneficiario, Long> id;
    public static volatile SingularAttribute<Beneficiario, Double> saldo;
    public static volatile SingularAttribute<Beneficiario, String> claveContrato;
    public static volatile SingularAttribute<Beneficiario, Nombre> nombre;
    public static volatile ListAttribute<Beneficiario, Pago> pagos;
    public static volatile SingularAttribute<Beneficiario, String> contraseña;

}