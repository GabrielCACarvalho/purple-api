package com.gmail.gabrielcacarvalho.RestApi.config.security.util;

import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.CredencialCliente;
import org.springframework.security.core.context.SecurityContextHolder;

public class ClienteAutenticadoUtil {

    public static CredencialCliente getClienteAutenticado(){
        try{
            return (CredencialCliente) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e){
            return null;
        }
    }

    public static String getUsuarioClienteAutenticado(){
        return getClienteAutenticado().getUsuario();
    }
}
