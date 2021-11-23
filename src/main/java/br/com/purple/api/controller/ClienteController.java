package br.com.purple.api.controller;

import br.com.purple.api.core.entity.model.Cliente;
import br.com.purple.api.dto.cliente.AlteraClienteDTO;
import br.com.purple.api.dto.cliente.ClienteDTO;
import br.com.purple.api.dto.cliente.FiltroListarClientes;
import br.com.purple.api.dto.cliente.FiltroTotalClientes;
import br.com.purple.api.dto.pedido.FiltroTotalPedidos;
import br.com.purple.api.usecase.ClienteUseCase;
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
@Api(tags = "Clientes") //TODO: ALTERAR TODOS OS AUTOWIRED PARA USAR O @ALLARGSCONSTRUCTOR
public class ClienteController {

    @Autowired
    private ClienteUseCase clienteUseCase;

    @GetMapping("/total")
    @ApiOperation("Consulta o total de clientes cadastrados.")
    public ResponseEntity<Long> totalClientes(FiltroTotalClientes filtro){
        return ResponseEntity.ok(clienteUseCase.obterTotalClientes(filtro));
    }

    @GetMapping("/listar")
    @ApiOperation(value = "Retorna Clientes de acordo com o filtro.")
    public ResponseEntity<Page<ClienteDTO>> obterClientes(@PageableDefault(size = 100, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
                                                          FiltroListarClientes filtros){
        return ResponseEntity.ok(clienteUseCase.obterClientes(pageable, filtros));
    }

    @PostMapping("/novo")
    @ApiOperation(value = "Cria um novo cliente.")
    public ResponseEntity<ClienteDTO> novoCliente(){
        return ResponseEntity.ok(clienteUseCase.novoCliente());
    }

    @PutMapping("/altera")
    @ApiOperation(value = "Altera um cliente existente.")
    public ResponseEntity<ClienteDTO> alteraCliente(@RequestBody AlteraClienteDTO alteraClienteDTO){
        return ResponseEntity.ok(clienteUseCase.alteraCliente(alteraClienteDTO));
    }

    @GetMapping("/autenticado")
    @ApiOperation(value = "Consulta o cliente autenticado")
    public ResponseEntity<Cliente> consultaCliente(){
        return ResponseEntity.ok(clienteUseCase.consultaClienteAutenticado());
    }
}