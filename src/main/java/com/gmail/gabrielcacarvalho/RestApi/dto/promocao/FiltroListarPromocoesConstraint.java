package com.gmail.gabrielcacarvalho.RestApi.dto.promocao;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FiltroListarPromocoesConstraint implements ConstraintValidator <FiltroListarPromocoesValido, FiltroListarPromocoes > {
    @Override
    public boolean isValid(FiltroListarPromocoes filtro, ConstraintValidatorContext constraintValidatorContext) {

        constraintValidatorContext.disableDefaultConstraintViolation();

        //if (filtro == null)

        return false;
    }
}
