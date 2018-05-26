package br.emprestimo.servico;

import br.emprestimo.modelo.EmprestimoDAO;
import br.emprestimo.modelo.LivroDAO;
import br.emprestimo.modelo.UsuarioDAO;

//Abstract class DAO Factory
public abstract class DAOFactory {

	// List of DAO types supported by the factory
	public static final int MYSQL = 1;
	public static final int ORACLE = 2;
	public static final int SQLSERVER = 3;

	// There will be a method for each DAO that can be
	// created. The concrete factories will have to
	// implement these methods.
	public abstract EmprestimoDAO getEmprestimoDAO();

	public abstract LivroDAO getLivroDAO();

	public abstract UsuarioDAO getUsuarioDAO();

	public static DAOFactory getDAOFactory(int whichFactory) {

		switch (whichFactory) {
		case MYSQL:
			return new MySQLDAOFactory();
		case ORACLE:
			return new OracleDAOFactory();
		case SQLSERVER:
			return new SQLServerDAOFactory();

		default:
			return null;
		}
	}
}