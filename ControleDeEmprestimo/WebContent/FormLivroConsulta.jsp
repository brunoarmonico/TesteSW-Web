<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" language="javascript">
	
	function voltar() {
		history.go(-1)
		
	}
</script>
<title>Consulta Livroa</title>
<link rel="stylesheet" type="text/css" href="/ControleDeEmprestimo/CSS/Formato.css">
</head>
<body>
<jsp:include page="Cabecalho.jsp" />
	<div id="principal">
		<div id="titulo">
			<h3>Mantem Livro - consulta</h3>
		</div>
		<hr>
		<div id="formulario2">

			<form name="formLivroConsultar" action="/ServletControle" method="post">
				<table id="tabcampos">
				
					<tr>
						<td><label> ISBN:</label></td>
						<td><input id="campo" size="90" type="text" name="txtISBN"
							value="<% out.println(request.getAttribute("isbn")); %>" disabled="disabled">
						</td>
					</tr>
					<tr>
						<td><label> Titulo:</label></td>
						<td><input id="campo" size="15" type="text" name="txtTitulo"
							value="<% out.println(request.getAttribute("titulo")); %>" disabled="disabled">
						</td>
					</tr>
					<tr>
						<td><label> Autor:</label></td>
						<td><input id="campo" size="50" type="text" name="txtAutor"
							value="<% out.println(request.getAttribute("autor")); %>"></td>
					</tr>
					
					
					<tr>
						<td colspan=2 id="botoes" align="center">
						
						<input id="botao" type=reset name=btnLimpar onclick=limpar() value=Limpar>
					    <input id="botao" type=button name=btnVoltar onclick=voltar() value=Voltar>
					    </td>
					</tr>
				</table>
				<input type="hidden" name="acao" value="">
				<input type="hidden" name="menu" value="principal">
				
				<%	
				    request.setAttribute("cnpj", request.getAttribute("cnpj"));
				    String msg = (String) request.getAttribute("msg"); 
					if(msg == null)
						msg="";
				%>
				<label><%=msg%></label>
			</form>
		</div>
	</div>
	<jsp:include page="Rodape.jsp" />
</body>
</html>