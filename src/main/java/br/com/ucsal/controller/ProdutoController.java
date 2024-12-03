package br.com.ucsal.controller;

import java.io.IOException;
import java.util.List;

import br.com.ucsal.model.Produto;
import br.com.ucsal.dao.ProdutoDAO;  // Supondo que você tenha uma classe DAO para manipulação de produtos
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/view/*")  // Mapeia todas as requisições com "/view/*"
public class ProdutoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();

        if (path.equals("/listarProdutos")) {
            listarProdutos(request, response);
        } else if (path.equals("/editarProduto")) {
            editarProduto(request, response);
        } else if (path.equals("/adicionarProduto")) {
            adicionarProdutoForm(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Página não encontrada");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();

        if (path.equals("/adicionarProduto")) {
            adicionarProduto(request, response);
        } else if (path.equals("/editarProduto")) {
            editarProdutoProcess(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Página não encontrada");
        }
    }

    // Exibe a lista de produtos
    private void listarProdutos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Produto> produtos = ProdutoDAO.listarTodos();  // Supondo que ProdutoDAO tenha esse método
        request.setAttribute("produtos", produtos);
        request.getRequestDispatcher("/WEB-INF/views/listarProdutos.jsp").forward(request, response);
    }

    // Exibe o formulário para adicionar um novo produto
    private void adicionarProdutoForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/formProduto.jsp").forward(request, response);
    }

    // Exibe o formulário para editar um produto
    private void editarProduto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Produto produto = ProdutoDAO.buscarPorId(Integer.parseInt(id));  // Supondo que ProdutoDAO tenha esse método
        request.setAttribute("produto", produto);
        request.getRequestDispatcher("/WEB-INF/views/formProduto.jsp").forward(request, response);
    }

    // Processa a adição de um novo produto
    private void adicionarProduto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nome = request.getParameter("nome");
        double preco = Double.parseDouble(request.getParameter("preco"));

        Produto produto = new Produto(nome, preco);
        ProdutoDAO.adicionar(produto);

        response.sendRedirect(request.getContextPath() + "/view/listarProdutos");
    }

    private void editarProdutoProcess(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        String nome = request.getParameter("nome");
        double preco = Double.parseDouble(request.getParameter("preco"));

        Produto produto = new Produto(Integer.parseInt(id), nome, preco);
        ProdutoDAO.atualizar(produto);

        response.sendRedirect(request.getContextPath() + "/view/listarProdutos");
    }
}
