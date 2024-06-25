package entidades;

import entidades.Estatus;
import entidades.Pago;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-06-25T03:27:06", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(PagosEstatus.class)
public class PagosEstatus_ { 

    public static volatile SingularAttribute<PagosEstatus, Estatus> estatus;
    public static volatile SingularAttribute<PagosEstatus, LocalDateTime> fechaHora;
    public static volatile SingularAttribute<PagosEstatus, Long> id;
    public static volatile SingularAttribute<PagosEstatus, String> mensaje;
    public static volatile SingularAttribute<PagosEstatus, Pago> pago;

}