package br.emprestimo.testeUnitario;

import static org.junit.Assert.*;

import org.junit.Test;

import br.emprestimo.modelo.ILivroDAO;
import br.emprestimo.modelo.Livro;
import br.emprestimo.modelo.LivroDAO;
import br.emprestimo.servico.DAOFactory;

public class UC05ConsultarLivro {

	@Test
	public void CT01ConsultarLivroComSucesso() {
		//cenario
		Livro umLivro = ObtemLivro.comDadosValidos();
		DAOFactory mySQLFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		ILivroDAO livroDAO = mySQLFactory.getLivroDAO();
		livroDAO.adiciona(umLivro);
		//acao
		Livro resultadoObtido = livroDAO.consulta(umLivro.getIsbn());
		//verfificacao
		assertTrue(resultadoObtido.equals(umLivro));
		livroDAO.exclui(umLivro.getIsbn());
		
		
	}

}
