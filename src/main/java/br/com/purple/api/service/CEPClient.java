package br.com.purple.api.service;

import br.com.purple.api.converter.Converter;
import br.com.purple.api.service.converter.CEPToDtoConverter;
import br.com.purple.api.service.model.dto.CEPResponseDto;
import br.com.purple.api.service.model.response.CEPResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CEPClient {

    private Converter<CEPResponse, CEPResponseDto> cepToDtoConverter = new CEPToDtoConverter();

    public CEPResponseDto consultaCEP(String cep) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://viacep.com.br/ws/" + cep + "/json/")
                .build();

        Response response = null;

        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(response != null){
            ObjectMapper mapper = new ObjectMapper();

            CEPResponse cepResponse = new CEPResponse();

            try {
                cepResponse = mapper.readValue(response.body().string(), CEPResponse.class);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return cepToDtoConverter.from(cepResponse);
        } else
            return new CEPResponseDto();
    }
}
