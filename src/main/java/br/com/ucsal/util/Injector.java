package br.com.ucsal.util;

import br.com.ucsal.persistencia.HSQLProdutoRepository;
import br.com.ucsal.service.ProdutoService;

public class Injector {

    public static ProdutoService createProdutoService() {
        // Cria o repositório
        HSQLProdutoRepository produtoRepository = new HSQLProdutoRepository();

        // Cria e retorna o serviço, injetando o repositório
        return new ProdutoService(produtoRepository);
    }

    // Você pode adicionar mais métodos para injetar outras dependências no futuro
}