package com.gmail.gabrielcacarvalho.RestApi.controller;

import com.gmail.gabrielcacarvalho.RestApi.dto.promocao.FiltroListarPromocoes;
import com.gmail.gabrielcacarvalho.RestApi.dto.promocao.EntradaPromocaoDTO;
import com.gmail.gabrielcacarvalho.RestApi.dto.promocao.PromocaoDTO;
import com.gmail.gabrielcacarvalho.RestApi.service.PromocaoService;
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
@Api("Promoções")
public class PromocaoController {

    @Autowired
    private PromocaoService promocaoService;

    @GetMapping("/listar")
    @ApiOperation("Retorna promoções de acordo com o filtro. Date pattern = yyyy-MM-dd")
    public ResponseEntity<Page<PromocaoDTO>> obterPromocoes(@PageableDefault(page = 0, size = 1, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                                                            @Valid @RequestBody(required = false) FiltroListarPromocoes filtros){
        return ResponseEntity.ok(promocaoService.obterPromocoes(pageable, filtros));
    }

    @PostMapping("/nova")
    @ApiOperation("Cadastra uma nova promoção e retorna a promoção cadastrada.")
    public ResponseEntity<PromocaoDTO> criaPromocao(@RequestBody EntradaPromocaoDTO entradaPromocaoDTO){
        return ResponseEntity.ok(promocaoService.criaPromocao(entradaPromocaoDTO));
    }

    @PutMapping("/altera")
    @ApiOperation("Modifica uma promoção e retorna e a promoção modificada.")
    public ResponseEntity<PromocaoDTO> alteraPromocao(@RequestBody EntradaPromocaoDTO entradaPromocaoDTO){
        return ResponseEntity.ok(promocaoService.alteraPromocao(entradaPromocaoDTO));
    }
}
