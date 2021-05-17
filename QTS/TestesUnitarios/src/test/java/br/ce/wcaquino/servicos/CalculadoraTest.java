package br.ce.wcaquino.servicos;

import org.junit.Test;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class CalculadoraTest {
	
	@Test
	public void SomarDoisNumeros() {
		
		int a = 15;
		int b = 10;
		Calculadora cal = new Calculadora();
		
		int res = cal.soma(a, b);

		
		Assert.assertEquals(25, res);
	}
	
	@Test
	public void SubtrairDoisNumeros() {
		
		int a = 30;
		int b = 5;
		Calculadora cal = new Calculadora();
		
		int res = cal.subtracao(a, b);

		
		Assert.assertEquals(25, res);
	}
	
	@Test
	public void multiplicarrDoisNumeros() {
	
	int a = 5;
	int b = 5;
	Calculadora cal = new Calculadora();
	
	int res = cal.multiplicacao(a, b);

	
	Assert.assertEquals(25, res);
}
	
	@Test
	public void DividirDoisNumeros() {
	
	int a = 100;
	int b = 4;
	Calculadora cal = new Calculadora();
	
	int res = cal.divisao(a, b);

	
	Assert.assertEquals(25, res);
}

}
