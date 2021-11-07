package br.com.purple.api.usecase;

import br.com.purple.api.converter.Converter;
import br.com.purple.api.converter.marca.MarcaMarcaDTOConverter;
import br.com.purple.api.dto.marca.MarcaDTO;
import br.com.purple.api.converter.marca.AlteraDTOMarcaConverter;
import br.com.purple.api.converter.marca.EntradaDTOMarcaConverter;
import br.com.purple.api.core.entity.model.Marca;
import br.com.purple.api.dto.marca.AlteraMarcaDTO;
import br.com.purple.api.dto.marca.EntradaMarcaDTO;
import br.com.purple.api.repositories.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MarcaUseCase {

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
