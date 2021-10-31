package com.gmail.gabrielcacarvalho.RestApi.controller;

import com.gmail.gabrielcacarvalho.RestApi.dto.cliente.credencial.AlteraCredencialClienteDTO;
import com.gmail.gabrielcacarvalho.RestApi.dto.cliente.credencial.CredencialClienteDTO;
import com.gmail.gabrielcacarvalho.RestApi.dto.cliente.credencial.RoleToCredentialDTO;
import com.gmail.gabrielcacarvalho.RestApi.service.CredencialClienteService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/credencial")
@RequiredArgsConstructor
public class ClienteCredencialController {

    private final CredencialClienteService credencialClienteService;

    @PostMapping("/nova")
    @ApiOperation(value = "Cria uma nova credencial cliente.")
    public ResponseEntity<CredencialClienteDTO> criarCredencial(@RequestBody CredencialClienteDTO credencialClienteDTO){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/credencial/nova").toUriString());
        return ResponseEntity.created(uri).body(credencialClienteService.criaCredencial(credencialClienteDTO));
    }

    @PutMapping("/esqueceu/senha")
    @ApiOperation(value = "Permite alterar a senha, se esquecida.")
    public ResponseEntity<CredencialClienteDTO> alterarSenha(@RequestBody AlteraCredencialClienteDTO alteraCredencialClienteDTO){
        return ResponseEntity.ok(credencialClienteService.alteraCredencial(alteraCredencialClienteDTO));
    }

    @PostMapping("/adicionar/role/credencial")
    @ApiOperation(value = "Cria uma nova credencial cliente.")
    public ResponseEntity<?> addRoleToCredencial(@RequestBody RoleToCredentialDTO roleToCredentialDTO){
        credencialClienteService.addRoleToCredencial(roleToCredentialDTO.getUsuario(), roleToCredentialDTO.getNomeRole());
        return ResponseEntity.ok().build();
    }
}
