package br.com.purple.api.dto.cliente.credencial;

import br.com.purple.api.dto.cliente.credencial.role.RoleDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CredencialClienteDTO {
    private String usuario;
    private String senha;
    private List<RoleDTO> roles = new ArrayList<>();
}
