package br.com.purple.api.dto.produto;

import br.com.purple.api.dto.promocao.PromocaoDTO;
import br.com.purple.api.dto.enumerator.CategoriaDTO;
import br.com.purple.api.dto.marca.MarcaDTO;
import br.com.purple.api.dto.tipovestimenta.TipoVestimentaDTO;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProdutoDTO {

    private Integer id;
    private String nome;
    private String descricao;
    private MarcaDTO marca;
    private CategoriaDTO categoria;
    private ImagemDTO image;
    private BigDecimal valorUnitario;
    private String cor;
    private PromocaoDTO promocao;
    private TipoVestimentaDTO tipoVestimenta;
}
