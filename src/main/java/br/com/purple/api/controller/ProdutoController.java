package br.com.purple.api.controller;

import br.com.purple.api.dto.produto.*;
import br.com.purple.api.usecase.ProdutoUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static br.com.purple.api.utils.ControllerDescription.PRODUTO_DESCRIPTION;
import static br.com.purple.api.utils.ControllerDescription.PRODUTO_TAG;

@RestController
@RequestMapping("/produto")
@Api(tags = PRODUTO_TAG, description = PRODUTO_DESCRIPTION)
@AllArgsConstructor
public class ProdutoController {

    private ProdutoUseCase produtoUseCase;

    @GetMapping("/cadastrados/total")
    @ApiOperation("Retorna o número de produtos cadastrados.")
    public ResponseEntity<Long> obterTotalProdutosCadastrados(){
        return ResponseEntity.ok(produtoUseCase.obterTotalProdutosCadastrados());
    }

    @GetMapping("/listar")
    @ApiOperation("Retorna produtos de acordo com o filtro.")
    public ResponseEntity<Page<ProdutoDTO>> obterProdutos(FiltroListarProdutos filtros,
                                                          @PageableDefault(size = 50, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.ok(produtoUseCase.obterProdutos(pageable, filtros));
    }

    @GetMapping("/{idProduto}")
    @ApiOperation("Retorna um produto pelo id.")
    public ResponseEntity<ProdutoDTO> obterProduto(@PathVariable Integer idProduto){
        return ResponseEntity.ok(produtoUseCase.obterProduto(idProduto));
    }

    @PostMapping("/novo")
    @ApiOperation("Cadastra um novo produto e retorna o produto cadastrado.")
    public ResponseEntity<ProdutoDTO> criaProduto(@RequestBody EntradaProdutoDTO entradaProdutoDTO){
        return ResponseEntity.ok(produtoUseCase.criaProduto(entradaProdutoDTO));
    }

    @PostMapping("/{idProduto}/adiciona/imagem")
    @ApiOperation("Adiciona imagem no produto escolhido.")
    public ResponseEntity<?> addImagemProduto(@RequestParam("imagens") MultipartFile[] imagens,
                                              @PathVariable Integer idProduto){
        return ResponseEntity.ok(produtoUseCase.adicionaImagemProduto(idProduto, imagens));
    }

    @GetMapping("{idProduto}/imagens")
    @ApiOperation("Busca imagens do produto escolhido.")
    public ResponseEntity<?> obtemImagemProduto(@PathVariable Integer idProduto){
        List<ImagemDTO> imagemDTOS = produtoUseCase.obterImagensProduto(idProduto);

        return ResponseEntity.ok(imagemDTOS);
    }

    @PutMapping("/altera")
    @ApiOperation("Modifica um produto e retorna o produto modificado.")
    public ResponseEntity<ProdutoDTO> alteraProduto(@RequestBody AlteraProdutoDTO alteraProdutoDTO){
        return ResponseEntity.ok(produtoUseCase.alteraProduto(alteraProdutoDTO));
    }

    @DeleteMapping("/deleta/{idProduto}")
    @ApiOperation("Deleta um produto.")
    public void deletaProduto(@PathVariable Integer idProduto){
        produtoUseCase.deletaProduto(idProduto);
    }
}
