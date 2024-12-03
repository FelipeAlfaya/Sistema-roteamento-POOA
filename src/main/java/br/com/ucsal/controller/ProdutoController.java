package br.com.ucsal.controller;

import java.io.IOException;
import java.util.List;

import br.com.ucsal.model.Produto;
import br.com.ucsal.dao.ProdutoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/view/*")
public class ProdutoController extends HttpServlet {

    private ProdutoDAO produtoDAO;

    public ProdutoController() {
        this.produtoDAO = ProdutoDAO.getInstance();
    }

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

    private void listarProdutos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Produto> produtos = produtoDAO.listarTodos();
        request.setAttribute("produtos", produtos);
        request.getRequestDispatcher("/WEB-INF/views/listarProdutos.jsp").forward(request, response);
    }

    private void adicionarProdutoForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/formProduto.jsp").forward(request, response);
    }

    private void editarProduto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Produto produto = produtoDAO.buscarPorId(Integer.parseInt(id));
        request.setAttribute("produto", produto);
        request.getRequestDispatcher("/WEB-INF/views/formProduto.jsp").forward(request, response);
    }

    private void adicionarProduto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nome = request.getParameter("nome");
        double preco = Double.parseDouble(request.getParameter("preco"));

        Produto produto = new Produto(nome, preco);
        produtoDAO.adicionar(produto);

        response.sendRedirect(request.getContextPath() + "/view/listarProdutos");
    }

    private void editarProdutoProcess(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        String nome = request.getParameter("nome");
        double preco = Double.parseDouble(request.getParameter("preco"));

        Produto produto = new Produto(Integer.parseInt(id), nome, preco);
        produtoDAO.atualizar(produto);

        response.sendRedirect(request.getContextPath() + "/view/listarProdutos");
    }
}
