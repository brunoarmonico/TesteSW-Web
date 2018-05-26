<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
            label {
                font: normal 12px courier !important;
            }
</style>
<title>Manter Livro</title>
<link rel="stylesheet" type="text/css" href="/ControleDeEmprestimo/CSS/Formato.css">
<script type="text/javascript" language="javascript">
	
	function incluir() {
		document.formLivroIncluir.acao.value = "CadastrarLivro";
		document.formLivroIncluir.submit();
	}
	function consultar() {
		document.formLivroIncluir.acao.value = "ConsultarLivro";
		document.formLivroIncluir.submit();
	}
	function excluir() {
		document.formLivroIncluir.acao.value = "ExcluirLivro";
		document.formLivroIncluir.submit();
	}
</script>
</head>
<body>
	<jsp:include page="Cabecalho.jsp" />
	<div id="principal">
		<div id="titulo">
			<h3>Mantem Cadastro de Livros</h3>
		</div>
		<hr>
		<div id="formulario2">

			<form name="formLivroIncluir" action="/ControleDeEmprestimo/ServletControle" method="post">
				<table id="tabcampos">
					<tr>
						<td><label> Titulo:</label></td>
						<td><input id="txttitulo" size="70" type="text" name="txtTitulo"
							value=""></td>
					</tr>
					<tr>
						<td><label> ISBN:</label></td>
						<td><input id="txtisbn" size="15" type="text" name="txtISBN"
							value=""></td>
					</tr>
					<tr>
						<td><label> Autor:</label></td>
						<td><input id="txtautor" size="70" type="text" name="txtAutor"
							value=""></td>
					</tr>
					
					
					
					
					<tr>
						<td colspan=2 id="botoes" align="center">
						<input id="botao" type=button name=btnIncluir onclick=incluir() value=Incluir>
						<input id="botao" type=reset name=btnConsultar onclick=consultar() value=Consultar>
					    <input id="botao" type=button name=btnExcluir onclick=excluir() value=Excluir>
					    </td>
					</tr>
				</table>
				<input type="hidden" name="acao" value="">
				<input type="hidden" name="menu" value="principal">
				<%	String msg = (String) request.getAttribute("msg"); 
					if(msg == null)
						msg="";
				%>
				<label id = "mensagem"><%=msg%></label>
			</form>
		</div>
	</div>
	<jsp:include page="Rodape.jsp" />
</body>
</html>