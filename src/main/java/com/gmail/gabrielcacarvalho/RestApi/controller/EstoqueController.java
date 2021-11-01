package com.gmail.gabrielcacarvalho.RestApi.controller;

import com.gmail.gabrielcacarvalho.RestApi.dto.estoque.EstoqueDTO;
import com.gmail.gabrielcacarvalho.RestApi.dto.estoque.FiltroConsultaEstoque;
import com.gmail.gabrielcacarvalho.RestApi.dto.estoque.SaidaEstoque;
import com.gmail.gabrielcacarvalho.RestApi.dto.produto.EntradaEstoque;
import com.gmail.gabrielcacarvalho.RestApi.service.EstoqueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estoque")
@Api(tags = "Estoque")
public class EstoqueController {

    @Autowired
    private EstoqueService estoqueService;

    @PostMapping
    @ApiOperation("Consulta o estoque de um produto")
    public ResponseEntity<EstoqueDTO> consultaEstoque(@RequestBody FiltroConsultaEstoque filtroConsultaEstoque){
        return ResponseEntity.ok(estoqueService.consultaEstoqueProduto(filtroConsultaEstoque));
    }

    @PostMapping("/entrada")
    @ApiOperation("Efetua entrada de um produto no estoque")
    public ResponseEntity<EstoqueDTO> entradaNoEstoque(@RequestBody EntradaEstoque entradaEstoque){
        return ResponseEntity.ok(estoqueService.entradaNoEstoque(entradaEstoque));
    }

    @PostMapping("/saida")
    @ApiOperation("Efetua saida de um ou mais produtos no estoque")
    public ResponseEntity<EstoqueDTO> saidaNoEstoque(@RequestBody SaidaEstoque saidaEstoque){
        return ResponseEntity.ok(estoqueService.saidaNoEstoque(saidaEstoque));
    }
}
