package com.gmail.gabrielcacarvalho.RestApi.converter.tipovestimenta;

import com.gmail.gabrielcacarvalho.RestApi.converter.Converter;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.TipoVestimenta;
import com.gmail.gabrielcacarvalho.RestApi.dto.tipovestimenta.EntradaTipoVestimentaDTO;

public class EntradaDTOTipoVestimentaConverter implements Converter<EntradaTipoVestimentaDTO, TipoVestimenta> {

    @Override
    public TipoVestimenta from(EntradaTipoVestimentaDTO entradaTipoVestimentaDTO) {
        TipoVestimenta tipoVestimenta = new TipoVestimenta();

        tipoVestimenta.setNome(entradaTipoVestimentaDTO.getNome());

        return tipoVestimenta;
    }
}
