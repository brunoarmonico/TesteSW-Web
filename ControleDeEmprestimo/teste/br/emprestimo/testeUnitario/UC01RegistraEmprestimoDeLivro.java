package br.emprestimo.testeUnitario;
import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.emprestimo.modelo.Emprestimo;
import br.emprestimo.modelo.EmprestimoDAO;
import br.emprestimo.modelo.Livro;
import br.emprestimo.modelo.LivroDAO;
import br.emprestimo.modelo.Usuario;
import br.emprestimo.modelo.UsuarioDAO;
import br.emprestimo.servico.ConfiguraDB;
import br.emprestimo.servico.FabricaDeConexoes;
import br.emprestimo.servico.ServicoEmprestimo;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class UC01RegistraEmprestimoDeLivro {
	static private Livro livro;
	static private Usuario usuario;
	static private ServicoEmprestimo servico;
	static private Emprestimo emprestimo;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//cenario
		livro = ObtemLivro.comDadosValidos();
		
		usuario = new Usuario();
		usuario.setRa("11111");
		usuario.setNome("Jose da Silva");
		servico = new ServicoEmprestimo();
		emprestimo = new Emprestimo();
	}
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	@Test
	public void CT01UC01FB_registrar_emprestimo_com_sucesso() {
		assertNotNull(servico.empresta(livro, usuario));
	}
	@Test(expected=RuntimeException.class)
	public void CT02UC01FB_registrar_emprestimo_com_dados_invalidos() {
		servico.empresta(null, usuario);
	}
	@Test
	public void CT03UC01FB_registrar_emprestimo_com_dados_invalidos(){
		try{
			servico.empresta(null, usuario);
			fail ("deveria lan�ar uma exce��o");
		}catch(RuntimeException e){
			assertEquals("Dados inv�lidos.", e.getMessage());
		}
	}
	@Test
	public void CT04UC01FB_registrar_emprestimo_com_sucesso_validacao_da_data() {
		//cenario
		DateTimeFormatter fmt = DateTimeFormat.forPattern("YYYY/MM/dd");
		String dataEsperada = new DateTime().plusDays(8).toString(fmt);
		//acao
		emprestimo = servico.empresta(livro, usuario);
		String dataObtida = emprestimo.getDataDevolucao();
		//verificacao
		assertTrue(dataEsperada.equals(dataObtida));
	}
	@Test
	public void CT05UC01FB_registrar_emprestimo_com_data_invalida() {
		assertTrue(emprestimo.validaData("2000/03/29"));
	}
	
	@Test
	public void CT06UC01FB_registrar_emprestimo_com_data_invalida() {
		Emprestimo emprestimo2 = new Emprestimo();
		try{
		emprestimo2.setDataEmprestimo("30/02/2000");
		fail ("deveria lan�ar uma exce��o");
		} catch (Exception e){
			assertEquals("Data invalida", e.getMessage());
		}
	}
	@Test(expected = RuntimeException.class)
	public void CT07UC01RegistrarEmprestimo_com_data_invalida() {
		emprestimo.setDataEmprestimo("30/02/2000");
	}
	@Test
	public void CT08UC01RegistrarEmprestimo_obtem_data_corrente(){
		//cenario
		DateTimeFormatter fmt = DateTimeFormat.forPattern("YYYY/MM/dd");
		String dataAtual = new DateTime().toString(fmt);
		//acao
		String dataEmprestimo = emprestimo.setDataEmprestimo();
		assertTrue(dataAtual.equals(dataEmprestimo));
	}
	@Test
	public void CT05_conecta_db_com_sucesso() {
		// cenario
		String url = "jdbc:mysql://localhost:3306/biblioteca";
		String driver = "com.mysql.jdbc.Driver";
		String usuario = "root";
		String senha = "alunosfatec";
		// acao
		ConfiguraDB configuraDB = new ConfiguraDB(url, driver, usuario, senha);
		FabricaDeConexoes fabricaDeConexoes = new FabricaDeConexoes(configuraDB);
		// verificacao
		assertNotNull(fabricaDeConexoes.getConnection());
	}

	@Test
	public void CT06_registra_emprestimo_com_sucesso() {
		// cenario
		Livro umLivro = ObtemLivro.comDadosValidos();
		LivroDAO livroDAO = new LivroDAO();
		livroDAO.adiciona(umLivro);
		
		Usuario umUsuario = ObtemUsuario.comDadosValidos();
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.adiciona(umUsuario);
       	
		ServicoEmprestimo servico = new ServicoEmprestimo();
		Emprestimo umEmprestimo = servico.empresta(umLivro, umUsuario);
		EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
		// acao
		int resultadoObtido = emprestimoDAO.adiciona(umEmprestimo);
		emprestimoDAO.exclui(0);
		usuarioDAO.exclui(umUsuario.getRa());
		livroDAO.exclui(umLivro.getIsbn());
		//verificacao
		assertEquals (1, resultadoObtido);
		
	}
	
}
