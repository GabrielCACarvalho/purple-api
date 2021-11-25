package br.com.purple.api.usecase;

import br.com.purple.api.converter.Converter;
import br.com.purple.api.converter.marca.AlteraDTOMarcaConverter;
import br.com.purple.api.converter.marca.EntradaDTOMarcaConverter;
import br.com.purple.api.converter.marca.MarcaMarcaDTOConverter;
import br.com.purple.api.core.entity.model.Marca;
import br.com.purple.api.dto.marca.AlteraMarcaDTO;
import br.com.purple.api.dto.marca.EntradaMarcaDTO;
import br.com.purple.api.dto.marca.MarcaDTO;
import br.com.purple.api.repositories.MarcaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MarcaUseCase {

    private MarcaRepository marcaRepository;

    private final Converter<Marca, MarcaDTO> marcaMarcaDTOConverter = new MarcaMarcaDTOConverter();

    private final Converter<EntradaMarcaDTO, Marca> entradaDTOMarcaConverter = new EntradaDTOMarcaConverter();

    private final Converter<AlteraMarcaDTO, Marca> alteraDTOMarcaConverter = new AlteraDTOMarcaConverter();

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

    public MarcaDTO obterMarca(Integer idMarca) {
        return marcaMarcaDTOConverter.from(marcaRepository.getById(idMarca));
    }
}
