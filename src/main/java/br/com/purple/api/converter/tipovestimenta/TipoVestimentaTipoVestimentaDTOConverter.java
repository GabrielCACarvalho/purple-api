package br.com.purple.api.converter.tipovestimenta;

import br.com.purple.api.converter.Converter;
import br.com.purple.api.core.entity.model.TipoVestimenta;
import br.com.purple.api.dto.tipovestimenta.TipoVestimentaDTO;

public class TipoVestimentaTipoVestimentaDTOConverter implements Converter<TipoVestimenta, TipoVestimentaDTO> {

    @Override
    public TipoVestimentaDTO from(TipoVestimenta tipoVestimenta) {
        TipoVestimentaDTO tipoVestimentaDTO = new TipoVestimentaDTO();

        tipoVestimentaDTO.setId(tipoVestimenta.getId());
        tipoVestimentaDTO.setNome(tipoVestimenta.getNome());

        return tipoVestimentaDTO;
    }
}
