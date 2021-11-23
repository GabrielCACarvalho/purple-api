package br.com.purple.api.dto.promocao;

import br.com.purple.api.validator.anotacao.FiltroListarPromocoesValido;
import lombok.Data;

import java.util.Date;

@Data
@FiltroListarPromocoesValido
public class FiltroListarPromocoes {

    private String dataInicio;
    private String dataFim;
}
