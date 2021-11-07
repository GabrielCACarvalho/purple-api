package br.com.purple.api.controller;

import br.com.purple.api.dto.produto.EntradaEstoque;
import br.com.purple.api.dto.estoque.EstoqueDTO;
import br.com.purple.api.dto.estoque.FiltroConsultaEstoque;
import br.com.purple.api.dto.estoque.SaidaEstoque;
import br.com.purple.api.usecase.EstoqueUseCase;
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
    private EstoqueUseCase estoqueUseCase;

    @PostMapping
    @ApiOperation("Consulta o estoque de um produto")
    public ResponseEntity<EstoqueDTO> consultaEstoque(@RequestBody FiltroConsultaEstoque filtroConsultaEstoque){
        return ResponseEntity.ok(estoqueUseCase.consultaEstoqueProduto(filtroConsultaEstoque));
    }

    @PostMapping("/entrada")
    @ApiOperation("Efetua entrada de um produto no estoque")
    public ResponseEntity<EstoqueDTO> entradaNoEstoque(@RequestBody EntradaEstoque entradaEstoque){
        return ResponseEntity.ok(estoqueUseCase.entradaNoEstoque(entradaEstoque));
    }

    @PostMapping("/saida")
    @ApiOperation("Efetua saida de um ou mais produtos no estoque")
    public ResponseEntity<EstoqueDTO> saidaNoEstoque(@RequestBody SaidaEstoque saidaEstoque){
        return ResponseEntity.ok(estoqueUseCase.saidaNoEstoque(saidaEstoque));
    }
}
