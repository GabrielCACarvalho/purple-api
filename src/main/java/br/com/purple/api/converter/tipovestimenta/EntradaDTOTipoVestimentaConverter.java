package br.com.purple.api.converter.tipovestimenta;

import br.com.purple.api.converter.Converter;
import br.com.purple.api.core.entity.model.TipoVestimenta;
import br.com.purple.api.dto.tipovestimenta.EntradaTipoVestimentaDTO;

public class EntradaDTOTipoVestimentaConverter implements Converter<EntradaTipoVestimentaDTO, TipoVestimenta> {

    @Override
    public TipoVestimenta from(EntradaTipoVestimentaDTO entradaTipoVestimentaDTO) {
        TipoVestimenta tipoVestimenta = new TipoVestimenta();

        tipoVestimenta.setNome(entradaTipoVestimentaDTO.getNome());

        return tipoVestimenta;
    }
}
