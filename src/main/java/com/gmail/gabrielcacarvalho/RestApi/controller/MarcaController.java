package com.gmail.gabrielcacarvalho.RestApi.controller;

import com.gmail.gabrielcacarvalho.RestApi.dto.marca.AlteraMarcaDTO;
import com.gmail.gabrielcacarvalho.RestApi.dto.marca.EntradaMarcaDTO;
import com.gmail.gabrielcacarvalho.RestApi.dto.marca.MarcaDTO;
import com.gmail.gabrielcacarvalho.RestApi.service.MarcaService;
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
@RequestMapping("/marca")
@Api(tags = "Marca")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    @GetMapping("/listar")
    @ApiOperation("Retorna as marcas cadastradas de acordo com o filtro.")
    public ResponseEntity<Page<MarcaDTO>> obterMarcas(@PageableDefault(page = 0, size = 1, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.ok(marcaService.obterMarcas(pageable));
    }

    @PostMapping("/nova")
    @ApiOperation("Cadastra uma nova marca e retorna a marca cadastrada.")
    public ResponseEntity<MarcaDTO> criarMarca(@RequestBody EntradaMarcaDTO entradaMarcaDTO){
        return ResponseEntity.ok(marcaService.criaMarca(entradaMarcaDTO));
    }

    @PutMapping("/altera")
    @ApiOperation("Modifica uma marca e retorna e a marca modificada.")
    public ResponseEntity<MarcaDTO> alteraMarca(@RequestBody AlteraMarcaDTO alteraMarcaDTO){
        return ResponseEntity.ok(marcaService.alteraMarca(alteraMarcaDTO));
    }

    @DeleteMapping("/deleta/{idMarca}")
    @ApiOperation("Deleta uma marca.")
    public void deletaMarca(@PathVariable Integer idMarca){
        marcaService.deletaMarca(idMarca);
    }
}
