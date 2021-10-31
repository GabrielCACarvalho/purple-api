package com.gmail.gabrielcacarvalho.RestApi.controller;

import com.gmail.gabrielcacarvalho.RestApi.dto.produto.*;
import com.gmail.gabrielcacarvalho.RestApi.service.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produto")
@Api("Produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/listar")
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

    @PostMapping("/{idProduto}/adiciona/imagem")
    @ApiOperation("Adiciona imagem no produto escolhido.")
    public ResponseEntity<?> addImagemProduto(@RequestParam("imagens") MultipartFile[] imagens,
                                              @PathVariable Integer idProduto){
        return ResponseEntity.ok(produtoService.adicionaImagemProduto(idProduto, imagens));
    }

    @GetMapping("{idProduto}/imagens")
    @ApiOperation("Busca imagens do produto escolhido.")
    public ResponseEntity<List<ImagemDTO>> obtemImagemProduto(@PathVariable Integer idProduto){
        List<ImagemDTO> imagemDTOS = produtoService.obterImagensProduto(idProduto);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment")
                .body(imagemDTOS);
    }

    @PutMapping("/altera")
    @ApiOperation("Modifica um produto e retorna o produto modificado.")
    public ResponseEntity<ProdutoDTO> alteraProduto(@RequestBody AlteraProdutoDTO alteraProdutoDTO){
        return ResponseEntity.ok(produtoService.alteraProduto(alteraProdutoDTO));
    }
}
