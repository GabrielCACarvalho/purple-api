package br.com.purple.api.converter.endereco;

import br.com.purple.api.converter.Converter;
import br.com.purple.api.dto.endereco.EntradaEnderecoDTO;
import br.com.purple.api.core.entity.model.Endereco;

public class EntradaDTOEnderecoConverter implements Converter<EntradaEnderecoDTO, Endereco> {

    @Override
    public Endereco from(EntradaEnderecoDTO entradaEnderecoDTO) {
        Endereco endereco = new Endereco();

        endereco.setBairro(entradaEnderecoDTO.getBairro());
        endereco.setCep(entradaEnderecoDTO.getCep());
        endereco.setCidade(entradaEnderecoDTO.getCidade());
        endereco.setEstado(entradaEnderecoDTO.getEstado());
        endereco.setNumero(entradaEnderecoDTO.getNumero());
        endereco.setRua(entradaEnderecoDTO.getRua());

        return endereco;
    }
}
