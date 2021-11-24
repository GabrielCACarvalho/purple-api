package br.com.purple.api.controller;

import br.com.purple.api.config.security.dto.AuthenticationResponseDTO;
import br.com.purple.api.dto.cliente.credencial.AlteraCredencialDTO;
import br.com.purple.api.dto.cliente.credencial.CredencialDTO;
import br.com.purple.api.dto.cliente.credencial.LoginClienteDTO;
import br.com.purple.api.dto.cliente.credencial.RoleToCredentialDTO;
import br.com.purple.api.core.usecase.CredencialUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/credencial")
@Api(tags = "Credencial")
@RequiredArgsConstructor
public class CredencialController {

    private final CredencialUseCase credencialUseCase;

    @PostMapping("/login")
    @ApiOperation("Efetua o login do cliente.")
    public ResponseEntity<AuthenticationResponseDTO> logar(@RequestBody LoginClienteDTO loginClienteDTO){
        return ResponseEntity.ok(credencialUseCase.autentica(loginClienteDTO));
    }

    @PostMapping("/nova")
    @ApiOperation(value = "Cria uma nova credencial cliente.")
    public ResponseEntity<CredencialDTO> criarCredencial(@RequestBody CredencialDTO credencialDTO){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/credencial/nova").toUriString());
        return ResponseEntity.created(uri).body(credencialUseCase.criaCredencial(credencialDTO));
    }

    @PutMapping("/esqueceu/senha")
    @ApiOperation(value = "Permite alterar a senha, se esquecida.")
    public ResponseEntity<CredencialDTO> alterarSenha(@RequestBody AlteraCredencialDTO alteraCredencialDTO){
        return ResponseEntity.ok(credencialUseCase.alteraCredencial(alteraCredencialDTO));
    }

    @PostMapping("/adicionar/role/credencial")
    @ApiOperation(value = "Adiciona uma nova permissão na credencial do usuário.")
    public ResponseEntity<?> addRoleToCredencial(@RequestBody RoleToCredentialDTO roleToCredentialDTO){
        credencialUseCase.addRoleToCredencial(roleToCredentialDTO.getUsuario(), roleToCredentialDTO.getNomeRole());
        return ResponseEntity.ok().build();
    }
}
