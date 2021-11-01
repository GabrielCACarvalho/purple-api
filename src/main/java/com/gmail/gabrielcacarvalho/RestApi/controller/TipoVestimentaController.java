package com.gmail.gabrielcacarvalho.RestApi.controller;

import com.gmail.gabrielcacarvalho.RestApi.dto.tipovestimenta.AlteraTipoVestimentaDTO;
import com.gmail.gabrielcacarvalho.RestApi.dto.tipovestimenta.EntradaTipoVestimentaDTO;
import com.gmail.gabrielcacarvalho.RestApi.dto.tipovestimenta.TipoVestimentaDTO;
import com.gmail.gabrielcacarvalho.RestApi.service.TipoVestimentaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tipo-vestimenta")
@Api(tags = "Tipos de vestimentas")
public class TipoVestimentaController {

    @Autowired
    private TipoVestimentaService tipoVestimentaService;

    @GetMapping("/listar")
    @ApiOperation("Retorna tipos de vestimenta de acordo com o filtro.")
    public ResponseEntity<Page<TipoVestimentaDTO>> obterTiposVestimenta(@PageableDefault(page = 0, size = 1, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.ok(tipoVestimentaService.obterTiposVestimenta(pageable));
    }

    @PostMapping("/novo")
    @ApiOperation("Cadastra um novo tipo de vestimenta e retorna o tipo de vestimenta cadastrado.")
    public ResponseEntity<TipoVestimentaDTO> criaTipoVestimenta(@RequestBody EntradaTipoVestimentaDTO entradaTipoVestimentaDTO){
        return ResponseEntity.ok(tipoVestimentaService.criaTipoVestimenta(entradaTipoVestimentaDTO));
    }

    @PutMapping("/altera")
    @ApiOperation("Modifica um tipo vestimenta e retorna o tipo vestimenta modificado.")
    public ResponseEntity<TipoVestimentaDTO> alteraTipoVestimenta(@RequestBody AlteraTipoVestimentaDTO alteraTipoVestimentaDTO){
        return ResponseEntity.ok(tipoVestimentaService.alteraTipoVestimenta(alteraTipoVestimentaDTO));
    }

    @DeleteMapping("/deleta/{idTipoVestimenta}")
    @ApiOperation("Deleta um tipo vestimenta.")
    public void deletaTipoVestimenta(@PathVariable Integer idTipoVestimenta){
        tipoVestimentaService.deletaTipoVestimenta(idTipoVestimenta);
    }
}
