package com.gmail.gabrielcacarvalho.RestApi.controller;

import com.gmail.gabrielcacarvalho.RestApi.dto.cliente.credencial.role.AlteraRoleDTO;
import com.gmail.gabrielcacarvalho.RestApi.dto.cliente.credencial.role.EntradaRoleDTO;
import com.gmail.gabrielcacarvalho.RestApi.dto.cliente.credencial.role.RoleDTO;
import com.gmail.gabrielcacarvalho.RestApi.service.CredencialClienteService;
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

    private final CredencialClienteService credencialClienteService;

    @GetMapping("/listar")
    @ApiOperation("Retorna as roles cadastradas.")
    public ResponseEntity<Page<RoleDTO>> obterRoles(@PageableDefault(page = 0, size = 1, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.ok(credencialClienteService.obterRoles(pageable));
    }

    @PostMapping("/nova")
    @ApiOperation("Cadastra uma nova role e retorna a role cadastrada.")
    public ResponseEntity<RoleDTO> criarRole(@RequestBody EntradaRoleDTO entradaRoleDTO){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/role/nova").toUriString());
        return ResponseEntity.created(uri).body(credencialClienteService.salvaRole(entradaRoleDTO));
    }

    @PutMapping("/alterar")
    @ApiOperation("Modifica a role e retorna a role modificada.")
    public ResponseEntity<RoleDTO> alteraRole(@RequestBody AlteraRoleDTO alteraRoleDTO){
        return ResponseEntity.ok(credencialClienteService.alteraRole(alteraRoleDTO));
    }

    @DeleteMapping("/delete/{idRole}")
    @ApiOperation("Deleta uma role.")
    public void deletaRole(@PathVariable Integer idRole){
        credencialClienteService.deleteRole(idRole);
    }
}
