package com.gmail.gabrielcacarvalho.RestApi.service;

import com.gmail.gabrielcacarvalho.RestApi.converter.Converter;
import com.gmail.gabrielcacarvalho.RestApi.converter.tipovestimenta.AlteraDTOTipoVestimentaConverter;
import com.gmail.gabrielcacarvalho.RestApi.converter.tipovestimenta.EntradaDTOTipoVestimentaConverter;
import com.gmail.gabrielcacarvalho.RestApi.converter.tipovestimenta.TipoVestimentaTipoVestimentaDTOConverter;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.TipoVestimenta;
import com.gmail.gabrielcacarvalho.RestApi.dto.tipovestimenta.AlteraTipoVestimentaDTO;
import com.gmail.gabrielcacarvalho.RestApi.dto.tipovestimenta.EntradaTipoVestimentaDTO;
import com.gmail.gabrielcacarvalho.RestApi.dto.tipovestimenta.TipoVestimentaDTO;
import com.gmail.gabrielcacarvalho.RestApi.repositories.TipoVestimentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TipoVestimentaService {

    @Autowired
    private TipoVestimentaRepository tipoVestimentaRepository;

    private Converter<EntradaTipoVestimentaDTO, TipoVestimenta> entradaDTOTipoVestimentaConverter = new EntradaDTOTipoVestimentaConverter();

    private Converter<TipoVestimenta, TipoVestimentaDTO> tipoVestimentaTipoVestimentaDTOConverter = new TipoVestimentaTipoVestimentaDTOConverter();

    private Converter<AlteraTipoVestimentaDTO, TipoVestimenta> alteraTipoVestimentaDTOTipoVestimentaConverter = new AlteraDTOTipoVestimentaConverter();

    public Page<TipoVestimentaDTO> obterTiposVestimenta(Pageable pageable){
        return tipoVestimentaTipoVestimentaDTOConverter.from(tipoVestimentaRepository.findAll(pageable));
    }

    public TipoVestimentaDTO criaTipoVestimenta(EntradaTipoVestimentaDTO entradaTipoVestimentaDTO){
        return tipoVestimentaTipoVestimentaDTOConverter.from(tipoVestimentaRepository.save(entradaDTOTipoVestimentaConverter.from(entradaTipoVestimentaDTO)));
    }

    public TipoVestimentaDTO alteraTipoVestimenta(AlteraTipoVestimentaDTO alteraTipoVestimentaDTO){
        return tipoVestimentaTipoVestimentaDTOConverter.from(tipoVestimentaRepository.save(alteraTipoVestimentaDTOTipoVestimentaConverter.from(alteraTipoVestimentaDTO)));
    }

    public void deletaTipoVestimenta(Integer idTipoVestimenta) {
        tipoVestimentaRepository.delete(tipoVestimentaRepository.getById(idTipoVestimenta));
    }
}
