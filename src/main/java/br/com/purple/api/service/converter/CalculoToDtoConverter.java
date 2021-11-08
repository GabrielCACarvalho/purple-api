package br.com.purple.api.service.converter;

import br.com.purple.api.converter.Converter;
import br.com.purple.api.service.model.dto.CalculoResponseDto;
import br.com.purple.api.service.model.response.CalculoResponse;

import java.math.BigDecimal;

public class CalculoToDtoConverter implements Converter<CalculoResponse, CalculoResponseDto> {

    @Override
    public CalculoResponseDto from(CalculoResponse calculoResponse) {
        CalculoResponseDto calculoResponseDto = new CalculoResponseDto();

        calculoResponseDto.setValor(new BigDecimal(calculoResponse.getValor().replace(",", ".")));
        calculoResponseDto.setPrazoEntregaEmDias(calculoResponse.getPrazoEntrega());
        if (calculoResponse.getMsgErro() != null) {
            calculoResponseDto.setCodigoErro(calculoResponse.getErro());
            calculoResponseDto.setMensagemErro(calculoResponse.getMsgErro());
        }
        return calculoResponseDto;
    }
}
