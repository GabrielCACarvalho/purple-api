package br.com.purple.api.controller;

import br.com.purple.api.dto.promocao.AlteraPromocaoDTO;
import br.com.purple.api.dto.promocao.EntradaPromocaoDTO;
import br.com.purple.api.dto.promocao.FiltroListarPromocoes;
import br.com.purple.api.dto.promocao.PromocaoDTO;
import br.com.purple.api.usecase.PromocaoUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/promocao")
@Api(tags = "Promoção")
public class PromocaoController {

    @Autowired
    private PromocaoUseCase promocaoUseCase;

    @GetMapping("/{idPromocao}")
    @ApiOperation("Retorna promoção pelo id.")
    public ResponseEntity<PromocaoDTO> obterPromocao(@PathVariable Integer idPromocao){
        return ResponseEntity.ok(promocaoUseCase.obterPromocao(idPromocao));
    }

    @GetMapping("/listar")
    @ApiOperation("Retorna promoções de acordo com o filtro. Date pattern = yyyy-MM-dd")
    public ResponseEntity<Page<PromocaoDTO>> obterPromocoes(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                                                            @Valid FiltroListarPromocoes filtros){
        return ResponseEntity.ok(promocaoUseCase.obterPromocoes(pageable, filtros));
    }

    @PostMapping("/nova")
    @ApiOperation("Cadastra uma nova promoção e retorna a promoção cadastrada.")
    public ResponseEntity<PromocaoDTO> criaPromocao(@RequestBody EntradaPromocaoDTO entradaPromocaoDTO){
        return ResponseEntity.ok(promocaoUseCase.criaPromocao(entradaPromocaoDTO));
    }

    @PutMapping("/altera")
    @ApiOperation("Modifica uma promoção e retorna e a promoção modificada.")
    public ResponseEntity<PromocaoDTO> alteraPromocao(@RequestBody AlteraPromocaoDTO alteraPromocaoDTO){
        return ResponseEntity.ok(promocaoUseCase.alteraPromocao(alteraPromocaoDTO));
    }

    @DeleteMapping("/deleta/{idPromocao}")
    @ApiOperation("Deleta uma promoção.")
    public void deletaPromocao(@PathVariable Integer idPromocao){
        promocaoUseCase.deletaPromocao(idPromocao);
    }
}
