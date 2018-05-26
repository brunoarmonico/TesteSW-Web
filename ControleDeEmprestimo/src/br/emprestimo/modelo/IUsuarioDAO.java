package br.emprestimo.modelo;

public interface IUsuarioDAO {
	public int adiciona(Usuario usuario);
	public int exclui (String ra);
}
