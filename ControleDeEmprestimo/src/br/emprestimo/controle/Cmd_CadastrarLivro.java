package br.emprestimo.controle;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import br.emprestimo.modelo.ILivroDAO;
import br.emprestimo.modelo.Livro;
import br.emprestimo.modelo.LivroDAO;
import br.emprestimo.servico.DAOFactory;


public class Cmd_CadastrarLivro implements ICommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String msg = null;
		Logger logger = Logger.getLogger("Cmd_CadastrarLivro");
		logger.info("chamou o comando cadastrar livro");
		int rc =0; //codigo de retorno da inclusao no DB
		DAOFactory mySQLFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		ILivroDAO livroDAO = mySQLFactory.getLivroDAO();
		Livro livro = new Livro();
		try {
			logger.info("obtem o isbn da pagina = " + request.getParameter("txtISBN"));
			livro.setIsbn(request.getParameter("txtISBN"));
			
			livro.setTitulo(request.getParameter("txtTitulo"));
			livro.setAutor(request.getParameter("txtAutor"));
			rc = livroDAO.adiciona(livro);
			if (rc == 1) {
				msg = "Mensagem = Cadastro realizado com sucesso";
			}else{
				msg = "Mensagem = Dados invalidos";
			}
			request.setAttribute("msg", msg);
			RequestDispatcher view = request.getRequestDispatcher("FormLivro.jsp");
	        view.forward(request, response); 
		} catch (Exception e) {
			msg = e.getMessage();
			logger.info("erro  = " + e.getMessage());
	        request.setAttribute("msg", msg);
	        RequestDispatcher view = request.getRequestDispatcher("FormLivro.jsp");;
	        view.forward(request, response); 
			
		}
		
	}

}
