package br.emprestimo.testeUnitario;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
/**
 * Executa todos os testes
 * @author Hp
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ UC01RegistraEmprestimoDeLivro.class, UC10CadastrarUsuario.class, UC06CadastrarLivro.class })
public class AllTests {

}
