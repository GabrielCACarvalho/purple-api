package com.gmail.gabrielcacarvalho.RestApi.converter.promocao;

import com.gmail.gabrielcacarvalho.RestApi.converter.Converter;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Promocao;
import com.gmail.gabrielcacarvalho.RestApi.dto.promocao.PromocaoDTO;

public class PromocaoPromocaoDTOConverter implements Converter<Promocao, PromocaoDTO> {

    @Override
    public PromocaoDTO from(Promocao promocao) {
        PromocaoDTO promocaoDTO = new PromocaoDTO();

        promocaoDTO.setId(promocao.getId());
        promocaoDTO.setPorcentagemDesconto(promocao.getPorcentagemDesconto());
        promocaoDTO.setDataFim(promocao.getDataFim());
        promocaoDTO.setDataInicio(promocao.getDataInicio());
        promocaoDTO.setDescricao(promocao.getDescricao());

        return promocaoDTO;
    }
}
