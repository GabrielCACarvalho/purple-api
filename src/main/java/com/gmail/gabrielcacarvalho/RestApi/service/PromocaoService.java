package com.gmail.gabrielcacarvalho.RestApi.service;

import com.gmail.gabrielcacarvalho.RestApi.converter.Converter;
import com.gmail.gabrielcacarvalho.RestApi.converter.promocao.AlteraDTOPromocaoConverter;
import com.gmail.gabrielcacarvalho.RestApi.converter.promocao.EntredaDTOPromocaoConverter;
import com.gmail.gabrielcacarvalho.RestApi.converter.promocao.PromocaoPromocaoDTOConverter;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Promocao;
import com.gmail.gabrielcacarvalho.RestApi.dto.promocao.AlteraPromocaoDTO;
import com.gmail.gabrielcacarvalho.RestApi.dto.promocao.FiltroListarPromocoes;
import com.gmail.gabrielcacarvalho.RestApi.dto.promocao.EntradaPromocaoDTO;
import com.gmail.gabrielcacarvalho.RestApi.dto.promocao.PromocaoDTO;
import com.gmail.gabrielcacarvalho.RestApi.repositories.PromocaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PromocaoService {

    @Autowired
    private PromocaoRepository promocaoRepository;

    private Converter<EntradaPromocaoDTO, Promocao> entradaDTOPromocaoConverter = new EntredaDTOPromocaoConverter();

    private Converter<Promocao, PromocaoDTO> promocaoPromocaoDTOConverter = new PromocaoPromocaoDTOConverter();

    private Converter<AlteraPromocaoDTO, Promocao> alteraDTOPromocaoConverter = new AlteraDTOPromocaoConverter();

    public Page<PromocaoDTO> obterPromocoes(Pageable pageable, FiltroListarPromocoes filtros){
        return promocaoPromocaoDTOConverter.from(promocaoRepository.findAll(criarFiltrosBuscarLista(filtros), pageable));
    }

    public PromocaoDTO criaPromocao(EntradaPromocaoDTO entradaPromocaoDTO) {
        return promocaoPromocaoDTOConverter.from(promocaoRepository.save(entradaDTOPromocaoConverter.from(entradaPromocaoDTO)));
    }

    public PromocaoDTO alteraPromocao(AlteraPromocaoDTO alteraPromocaoDTO) {
        return promocaoPromocaoDTOConverter.from(promocaoRepository.save(alteraDTOPromocaoConverter.from(alteraPromocaoDTO)));
    }

    private Specification<Promocao> criarFiltrosBuscarLista(FiltroListarPromocoes filtroListarPromocoes){

        FiltroListarPromocoes filtros = Optional.ofNullable(filtroListarPromocoes).orElse(new FiltroListarPromocoes());

        return (root, query, builder) ->{
            List<Predicate> predicates = new ArrayList<>();

            if (filtros.getDataFim() != null && filtros.getDataInicio() != null)
                predicates.add(builder.between(root.get("dataInicio"),
                        filtros.getDataInicio(),
                        filtros.getDataFim()));

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
