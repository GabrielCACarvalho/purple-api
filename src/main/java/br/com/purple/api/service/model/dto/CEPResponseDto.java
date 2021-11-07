package br.com.purple.api.service.model.dto;

import lombok.Data;

@Data
public class CEPResponseDto {

    private String cep;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String estado;
    private String ddd;
}
