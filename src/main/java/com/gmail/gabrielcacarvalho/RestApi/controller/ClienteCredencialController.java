package com.gmail.gabrielcacarvalho.RestApi.controller;

import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.CredencialCliente;
import com.gmail.gabrielcacarvalho.RestApi.dto.cliente.RoleToCredentialDTO;
import com.gmail.gabrielcacarvalho.RestApi.service.CredencialClienteService;
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
    public ResponseEntity<CredencialCliente> criarCredencial(@RequestBody CredencialCliente credencialCliente){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/credencial/nova").toUriString());
        return ResponseEntity.created(uri).body(credencialClienteService.salvaCredencial(credencialCliente));
    }

    @PostMapping("/adicionar/role/credencial")
    public ResponseEntity<?> addRoleToCredencial(@RequestBody RoleToCredentialDTO roleToCredentialDTO){
        credencialClienteService.addRoleToCredencial(roleToCredentialDTO.getUsuario(), roleToCredentialDTO.getNomeRole());
        return ResponseEntity.ok().build();
    }
}
