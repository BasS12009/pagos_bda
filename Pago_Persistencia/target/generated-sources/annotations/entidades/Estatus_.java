package entidades;

import entidades.PagosEstatus;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-06-26T10:34:49", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Estatus.class)
public class Estatus_ { 

    public static volatile ListAttribute<Estatus, PagosEstatus> pagosEstatus;
    public static volatile SingularAttribute<Estatus, Long> id;
    public static volatile SingularAttribute<Estatus, String> nombre;

}