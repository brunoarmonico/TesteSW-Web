package br.emprestimo.servico;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import br.emprestimo.modelo.EmprestimoDAO;
import br.emprestimo.modelo.LivroDAO;
import br.emprestimo.modelo.UsuarioDAO;

public class MySQLDAOFactory extends DAOFactory {
	static Logger logger = Logger.getLogger(MySQLDAOFactory.class);
	static String url = "jdbc:mysql://mysql8.db4free.net:3306/sceweb";
	static String driver = "com.mysql.jdbc.Driver";
	static String usuario = "alunos";
	static String senha = "alunosfatec";
	
	// method to create Cloudscape connections
	public static Connection createConnection() {
		try {
			Class.forName(driver);
			
			return (Connection) DriverManager.getConnection(url,usuario,senha);
			}
		catch (CommunicationsException e){
			logger.info("Exceçãoo de comunicacao com o DB causa: " + e.getMessage());
			
			throw new RuntimeException(e); 
		}
		catch (SQLException e){
			logger.info("Exceção geral causa SQLException: " + e.getMessage() + ";" + e.getErrorCode());
			
			throw new RuntimeException(e); 
		}
		catch (Exception e){
			logger.info("Exceçãoo geral causa: " + e.getMessage());
			
			throw new RuntimeException(e); 
		}
	  }
	@Override
	public EmprestimoDAO getEmprestimoDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LivroDAO getLivroDAO() {
		return new LivroDAO();
	}

	@Override
	public UsuarioDAO getUsuarioDAO() {
		// TODO Auto-generated method stub
		return null;
	}

}
