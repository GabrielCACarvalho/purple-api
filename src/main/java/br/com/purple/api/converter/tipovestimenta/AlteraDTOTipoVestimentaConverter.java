package br.com.purple.api.converter.tipovestimenta;

import br.com.purple.api.converter.Converter;
import br.com.purple.api.core.entity.model.TipoVestimenta;
import br.com.purple.api.dto.tipovestimenta.AlteraTipoVestimentaDTO;

public class AlteraDTOTipoVestimentaConverter implements Converter<AlteraTipoVestimentaDTO, TipoVestimenta> {

    @Override
    public TipoVestimenta from(AlteraTipoVestimentaDTO alteraTipoVestimentaDTO) {
        TipoVestimenta tipoVestimenta = new TipoVestimenta();

        tipoVestimenta.setId(alteraTipoVestimentaDTO.getId());
        tipoVestimenta.setNome(alteraTipoVestimentaDTO.getNome());

        return tipoVestimenta;
    }
}
