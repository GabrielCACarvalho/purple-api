package br.com.purple.api.usecase;

import br.com.purple.api.converter.Converter;
import br.com.purple.api.converter.cliente.credencial.CredencialCredencialDTOConverter;
import br.com.purple.api.converter.cliente.credencial.CredencialDTOCredencialConverter;
import br.com.purple.api.converter.cliente.credencial.role.RoleDtoRoleConverter;
import br.com.purple.api.core.entity.model.CredencialCliente;
import br.com.purple.api.core.entity.model.Role;
import br.com.purple.api.dto.cliente.credencial.AlteraCredencialClienteDTO;
import br.com.purple.api.dto.cliente.credencial.CredencialClienteDTO;
import br.com.purple.api.repositories.CredencialClienteRepository;
import br.com.purple.api.repositories.RoleRepository;
import br.com.purple.api.converter.cliente.credencial.role.AlteraDTORoleConverter;
import br.com.purple.api.converter.cliente.credencial.role.EntradaDTORoleConverter;
import br.com.purple.api.converter.cliente.credencial.role.RoleRoleDTOConverter;
import br.com.purple.api.dto.cliente.credencial.role.AlteraRoleDTO;
import br.com.purple.api.dto.cliente.credencial.role.EntradaRoleDTO;
import br.com.purple.api.dto.cliente.credencial.role.RoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CredencialClienteUseCase implements UserDetailsService {

    @Autowired
    private CredencialClienteRepository credencialClienteRepository;
    @Autowired
    private RoleRepository roleRepository;

    private Converter<CredencialCliente, CredencialClienteDTO> credencialCredencialDTOConverter = new CredencialCredencialDTOConverter();
    private Converter<CredencialClienteDTO, CredencialCliente> credencialDTOCredencialConverter = new CredencialDTOCredencialConverter();
    private Converter<Role, RoleDTO> roleRoleDTOConverter = new RoleRoleDTOConverter();
    private Converter<EntradaRoleDTO, Role> entradaDTORoleConverter = new EntradaDTORoleConverter();
    private Converter<AlteraRoleDTO, Role> alteraDTORoleConverter = new AlteraDTORoleConverter();

    @Override
    public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
        CredencialCliente credencialCliente = credencialClienteRepository.findByUsuario(usuario);
        if (credencialCliente == null)
            throw new UsernameNotFoundException("Usuário não encontrado no banco de dados.");
        return new User(credencialCliente.getUsuario(), credencialCliente.getSenha(),
                credencialCliente.getRoles()
                        .stream()
                        .map(role -> new SimpleGrantedAuthority(role.getNome()))
                        .collect(Collectors.toList()));
    }

    public CredencialClienteDTO criaCredencial(CredencialClienteDTO credencialClienteDTO){
        credencialClienteDTO.setSenha(passwordEncoder().encode(credencialClienteDTO.getSenha()));
        return credencialCredencialDTOConverter.from(credencialClienteRepository
                .save(credencialDTOCredencialConverter.from(credencialClienteDTO)));
    }

    public RoleDTO salvaRole(EntradaRoleDTO entradaRoleDTO){
        return roleRoleDTOConverter.from(roleRepository.save(entradaDTORoleConverter.from(entradaRoleDTO)));
    }

    public RoleDTO alteraRole(AlteraRoleDTO alteraRoleDTO) {
        return roleRoleDTOConverter.from(roleRepository.save(alteraDTORoleConverter.from(alteraRoleDTO)));
    }

    public Page<RoleDTO> obterRoles(Pageable pageable) {
        Page<Role> roles = roleRepository.findAll(pageable);

        return roleRoleDTOConverter.from(roles);
    }

    public void deleteRole(Integer idRole) {
        roleRepository.delete(roleRepository.getById(idRole));
    }

    public void addRoleToCredencial(String email, String nomeRole){
        CredencialCliente credencialCliente = credencialClienteRepository.findByUsuario(email);
        Role role = roleRepository.findByNome(nomeRole);
        credencialCliente.getRoles().add(role);
    }

    public CredencialClienteDTO alteraCredencial(AlteraCredencialClienteDTO alteraCredencialClienteDTO) {
        CredencialCliente credencialCliente = credencialClienteRepository.findByUsuario(alteraCredencialClienteDTO.getUsuario());

        credencialCliente.setSenha(passwordEncoder().encode(alteraCredencialClienteDTO.getNovaSenha()));

        return credencialCredencialDTOConverter.from(credencialClienteRepository.save(credencialCliente));
    }

    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
