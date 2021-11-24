package br.com.purple.api.dto.cliente.credencial;

import br.com.purple.api.dto.cliente.credencial.role.RoleDTO;
import lombok.Data;

import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Data
public class CredencialDTO {
    @Email
    private String usuario;
    private String senha;
    private List<RoleDTO> roles = new ArrayList<>();
}
