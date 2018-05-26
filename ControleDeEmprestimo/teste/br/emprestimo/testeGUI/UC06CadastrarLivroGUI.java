package br.emprestimo.testeGUI;


import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.emprestimo.servico.DAOFactory;
import br.emprestimo.modelo.ILivroDAO;


public class UC06CadastrarLivroGUI {
	private WebDriver driver;
	private String baseUrl;

	private StringBuffer verificationErrors = new StringBuffer();
	

	@Before
	public void setUp() throws Exception {

		System.setProperty("webdriver.gecko.driver",
				"WebContent/WEB-INF/lib/geckodriver.exe");
		driver = new FirefoxDriver();

//		System.setProperty("webdriver.chrome.driver", "C:/Users/esadv6/git/20171s_fatec3/sceweb/WebContent/WEB-INF/lib/chromedriver.exe");
//		driver = new ChromeDriver();
		baseUrl = "http://localhost:8080/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void CT01CadastrarLivroComSucessoPelaGUI() throws Exception {
		//cenario
		driver.get(baseUrl + "ControleDeEmprestimo/Home.html");
		driver.findElement(By.linkText("Manter Cadastro de Livros")).click();
		driver.findElement(By.id("txttitulo")).clear(); //ou By.name
		driver.findElement(By.id("txttitulo")).sendKeys("Introdução ao teste de software");
		driver.findElement(By.id("txtisbn")).clear();
		driver.findElement(By.id("txtisbn")).sendKeys("1111");
		driver.findElement(By.id("txtautor")).clear();
		driver.findElement(By.id("txtautor")).sendKeys("Delamaro");
		//acao
		driver.findElement(By.name("btnIncluir")).click();//id do botao esta associado ao estilo css
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mensagem")));
			assertEquals("Mensagem = Cadastro realizado com sucesso", driver.findElement(By.id("mensagem")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	@After
	public void tearDown() throws Exception {
		DAOFactory fabricaDAO = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		ILivroDAO livroDAO = fabricaDAO.getLivroDAO();
		livroDAO.exclui("1111");
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	
}