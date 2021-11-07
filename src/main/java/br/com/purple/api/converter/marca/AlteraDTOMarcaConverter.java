package br.com.purple.api.converter.marca;

import br.com.purple.api.converter.Converter;
import br.com.purple.api.core.entity.model.Marca;
import br.com.purple.api.dto.marca.AlteraMarcaDTO;

public class AlteraDTOMarcaConverter implements Converter<AlteraMarcaDTO, Marca> {
    @Override
    public Marca from(AlteraMarcaDTO alteraMarcaDTO) {
        Marca marca = new Marca();

        marca.setId(alteraMarcaDTO.getId());
        marca.setNome(alteraMarcaDTO.getNome());

        return marca;
    }
}
