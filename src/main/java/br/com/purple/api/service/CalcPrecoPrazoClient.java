package br.com.purple.api.service;

import br.com.purple.api.converter.Converter;
import br.com.purple.api.service.converter.CalculoToDtoConverter;
import br.com.purple.api.service.model.FiltroCalculoPrecoPrazoProduto;
import br.com.purple.api.service.model.dto.CEPResponseDto;
import br.com.purple.api.service.model.dto.CalculoResponseDto;
import br.com.purple.api.service.model.response.CalculoResponse;
import br.com.purple.api.service.model.response.ServicosResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CalcPrecoPrazoClient {

    private final Converter<CalculoResponse, CalculoResponseDto> calculoToDtoConverter = new CalculoToDtoConverter();

    @Value("${empresa.cep}")
    private String empresaCEP;

    public CalculoResponseDto getCalculo(FiltroCalculoPrecoPrazoProduto filtro){

        String params = "sCepOrigem=" + empresaCEP +
                "&sCepDestino=" + filtro.getCepDestino() +
                "&nVlPeso=" + filtro.getPeso() +
                "&nVlComprimento=" + filtro.getComprimento() +
                "&nVlAltura=" + filtro.getAltura() +
                "&nVlLargura=" + filtro.getLargura() +
                "&sCdMaoPropria=N" +
                "&nVlValorDeclarado=0" +
                "&sCdAvisoRecebimento=N" +
                "&nCdServico=" + filtro.getCodigoServico() +
                "&StrRetorno=xml";

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://ws.correios.com.br/calculador/CalcPrecoPrazo.aspx?" + params)
                .get()
                .build();

        Response response = null;

        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(response != null){
            Serializer serializer = new Persister();

            ServicosResponse servicosResponse = new ServicosResponse();

            try {
                 servicosResponse = serializer.read(ServicosResponse.class, response.body().string());
            } catch (Exception e) {
                e.printStackTrace();
            }

            return calculoToDtoConverter.from(servicosResponse.getCalculoResponse());
        } else
            return new CalculoResponseDto();
    }
}
