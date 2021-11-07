package br.com.purple.api.converter.marca;

import br.com.purple.api.converter.Converter;
import br.com.purple.api.core.entity.model.Marca;
import br.com.purple.api.dto.marca.EntradaMarcaDTO;

public class EntradaDTOMarcaConverter implements Converter<EntradaMarcaDTO, Marca> {

    @Override
    public Marca from(EntradaMarcaDTO entradaMarcaDTO) {
        Marca marca = new Marca();

        marca.setNome(entradaMarcaDTO.getNome());

        return marca;
    }
}
