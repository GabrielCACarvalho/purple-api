package br.com.purple.api.dto.estoque;

import br.com.purple.api.core.entity.enumerator.Tamanho;
import lombok.Data;

@Data
public class SaidaEstoque {

    private Integer idProduto;
    private Integer quantidade;
    private Tamanho tamanho;
}
