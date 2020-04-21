package br.com.jakartaEE.jax.rs;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.jakartaEE.dao.ShoppingListDAO;
import br.com.jakartaEE.dto.ShoppingListRequest;
import br.com.jakartaEE.services.ShoppingListService;

@Path("/lists")
@Produces(value = MediaType.APPLICATION_JSON)
@Consumes(value = MediaType.APPLICATION_JSON)
public class ShoppingListRS {

	@Inject
	private ShoppingListDAO dao;

	@Inject
	private ShoppingListService service;

	@GET
	public Response listAll() {
		return Response.ok(service.listAll().getShoppingLists()).build();
	}

	@GET
	@Path("/{id}")
	@Valid
	public Response get(@PathParam("id") Long id) {
		return Response.ok(service.get(id)).build();
	}

	@POST
	public Response create(@Valid ShoppingListRequest shoppingListRequest) {
		return Response.ok(service.create(shoppingListRequest)).build();
	}

	@PATCH
	@Path("/{id}")
	@Valid
	public Response update(@PathParam("id") @NotNull Long id, ShoppingListRequest shoppingListRequest) {
		return Response.ok(service.update(id, shoppingListRequest)).build();
	}

	@DELETE
	@Path("/{id}")
	@Valid
	@Consumes(value = MediaType.WILDCARD)
	public Response delete(@PathParam("id") Long id) {
		dao.delete(id);
		return Response.ok().build();
	}

}
