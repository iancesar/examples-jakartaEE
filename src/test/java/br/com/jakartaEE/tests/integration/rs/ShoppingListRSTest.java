package br.com.jakartaEE.tests.integration.rs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.jakartaEE.dto.ShoppingListRequest;
import br.com.jakartaEE.dto.ShoppingListResponse;
import br.com.jakartaEE.tests.integration.Arquillian;

public class ShoppingListRSTest extends Arquillian
{

	private ShoppingListResponse	shoppingListResponse;

	private WebTarget					target;

	@Before
	public void init()
	{
		target = createWebTarger().path("lists");
	}

	@Test
	public void test()
	{

		try
		{
			create();
			get();
			listAll();
			update();
			delete();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			fail();
		}
	}

	private void create() throws JsonProcessingException
	{
		ShoppingListRequest request = new ShoppingListRequest();
		request.setName("Teste unitario rest");

		shoppingListResponse = target.request().post(Entity.entity(request, MediaType.APPLICATION_JSON), ShoppingListResponse.class);

		assertNotNull(shoppingListResponse.getId());
	}

	private void get()
	{

		WebTarget getTarget = target.path("{id}");

		shoppingListResponse = getTarget.resolveTemplate("id", shoppingListResponse.getId()).request().get(ShoppingListResponse.class);

		assertNotNull(shoppingListResponse);
		assertNotNull(shoppingListResponse.getId());

	}

	private void listAll()
	{

		List<ShoppingListResponse> response = target.request().get(new GenericType<List<ShoppingListResponse>>()
		{
		});

		assertNotNull(response);
		assertTrue(!response.isEmpty());
	}

	private void update()
	{
		ShoppingListRequest request = new ShoppingListRequest();
		request.setName("Atualiazado teste unitario rest");

		WebTarget patchTarget = target.path("{id}");

		shoppingListResponse = patchTarget//
			.resolveTemplate("id", shoppingListResponse.getId())//
			.request()//
			.method("PATCH", Entity.entity(request, MediaType.APPLICATION_JSON), ShoppingListResponse.class);

		get();

		assertNotNull(shoppingListResponse);
		assertNotNull(shoppingListResponse.getId());
		assertEquals(shoppingListResponse.getName(), request.getName());
	}

	private void delete()
	{

		WebTarget deleteTarget = target.path("{id}");

		Response response = deleteTarget.resolveTemplate("id", shoppingListResponse.getId()).request().delete();

		assertEquals(response.getStatus(), 200);

		try
		{
			get();
		}
		catch(Exception e)
		{
			assertTrue(ExceptionUtils.getMessage(e).contains("HTTP 400 Bad Request"));
		}

	}

}
