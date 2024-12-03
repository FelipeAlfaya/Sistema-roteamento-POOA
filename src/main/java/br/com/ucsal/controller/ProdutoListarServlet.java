package br.com.ucsal.controller;

import java.io.IOException;
import java.util.List;

import br.com.ucsal.model.Produto;
import br.com.ucsal.persistencia.HSQLProdutoRepository;
import br.com.ucsal.service.ProdutoService;
import br.com.ucsal.util.Injector;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/view/listarProdutos")
public class ProdutoListarServlet extends HttpServlet {

    private ProdutoService produtoService;

    public ProdutoListarServlet() {
        this.produtoService = Injector.createProdutoService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtém a lista de produtos
        List<Produto> produtos = produtoService.listarProdutos();

        // Define a lista de produtos como atributo da requisição
        request.setAttribute("produtos", produtos);

        // Encaminha para a página JSP dentro de WEB-INF
        request.getRequestDispatcher("/WEB-INF/views/produtolista.jsp").forward(request, response);
    }
}



