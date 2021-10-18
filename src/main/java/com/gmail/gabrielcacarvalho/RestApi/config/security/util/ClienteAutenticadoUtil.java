package com.gmail.gabrielcacarvalho.RestApi.config.security.util;

import org.springframework.security.core.context.SecurityContextHolder;

public class ClienteAutenticadoUtil {

    public static String getUsuarioClienteAutenticado(){
        try{
            return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e){
            return null;
        }
    }
}
