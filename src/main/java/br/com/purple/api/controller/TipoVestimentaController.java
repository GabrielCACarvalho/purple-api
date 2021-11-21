package br.com.purple.api.controller;

import br.com.purple.api.dto.tipovestimenta.AlteraTipoVestimentaDTO;
import br.com.purple.api.dto.tipovestimenta.EntradaTipoVestimentaDTO;
import br.com.purple.api.dto.tipovestimenta.TipoVestimentaDTO;
import br.com.purple.api.usecase.TipoVestimentaUseCase;
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
    private TipoVestimentaUseCase tipoVestimentaUseCase;

    @GetMapping("/{idTipoVestimenta}")
    @ApiOperation("Retorna o tipo vestimenta pelo id.")
    public ResponseEntity<TipoVestimentaDTO> obterTipoVestimenta(@PathVariable Integer idTipoVestimenta){
        return ResponseEntity.ok(tipoVestimentaUseCase.obterTipoVestimenta(idTipoVestimenta));
    }

    @GetMapping("/listar")
    @ApiOperation("Retorna tipos de vestimenta de acordo com o filtro.")
    public ResponseEntity<Page<TipoVestimentaDTO>> obterTiposVestimenta(@PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.ok(tipoVestimentaUseCase.obterTiposVestimenta(pageable));
    }

    @PostMapping("/novo")
    @ApiOperation("Cadastra um novo tipo de vestimenta e retorna o tipo de vestimenta cadastrado.")
    public ResponseEntity<TipoVestimentaDTO> criaTipoVestimenta(@RequestBody EntradaTipoVestimentaDTO entradaTipoVestimentaDTO){
        return ResponseEntity.ok(tipoVestimentaUseCase.criaTipoVestimenta(entradaTipoVestimentaDTO));
    }

    @PutMapping("/altera")
    @ApiOperation("Modifica um tipo vestimenta e retorna o tipo vestimenta modificado.")
    public ResponseEntity<TipoVestimentaDTO> alteraTipoVestimenta(@RequestBody AlteraTipoVestimentaDTO alteraTipoVestimentaDTO){
        return ResponseEntity.ok(tipoVestimentaUseCase.alteraTipoVestimenta(alteraTipoVestimentaDTO));
    }

    @DeleteMapping("/deleta/{idTipoVestimenta}")
    @ApiOperation("Deleta um tipo vestimenta.")
    public void deletaTipoVestimenta(@PathVariable Integer idTipoVestimenta){
        tipoVestimentaUseCase.deletaTipoVestimenta(idTipoVestimenta);
    }
}
