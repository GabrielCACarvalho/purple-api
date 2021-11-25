package br.com.purple.api.dto.tipovestimenta;

import br.com.purple.api.dto.enumerator.CategoriaDTO;
import lombok.Data;

import java.util.List;

@Data
public class FiltroTipoVestimenta {

    List<CategoriaDTO> categorias;
}
