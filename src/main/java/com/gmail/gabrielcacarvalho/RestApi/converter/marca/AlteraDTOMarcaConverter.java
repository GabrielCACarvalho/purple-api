package com.gmail.gabrielcacarvalho.RestApi.converter.marca;

import com.gmail.gabrielcacarvalho.RestApi.converter.Converter;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Marca;
import com.gmail.gabrielcacarvalho.RestApi.dto.marca.AlteraMarcaDTO;

public class AlteraDTOMarcaConverter implements Converter<AlteraMarcaDTO, Marca> {
    @Override
    public Marca from(AlteraMarcaDTO alteraMarcaDTO) {
        Marca marca = new Marca();

        marca.setId(alteraMarcaDTO.getId());
        marca.setNome(alteraMarcaDTO.getNome());

        return marca;
    }
}
