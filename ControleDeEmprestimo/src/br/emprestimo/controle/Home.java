package br.emprestimo.controle;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Home implements ICommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		RequestDispatcher view = request.getRequestDispatcher("home.jsp");
        view.forward(request, response); 	
	}

}