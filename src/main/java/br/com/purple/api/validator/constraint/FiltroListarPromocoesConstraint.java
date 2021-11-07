package br.com.purple.api.validator.constraint;

import br.com.purple.api.validator.anotacao.FiltroListarPromocoesValido;
import br.com.purple.api.dto.promocao.FiltroListarPromocoes;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FiltroListarPromocoesConstraint implements ConstraintValidator <FiltroListarPromocoesValido, FiltroListarPromocoes> {
    @Override
    public boolean isValid(FiltroListarPromocoes filtro, ConstraintValidatorContext constraintValidatorContext) {

        constraintValidatorContext.disableDefaultConstraintViolation();

        //if (filtro == null)

        return false;
    }
}
