package br.com.purple.api.usecase;

import br.com.purple.api.converter.Converter;
import br.com.purple.api.converter.produto.AlteraDTOProdutoConverter;
import br.com.purple.api.converter.produto.EntradaDTOProdutoConverter;
import br.com.purple.api.converter.produto.ImagemImagemDTOConverter;
import br.com.purple.api.converter.produto.ProdutoProdutoDTOConverter;
import br.com.purple.api.core.entity.enumerator.Categoria;
import br.com.purple.api.core.entity.model.Imagem;
import br.com.purple.api.core.entity.model.Produto;
import br.com.purple.api.dto.produto.*;
import br.com.purple.api.repositories.ImagemRepository;
import br.com.purple.api.repositories.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
@AllArgsConstructor
public class ProdutoUseCase {

    private ProdutoRepository produtoRepository;
    private ImagemRepository imagemRepository;

    private final Converter<EntradaProdutoDTO, Produto> entradaDTOProdutoConverter = new EntradaDTOProdutoConverter();

    private final Converter<AlteraProdutoDTO, Produto> alteraDTOProdutoConverter = new AlteraDTOProdutoConverter();

    private final Converter<Produto, ProdutoDTO> produtoProdutoDTOConverter = new ProdutoProdutoDTOConverter();

    private final Converter<Imagem, ImagemDTO> imagemImagemDTOConverter = new ImagemImagemDTOConverter();

    public Page<ProdutoDTO> obterProdutos(Pageable pageable, FiltroListarProdutos filtros) {
        return produtoProdutoDTOConverter.from(produtoRepository.findAll(criarFiltroBuscarLista(filtros), pageable));
    }

    public ProdutoDTO obterProduto(Integer idProduto) {
        Optional<Produto> optionalProduto = produtoRepository.findById(idProduto);

        Produto produto;

        if (optionalProduto.isPresent()) {
            produto = optionalProduto.get();
        } else {
            throw new RuntimeException("Produto não existe.");
        }

        return produtoProdutoDTOConverter.from(produto);
    }

    public ProdutoDTO criaProduto(EntradaProdutoDTO entradaProdutoDTO) {
        return produtoProdutoDTOConverter.from(produtoRepository.save(entradaDTOProdutoConverter.from(entradaProdutoDTO)));
    }

    public List<ImagemDTO> obterImagensProduto(Integer idProduto) {

        List<Imagem> imagems = imagemRepository.findByProdutoId(idProduto);

        return imagemImagemDTOConverter.from(imagems);
    }

    public ProdutoDTO adicionaImagemProduto(Integer idProduto, MultipartFile[] imagens) {
        Optional<Produto> optionalProduto = produtoRepository.findById(idProduto);

        Produto produto = new Produto();

        if (optionalProduto.isPresent()) {
            produto = optionalProduto.get();
        }


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

    public Long obterTotalProdutosCadastrados() {
        return produtoRepository.count();
    }

    public ProdutoDTO alteraProduto(AlteraProdutoDTO alteraProdutoDTO) {

        Optional<Produto> optionalProduto = produtoRepository.findById(alteraProdutoDTO.getId());

        Produto produto = new Produto();

        if (optionalProduto.isPresent()) {
            produto = optionalProduto.get();
        }

        Produto alteracaoProduto = alteraDTOProdutoConverter.from(alteraProdutoDTO);

        alteracaoProduto.setPath(produto.getPath());

        return produtoProdutoDTOConverter.from(produtoRepository.save(alteracaoProduto));
    }

    public void deletaProduto(Integer idProduto) {
        imagemRepository.deleteByProdutoId(idProduto);

        Optional<Produto> optionalProduto = produtoRepository.findById(idProduto);

        Produto produto = new Produto();
        if (optionalProduto.isPresent()){
            produto = optionalProduto.get();
        }

        produtoRepository.delete(produto);
    }

    private Specification<Produto> criarFiltroBuscarLista(FiltroListarProdutos filtroListarProdutos) {

        FiltroListarProdutos filtros = Optional.ofNullable(filtroListarProdutos).orElse(new FiltroListarProdutos());

        return (root, query, builder)->{
            List<Predicate> predicates = new ArrayList<>();

            if(filtros.getIdsTipoVestimenta() != null)
                if(filtros.getIdsTipoVestimenta().size() > 0)
                    predicates.add(root.get("tipoVestimenta").get("id").in(filtros.getIdsTipoVestimenta()));

            if (filtros.getCategorias() != null) {
                List<Categoria> categorias = filtros.getCategorias().stream().map(catDTO -> Categoria.valueOf(catDTO.name())).collect(Collectors.toList());
                if (categorias.size() > 0)
                    predicates.add(root.get("categoria").in(categorias));
            }

            if (filtros.getCores() != null)
                if (filtros.getCores().size() > 0)
                    predicates.add(root.get("cor").in(filtros.getCores()));

            if (filtros.getIdMarca() != null)
                predicates.add(builder.equal(root.get("marca").get("id"), filtros.getIdMarca()));

            if (filtros.getIdPromocao() != null)
                predicates.add(builder.equal(root.get("promocao").get("id"), filtros.getIdPromocao()));

            if (filtros.getPrecoMin() != null && filtros.getPrecoMax() != null)
                predicates.add(builder.between(root.get("valorUnitario"), filtros.getPrecoMin(), filtros.getPrecoMax()));

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
