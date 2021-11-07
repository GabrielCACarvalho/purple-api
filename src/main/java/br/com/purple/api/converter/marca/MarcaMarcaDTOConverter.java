package br.com.purple.api.converter.marca;

import br.com.purple.api.converter.Converter;
import br.com.purple.api.dto.marca.MarcaDTO;
import br.com.purple.api.core.entity.model.Marca;

public class MarcaMarcaDTOConverter implements Converter<Marca, MarcaDTO> {
    @Override
    public MarcaDTO from(Marca marca) {
        MarcaDTO marcaDTO = new MarcaDTO();

        marcaDTO.setId(marca.getId());
        marcaDTO.setNome(marca.getNome());

        return marcaDTO;
    }
}
