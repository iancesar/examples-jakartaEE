package br.com.jakartaEE.tests.integration.ws;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.jakartaEE.dto.ShoppingListRequest;
import br.com.jakartaEE.dto.ShoppingListResponse;
import br.com.jakartaEE.exceptions.BusinessFault;
import br.com.jakartaEE.jax.ws.ShoppingListWS;
import br.com.jakartaEE.tests.integration.Arquillian;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ShoppinListWSTest extends Arquillian {

	private ShoppingListWS ws;

	private ShoppingListResponse response;

	@Before
	public void init() {
		ws = createService("ShoppingListWS", "ShoppingListImplWSService").getPort(ShoppingListWS.class);
		assertNotNull(ws);
	}

	@Test
	public void test() throws Exception {
		create();
		get();
		listAll();
		update();
		delete();
	}

	private void create() throws Exception {
		ShoppingListRequest request = new ShoppingListRequest();
		request.setName("Teste Unitário");

		response = ws.create(request);

		assertNotNull(response);
		assertNotNull(response.getId());
	}

	private void get() throws BusinessFault {
		response = ws.get(response.getId());
		assertNotNull(response);
		assertNotNull(response.getId());
	}

	private void listAll() {
		ShoppingListResponse response = ws.listAll();

		assertNotNull(response);
		assertTrue(!response.getShoppingLists().isEmpty());
	}

	private void update() throws BusinessFault {
		ShoppingListRequest request = new ShoppingListRequest();
		request.setName("Atualizando via soap");

		response = ws.update(response.getId(), request);

		assertNotNull(response);
		assertNotNull(response.getId());

		get();

		assertEquals(response.getName(), request.getName());

	}

	private void delete() {

		try {
			ws.delete(response.getId());
			ws.get(response.getId());
		} catch (Exception e) {
			assertTrue(ExceptionUtils.hasCause(e, BusinessFault.class));
		}

	}

}