package br.emprestimo.modelo;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import br.emprestimo.servico.FabricaDeConexoes;
import br.emprestimo.servico.MySQLDAOFactory;

public class UsuarioDAO implements IUsuarioDAO {
	Logger logger = Logger.getLogger(UsuarioDAO.class);
	@Override
	public int adiciona(Usuario usuario) {
		PreparedStatement ps;
		int codigoRetorno = 0;
		try (Connection conn = MySQLDAOFactory.createConnection()) {
			ps = (PreparedStatement) conn.prepareStatement("insert into Usuario (ra, nome) values(?,?)");
			ps.setString(1, usuario.getRa());
			ps.setString(2, usuario.getNome());
			codigoRetorno = ps.executeUpdate();
			logger.info("codigo de retorno do metodo adiciona usuario= " + codigoRetorno);

			ps.close();

		} catch (SQLException e) {
			logger.info("SQLException metodo adiciona usuario= " + e.getMessage());
			throw new RuntimeException(e);
		}
		return codigoRetorno;
	}

	@Override
	public int exclui(String ra) {
		java.sql.PreparedStatement ps;
		int codigoretorno = 0;
		try (Connection conn = new FabricaDeConexoes().getConnection()) {
			ps= conn.prepareStatement ("delete from Usuario where ra = ?");
			ps.setString(1, ra);
			codigoretorno = ps.executeUpdate();
			}
		catch (SQLException e){
			logger.info("SQLException do metodo exclui = " + e.getMessage());
			throw new RuntimeException(e);
		}
	return codigoretorno;
	}

}