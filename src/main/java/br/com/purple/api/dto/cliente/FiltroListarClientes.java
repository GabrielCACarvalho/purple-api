package br.com.purple.api.dto.cliente;

import br.com.purple.api.core.entity.enumerator.Sexo;
import lombok.Data;

@Data
public class FiltroListarClientes {
    private Sexo sexo;
    private Integer id;
}
