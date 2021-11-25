package br.com.purple.api.controller;

import br.com.purple.api.dto.endereco.AlteraEnderecoDTO;
import br.com.purple.api.dto.endereco.EnderecoDTO;
import br.com.purple.api.dto.endereco.EntradaEnderecoDTO;
import br.com.purple.api.usecase.EnderecoUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static br.com.purple.api.utils.ControllerDescription.ENDERECO_DESCRIPTION;
import static br.com.purple.api.utils.ControllerDescription.ENDERECO_TAG;

@RestController
@RequestMapping("/endereco")
@Api(tags = ENDERECO_TAG, description = ENDERECO_DESCRIPTION)
@AllArgsConstructor
public class EnderecoController {

    private EnderecoUseCase enderecoUseCase;

    @PostMapping("/novo")
    @ApiOperation("Cadastra um novo endereço e retorna o endereço cadastrado.")
    public ResponseEntity<EnderecoDTO> criaEndereco(@RequestBody EntradaEnderecoDTO entradaEnderecoDTO){
        return ResponseEntity.ok(enderecoUseCase.criaEndereco(entradaEnderecoDTO));
    }

    @PutMapping("/altera")
    @ApiOperation("Modifica um endereço e retorna o endereço modificado.")
    public ResponseEntity<EnderecoDTO> alteraEndereco(@RequestBody AlteraEnderecoDTO alteraEnderecoDTO){
        return ResponseEntity.ok(enderecoUseCase.alteraEndereco(alteraEnderecoDTO));
    }
}
