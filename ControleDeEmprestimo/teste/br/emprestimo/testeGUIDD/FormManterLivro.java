package br.emprestimo.testeGUIDD;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FormManterLivro {
	private WebDriver driver;
	private String baseUrl;
	Logger logger = Logger.getLogger("FormManterLivro");	
	public FormManterLivro (WebDriver driver){
		this.driver = driver;
		baseUrl = "http://localhost:8080/";
	}
	
	public void abre(){
		driver.get(baseUrl + "ControleDeEmprestimo/FormLivro.jsp");
	}
	public void cadastra(String i, String t, String a, String re){
		try{
		logger.info("chamada do metodo cadastrar = " + i);
		driver.findElement(By.name("txtISBN")).clear();
		driver.findElement(By.id("txtisbn")).sendKeys(i);
		driver.findElement(By.id("txttitulo")).clear();
		driver.findElement(By.id("txttitulo")).sendKeys(t);
		driver.findElement(By.id("txtautor")).clear();
		driver.findElement(By.id("txtautor")).sendKeys(a);
		
		driver.findElement(By.name("btnIncluir")).click();
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		
	}
	public void excluir(String isbn){
		WebDriverWait wait = new WebDriverWait(driver, 20);
		driver.get(baseUrl + "ControleDeEmprestimo/FormLivro.jsp");
		driver.findElement(By.id("txtisbn")).clear();
		driver.findElement(By.id("txtisbn")).sendKeys(isbn);
		driver.findElement(By.name("btnExcluir")).click();
	}
	public void consultar(String isbn){
		WebDriverWait wait = new WebDriverWait(driver, 20);
		
		driver.findElement(By.id("txtisbn")).clear();
		driver.findElement(By.id("txtisbn")).sendKeys(isbn);
		driver.findElement(By.name("btnConsultar")).click();
	}
}
