package br.com.ucsal.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import br.com.ucsal.service.ProdutoService;
import br.com.ucsal.util.Injector;

@WebServlet("/adicionarProduto")
public class ProdutoAdicionarServlet implements Command {

    private ProdutoService produtoService;

    public ProdutoAdicionarServlet() {
        this.produtoService = Injector.createProdutoService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getMethod();

        if ("GET".equalsIgnoreCase(method)) {
            doGet(request, response);
        } else if ("POST".equalsIgnoreCase(method)) {
            doPost(request, response);
        }
    }

    private void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/produtoformulario.jsp");
        dispatcher.forward(request, response);
    }

    private void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String precoStr = request.getParameter("preco");

        if (nome == null || nome.isEmpty() || precoStr == null || precoStr.isEmpty()) {
            request.setAttribute("erro", "Nome e preço são obrigatórios!");
            request.getRequestDispatcher("/WEB-INF/views/produtoformulario.jsp").forward(request, response);
            return;
        }

        try {
            double preco = Double.parseDouble(precoStr);
            produtoService.adicionarProduto(nome, preco);
            response.sendRedirect("listarProdutos");
        } catch (NumberFormatException e) {
            request.setAttribute("erro", "Preço inválido!");
            request.getRequestDispatcher("/WEB-INF/views/produtoformulario.jsp").forward(request, response);
        }
    }
}
