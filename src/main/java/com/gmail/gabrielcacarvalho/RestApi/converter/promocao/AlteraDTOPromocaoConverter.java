package com.gmail.gabrielcacarvalho.RestApi.converter.promocao;

import com.gmail.gabrielcacarvalho.RestApi.converter.Converter;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Promocao;
import com.gmail.gabrielcacarvalho.RestApi.dto.promocao.AlteraPromocaoDTO;

public class AlteraDTOPromocaoConverter implements Converter<AlteraPromocaoDTO, Promocao> {
    @Override
    public Promocao from(AlteraPromocaoDTO alteraPromocaoDTO) {
        Promocao promocao = new Promocao();

        promocao.setId(alteraPromocaoDTO.getId());
        promocao.setDataInicio(alteraPromocaoDTO.getDataInicio());
        promocao.setDataFim(alteraPromocaoDTO.getDataFim());
        promocao.setDescricao(alteraPromocaoDTO.getDescricao());
        promocao.setPorcentagemDesconto(alteraPromocaoDTO.getPorcentagemDesconto());

        return promocao;
    }
}
