package br.com.jakartaEE.tests.units;

import static org.junit.Assert.assertNotNull;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.junit.Test;

public class WebClientTest {

	@Test
	public void testName() throws Exception {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("https://www.thecocktaildb.com/api/json/v1/1/search.php?s=margarita");
		Response response = target.request().get();
		assertNotNull(response.getEntity());
	}

}
