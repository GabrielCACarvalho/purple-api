package br.com.purple.api.service.model.response;

import lombok.Data;
import org.simpleframework.xml.Element;

@Data
public class CalculoResponse {

    @Element(name = "Codigo")
    private String codigo;
    @Element(name = "Valor")
    private String valor;
    @Element(name = "PrazoEntrega")
    private Integer prazoEntrega;
    @Element(name = "ValorSemAdicionais")
    private String valorSemAdicionais;
    @Element(name = "ValorMaoPropria")
    private String valorMaoPropria;
    @Element(name = "ValorAvisoRecebimento")
    private String valorAvisoRecebimento;
    @Element(name = "ValorValorDeclarado")
    private String valorValorDeclarado;
    @Element(name = "EntregaDomiciliar")
    private String entregaDomiciliar;
    @Element(name = "EntregaSabado")
    private String entregaSabado;
    @Element(name = "obsFim", required = false)
    private String obsFim;
    @Element(name = "Erro")
    private String erro;
    @Element(name = "MsgErro", required = false)
    private String msgErro;
}
