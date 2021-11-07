package br.com.purple.api.validator.anotacao;

import br.com.purple.api.validator.constraint.FiltroListarPromocoesConstraint;

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
