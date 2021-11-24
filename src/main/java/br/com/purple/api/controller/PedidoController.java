package br.com.purple.api.controller;

import br.com.purple.api.dto.pedido.*;
import br.com.purple.api.dto.pedido.item.EntradaItemDTO;
import br.com.purple.api.core.usecase.PedidoUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/pedido")
@Api(tags = "Pedidos")
public class PedidoController {

    @Autowired
    private PedidoUseCase pedidoUseCase;

    @GetMapping("/total")
    @ApiOperation("Consulta o total de pedidos finalizados em um período de tempo.")
    public ResponseEntity<Long> totalPedidos(FiltroTotalPedidos filtro){
        return ResponseEntity.ok(pedidoUseCase.obterTotalPedidos(filtro));
    }

    @GetMapping("/total/renda")
    @ApiOperation("Consulta a renda gerada pelas vendas em um período de tempo.")
    public ResponseEntity<BigDecimal> totalRenda(FiltroRendaTotal filtro){
        return ResponseEntity.ok(pedidoUseCase.obterRendaTotal(filtro));
    }

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
