package br.emprestimo.controle;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import br.emprestimo.modelo.Livro;
import br.emprestimo.modelo.LivroDAO;

public class Cmd_ExcluirLivro implements ICommand{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String msg = null;
		Logger logger = Logger.getLogger("Cmd_ExcluirLivro");
		logger.info("chamou o comando excluir livro");
		int rc =0; //codigo de retorno da inclusao no DB
		LivroDAO livroDAO = new LivroDAO();
		Livro livro = new Livro();
		try {
			logger.info("obtem o isbn da pagina = " + request.getParameter("txtISBN"));
			livro.setIsbn(request.getParameter("txtISBN"));
			rc = livroDAO.exclui(livro.getIsbn());
			if (rc == 1) {
				msg = "Mensagem = Exclusao realizada com sucesso";
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
