package br.com.purple.api.usecase;

import br.com.purple.api.core.entity.model.TipoVestimenta;
import br.com.purple.api.converter.Converter;
import br.com.purple.api.converter.tipovestimenta.AlteraDTOTipoVestimentaConverter;
import br.com.purple.api.converter.tipovestimenta.EntradaDTOTipoVestimentaConverter;
import br.com.purple.api.converter.tipovestimenta.TipoVestimentaTipoVestimentaDTOConverter;
import br.com.purple.api.dto.tipovestimenta.AlteraTipoVestimentaDTO;
import br.com.purple.api.dto.tipovestimenta.EntradaTipoVestimentaDTO;
import br.com.purple.api.dto.tipovestimenta.TipoVestimentaDTO;
import br.com.purple.api.repositories.TipoVestimentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TipoVestimentaUseCase {

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
