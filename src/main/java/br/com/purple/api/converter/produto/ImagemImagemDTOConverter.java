package br.com.purple.api.converter.produto;

import br.com.purple.api.converter.Converter;
import br.com.purple.api.core.entity.model.Imagem;
import br.com.purple.api.dto.produto.ImagemDTO;

public class ImagemImagemDTOConverter implements Converter<Imagem, ImagemDTO> {
    @Override
    public ImagemDTO from(Imagem imagem) {
        ImagemDTO imagemDTO = new ImagemDTO();

        imagemDTO.setNome(imagem.getNome());
        imagemDTO.setArquivo(imagem.getArquivo());

        return imagemDTO;
    }
}
