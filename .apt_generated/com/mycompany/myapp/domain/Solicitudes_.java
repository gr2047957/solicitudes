package com.mycompany.myapp.domain;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Solicitudes.class)
public abstract class Solicitudes_ {

	public static volatile SingularAttribute<Solicitudes, String> apellido;
	public static volatile SingularAttribute<Solicitudes, Long> id;
	public static volatile SingularAttribute<Solicitudes, LocalDate> fecha_Registro;
	public static volatile SingularAttribute<Solicitudes, String> id_solicitudes;
	public static volatile SingularAttribute<Solicitudes, String> nombre_corto;

}

