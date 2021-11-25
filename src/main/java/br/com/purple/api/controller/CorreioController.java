package br.com.purple.api.controller;

import br.com.purple.api.service.CEPClient;
import br.com.purple.api.service.CalcPrecoPrazoClient;
import br.com.purple.api.service.model.FiltroCalculoPrecoPrazoProduto;
import br.com.purple.api.service.model.dto.CEPResponseDto;
import br.com.purple.api.service.model.dto.CalculoResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static br.com.purple.api.utils.ControllerDescription.CORREIO_DESCRIPTION;
import static br.com.purple.api.utils.ControllerDescription.CORREIO_TAG;

@RestController
@RequestMapping("/correio")
@Api(tags = CORREIO_TAG, description = CORREIO_DESCRIPTION)
@AllArgsConstructor
public class CorreioController {

    private CalcPrecoPrazoClient calcClient;
    private CEPClient CEPClient;

    @PostMapping("/calcula/preco/prazo")
    @ApiOperation("Consulta o valor e o prazo de acordo com o com o servico escolhido")
    public ResponseEntity<CalculoResponseDto> consultaPrecoPrazo(@Valid @RequestBody FiltroCalculoPrecoPrazoProduto filtroCalculoPrecoPrazoProduto){
        return ResponseEntity.ok(calcClient.getCalculo(filtroCalculoPrecoPrazoProduto));
    }

    @GetMapping("/consulta/cep/{cep}")
    @ApiOperation("Consulta o CEP informado.")
    public ResponseEntity<CEPResponseDto> consultaCEP(@NotNull @NotBlank @PathVariable String cep){
        return ResponseEntity.ok(CEPClient.consultaCEP(cep));
    }
}
