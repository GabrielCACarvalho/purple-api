package br.com.purple.api.service.converter;

import br.com.purple.api.converter.Converter;
import br.com.purple.api.service.model.dto.CEPResponseDto;
import br.com.purple.api.service.model.response.CEPResponse;

public class CEPToDtoConverter implements Converter<CEPResponse, CEPResponseDto> {

    @Override
    public CEPResponseDto from(CEPResponse cepResponse) {
        CEPResponseDto cepResponseDto = new CEPResponseDto();

        cepResponseDto.setCep(cepResponse.getCep());
        cepResponseDto.setBairro(cepResponse.getBairro());
        cepResponseDto.setCidade(cepResponse.getLocalidade());
        cepResponseDto.setDdd(cepResponse.getDdd());
        cepResponseDto.setEstado(cepResponse.getUf());
        cepResponseDto.setLogradouro(cepResponse.getLogradouro());

        return cepResponseDto;
    }
}
