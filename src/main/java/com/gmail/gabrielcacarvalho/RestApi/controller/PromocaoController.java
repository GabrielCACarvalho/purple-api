package com.gmail.gabrielcacarvalho.RestApi.controller;

import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Promocao;
import com.gmail.gabrielcacarvalho.RestApi.dto.promocao.FiltroListarPromocoes;
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

@RestController
@RequestMapping("/promocoes")
@Api("Promoções")
public class PromocaoController {

    @Autowired
    private PromocaoService promocaoService;

    @PostMapping
    @ApiOperation(value = "Retorna promoções de acordo com o filtro. Date pattern = yyyy-MM-dd")
    public ResponseEntity<Page<Promocao>> obterPromocoes(@PageableDefault(page = 0, size = 1, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                                                         @RequestBody(required = false) FiltroListarPromocoes filtros){
        return ResponseEntity.ok(promocaoService.obterPromocoes(pageable, filtros));
    }
}
