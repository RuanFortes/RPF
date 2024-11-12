package gerenciador;

import modelo.Produto;

import java.util.List;
import java.util.stream.Collectors;

public class GerenciadorProduto extends Produto {
    private int proximoId;
    private List<Produto> produtos;
    public GerenciadorProduto(String nome, double preco, int quantidadeEstoque, String categoria) {
        super(nome, preco, quantidadeEstoque, categoria);
    }
    public void criar(Produto produto) {
       try{ validarProduto(produto);
        produto.setId(proximoId++);
        produtos.add(produto);
    } catch (Validacao)

    public Produto buscarPorId(int id) {
        return produtos.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    } catch

    public List<Produto> listarTodos() {
        return new List<>(produtos);
    }

    public boolean atualizar(Produto produto) {
        Produto produtoExistente = buscarPorId(produto.getId());
        if (produtoExistente != null) {
            produtoExistente.setNome(produto.getNome());
            produtoExistente.setPreco(produto.getPreco());
            produtoExistente.setQuantidadeEstoque(produto.getQuantidadeEstoque());
            produtoExistente.setCategoria(produto.getCategoria());
            return true;
        }
        return false;
    }

    public boolean deletar(int id) {
        Produto produto = buscarPorId(id);
        if (produto != null) {
            produtos.remove(produto);
            return true;
        }
        return false;
    }

    public List<Produto> buscarPorNome(String nome) {
        return produtos.stream()
                .filter(p -> p.getNome().toLowerCase().contains(nome.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Produto> buscarPorCategoria(String categoria) {
        return produtos.stream()
                .filter(p -> p.getCategoria().toLowerCase().equals(categoria.toLowerCase()))
                .collect(Collectors.toList());
    }

    private void validarProduto(Produto produto) {
        if (produto.getNome().isEmpty() || produto.getNome().length() < 2) {
            ValidacaoException("Nome do produto inválido.");
        }
        if (produto.getPreco() <= 0) {
            ValidacaoException("Preço deve ser maior que zero.");
        }
        if (produto.getQuantidadeEstoque() < 0) {
             ValidacaoException("Quantidade em estoque não pode ser negativa.");
        }
        if (produto.getCategoria().isEmpty()) {
             ValidacaoException("Categoria não pode ser vazia.");
        }
    }

}
