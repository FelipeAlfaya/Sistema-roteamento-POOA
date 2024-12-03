package br.com.ucsal.util;

import br.com.ucsal.persistencia.HSQLProdutoRepository;
import br.com.ucsal.service.ProdutoService;

public class Injector {

    public static ProdutoService createProdutoService() {
        HSQLProdutoRepository produtoRepository = new HSQLProdutoRepository();

        return new ProdutoService(produtoRepository);
    }
}