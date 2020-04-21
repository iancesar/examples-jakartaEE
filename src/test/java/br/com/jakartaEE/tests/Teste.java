package br.com.jakartaEE.tests;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class Teste extends Arquillian {

	@Test
	public void testName() throws Exception {
		assertNotNull(deploymentUrl);
	}

}