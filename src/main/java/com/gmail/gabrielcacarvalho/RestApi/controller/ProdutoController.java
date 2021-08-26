package com.gmail.gabrielcacarvalho.RestApi.controller;

import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Produto;
import com.gmail.gabrielcacarvalho.RestApi.dto.produto.FiltroListarProdutos;
import com.gmail.gabrielcacarvalho.RestApi.service.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produtos")
@Api("Produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    @ApiOperation(value = "Retorna produtos de acordo com o filtro.")
    public ResponseEntity<Page<Produto>> obterProdutos(@PageableDefault(page = 0, size = 20, sort = "idProduto", direction = Sort.Direction.DESC)Pageable pageable,
                                                       @RequestBody(required = false) FiltroListarProdutos filtros){
        return ResponseEntity.ok(produtoService.obterProdutos(pageable, filtros));
    }
}
