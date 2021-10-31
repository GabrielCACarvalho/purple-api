package com.gmail.gabrielcacarvalho.RestApi.service;

import com.gmail.gabrielcacarvalho.RestApi.converter.Converter;
import com.gmail.gabrielcacarvalho.RestApi.converter.produto.AlteraDTOProdutoConverter;
import com.gmail.gabrielcacarvalho.RestApi.converter.produto.EntradaDTOProdutoConverter;
import com.gmail.gabrielcacarvalho.RestApi.converter.produto.ImagemImagemDTOConverter;
import com.gmail.gabrielcacarvalho.RestApi.converter.produto.ProdutoProdutoDTOConverter;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Imagem;
import com.gmail.gabrielcacarvalho.RestApi.core.entity.model.Produto;
import com.gmail.gabrielcacarvalho.RestApi.dto.produto.*;
import com.gmail.gabrielcacarvalho.RestApi.repositories.ImagemRepository;
import com.gmail.gabrielcacarvalho.RestApi.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ImagemRepository imagemRepository;

    private Converter<EntradaProdutoDTO, Produto> entradaDTOProdutoConverter = new EntradaDTOProdutoConverter();

    private Converter<AlteraProdutoDTO, Produto> alteraDTOProdutoConverter = new AlteraDTOProdutoConverter();

    private Converter<Produto, ProdutoDTO> produtoProdutoDTOConverter = new ProdutoProdutoDTOConverter();

    private Converter<Imagem, ImagemDTO> imagemImagemDTOConverter = new ImagemImagemDTOConverter();

    public Page<ProdutoDTO> obterProdutos(Pageable pageable, FiltroListarProdutos filtros) {
        return produtoProdutoDTOConverter.from(produtoRepository.findAll(criarFiltroBuscarLista(filtros), pageable));
    }

    public ProdutoDTO obterProduto(Integer idProduto) {
        return produtoProdutoDTOConverter.from(produtoRepository.findById(idProduto).get());
    }

    public ProdutoDTO criaProduto(EntradaProdutoDTO entradaProdutoDTO) {
        return produtoProdutoDTOConverter.from(produtoRepository.save(entradaDTOProdutoConverter.from(entradaProdutoDTO)));
    }

    public List<ImagemDTO> obterImagensProduto(Integer idProduto) {

        List<Imagem> imagems = imagemRepository.findByProdutoId(idProduto);

        return imagemImagemDTOConverter.from(imagems);
    }

    public ProdutoDTO adicionaImagemProduto(Integer idProduto, MultipartFile[] imagens) {
        Produto produto = produtoRepository.findById(idProduto).get();

        List<Imagem> imgs = Arrays
                .stream(imagens)
                .map(imagem -> {
                    Imagem img = new Imagem();
                    Produto pdt = new Produto();
                    pdt.setId(idProduto);
                    img.setProduto(pdt);
                    img.setNome(imagem.getOriginalFilename());
                    try {
                        img.setArquivo(imagem.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return imagemRepository.save(img);
                })
                .collect(Collectors.toList());

        produto.setImagens(imgs);

        return produtoProdutoDTOConverter.from(produtoRepository.save(produto));
    }

    public ProdutoDTO alteraProduto(AlteraProdutoDTO alteraProdutoDTO) {
        return produtoProdutoDTOConverter.from(produtoRepository.save(alteraDTOProdutoConverter.from(alteraProdutoDTO)));
    }

    private Specification<Produto> criarFiltroBuscarLista(FiltroListarProdutos filtroListarProdutos) {

        FiltroListarProdutos filtros = Optional.ofNullable(filtroListarProdutos).orElse(new FiltroListarProdutos());

        return (root, query, builder)->{
            List<Predicate> predicates = new ArrayList<>();

            if(filtros.getIdTipoVestimenta() != null)
                predicates.add(builder.equal(root.get("tipoVestimenta").get("id"), filtros.getIdTipoVestimenta()));

            if (filtros.getCategoria() != null)
                predicates.add(builder.equal(root.get("categoria"), filtros.getCategoria()));

            if (filtros.getCor() != null)
                predicates.add(builder.equal(root.get("cor"), filtros.getCor()));

            if (filtros.getIdMarca() != null)
                predicates.add(builder.equal(root.get("marca").get("id"), filtros.getIdMarca()));

            if (filtros.getIdPromocao() != null)
                predicates.add(builder.equal(root.get("promocao").get("id"), filtros.getIdPromocao()));

            if (filtros.getTamanho() != null)
                predicates.add(builder.equal(root.get("tamanho"), filtros.getTamanho()));

            if (filtros.getPrecoMin() != null && filtros.getPrecoMax() != null)
                predicates.add(builder.between(root.get("valorUnitario"), filtros.getPrecoMin(), filtros.getPrecoMax()));

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
