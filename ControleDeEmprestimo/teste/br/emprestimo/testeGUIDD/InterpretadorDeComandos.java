package br.emprestimo.testeGUIDD;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.*;
public class InterpretadorDeComandos {
	private static WebDriver driver;
	private static String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();
	private static FormManterLivro formManterLivro;
	Logger logger = Logger.getLogger("InterpretadorDeComandos");
	
	public InterpretadorDeComandos(){
		inicializa();
	}
	public void inicializa(){
		try {
			ExcelUtils.setExcelFile(Constant.Path_TestData2 + Constant.File_TestData, "Planilha1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.info("erro no inicializa = " + e.getMessage());
		}
		System.setProperty("webdriver.gecko.driver", "WebContent/WEB-INF/lib/geckodriver.exe");
		//System.setProperty("webdriver.chrome.driver", Constant.Chrome);
		//driver = new ChromeDriver();
		driver = new FirefoxDriver();
		baseUrl = "http://localhost:8080/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	public void fluxo() throws Exception {
		String acao;
		
		for (int i = 1; i < 8; i++) {
			// linha x coluna
			logger.info("percorrendo a linha = " + i); 
			logger.info("coluna acao = " + ExcelUtils.getCellData(i, 2)); 
			
			Thread.sleep(5000);
			//obtem da coluna C da planilha "roteiro_de_teste.xls" a ação a ser executada.
			acao = ExcelUtils.getCellData(i, 2);
			
			if (acao.equals("abreFormManterLivro")){
				formManterLivro = new FormManterLivro(driver);
				formManterLivro.abre();
				Thread.sleep(5000);
			}
			
			
			if (acao.equals("cadastrarLivro")){
				formManterLivro = new FormManterLivro(driver);
				logger.info("interpretador celula = " + ExcelUtils.getCellData(i, 4));
				formManterLivro.cadastra(ExcelUtils.getCellData(i, 4), ExcelUtils.getCellData(i, 5),
						ExcelUtils.getCellData(i, 6), ExcelUtils.getCellData(i, 7));
				
				try {
					WebDriverWait wait = new WebDriverWait(driver, 10);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mensagem")));
					assertEquals(ExcelUtils.getCellData(1, 7), driver.findElement(By.id("mensagem")).getText());
					driver.quit();
				} catch (Error e) {
					verificationErrors.append(e.toString());
				}
			}
			
			
			if (acao.equals("excluirLivro")){
				formManterLivro = new FormManterLivro(driver);
				formManterLivro.excluir(ExcelUtils.getCellData(i, 4));
				
				
				try {
					WebDriverWait wait = new WebDriverWait(driver, 20);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mensagem")));
					//assertEquals(ExcelUtils.getCellData(1, 7), driver.findElement(By.id("mensagem")).getText());
					//driver.quit();
				} catch (Error e) {
					verificationErrors.append(e.toString());
				}
			}
			if (acao.equals("consultarLivro")){
				formManterLivro = new FormManterLivro(driver);
				formManterLivro.consultar(ExcelUtils.getCellData(i, 4));
			}
		}
		driver.quit();
	}
	
}
