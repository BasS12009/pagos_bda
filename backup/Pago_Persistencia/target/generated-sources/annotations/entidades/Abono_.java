package entidades;

import entidades.Pago;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-06-25T03:27:06", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Abono.class)
public class Abono_ { 

    public static volatile SingularAttribute<Abono, Double> monto;
    public static volatile SingularAttribute<Abono, LocalDateTime> fechaHora;
    public static volatile SingularAttribute<Abono, Long> id;
    public static volatile SingularAttribute<Abono, Pago> pago;

}