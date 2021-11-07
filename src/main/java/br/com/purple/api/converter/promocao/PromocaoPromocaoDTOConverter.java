package br.com.purple.api.converter.promocao;

import br.com.purple.api.converter.Converter;
import br.com.purple.api.dto.promocao.PromocaoDTO;
import br.com.purple.api.core.entity.model.Promocao;

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
