package br.com.purple.api.dto.promocao;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class EntradaPromocaoDTO {

    private String descricao;
    private Date dataInicio;
    private Date dataFim;
    private BigDecimal porcentagemDesconto;
}
