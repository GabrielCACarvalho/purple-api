package com.gmail.gabrielcacarvalho.RestApi.service;

import com.gmail.gabrielcacarvalho.RestApi.converter.Converter;
import com.gmail.gabrielcacarvalho.RestApi.converter.marca.AlteraDTOMarcaConverter;
import com.gmail.gabrielcacarvalho.RestApi.converter.marca.EntradaDTOMarcaConverter;
import com.gmail.gabrielcacarvalho.RestApi.converter.marca.MarcaMarcaDTOConverter;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Marca;
import com.gmail.gabrielcacarvalho.RestApi.dto.marca.AlteraMarcaDTO;
import com.gmail.gabrielcacarvalho.RestApi.dto.marca.EntradaMarcaDTO;
import com.gmail.gabrielcacarvalho.RestApi.dto.marca.MarcaDTO;
import com.gmail.gabrielcacarvalho.RestApi.repositories.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    private Converter<Marca, MarcaDTO> marcaMarcaDTOConverter = new MarcaMarcaDTOConverter();

    private Converter<EntradaMarcaDTO, Marca> entradaDTOMarcaConverter = new EntradaDTOMarcaConverter();

    private Converter<AlteraMarcaDTO, Marca> alteraDTOMarcaConverter = new AlteraDTOMarcaConverter();

    public Page<MarcaDTO> obterMarcas(Pageable pageable){
        return marcaMarcaDTOConverter.from(marcaRepository.findAll(pageable));
    }

    public MarcaDTO criaMarca(EntradaMarcaDTO entradaMarcaDTO){
        return marcaMarcaDTOConverter.from(marcaRepository.save(entradaDTOMarcaConverter.from(entradaMarcaDTO)));
    }

    public MarcaDTO alteraMarca(AlteraMarcaDTO alteraMarcaDTO){
        return marcaMarcaDTOConverter.from(marcaRepository.save(alteraDTOMarcaConverter.from(alteraMarcaDTO)));
    }

    public void deletaMarca(Integer idMarca) {
        marcaRepository.delete(marcaRepository.getById(idMarca));
    }
}
