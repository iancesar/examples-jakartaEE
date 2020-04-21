package br.com.jakartaEE.tests.integration;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import br.com.jakartaEE.dto.ShoppingListResponse;
import br.com.jakartaEE.jax.ws.ShoppingListWS;

public class ShoppinListWSTest extends Arquillian {

	@Test
	public void testName() throws Exception {

		try {

			ShoppingListWS ws = createService("ShoppingListWS", "ShoppingListImplWSService").getPort(ShoppingListWS.class);

			assertNotNull(ws);

			ShoppingListResponse response = ws.listAll();
//
//			assertNotNull(response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}