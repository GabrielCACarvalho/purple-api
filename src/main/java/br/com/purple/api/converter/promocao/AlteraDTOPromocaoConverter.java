package br.com.purple.api.converter.promocao;

import br.com.purple.api.dto.promocao.AlteraPromocaoDTO;
import br.com.purple.api.converter.Converter;
import br.com.purple.api.core.entity.model.Promocao;

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
