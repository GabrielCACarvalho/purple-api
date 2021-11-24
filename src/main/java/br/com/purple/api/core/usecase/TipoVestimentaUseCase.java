package br.com.purple.api.core.usecase;

import br.com.purple.api.core.entity.enumerator.Categoria;
import br.com.purple.api.core.entity.model.TipoVestimenta;
import br.com.purple.api.converter.Converter;
import br.com.purple.api.converter.tipovestimenta.AlteraDTOTipoVestimentaConverter;
import br.com.purple.api.converter.tipovestimenta.EntradaDTOTipoVestimentaConverter;
import br.com.purple.api.converter.tipovestimenta.TipoVestimentaTipoVestimentaDTOConverter;
import br.com.purple.api.dto.enumerator.CategoriaDTO;
import br.com.purple.api.dto.tipovestimenta.AlteraTipoVestimentaDTO;
import br.com.purple.api.dto.tipovestimenta.EntradaTipoVestimentaDTO;
import br.com.purple.api.dto.tipovestimenta.TipoVestimentaDTO;
import br.com.purple.api.repositories.TipoVestimentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipoVestimentaUseCase {

    @Autowired
    private TipoVestimentaRepository tipoVestimentaRepository;

    private Converter<EntradaTipoVestimentaDTO, TipoVestimenta> entradaDTOTipoVestimentaConverter = new EntradaDTOTipoVestimentaConverter();

    private Converter<TipoVestimenta, TipoVestimentaDTO> tipoVestimentaTipoVestimentaDTOConverter = new TipoVestimentaTipoVestimentaDTOConverter();

    private Converter<AlteraTipoVestimentaDTO, TipoVestimenta> alteraTipoVestimentaDTOTipoVestimentaConverter = new AlteraDTOTipoVestimentaConverter();

    public Page<TipoVestimentaDTO> obterTiposVestimenta(Pageable pageable, List<CategoriaDTO> categoriaDTO){
        return tipoVestimentaTipoVestimentaDTOConverter.from(tipoVestimentaRepository.findAll(criarFiltroTipoVestimenta(categoriaDTO), pageable));
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

    public TipoVestimentaDTO obterTipoVestimenta(Integer idTipoVestimenta) {
        return tipoVestimentaTipoVestimentaDTOConverter.from(tipoVestimentaRepository.getById(idTipoVestimenta));
    }

    private Specification<TipoVestimenta> criarFiltroTipoVestimenta(List<CategoriaDTO> categoriaDTO) {
        return (root, query, builder)-> {
            List<Predicate> predicates = new ArrayList<>();

            if (categoriaDTO != null){
                List<Categoria> categorias = categoriaDTO.stream().map(catDTO -> Categoria.valueOf(catDTO.name())).collect(Collectors.toList());
                if (categorias.size() > 0)
                    predicates.add(root.get("categoria").in(categorias));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
