package br.emprestimo.controle;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import br.emprestimo.modelo.ILivroDAO;
import br.emprestimo.modelo.Livro;
import br.emprestimo.servico.DAOFactory;

public class Cmd_ConsultarLivro implements ICommand {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logger logger = Logger.getLogger("Cmd_ConsultarLivro");
		String url = "/FormLivro.jsp";
		Livro livro = new Livro();
		String isbn = request.getParameter("txtISBN");
		logger.info("obtem isbn da pagina  = " + isbn);
		try {
			if (isbn != null) {
				DAOFactory mySQLFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
				ILivroDAO livroDAO = mySQLFactory.getLivroDAO();
				livro = livroDAO.consulta(isbn);
				if (livro != null) {
					logger.info("consulta livro com titulo = " + livro.getTitulo());
					request.setAttribute("isbn", livro.getIsbn());
					request.setAttribute("titulo", livro.getTitulo());
					request.setAttribute("autor", livro.getAutor());
					request.setAttribute("msg", "");
					url = "/FormLivroConsulta.jsp";
				} else {
					request.setAttribute("msg", "isbn invalido");
				}
			} else {
				request.setAttribute("msg", "isbn invalido");
			}
		} catch (Exception e) {
			logger.info(e.getMessage() + e.getCause());
		}
		request.getRequestDispatcher(url).forward(request, response);

	}

}
