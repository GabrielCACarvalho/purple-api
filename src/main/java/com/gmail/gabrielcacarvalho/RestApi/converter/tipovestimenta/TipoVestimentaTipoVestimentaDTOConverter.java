package com.gmail.gabrielcacarvalho.RestApi.converter.tipovestimenta;

import com.gmail.gabrielcacarvalho.RestApi.converter.Converter;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.TipoVestimenta;
import com.gmail.gabrielcacarvalho.RestApi.dto.tipovestimenta.TipoVestimentaDTO;

public class TipoVestimentaTipoVestimentaDTOConverter implements Converter<TipoVestimenta, TipoVestimentaDTO> {

    @Override
    public TipoVestimentaDTO from(TipoVestimenta tipoVestimenta) {
        TipoVestimentaDTO tipoVestimentaDTO = new TipoVestimentaDTO();

        tipoVestimentaDTO.setId(tipoVestimenta.getId());
        tipoVestimentaDTO.setNome(tipoVestimenta.getNome());

        return tipoVestimentaDTO;
    }
}
