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

import org.modelmapper.ModelMapper;

import br.com.jakartaEE.dao.ListsDAO;
import br.com.jakartaEE.dto.ListsRequest;
import br.com.jakartaEE.entities.Lists;
import br.com.jakartaEE.jax.contracts.ListsContract;

@Path("/lists")
@Produces(value = MediaType.APPLICATION_JSON)
@Consumes(value = MediaType.APPLICATION_JSON)
public class ListsRS implements ListsContract {

	@Inject
	private ListsDAO dao;

	@Inject
	private ModelMapper modelMapper;

	@Override
	@GET
	public Response listAll() {
		return Response.ok(dao.findAll()).build();
	}

	@Override
	@GET
	@Path("/{id}")
	@Valid
	public Response get(@PathParam("id") Long id) {
		return Response.ok(dao.findById(id)).build();
	}

	@Override
	@POST
	public Response create(@Valid ListsRequest listRequest) {

		Lists list = modelMapper.map(listRequest, Lists.class);

		return Response.ok(dao.save(list)).build();
	}

	@Override
	@PATCH
	@Path("/{id}")
	@Valid
	public Response update(@PathParam("id") @NotNull Long id, ListsRequest listRequest) {

		Lists list = modelMapper.map(listRequest, Lists.class);

		list.setId(id);
		return Response.ok(dao.save(list)).build();
	}

	@Override
	@DELETE
	@Path("/{id}")
	@Valid
	@Consumes(value = MediaType.WILDCARD)
	public Response delete(@PathParam("id") Long id) {
		dao.delete(id);
		return Response.ok().build();
	}

}
