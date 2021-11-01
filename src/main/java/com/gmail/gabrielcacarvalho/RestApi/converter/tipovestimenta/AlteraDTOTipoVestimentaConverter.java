package com.gmail.gabrielcacarvalho.RestApi.converter.tipovestimenta;

import com.gmail.gabrielcacarvalho.RestApi.converter.Converter;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.TipoVestimenta;
import com.gmail.gabrielcacarvalho.RestApi.dto.tipovestimenta.AlteraTipoVestimentaDTO;

public class AlteraDTOTipoVestimentaConverter implements Converter<AlteraTipoVestimentaDTO, TipoVestimenta> {

    @Override
    public TipoVestimenta from(AlteraTipoVestimentaDTO alteraTipoVestimentaDTO) {
        TipoVestimenta tipoVestimenta = new TipoVestimenta();

        tipoVestimenta.setId(alteraTipoVestimentaDTO.getId());
        tipoVestimenta.setNome(alteraTipoVestimentaDTO.getNome());

        return tipoVestimenta;
    }
}
