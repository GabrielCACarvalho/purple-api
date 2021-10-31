package com.gmail.gabrielcacarvalho.RestApi.controller;

import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Cliente;
import com.gmail.gabrielcacarvalho.RestApi.dto.cliente.AlteraClienteDTO;
import com.gmail.gabrielcacarvalho.RestApi.dto.cliente.ClienteDTO;
import com.gmail.gabrielcacarvalho.RestApi.dto.cliente.EntradaClienteDTO;
import com.gmail.gabrielcacarvalho.RestApi.dto.cliente.FiltroListarClientes;
import com.gmail.gabrielcacarvalho.RestApi.service.ClienteService;
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
@RequestMapping("/cliente")
@Api("Clientes") //TODO: ALTERAR TODOS OS AUTOWIRED PARA USAR O @ALLARGSCONSTRUCTOR
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/listar")
    @ApiOperation(value = "Retorna Clientes de acordo com o filtro.")
    public ResponseEntity<Page<ClienteDTO>> obterClientes(@PageableDefault(page = 0, size = 1, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
                                                        @RequestBody(required = false) FiltroListarClientes filtros){
        return ResponseEntity.ok(clienteService.obterClientes(pageable, filtros));
    }

    @PostMapping("/novo")
    @ApiOperation(value = "Cria um novo cliente.")
    public ResponseEntity<ClienteDTO> novoCliente(){
        return ResponseEntity.ok(clienteService.novoCliente());
    }

    @PutMapping("/altera")
    @ApiOperation(value = "")
    public ResponseEntity<ClienteDTO> alteraCliente(@RequestBody AlteraClienteDTO alteraClienteDTO){
        return ResponseEntity.ok(clienteService.alteraCliente(alteraClienteDTO));
    }

    @GetMapping("/autenticado")
    @ApiOperation(value = "Consulta o cliente autenticado")
    public ResponseEntity<Cliente> consultaCliente(){
        return ResponseEntity.ok(clienteService.consultaClienteAutenticado());
    }
}