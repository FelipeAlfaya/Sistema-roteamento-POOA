package br.com.ucsal.model;

public class Produto {

	private Integer id;  // ID do produto (opcional, caso use para banco de dados)
	private String nome; // Nome do produto
	private double preco; // Preço do produto

	// Construtor com nome e preço (sem ID)
	public Produto(String nome, double preco) {
		this.nome = nome;
		this.preco = preco;
	}

	// Construtor com id, nome e preço (usado quando o ID for necessário)
	public Produto(Integer id, String nome, double preco) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}

	// Getters e Setters para os atributos

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	@Override
	public String toString() {
		return "Produto{" +
				"id=" + id +
				", nome='" + nome + '\'' +
				", preco=" + preco +
				'}';
	}
}
