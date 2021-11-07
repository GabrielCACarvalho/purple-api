package br.com.purple.api.dto.produto;

import br.com.purple.api.core.entity.enumerator.Tamanho;
import lombok.Data;

@Data
public class EntradaEstoque {

    private Integer idProduto;
    private Integer quantidade;
    private Tamanho tamanho;
}
