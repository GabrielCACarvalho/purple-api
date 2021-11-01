package com.gmail.gabrielcacarvalho.RestApi.converter.marca;

import com.gmail.gabrielcacarvalho.RestApi.converter.Converter;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Marca;
import com.gmail.gabrielcacarvalho.RestApi.dto.marca.EntradaMarcaDTO;

public class EntradaDTOMarcaConverter implements Converter<EntradaMarcaDTO, Marca> {

    @Override
    public Marca from(EntradaMarcaDTO entradaMarcaDTO) {
        Marca marca = new Marca();

        marca.setNome(entradaMarcaDTO.getNome());

        return marca;
    }
}
