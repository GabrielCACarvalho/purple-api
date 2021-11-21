package br.com.purple.api.controller;

import br.com.purple.api.dto.pedido.FinalizaPedidoDTO;
import br.com.purple.api.dto.pedido.PedidoDTO;
import br.com.purple.api.dto.pedido.PedidoFinalizadoDTO;
import br.com.purple.api.dto.pedido.item.EntradaItemDTO;
import br.com.purple.api.usecase.PedidoUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedido")
@Api(tags = "Pedidos")
public class PedidoController {

    @Autowired
    private PedidoUseCase pedidoUseCase;

    @PostMapping("/novo")
    @ApiOperation("Abre um novo pedido ou 'Carrinho'.")
    public ResponseEntity<PedidoDTO> criaPedido(@RequestBody EntradaItemDTO entradaItemDTO){
        return ResponseEntity.ok(pedidoUseCase.criaPedido(entradaItemDTO));
    }

    @PutMapping("/adicionar/item")
    @ApiOperation("Adiciona item no pedido aberto do cliente autenticado.")
    public ResponseEntity<PedidoDTO> addItem(@RequestBody EntradaItemDTO entradaItemDTO){
        return ResponseEntity.ok(pedidoUseCase.addItem(entradaItemDTO));
    }

    @PutMapping("/retirar/item")
    @ApiOperation("Retira um item do pedido aberto do cliente autenticado.")
    public ResponseEntity<PedidoDTO> retiraItem(@RequestParam Integer idItem){
        return ResponseEntity.ok(pedidoUseCase.retiraItem(idItem));
    }

    @PutMapping("/finaliza")
    @ApiOperation("Finaliza o pedido aberto do cliente autenticado.")
    public ResponseEntity<PedidoFinalizadoDTO> encerraPedido(@RequestBody FinalizaPedidoDTO finalizaPedidoDTO){
        return ResponseEntity.ok(pedidoUseCase.finalizaPedido(finalizaPedidoDTO));
    }

    @DeleteMapping("/cancela")
    @ApiOperation("Cancela o pedido aberto do cliente autenticado.")
    public void cancelaPedido(){
        pedidoUseCase.cancelaPedido();
    }
}
