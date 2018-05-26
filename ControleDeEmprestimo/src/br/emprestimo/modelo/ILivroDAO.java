package br.emprestimo.modelo;

public interface ILivroDAO  {
	public int adiciona(Livro livro);
	public int exclui (String isbn);
	public Livro consulta(String isbn);
}
