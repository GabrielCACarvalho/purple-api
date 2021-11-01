package com.gmail.gabrielcacarvalho.RestApi.converter.marca;

import com.gmail.gabrielcacarvalho.RestApi.converter.Converter;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Marca;
import com.gmail.gabrielcacarvalho.RestApi.dto.marca.MarcaDTO;

public class MarcaMarcaDTOConverter implements Converter<Marca, MarcaDTO> {
    @Override
    public MarcaDTO from(Marca marca) {
        MarcaDTO marcaDTO = new MarcaDTO();

        marcaDTO.setId(marca.getId());
        marcaDTO.setNome(marca.getNome());

        return marcaDTO;
    }
}
