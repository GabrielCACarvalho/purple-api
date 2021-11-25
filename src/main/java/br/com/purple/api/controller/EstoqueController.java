package br.com.purple.api.controller;

import br.com.purple.api.dto.estoque.EstoqueDTO;
import br.com.purple.api.dto.estoque.FiltroConsultaEstoque;
import br.com.purple.api.dto.estoque.SaidaEstoque;
import br.com.purple.api.dto.produto.EntradaEstoque;
import br.com.purple.api.usecase.EstoqueUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.purple.api.utils.ControllerDescription.ESTOQUE_DESCRIPTION;
import static br.com.purple.api.utils.ControllerDescription.ESTOQUE_TAG;

@RestController
@RequestMapping("/estoque")
@Api(tags = ESTOQUE_TAG, description = ESTOQUE_DESCRIPTION)
@AllArgsConstructor
public class EstoqueController {

    private EstoqueUseCase estoqueUseCase;

    @GetMapping
    @ApiOperation("Consulta o estoque de um produto")
    public ResponseEntity<EstoqueDTO> consultaEstoque(FiltroConsultaEstoque filtroConsultaEstoque){
        return ResponseEntity.ok(estoqueUseCase.consultaEstoqueProduto(filtroConsultaEstoque));
    }

    @GetMapping("/{idProduto}")
    @ApiOperation("Consulta o estoque de um produto")
    public ResponseEntity<List<EstoqueDTO>> consultaEstoque(@PathVariable Integer idProduto){
        return ResponseEntity.ok(estoqueUseCase.consultaEstoqueProduto(idProduto));
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
