package com.gmail.gabrielcacarvalho.RestApi.dto.produto;

import lombok.Data;
import org.springframework.core.io.Resource;

@Data
public class ImagemDTO {

    private String nome;
    private byte[] arquivo;
}
