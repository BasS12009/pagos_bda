package entidades;

import entidades.Pago;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-06-25T03:42:19", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Tipos.class)
public class Tipos_ { 

    public static volatile SingularAttribute<Tipos, Integer> numeroParcialidades;
    public static volatile SingularAttribute<Tipos, Long> id;
    public static volatile SingularAttribute<Tipos, String> nombre;
    public static volatile ListAttribute<Tipos, Pago> pagos;

}