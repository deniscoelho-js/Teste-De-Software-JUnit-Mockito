package br.com.denis.servicos;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.com.denis.exceptions.NaoPodeDividirPorZeroException;

public class CalculadoraTest {
	
	private Calculadora calc;
	
	@Before
	public void setup() {
		calc = new Calculadora();
	}
	
	@Test
	public void deveSomarDoisValores() {
		//cenario
		int a = 5;
		int b = 3;
		//acao
		int resultado = calc.somar(a,b);
		
		//verificacao
		assertEquals(8, resultado);
	}
	
	@Test
	public void deveSubtrairDoisValores() {
		int a = 10;
		int b = 5;
		
		int resultado = calc.subtrair(a,b);
		
		
		assertEquals(5, resultado);
	}
	
	@Test
	public void deveDividirDoisValores() throws NaoPodeDividirPorZeroException {
		int a = 20;
		int b = 10;
		
		int resultado = calc.dividir(a,b);
		
		assertEquals(2, resultado);
	}
	
	@Test(expected =  NaoPodeDividirPorZeroException.class)
	public void deveLancarExcecaoAoDividirPorZero() throws NaoPodeDividirPorZeroException {
	int a = 10;
	int b = 0;
	
	calc.dividir(a, b);
		
	}
}
