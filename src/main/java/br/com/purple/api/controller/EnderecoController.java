package br.com.purple.api.controller;

import br.com.purple.api.dto.endereco.AlteraEnderecoDTO;
import br.com.purple.api.dto.endereco.EnderecoDTO;
import br.com.purple.api.dto.endereco.EntradaEnderecoDTO;
import br.com.purple.api.usecase.EnderecoUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/endereco")
@Api(tags = "Endereços")
public class EnderecoController {

    @Autowired
    private EnderecoUseCase enderecoUseCase;

    //TODO: Consulta frete de acordo com o CEP

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
