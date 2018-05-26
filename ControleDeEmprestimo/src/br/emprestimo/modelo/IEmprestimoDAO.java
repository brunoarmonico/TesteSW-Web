package br.emprestimo.modelo;

public interface IEmprestimoDAO {
	public int adiciona(Emprestimo emprestimo);
	public int exclui (int numeroEmprestimo);
}
