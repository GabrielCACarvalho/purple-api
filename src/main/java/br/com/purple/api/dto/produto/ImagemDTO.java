package br.com.purple.api.dto.produto;

import lombok.Data;
import org.springframework.core.io.Resource;

@Data
public class ImagemDTO {

    private String nome;
    private byte[] arquivo;
}
