package br.com.purple.api.dto.produto;

import br.com.purple.api.dto.enumerator.CategoriaDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class FiltroListarProdutos {

    private List<Integer> idsTipoVestimenta;
    private List<CategoriaDTO> categorias;
    private Integer idMarca;
    private Integer idPromocao;
    private List<String> cores;
    private BigDecimal precoMin;
    private BigDecimal precoMax;
}
