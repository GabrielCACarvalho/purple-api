package br.com.purple.api.usecase;

import br.com.purple.api.converter.Converter;
import br.com.purple.api.converter.promocao.AlteraDTOPromocaoConverter;
import br.com.purple.api.converter.promocao.EntradaDTOPromocaoConverter;
import br.com.purple.api.converter.promocao.PromocaoPromocaoDTOConverter;
import br.com.purple.api.core.entity.model.Promocao;
import br.com.purple.api.dto.promocao.AlteraPromocaoDTO;
import br.com.purple.api.dto.promocao.EntradaPromocaoDTO;
import br.com.purple.api.dto.promocao.FiltroListarPromocoes;
import br.com.purple.api.dto.promocao.PromocaoDTO;
import br.com.purple.api.repositories.PromocaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PromocaoUseCase {

    private PromocaoRepository promocaoRepository;

    private final Converter<EntradaPromocaoDTO, Promocao> entradaDTOPromocaoConverter = new EntradaDTOPromocaoConverter();

    private final Converter<Promocao, PromocaoDTO> promocaoPromocaoDTOConverter = new PromocaoPromocaoDTOConverter();

    private final Converter<AlteraPromocaoDTO, Promocao> alteraDTOPromocaoConverter = new AlteraDTOPromocaoConverter();

    public Page<PromocaoDTO> obterPromocoes(Pageable pageable, FiltroListarPromocoes filtros){
        return promocaoPromocaoDTOConverter.from(promocaoRepository.findAll(criarFiltrosBuscarLista(filtros), pageable));
    }

    public PromocaoDTO criaPromocao(EntradaPromocaoDTO entradaPromocaoDTO) {
        return promocaoPromocaoDTOConverter.from(promocaoRepository.save(entradaDTOPromocaoConverter.from(entradaPromocaoDTO)));
    }

    public PromocaoDTO alteraPromocao(AlteraPromocaoDTO alteraPromocaoDTO) {
        return promocaoPromocaoDTOConverter.from(promocaoRepository.save(alteraDTOPromocaoConverter.from(alteraPromocaoDTO)));
    }

    public void deletaPromocao(Integer idPromocao) {
        promocaoRepository.delete(promocaoRepository.getById(idPromocao));
    }

    private Specification<Promocao> criarFiltrosBuscarLista(FiltroListarPromocoes filtroListarPromocoes) {

        FiltroListarPromocoes filtros = Optional.ofNullable(filtroListarPromocoes).orElse(new FiltroListarPromocoes());

        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtros.getDataFim() != null && filtros.getDataInicio() != null)
                predicates.add(builder.between(root.get("dataInicio"),
                        Date.valueOf(filtros.getDataInicio()),
                        Date.valueOf(filtros.getDataFim())));

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public PromocaoDTO obterPromocao(Integer idPromocao) {

        return promocaoPromocaoDTOConverter.from(promocaoRepository.getById(idPromocao));
    }
}
