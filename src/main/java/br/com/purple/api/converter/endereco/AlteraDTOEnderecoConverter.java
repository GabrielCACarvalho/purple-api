package br.com.purple.api.converter.endereco;

import br.com.purple.api.dto.endereco.AlteraEnderecoDTO;
import br.com.purple.api.converter.Converter;
import br.com.purple.api.core.entity.model.Endereco;

public class AlteraDTOEnderecoConverter implements Converter<AlteraEnderecoDTO, Endereco> {

    @Override
    public Endereco from(AlteraEnderecoDTO alteraEnderecoDTO) {
        Endereco endereco = new Endereco();

        endereco.setCep(alteraEnderecoDTO.getCep());
        endereco.setRua(alteraEnderecoDTO.getRua());
        endereco.setNumero(alteraEnderecoDTO.getNumero());
        endereco.setEstado(alteraEnderecoDTO.getEstado());
        endereco.setCidade(alteraEnderecoDTO.getCidade());
        endereco.setBairro(alteraEnderecoDTO.getBairro());

        return endereco;
    }
}
