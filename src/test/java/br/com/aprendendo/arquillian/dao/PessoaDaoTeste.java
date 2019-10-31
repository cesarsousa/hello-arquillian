package br.com.aprendendo.arquillian.dao;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.runner.RunWith;

import br.com.aprendendo.arquillian.modelo.Pessoa;

@RunWith(Arquillian.class)
public class PessoaDaoTeste {

	@Deployment
	public static Archive<?> criarArquivoTeste() {

		Archive<?> arquivoTeste = ShrinkWrap.create(WebArchive.class, "aplicacaoTeste.war")
				// Adicionando o pacote inteiro da classe PessoaDao, ou seja incluí todas as outras classes deste pacote
				.addPackage(PessoaDao.class.getPackage())
				// Adicionando apenas a classe Pessoa, e não o pacote inteiro como na linha anterior
				.addClass(Pessoa.class)
				// Adicionando o arquivo persistence.xml para conexão JPA
				.addAsResource("META-INF/persistence.xml")
				// Adicionando o beans.xml para ativação do CDI
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

		return arquivoTeste;
	}

}
