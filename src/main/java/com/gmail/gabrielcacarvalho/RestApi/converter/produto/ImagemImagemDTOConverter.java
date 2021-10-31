package com.gmail.gabrielcacarvalho.RestApi.converter.produto;

import com.gmail.gabrielcacarvalho.RestApi.converter.Converter;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Imagem;
import com.gmail.gabrielcacarvalho.RestApi.dto.produto.ImagemDTO;
import org.springframework.core.io.ByteArrayResource;

public class ImagemImagemDTOConverter implements Converter<Imagem, ImagemDTO> {
    @Override
    public ImagemDTO from(Imagem imagem) {
        ImagemDTO imagemDTO = new ImagemDTO();

        imagemDTO.setNome(imagem.getNome());
        imagemDTO.setArquivo(new ByteArrayResource(imagem.getArquivo()));

        return imagemDTO;
    }
}
