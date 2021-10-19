package com.gmail.gabrielcacarvalho.RestApi.controller;

import com.gmail.gabrielcacarvalho.RestApi.dto.endereco.AlteraEnderecoDTO;
import com.gmail.gabrielcacarvalho.RestApi.dto.endereco.EnderecoDTO;
import com.gmail.gabrielcacarvalho.RestApi.dto.endereco.EntradaEnderecoDTO;
import com.gmail.gabrielcacarvalho.RestApi.service.EnderecoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/endereco")
@Api("Endereços")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    //TODO: Consulta frete de acordo com o CEP

    @PostMapping("/novo")
    @ApiOperation("Cadastra um novo endereço e retorna o endereço cadastrado.")
    public ResponseEntity<EnderecoDTO> criaEndereco(@RequestBody EntradaEnderecoDTO entradaEnderecoDTO){
        return ResponseEntity.ok(enderecoService.criaEndereco(entradaEnderecoDTO));
    }

    @PutMapping("/altera")
    @ApiOperation("Modifica um endereço e retorna o endereço modificado.")
    public ResponseEntity<EnderecoDTO> alteraEndereco(@RequestBody AlteraEnderecoDTO alteraEnderecoDTO){
        return ResponseEntity.ok(enderecoService.alteraEndereco(alteraEnderecoDTO));
    }
}
