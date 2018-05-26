package br.emprestimo.controle;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import br.emprestimo.modelo.Emprestimo;
import br.emprestimo.modelo.Livro;
import br.emprestimo.modelo.Usuario;
import br.emprestimo.servico.ServicoEmprestimo;

public class Cmd_RegistrarEmprestimo implements ICommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String msg = null;
		Logger logger = Logger.getLogger("Cmd_RegistrarEmprestimo");
		logger.info("chamou o comando registrar emprestimo");
		int rc =0; //codigo de retorno da inclusao no DB
		ServicoEmprestimo servico = new ServicoEmprestimo();
		Emprestimo emprestimo = new Emprestimo();
		Livro livro = new Livro();
		Usuario usuario = new Usuario();
		try {
			usuario.setRa(request.getParameter("txtRa"));
			livro.setIsbn(request.getParameter("txtIsbn"));
			emprestimo = servico.empresta(livro, usuario);
			if (rc == 1) {
				msg = "cadastro realizado com sucesso";
			}else{
				msg = "dados invalidos";
			}
			request.setAttribute("msg", msg);
			RequestDispatcher view = request.getRequestDispatcher("FormEmprestimo.jsp");
	        view.forward(request, response); 
		} catch (Exception e) {
			msg = e.getMessage();
			logger.info("erro  = " + e.getMessage());
	        request.setAttribute("msg", msg);
	        RequestDispatcher view = request.getRequestDispatcher("FormEmprestimo.jsp");;
	        view.forward(request, response); 
			
		}
		
	}

}
