package br.com.exemplo.exemplo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.exemplo.models.Exemplo;
import br.com.exemplo.resources.ExemploResource;

@SpringBootTest
public class ExemploResourceTest {
	
	Exemplo e;
	ExemploResource eR;
	@Before
	public void setUp() throws Exception{
		e = new Exemplo();
		eR = new ExemploResource();
		e.setCodigo(1);
		e.setData("01012001");
		e.setHorario("01:01");
		e.setLocal("SP");
		e.setNome("Silvia");
	}

	@Test
	public void listaExemplo(){
		eR.listaExemplo();
		assertEquals(e, eR.listaExemplo());
	}
	
	@Test
	public void exemplo(){
		eR.exemplo(e.getCodigo());
		assertEquals(e, eR.exemplo(e.getCodigo()));
	}

}
