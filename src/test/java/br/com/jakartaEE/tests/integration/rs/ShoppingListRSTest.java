package br.com.jakartaEE.tests.integration.rs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.jakartaEE.dto.ShoppingListRequest;
import br.com.jakartaEE.dto.ShoppingListResponse;
import br.com.jakartaEE.tests.integration.Arquillian;
import kong.unirest.GenericType;
import kong.unirest.Unirest;

public class ShoppingListRSTest extends Arquillian {

	private ShoppingListResponse response;

	@Before
	public void init() {
		Unirest.config().defaultBaseUrl(deploymentUrl.toString());
		Unirest.config().addDefaultHeader("Content-Type", "application/json");
	}

	@Test
	public void test() {

		try {
			create();
			get();
			listAll();
			update();
			delete();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	private void create() {
		ShoppingListRequest request = new ShoppingListRequest();
		request.setName("Teste unitario rest");

		response = Unirest.post("lists")//
				.body(request)//
				.asObject(ShoppingListResponse.class)//
				.getBody();

		assertNotNull(response);
		assertNotNull(response.getId());
	}

	private void get() {
		response = Unirest.get("lists/{id}")//
				.routeParam("id", response.getId().toString())//
				.asObject(ShoppingListResponse.class)//
				.getBody();

		assertNotNull(response);
		assertNotNull(response.getId());

	}

	private void listAll() {

		List<ShoppingListResponse> response = Unirest.get("lists")//
				.asObject(new GenericType<List<ShoppingListResponse>>() {
				}).getBody();

		assertNotNull(response);
		assertTrue(!response.isEmpty());
	}

	private void update() {
		ShoppingListRequest request = new ShoppingListRequest();
		request.setName("Atualiazado teste unitario rest");

		response = Unirest.patch("lists/{id}")//
				.routeParam("id", response.getId().toString())//
				.body(request)//
				.asObject(ShoppingListResponse.class)//
				.getBody();

		assertNotNull(response);
		assertNotNull(response.getId());
		assertEquals(response.getName(), request.getName());
	}

	private void delete() {
		// TODO Auto-generated method stub

	}

}
