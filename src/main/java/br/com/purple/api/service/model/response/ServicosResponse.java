package br.com.purple.api.service.model.response;

import lombok.Data;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Servicos")
@Data
public class ServicosResponse {

    @Element(name = "cServico")
    private CalculoResponse calculoResponse;
}
