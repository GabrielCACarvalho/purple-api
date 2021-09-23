package com.gmail.gabrielcacarvalho.RestApi.service;

import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.CredencialCliente;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Role;
import com.gmail.gabrielcacarvalho.RestApi.repositories.CredencialClienteRepository;
import com.gmail.gabrielcacarvalho.RestApi.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CredencialClienteService implements UserDetailsService {

    @Autowired
    private CredencialClienteRepository credencialClienteRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

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

    public CredencialCliente salvaCredencial(CredencialCliente credencialCliente){
        credencialCliente.setSenha(passwordEncoder.encode(credencialCliente.getSenha()));
        return credencialClienteRepository.save(credencialCliente);
    }

    public Role salvaRole(Role role){//TODO: Criar os DTOS de Role
        return roleRepository.save(role);
    }

    public void addRoleToCredencial(String email, String nomeRole){
        CredencialCliente credencialCliente = credencialClienteRepository.findByUsuario(email);
        Role role = roleRepository.findByNome(nomeRole);
        credencialCliente.getRoles().add(role);
    }

    public CredencialCliente obterCredencialPorEmail(String usuario) {
        return credencialClienteRepository.findByUsuario(usuario);
    }


}
