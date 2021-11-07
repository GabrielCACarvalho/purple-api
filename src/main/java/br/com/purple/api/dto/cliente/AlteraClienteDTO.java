package br.com.purple.api.dto.cliente;

import br.com.purple.api.core.entity.enumerator.Sexo;
import lombok.Data;

@Data
public class AlteraClienteDTO {

    private Integer id;
    private Sexo sexo;
    private String nome;
    private String cpf;
    private String celular;
}
