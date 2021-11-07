package br.com.purple.api.dto.cliente;

import br.com.purple.api.core.entity.enumerator.Sexo;
import br.com.purple.api.dto.endereco.EnderecoDTO;
import lombok.Data;

import java.util.Date;

@Data
public class ClienteDTO {

    private Sexo sexo;
    private String nome;
    private String cpf;
    private String celular;
    private EnderecoDTO enderecoDTO;
    private Date dataCadastro;
}
