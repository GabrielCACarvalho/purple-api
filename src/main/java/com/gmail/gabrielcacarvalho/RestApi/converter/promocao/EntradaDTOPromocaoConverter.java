package com.gmail.gabrielcacarvalho.RestApi.converter.promocao;

import com.gmail.gabrielcacarvalho.RestApi.converter.Converter;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Promocao;
import com.gmail.gabrielcacarvalho.RestApi.dto.promocao.EntradaPromocaoDTO;

public class EntradaDTOPromocaoConverter implements Converter<EntradaPromocaoDTO, Promocao> {

    @Override
    public Promocao from(EntradaPromocaoDTO entradaPromocaoDTO) {
        Promocao promocao = new Promocao();

        promocao.setDataFim(entradaPromocaoDTO.getDataFim());
        promocao.setDataInicio(entradaPromocaoDTO.getDataInicio());
        promocao.setDescricao(entradaPromocaoDTO.getDescricao());
        promocao.setPorcentagemDesconto(entradaPromocaoDTO.getPorcentagemDesconto());

        return promocao;
    }
}
