package br.com.purple.api.controller;

import br.com.purple.api.dto.cliente.credencial.AlteraCredencialClienteDTO;
import br.com.purple.api.dto.cliente.credencial.CredencialClienteDTO;
import br.com.purple.api.dto.cliente.credencial.RoleToCredentialDTO;
import br.com.purple.api.usecase.CredencialClienteUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/credencial")
@Api(tags = "Credencial Cliente")
@RequiredArgsConstructor
public class ClienteCredencialController {

    private final CredencialClienteUseCase credencialClienteUseCase;

    @PostMapping("/nova")
    @ApiOperation(value = "Cria uma nova credencial cliente.")
    public ResponseEntity<CredencialClienteDTO> criarCredencial(@RequestBody CredencialClienteDTO credencialClienteDTO){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/credencial/nova").toUriString());
        return ResponseEntity.created(uri).body(credencialClienteUseCase.criaCredencial(credencialClienteDTO));
    }

    @PutMapping("/esqueceu/senha")
    @ApiOperation(value = "Permite alterar a senha, se esquecida.")
    public ResponseEntity<CredencialClienteDTO> alterarSenha(@RequestBody AlteraCredencialClienteDTO alteraCredencialClienteDTO){
        return ResponseEntity.ok(credencialClienteUseCase.alteraCredencial(alteraCredencialClienteDTO));
    }

    @PostMapping("/adicionar/role/credencial")
    @ApiOperation(value = "Cria uma nova credencial cliente.")
    public ResponseEntity<?> addRoleToCredencial(@RequestBody RoleToCredentialDTO roleToCredentialDTO){
        credencialClienteUseCase.addRoleToCredencial(roleToCredentialDTO.getUsuario(), roleToCredentialDTO.getNomeRole());
        return ResponseEntity.ok().build();
    }
}
