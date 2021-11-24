package br.com.purple.api.dto.tipovestimenta;

import br.com.purple.api.dto.enumerator.CategoriaDTO;
import lombok.Data;

@Data
public class TipoVestimentaDTO {

    private Integer id;
    private String nome;
    private CategoriaDTO categoriaDTO;
}
