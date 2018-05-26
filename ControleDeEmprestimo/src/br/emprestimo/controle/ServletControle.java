package br.emprestimo.controle;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class ServletControle
 */
@WebServlet("/ServletControle")
public class ServletControle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final WebsiteTitle myapp = new WebsiteTitle();
	private Map<String,ICommand> cmds = new HashMap<String,ICommand>();
	Logger logger = Logger.getLogger("ServletControle");

    /**
     * Default constructor. 
     */
    public ServletControle() {
    	initCommands();
    }
    /**
     * instancia os comandos
     */
    private void initCommands() {
    	cmds.put("/home", new Home());
    	cmds.put("CadastrarLivro", new Cmd_CadastrarLivro());
    	cmds.put("ExcluirLivro", new Cmd_ExcluirLivro());
    	cmds.put("ConsultarLivro", new Cmd_ConsultarLivro());
   	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getRequestURI().substring(request.getContextPath().length());
		String parametro = request.getParameter("acao");
		ICommand acao = null;
		logger.info("caminho = " + path);
		logger.info("acao a ser executada = " + parametro);
		request.setAttribute("myapp", myapp);
			
		try {
			acao = cmds.get(parametro);
        	acao.execute(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}
	
	
}
