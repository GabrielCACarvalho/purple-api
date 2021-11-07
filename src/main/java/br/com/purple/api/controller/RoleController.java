package br.com.purple.api.controller;

import br.com.purple.api.dto.cliente.credencial.role.AlteraRoleDTO;
import br.com.purple.api.dto.cliente.credencial.role.EntradaRoleDTO;
import br.com.purple.api.dto.cliente.credencial.role.RoleDTO;
import br.com.purple.api.usecase.CredencialClienteUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/role")
@Api(tags = "Roles")
@RequiredArgsConstructor
public class RoleController {

    private final CredencialClienteUseCase credencialClienteUseCase;

    @GetMapping("/listar")
    @ApiOperation("Retorna as roles cadastradas.")
    public ResponseEntity<Page<RoleDTO>> obterRoles(@PageableDefault(size = 1, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.ok(credencialClienteUseCase.obterRoles(pageable));
    }

    @PostMapping("/nova")
    @ApiOperation("Cadastra uma nova role e retorna a role cadastrada.")
    public ResponseEntity<RoleDTO> criarRole(@RequestBody EntradaRoleDTO entradaRoleDTO){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/role/nova").toUriString());
        return ResponseEntity.created(uri).body(credencialClienteUseCase.salvaRole(entradaRoleDTO));
    }

    @PutMapping("/alterar")
    @ApiOperation("Modifica a role e retorna a role modificada.")
    public ResponseEntity<RoleDTO> alteraRole(@RequestBody AlteraRoleDTO alteraRoleDTO){
        return ResponseEntity.ok(credencialClienteUseCase.alteraRole(alteraRoleDTO));
    }

    @DeleteMapping("/delete/{idRole}")
    @ApiOperation("Deleta uma role.")
    public void deletaRole(@PathVariable Integer idRole){
        credencialClienteUseCase.deleteRole(idRole);
    }
}