package com.gmail.gabrielcacarvalho.RestApi.controller;

import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Cliente;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Promocao;
import com.gmail.gabrielcacarvalho.RestApi.dto.cliente.FiltroListarClientes;
import com.gmail.gabrielcacarvalho.RestApi.dto.promocao.FiltroListarPromocoes;
import com.gmail.gabrielcacarvalho.RestApi.service.ClienteService;
import com.gmail.gabrielcacarvalho.RestApi.service.PromocaoService;
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
@RequestMapping("/clientes")
@Api("Clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    @ApiOperation(value = "Retorna Clientes de acordo com o filtro.")
    public ResponseEntity<Page<Cliente>> obterPromocoes(@PageableDefault(page = 0, size = 1, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
                                                        @RequestBody(required = false) FiltroListarClientes filtros){
        return ResponseEntity.ok(clienteService.obterClientes(pageable, filtros));
    }
}