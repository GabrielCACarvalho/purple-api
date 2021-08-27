package com.gmail.gabrielcacarvalho.RestApi.dto.promocao;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FiltroListarPromocoesConstraint.class)
public @interface FiltroListarPromocoesValido {
}
