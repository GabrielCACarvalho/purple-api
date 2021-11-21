package br.com.purple.api.dto.produto;

import br.com.purple.api.dto.enumerator.CategoriaDTO;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProdutoDTO {

    private Integer id;
    private String nome;
    private String path;
    private String descricao;
    private Integer idMarca;
    private CategoriaDTO categoria;
    private ImagemDTO image;
    private BigDecimal valorUnitario;
    private BigDecimal valorUnitarioDesconto;
    private String cor;
    private Integer idPromocao;
    private Integer idTipoVestimenta;
}
