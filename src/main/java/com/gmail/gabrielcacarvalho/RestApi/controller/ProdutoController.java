package com.gmail.gabrielcacarvalho.RestApi.controller;

import com.gmail.gabrielcacarvalho.RestApi.dto.produto.FiltroListarProdutos;
import com.gmail.gabrielcacarvalho.RestApi.dto.produto.EntradaProdutoDTO;
import com.gmail.gabrielcacarvalho.RestApi.dto.produto.ProdutoDTO;
import com.gmail.gabrielcacarvalho.RestApi.service.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produto")
@Api("Produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/listar")
    @ApiOperation("Retorna produtos de acordo com o filtro.")
    public ResponseEntity<Page<ProdutoDTO>> obterProdutos(@PageableDefault(page = 0, size = 20, sort = "id", direction = Sort.Direction.DESC)Pageable pageable,
                                                          @RequestBody(required = false) FiltroListarProdutos filtros){
        return ResponseEntity.ok(produtoService.obterProdutos(pageable, filtros));
    }

    @GetMapping("/{idProduto}")
    @ApiOperation("Retorna um produto pelo id.")
    public ResponseEntity<ProdutoDTO> obterProduto(@PathVariable Integer idProduto){
        return ResponseEntity.ok(produtoService.obterProduto(idProduto));
    }

    @PostMapping("/novo")
    @ApiOperation("Cadastra um novo produto e retorna o produto cadastrado.")
    public ResponseEntity<ProdutoDTO> criaProduto(@RequestBody EntradaProdutoDTO entradaProdutoDTO){
        return ResponseEntity.ok(produtoService.criaProduto(entradaProdutoDTO));
    }

    @PutMapping("/altera")
    @ApiOperation("Modifica um produto e retorna o produto modificado.")
    public ResponseEntity<ProdutoDTO> alteraProduto(@RequestBody EntradaProdutoDTO entradaProdutoDTO){
        return ResponseEntity.ok(produtoService.alteraProduto(entradaProdutoDTO));
    }
}
