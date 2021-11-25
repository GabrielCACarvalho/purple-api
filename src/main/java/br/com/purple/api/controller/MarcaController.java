package br.com.purple.api.controller;

import br.com.purple.api.dto.marca.AlteraMarcaDTO;
import br.com.purple.api.dto.marca.EntradaMarcaDTO;
import br.com.purple.api.dto.marca.MarcaDTO;
import br.com.purple.api.usecase.MarcaUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static br.com.purple.api.utils.ControllerDescription.MARCA_DESCRIPTION;
import static br.com.purple.api.utils.ControllerDescription.MARCA_TAG;

@RestController
@RequestMapping("/marca")
@Api(tags = MARCA_TAG, description = MARCA_DESCRIPTION)
@AllArgsConstructor
public class MarcaController {

    private MarcaUseCase marcaUseCase;

    @GetMapping("/{idMarca}")
    @ApiOperation("Retorna a marca pelo id.")
    public ResponseEntity<MarcaDTO> obterMarca(@PathVariable Integer idMarca){
        return ResponseEntity.ok(marcaUseCase.obterMarca(idMarca));
    }

    @GetMapping("/listar")
    @ApiOperation("Retorna as marcas cadastradas de acordo com o filtro.")
    public ResponseEntity<Page<MarcaDTO>> obterMarcas(@PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.ok(marcaUseCase.obterMarcas(pageable));
    }

    @PostMapping("/nova")
    @ApiOperation("Cadastra uma nova marca e retorna a marca cadastrada.")
    public ResponseEntity<MarcaDTO> criarMarca(@RequestBody EntradaMarcaDTO entradaMarcaDTO){
        return ResponseEntity.ok(marcaUseCase.criaMarca(entradaMarcaDTO));
    }

    @PutMapping("/altera")
    @ApiOperation("Modifica uma marca e retorna e a marca modificada.")
    public ResponseEntity<MarcaDTO> alteraMarca(@RequestBody AlteraMarcaDTO alteraMarcaDTO){
        return ResponseEntity.ok(marcaUseCase.alteraMarca(alteraMarcaDTO));
    }

    @DeleteMapping("/deleta/{idMarca}")
    @ApiOperation("Deleta uma marca.")
    public void deletaMarca(@PathVariable Integer idMarca){
        marcaUseCase.deletaMarca(idMarca);
    }
}
