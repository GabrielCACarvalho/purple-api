package br.com.purple.api.handler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDto {

    private Integer code;
    private String message;
}
