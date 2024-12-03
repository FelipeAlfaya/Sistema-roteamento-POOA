package br.com.ucsal.dao;

import br.com.ucsal.model.Produto;
import java.util.List;
import java.util.ArrayList;

public class ProdutoDAO {

    // Simulação de banco de dados
    private static List<Produto> produtos = new ArrayList<>();

    // Método para listar todos os produtos
    public static List<Produto> listarTodos() {
        return produtos;
    }

    // Método para buscar um produto pelo ID
    public static Produto buscarPorId(int id) {
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                return produto;
            }
        }
        return null;
    }

    // Método para adicionar um produto
    public static void adicionar(Produto produto) {
        produto.setId(produtos.size() + 1);
        produtos.add(produto);
    }

    // Método para atualizar um produto
    public static void atualizar(Produto produto) {
        Produto produtoExistente = buscarPorId(produto.getId());
        if (produtoExistente != null) {
            produtoExistente.setNome(produto.getNome());
            produtoExistente.setPreco(produto.getPreco());
        }
    }

    // Método para excluir um produto
    public static void excluir(int id) {
        Produto produto = buscarPorId(id);
        if (produto != null) {
            produtos.remove(produto);
        }
    }
}
