package br.com.purple.api.converter.promocao;

import br.com.purple.api.converter.Converter;
import br.com.purple.api.core.entity.model.Promocao;
import br.com.purple.api.dto.promocao.EntradaPromocaoDTO;

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
