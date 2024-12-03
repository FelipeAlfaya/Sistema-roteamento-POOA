package br.com.ucsal.controller;

import br.com.ucsal.service.ProdutoService;
import br.com.ucsal.persistencia.HSQLProdutoRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/excluirProduto")
public class ProdutoExcluirServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProdutoService produtoService;

	public ProdutoExcluirServlet() {
		this.produtoService = new ProdutoService(new HSQLProdutoRepository());
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));

			produtoService.removerProduto(id);

			response.sendRedirect("listarProdutos");

		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido.");
		}
	}
}
