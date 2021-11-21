package br.com.purple.api.handler;

import br.com.purple.api.handler.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HttpExceptionHandler {

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public ErrorDto handleRuntime(RuntimeException e){
        return new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }
}
