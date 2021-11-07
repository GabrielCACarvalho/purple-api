package br.com.purple.api.dto.produto;

import br.com.purple.api.core.entity.enumerator.Tamanho;
import br.com.purple.api.core.entity.enumerator.Categoria;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class FiltroListarProdutos {

    private Integer idTipoVestimenta;
    private Categoria categoria;
    private Integer idMarca;
    private Integer idPromocao;
    private String cor;
    private Tamanho tamanho;
    private BigDecimal precoMin;
    private BigDecimal precoMax;
}
