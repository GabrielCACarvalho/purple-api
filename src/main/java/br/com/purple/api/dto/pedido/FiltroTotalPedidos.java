package br.com.purple.api.dto.pedido;

import lombok.Data;

import java.util.Date;

@Data
public class FiltroTotalPedidos {

    private Date dataInicio;
    private Date dataFim;
}
